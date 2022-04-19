package com.example.application;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class Event {

    private int id;
    private String name;

    @Min(0)
    private int limit;
}
