package com.vector.studentlessonservlet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {

    private int id;
    private String name;
    private int duration;
    private double price;
    private String lecturerName;
    private User user;
}
