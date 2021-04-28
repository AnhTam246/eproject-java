/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.helpers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author tonamson
 */
public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(HttpStatus status, boolean error, String message, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("status", status.value());
            map.put("isSuccess", error);
            map.put("message", message);
            map.put("data", responseObj);

            return new ResponseEntity<Object>(map, status);
        } catch (Exception e) {
            map.clear();
            map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("isSuccess", false);
            map.put("message", e.getMessage());
            map.put("data", null);
            return new ResponseEntity<Object>(map, status);
        }
    }
}
