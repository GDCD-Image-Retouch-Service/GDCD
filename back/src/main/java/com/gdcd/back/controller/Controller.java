package com.gdcd.back.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Map;

public class Controller {
    private String SUCCESS = "SUCCESS";
    private String FAIL = "FAIL";

    public ResponseEntity<Map<String, Object>> getResponseEntity(Object obj) {
        Map<String, Object> result = new HashMap<>();
        HttpStatus status;
        try {
            result.put("item", obj);
            result.put("msg", SUCCESS);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            result.put("item", e.getMessage());
            result.put("msg", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            e.printStackTrace();
        }
        return new ResponseEntity<>(result, status);
    }
}
