package com.example.application;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class PersonFormatter implements Formatter<Person> {

    @Override
    public Person parse(String text, Locale locale) throws ParseException {
        // 문자열을 받아 객체로 만들어줌
        Person person = new Person();
        person.setName(text);
        return person;
    }

    @Override
    public String print(Person object, Locale locale) {
        return object.getName();
    }
}
