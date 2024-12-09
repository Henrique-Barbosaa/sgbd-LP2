package utils;

import models.Address;
import models.Person;
import models.PersonsDB;
import models.Professor;
import models.enums.Education;
import models.enums.Gender;
import models.enums.Level;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Menu {
    PersonsDB personsDB = PersonsDB.getInstance();
    ArrayList<Person> personsList = personsDB.getPersons();

    public Menu(){
        this.saveWhenCloseApp();
    }

    public void professorRegister() {
        try {
            Utils.clearConsole();
            System.out.println("Cadastro de Professor");
            String name = Utils.readString("Nome: ", true);
            String cpf = Utils.readString("CPF: ", true);
            String department = Utils.readString("Departamento: ", true);
            Integer workingHours = Utils.readInt("Carga Horária: ");
            Long registration = Utils.readLong("Matrícula: ");
            Gender gender = Utils.readGender("Gênero: ");
            LocalDate birthDate = Utils.readLocalDate("Data de Nascimento: ");
            LocalDate entryDate = Utils.readLocalDate("Data de Entrada: ");
            Address address = Utils.readAddress("Endereço: ");
            Level level = Utils.readLevel("Nível do Professor: ");
            Education education = Utils.readEducation("Formação: ");
            List<String> subjects = Utils.readSubjects();

            personsList.add(new Professor(name,
                                        cpf,
                                        birthDate,
                                        gender,
                                        address,
                                        registration,
                                        4000.0,
                                        department,
                                        workingHours,
                                        entryDate,
                                        level,
                                        education,
                                        subjects));
            System.out.println("Cadastro do professor realizado com sucesso!");
            Printer.printList.clear();
        } catch (NoSuchElementException e) {
            System.out.println("\n\nEntrada interrompida. Programa Encerrado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printAllProfessors() {
        if(personsList.isEmpty()) {
            System.out.println("\nNão há funcionários cadastrados no sistema");
            return;
        };
        System.out.println("\n------------------------------");
        for (Person person : personsList) {
            if(person instanceof Professor){
                Professor prof = (Professor) person;
                System.out.println("Nome: " + prof.getName());
                System.out.println("Gênero: " + prof.getGender());
                System.out.println("------------------------------");
            }
        }
    }

    // Salva o ArrayList no arquivo binário quando o programa for fechado.
    public void saveWhenCloseApp() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/bin/persons.bin"))) {
                oos.writeObject(personsList);
                System.out.println("\nArquivo binário salvo com sucesso!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
}