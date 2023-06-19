package com.example.excelgpt.controller;

import com.example.excelgpt.model.request.ChatBotInputRequest;
import com.example.excelgpt.model.response.ExcelGPTResponse;
import com.example.excelgpt.service.ExcelGPTService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcelGPTController {
    private ExcelGPTService excelGPTService;

    public ExcelGPTController(ExcelGPTService excelGPTService){
        this.excelGPTService = excelGPTService;
    }

    @PostMapping("/chat")
    public ResponseEntity<ExcelGPTResponse> processInputRequest(@RequestBody ChatBotInputRequest chatBotInputRequest){
        ExcelGPTResponse excelGPTResponse = excelGPTService.excelGPTResponse(chatBotInputRequest.getMessage());
        return new ResponseEntity<>(excelGPTResponse, HttpStatus.OK);
    }
}
