package com.example.application;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    // Mapping 연습

    @GetMapping("/events")
    @ResponseBody
    public String getEvents() {
        return "event";
    }

    @GetMapping("/events/{id}")
    @ResponseBody
    public String getAnEvent(@PathVariable int id) {
        return "event";
    }

    @PostMapping(
            value = "/events",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createEvent() {
        return "event";
    }

    @DeleteMapping("/events/{id}")
    @ResponseBody
    public String deleteEvent(@PathVariable int id) {
        return "event";
    }

    @PutMapping("/events/{id}")
    @ResponseBody
    public String putEvent(@PathVariable int id) {
        return "event";
    }
}
