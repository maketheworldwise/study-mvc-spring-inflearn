package com.example.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    // preHandle 1
    // preHandle 2
    // 요청 처리
    // postHandle 2
    // postHandle 1
    // 뷰 렌더링 (Rest에서는 생략)
    // afterCompletion 2
    // afterCompletion 1

    // 비동기 요청에서는 postHandler, afterCompletion가 호출되지 않고, AsyncHandlerInterceptor의 다른 메서드가 호출됨

    @GetMapping("/hello")
    public String hello(@RequestParam("id") Person person) {
        return "hello " + person.getName();
    }

    @GetMapping("/message")
    public String messageString(@RequestBody String body) {
        return "message " + body;
    }

    @GetMapping("/jsonmessage")
    public Person jsonmessage(@RequestBody Person person) {
        return person;
    }
}
