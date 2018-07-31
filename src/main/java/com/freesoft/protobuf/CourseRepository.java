package com.freesoft.protobuf;

import java.util.Map;

public class CourseRepository {

    Map<Integer, Training.Course> courses;

    public CourseRepository(Map<Integer, Training.Course> courses) {
        this.courses = courses;
    }

    public Training.Course getCourse(int id) {
        return courses.get(id);
    }
}
