package com.example.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
// @WebMvcTest는 웹과 관련된 빈을 등록해주기 때문에 PersonFormatter 빈을 등록해주지 않음
// - 명시적으로 빈을 등록해서 해결
// - 통합 테스트로 변경하여 모든 빈들을 등록
@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest
public class SimpleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello() throws Exception {
        this.mockMvc.perform(get("/hello").param("name","kevin"))
                .andDo(print())
                .andExpect(content().string("hello kevin"));
    }
}