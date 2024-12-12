package models;

import models.enums.Education;
import models.enums.Gender;
import models.enums.Level;

import java.time.LocalDate;
import java.util.List;

public class Professor extends Person implements Employee{
    Level professorLevel;
    Education professorEducation;
    List<String> subjects;

    public Professor(String name,
                     String cpf,
                     LocalDate birthDate,
                     Gender gender,
                     Address address,
                     Long registration,
                     Double wage,
                     String department,
                     Integer workingHours,
                     LocalDate entryDate) {
        super(name, cpf, birthDate, gender, address, registration, wage, department, workingHours, entryDate);
    }

    public Professor(String name,
                     String cpf,
                     LocalDate birthDate,
                     Gender gender,
                     Address address,
                     Long registration,
                     Double wage,
                     String department,
                     Integer workingHours,
                     LocalDate entryDate,
                     Level professorLevel,
                     Education professorEducation,
                     List<String> subjects) {
        super(name, cpf, birthDate, gender, address, registration, wage, department, workingHours, entryDate);
        this.professorLevel = professorLevel;
        this.professorEducation = professorEducation;
        this.subjects = subjects;
    }

    @Override
    public Double calculateWage() {
        Double baseWage = 4000.0;
        Double educationBonus = this.professorEducation.getId() * 25.0;

        Double wage = baseWage + ((baseWage * educationBonus)/ 100.0);
        if(this.getProfessorLevel().getId() > 1) {
            for (int i = 1; i < this.getProfessorLevel().getId(); i++) {
                wage = wage + ((wage * 5)/ 100.0);
            }
        }
        this.wage = wage;
        return wage;
    }

    public Level getProfessorLevel() {
        return professorLevel;
    }

    public void setProfessorLevel(Level professorLevel) {
        this.professorLevel = professorLevel;
    }

    public Education getProfessorEducation() {
        return professorEducation;
    }

    public void setProfessorEducation(Education professorEducation) {
        this.professorEducation = professorEducation;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }
}
