package com.freesoft.protobuf;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    private final CourseRepository courseRepository;

    private CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @RequestMapping("/courses/{id}")
    Training.Course getCourseById(@PathVariable Integer id) {
        return courseRepository.getCourse(id);
    }
}
