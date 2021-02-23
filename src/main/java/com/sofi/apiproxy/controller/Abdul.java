package com.sofi.apiproxy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Abdul {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        List<Student> studentList = new ArrayList<>();

        studentList = Arrays.asList(mapper.readValue(new File("data.json"), Student[].class));
        for(Student list: studentList){
            System.out.println(list.getFirstName());
        }
    }
}
