package me.markminisce.app.model;

import lombok.Builder;

@Builder
public class Student {

    private final int id;
    private final String firstName;
    private final String lastName; 
    private final String emailAddress;
}
