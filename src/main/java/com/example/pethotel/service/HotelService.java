package com.example.pethotel.service;

import com.example.pethotel.dto.Criteria;
import com.example.pethotel.dto.hotel.SearchHotelRequest;
import com.example.pethotel.dto.hotel.SearchHotelResponse;
import com.example.pethotel.dto.manager.AddHotelRequest;
import com.example.pethotel.dto.manager.UpdateHotelRequest;
import com.example.pethotel.entity.Hotel;
import com.example.pethotel.mapper.HotelMapper;
import com.example.pethotel.repository.HotelImgRepository;
import com.example.pethotel.repository.HotelRepository;
import com.example.pethotel.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final HotelImgRepository hotelImgRepository;
    private final RoomRepository roomRepository;

    private final RoomService roomService;
    private final HotelFacilityService hotelFacilityService;

    private final HotelMapper hotelMapper;

    // 호텔 저장 요청
    public Hotel save(AddHotelRequest req) {
        return hotelRepository.save(req.toEntity());
    }

    // 호텔 수정 요청
    @Transactional
    public Hotel update(Long hotelId, UpdateHotelRequest req) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() ->  new IllegalArgumentException("not found : "+hotelId));
        hotel.update(req.getHotelName(), req.getHotelType(), req.getLocation(),
                req.getPostcode(), req.getAddress(),
                req.getDetailAddress(), req.getExtraAddress(),
                req.getHotelPhone(), req.getHotelInfo());
        return hotel;
    }

    // 매니저가 호텔 삭제 요청
    @Transactional
    public void delete(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() ->  new IllegalArgumentException("not found : "+hotelId));

        // 호텔 시설 삭제
        hotelFacilityService.deleteByHotel(hotel);

        // 객실관련 삭제
        roomService.deleteByHotel(hotel);

        hotelImgRepository.deleteAllByHotel(hotel);
        hotelRepository.delete(hotel);
    }


    public List<Hotel> findAllByUserId(Long userId) {
        return hotelRepository.findAllByUserId(userId);
    }

    public Hotel findById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() ->  new IllegalArgumentException("호텔을 찾을수 없습니다."));
    }

    public Page<SearchHotelResponse> findBySearchOption(SearchHotelRequest request, int page, int size){
        Pageable pageable = PageRequest.of(page - 1, size);  // 페이지는 0부터 시작하므로, 1을 빼서 전달
        return hotelRepository.findBySearchOption(pageable, request.getLocation(), request.getHotelType(),
                request.getGuest(), request.getPet(),
                request.getCheckIn(), request.getCheckOut(), request.getRoom());
    }

    // 페이징 처리된 호텔 목록 조회
    public List<SearchHotelResponse> findBySearchFilter(SearchHotelRequest request, Criteria criteria) {
        int skip = (criteria.getPage() - 1) * criteria.getSize();
        List<String> filterList = Arrays.asList(request.getFilter().split(","));
        return hotelMapper.findBySearchFilter(filterList, request.getFilterSize(),
                request.getLocation(), request.getHotelType(),
                request.getGuest(), request.getPet(),
                request.getCheckIn(), request.getCheckOut(),
                request.getRoom(), criteria.getSize(), skip);
    }


}
