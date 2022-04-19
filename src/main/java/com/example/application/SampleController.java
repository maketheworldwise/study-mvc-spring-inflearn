package com.example.application;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// @RequestMapping(method = RequestMethod.GET)
public class SampleController {

    // @GetMapping("/hello")
    @RequestMapping(value = "/hello",
            method = {RequestMethod.GET, RequestMethod.PUT},
            headers = HttpHeaders.FROM,
            params = "name",
            consumes = MediaType.APPLICATION_JSON_VALUE, // 요청
            produces = MediaType.APPLICATION_JSON_VALUE) // 응답
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @RequestMapping("/sample")
    @ResponseBody
    public String sample() {
        return "sample";
    }

    @GetWorldMapping
    @ResponseBody
    public String world() {
        return "world";
    }
}
