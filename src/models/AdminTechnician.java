package models;

import models.enums.Education;
import models.enums.Gender;
import models.enums.Level;

import java.time.LocalDate;

public class AdminTechnician extends Person implements Employee{
    Level technicianLevel;
    Education technicianEducation;
    Boolean hazardousCondition;
    Boolean benefitedRole;

    public AdminTechnician(String name,
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

    public AdminTechnician(String name,
                           String cpf,
                           LocalDate birthDate,
                           Gender gender,
                           Address address,
                           Long registration,
                           Double wage,
                           String department,
                           Integer workingHours,
                           LocalDate entryDate,
                           Level technicianLevel,
                           Education technicianEducation,
                           Boolean hazardousCondition,
                           Boolean benefitedRole) {
        super(name, cpf, birthDate, gender, address, registration, wage, department, workingHours, entryDate);
        this.technicianLevel = technicianLevel;
        this.technicianEducation = technicianEducation;
        this.hazardousCondition = hazardousCondition;
        this.benefitedRole = benefitedRole;
    }

    @Override
    public Double calculateWage() {
        return null;
    }

    public Level getTechnicianLevel() {
        return technicianLevel;
    }

    public void setTechnicianLevel(Level technicianLevel) {
        this.technicianLevel = technicianLevel;
    }

    public Education getTechnicianEducation() {
        return technicianEducation;
    }

    public void setTechnicianEducation(Education technicianEducation) {
        this.technicianEducation = technicianEducation;
    }

    public Boolean getHazardousCondition() {
        return hazardousCondition;
    }

    public void setHazardousCondition(Boolean hazardousCondition) {
        this.hazardousCondition = hazardousCondition;
    }

    public Boolean getBenefitedRole() {
        return benefitedRole;
    }

    public void setBenefitedRole(Boolean benefitedRole) {
        this.benefitedRole = benefitedRole;
    }
}
