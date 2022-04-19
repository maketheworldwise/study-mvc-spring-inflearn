package com.example.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void helloTest() throws Exception {

//        this.mockMvc.perform(put("/hello"))
//                .andDo(print())
//                .andExpect(status().isOk());

//        this.mockMvc.perform(delete("/hello"))
//                .andDo(print())
//                .andExpect(status().isMethodNotAllowed());

        this.mockMvc.perform(get("/hello")
                        .header(HttpHeaders.FROM, "localhost")
                        .param("name", "kevin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"))
                .andExpect(handler().handlerType(SampleController.class))
                .andExpect(handler().methodName("hello"));
    }

    @Test
    public void sampleTest() throws Exception {
        // HEAD는 응답 본문을 받아오지 않고 응답 헤더만 받아옴
//        this.mockMvc.perform(head("/sample"))
//                .andDo(print())
//                .andExpect(status().isOk());

        this.mockMvc.perform(options("/sample"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().stringValues(
                        HttpHeaders.ALLOW,
                        hasItems(
                                containsString("GET"),
                                containsString("POST"),
                                containsString("HEAD"),
                                containsString("OPTIONS"))));
    }
}