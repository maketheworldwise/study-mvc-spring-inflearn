package com.example.application;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
@SessionAttributes("event")
public class SampleController {

    @GetMapping("/events/{id}")
    @ResponseBody
    public Event getEvent(@PathVariable int id, @MatrixVariable String name) {
        Event event = new Event();
        event.setId(id);
        event.setName(name);
        return event;
    }

    @GetMapping("/events")
    @ResponseBody
    public Event getEvents(@Validated(Event.ValidateName.class) @ModelAttribute Event event, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(e -> {
                System.out.println(e.toString());
            });
        }
        return event;
    }

    @GetMapping("/events/sample")
    @ResponseBody
    public Event getEventSample(Model model) { //HttpSession session, SessionStatus sessionStatus) {
        Event event = new Event();
        event.setName("kevin");
        event.setLimit(50);
        model.addAttribute(event);
        // 세션 추가
        // session.setAttribute("event", event);
        // 세션 비우기
        //sessionStatus.setComplete();
        return event;
    }

    @GetMapping("/events/sessionAttribute")
    @ResponseBody
    public void getSessionAttribute(@SessionAttribute LocalDateTime visitTime) {
        System.out.println(visitTime);
    }
}
