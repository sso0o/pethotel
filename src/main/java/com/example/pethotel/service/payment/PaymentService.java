package com.example.pethotel.service.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {

    public Map<String, Object> nPayProgress(String paymentId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", "HN3GGCMDdTgGUfl0kFCo");
        headers.set("X-Naver-Client-Secret", "ftZjkkRNMR");
        headers.set("X-NaverPay-Chain-Id", "Wms3cFptanlpSjR");

        MultiValueMap<String, Object> payParams = new LinkedMultiValueMap<String, Object>();
        payParams.add("paymentId", paymentId);

        HttpEntity<?> request = new HttpEntity<>(payParams, headers);
        RestTemplate template = new RestTemplate();
        String url = "https://dev.apis.naver.com/naverpay-partner/naverpay/payments/v2.2/apply/payment";

        Map<String, Object> result = template.postForObject(url, request, Map.class);
        return result;

    }

}
