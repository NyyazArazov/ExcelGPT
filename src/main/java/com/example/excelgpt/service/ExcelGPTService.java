package com.example.excelgpt.service;

import com.example.excelgpt.model.common.Message;
import com.example.excelgpt.model.request.ExcelGPTRequest;
import com.example.excelgpt.model.response.ExcelGPTResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExcelGPTService {

    @Value("${openai.api.key}")
    private String apiKey;

    private static final String OPEN_AI_CHAT_ENDPOINT = "https://api.openai.com/v1/chat/completions";
    private RestTemplate restTemplate;

    public ExcelGPTService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public ExcelGPTResponse excelGPTResponse(String prompt){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        ExcelGPTRequest excelGPTRequest = new ExcelGPTRequest();
        excelGPTRequest.setModel("gpt-3.5-turbo");
        excelGPTRequest.setMessages(List.of(new Message("user", prompt)));
        excelGPTRequest.setMax_tokens(50);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ExcelGPTRequest> request = new HttpEntity<>(excelGPTRequest, headers);

        return restTemplate.postForObject(OPEN_AI_CHAT_ENDPOINT, request, ExcelGPTResponse.class);
    }
}
