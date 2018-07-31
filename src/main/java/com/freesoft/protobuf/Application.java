package com.freesoft.protobuf;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

import java.util.*;

@SpringBootApplication
public class Application {

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

    @Bean
    public CourseRepository createTestCourses() {
        Map<Integer, Training.Course> courses = new HashMap<>();
        Training.Course firstCourse = Training.Course.newBuilder().setId(1).setCourseName("Effective Java")
                .addAllStudent(createTestStudents()).build();

        Training.Course secondCourse = Training.Course.newBuilder().setId(2).setCourseName("Clean code")
                .addAllStudent(new Iterable<Training.Student>() {
                    @Override
                    public Iterator<Training.Student> iterator() {
                        return null;
                    }
                }).build();

        courses.put(firstCourse.getId(), firstCourse);
        courses.put(secondCourse.getId(), secondCourse);

        return new CourseRepository(courses);

    }

    private List<Training.Student> createTestStudents() {
        Training.Student.PhoneNumber firstPhoneNo = createPhoneNumber("0744441234", Training.Student.PhoneType.MOBILE);
        Training.Student.PhoneNumber secondPhoneNo = createPhoneNumber("0214422123", Training.Student.PhoneType.LANDLINE);
        Training.Student.PhoneNumber thirdPhoneNo = createPhoneNumber("0744441122", Training.Student.PhoneType.MOBILE);
        Training.Student.PhoneNumber fourthPhoneNo = createPhoneNumber("0744442323", Training.Student.PhoneType.MOBILE);

        Training.Student firstStudent = createStudent(1, "John", "Doe", "jdoe@freesoft.com", Arrays.asList(firstPhoneNo));
        Training.Student secondStudent = createStudent(2, "Jane", "Doe", "janedoe@freesoft.com", Arrays.asList(secondPhoneNo));
        Training.Student thirdStudent = createStudent(3, "Freddy", "Kruegger", "fkruger@freesoft.com", Arrays.asList(thirdPhoneNo, fourthPhoneNo));

        return Arrays.asList(firstStudent, secondStudent, thirdStudent);
    }

    private Training.Student createStudent(int id, String firstName, String lastName, String email, List<Training.Student.PhoneNumber> phones) {
        return Training.Student.newBuilder()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .addAllPhone(phones)
                .build();
    }

    private Training.Student.PhoneNumber createPhoneNumber(String number, Training.Student.PhoneType type) {
        return Training.Student.PhoneNumber.newBuilder()
                .setNumber(number)
                .setType(type)
                .build();
    }

}
