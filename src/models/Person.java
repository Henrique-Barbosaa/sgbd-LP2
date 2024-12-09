package models;

import models.enums.Gender;

import java.io.Serializable;
import java.time.LocalDate;
public abstract class Person implements Serializable {
    String name;
    String cpf;
    LocalDate birthDate;
    Gender gender;
    Address address;
    Long registration;
    Double wage;
    String department;
    Integer workingHours;
    LocalDate entryDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getRegistration() {
        return registration;
    }

    public void setRegistration(Long registration) {
        this.registration = registration;
    }

    public Double getWage() {
        return wage;
    }

    public void setWage(Double wage) {
        this.wage = wage;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Integer workingHours) {
        this.workingHours = workingHours;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public Person(String name,
                  String cpf,
                  LocalDate birthDate,
                  Gender gender,
                  Address address,
                  Long registration,
                  Double wage,
                  String department,
                  Integer workingHours,
                  LocalDate entryDate) {
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.registration = registration;
        this.wage = wage;
        this.department = department;
        this.workingHours = workingHours;
        this.entryDate = entryDate;
    }
}
