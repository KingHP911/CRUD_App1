package ru.kinghp.crudapp.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import javax.validation.constraints.NotEmpty;

@Entity //hibernate
@Table(name = "person")
public class Person {

    @Id //hibernate
    @GeneratedValue(strategy = GenerationType.IDENTITY) //hibernate
    private int id;

    @NotEmpty(message = "Name is empty") //из Hibernate Validator
    @Size(min = 2, max = 30, message = "Bad name size")
    @Column(name = "name") //hibernate можно не указывать
    private String name;

//    @Min(value = 0, message = "Bad age")
//    private int age;
//
//    @NotEmpty(message = "Bad mail")
//    @Email(message = "It not email")
//    private String email;

    public Person() {

    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
//        this.age = age;
//        this.email = email;
    }

    public int getId() {
        return id;
    } //для hibernate можно не создавать

    public void setId(int id) {
        this.id = id;
    } //для hibernate можно не создавать

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
}
