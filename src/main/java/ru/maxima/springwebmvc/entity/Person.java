package ru.maxima.springwebmvc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name="person")
public class Person {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 3, max = 100, message = "ФИО должно быть от 3 до 100 символов")
    private String name;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 3, max = 100, message = "ФИО должно быть от 3 до 100 символов")
    private String surname;

    @Min(value = 16, message = "Возраст больше 16")
    private int age;

    // Страна, Город, Индекс (состоит из 6 цифр)
    //Russia, Moscow - , 123456   -   ^\d{6,}$
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6,}",
            message = "Адрес должен быть в формате: Country, City, Индекс (6 цифр)")
    private String address;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Email(message = "почта указана неверно")
    private String email;

    public Person() {
    }

    public Person(String name, String surname, int age, String email, String  address) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
