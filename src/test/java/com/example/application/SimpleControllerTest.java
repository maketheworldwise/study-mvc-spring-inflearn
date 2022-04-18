package com.example.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.oxm.Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import java.io.StringWriter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    Marshaller marshaller; // WebConfig에 등록한 빈을 주입 받음

    @Test
    public void hello() throws Exception {
        Person person = new Person();
        person.setName("kevin");
        Person savedPerson = personRepository.save(person);

        this.mockMvc.perform(get("/hello").param("id",savedPerson.getId().toString()))
                .andDo(print())
                .andExpect(content().string("hello kevin"));
    }

    @Test
    public void helloStatic() throws Exception {
        // 기본 ResourceHandler
//        this.mockMvc.perform(get("/index.html"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(Matchers.containsString("index")));

        // 직접 만든 ResourceHandler
        this.mockMvc.perform(get("/mobile/index.html"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("mobile")))
                .andExpect(header().exists(HttpHeaders.CACHE_CONTROL));

    }

    @Test
    public void messageString() throws Exception {
        this.mockMvc.perform(get("/message").content("body"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("message body"));
    }

    @Test
    public void jsonmessage() throws Exception {
        Person person = new Person();
        person.setId(10L);
        person.setName("kevin");

        String jsonString = objectMapper.writeValueAsString(person);

        this.mockMvc.perform(get("/jsonmessage")
                        .contentType(MediaType.APPLICATION_JSON) // JSON 요청을 보냄
                        .accept(MediaType.APPLICATION_JSON) // JSON 응답을 기대
                        .content(jsonString))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.name").value("kevin"));
    }

    @Test
    public void xmlmessage() throws Exception {
        Person person = new Person();
        person.setId(10L);
        person.setName("kevin");

        StringWriter stringWriter = new StringWriter();
        Result result = new StreamResult(stringWriter);
        marshaller.marshal(person, result);

        String xmlString = stringWriter.toString();

        this.mockMvc.perform(get("/jsonmessage")
                        .contentType(MediaType.APPLICATION_XML) // XML 요청을 보냄
                        .accept(MediaType.APPLICATION_XML) // XML 응답을 기대
                        .content(xmlString))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("person/id").string("10"))
                .andExpect(xpath("person/name").string("kevin"));
    }
}