package models;

import models.enums.Education;
import models.enums.Gender;
import models.enums.Level;
import utils.Printer;

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
        Printer.printList.add("Calculo do técnico " + this.getName() + " de matrícula " + this.getRegistration() + ".\n");
        Printer.printList.add("Salário base dos técnicos administrativos: R$2500,00");
        Double baseWage = 2500.0;

        Double educationBonus = this.technicianEducation.getId() * 25.0;
        Double wage = baseWage + ((baseWage * educationBonus)/ 100.0);
        Printer.printList.add("Salário após a bonificação por " + this.getTechnicianEducation().toString().toLowerCase() + ": R$" + String.format("%.2f", wage) + "  (+" + String.format("%.2f", educationBonus) + "%)");

        if(this.getTechnicianLevel().getId() > 1) {
            for (int i = 1; i < this.getTechnicianLevel().getId(); i++) {
                wage = wage + ((wage * 3)/ 100.0);
            }
        }
        Printer.printList.add("Salário após a bonificação por nível " + this.getTechnicianLevel() + ": R$" + String.format("%.2f", wage));

        if(this.hazardousCondition){
            wage = wage + ((baseWage * 50.0)/ 100.0);
            Printer.printList.add("Salário após a bonificação por insalubridade: R$" + String.format("%.2f", wage) + "  (+" + String.format("%.2f", 50.0) + "% do salário base)");
        }
        if(this.benefitedRole) {
            wage = wage + ((baseWage * 50.0)/ 100.0);
            Printer.printList.add("Salário após a bonificação por exercer função gratificada: R$" + String.format("%.2f", wage) + "  (+" + String.format("%.2f", 50.0) + "% do salário base)");
        }
        this.wage = wage;
        Printer.printList.add("\nSalário total do técnico: R$" + String.format("%.2f", wage));
        return wage;
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
