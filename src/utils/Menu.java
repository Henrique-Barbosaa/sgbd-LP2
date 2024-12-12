package utils;

import models.Address;
import models.AdminTechnician;
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
    ArrayList<Person> personsList = PersonsDB.getPersons();

    public Menu(){
        this.saveWhenCloseApp();
    }

    public void run() {
        Utils.clearConsole();
        boolean running = true;

        while (running) {
            Printer.print(true);
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Cadastrar Professor");
            System.out.println("2. Cadastrar Técnico Administrativo");
            System.out.println("3. Listar Técnicos Administrativos");
            System.out.println("4. Listar Professores");
            System.out.println("5. Deletar Professor");
            System.out.println("6. Deletar Técnico Administrativo");
            System.out.println("7. Buscar Professor");
            System.out.println("8. Buscar Técnico Administrativo");
            System.out.println("9. Calcular Salário De Funcionário");
            System.out.println("10. Sair");
            int option = Utils.readInt("\nEscolha uma opção: ", false);

            switch (option) {
                case 1:
                    Utils.clearConsole();
                    Printer.printList.clear();
                    professorRegister();
                    break;
                case 2:
                    Utils.clearConsole();
                    Printer.printList.clear();
                    technicianRegister();
                    break;
                case 3:
                    Utils.clearConsole();
                    Printer.printList.clear();
                    printAllAdminTechnician();
                    break;
                case 4:
                    Utils.clearConsole();
                    Printer.printList.clear();
                    printAllProfessors();
                    break;
                case 5:
                    Utils.clearConsole();
                    Printer.printList.clear();
                    deleteProfessor();
                    break;
                case 6:
                    Utils.clearConsole();
                    Printer.printList.clear();
                    deleteTechnician();
                    break;
                case 7:
                    Utils.clearConsole();
                    Printer.printList.clear();
                    searchProfessor();
                    break;
                case 8:
                    Utils.clearConsole();
                    Printer.printList.clear();
                    searchTechnician();
                    break;
                case 9:
                    Utils.clearConsole();
                    Printer.printList.clear();
                    calculateWage();
                    break;
                case 10:
                    System.out.println("\nFechando programa...");
                    running = false;
                    break;
                default:
                    Utils.clearConsole();
                    System.out.println("\nOpção inválida. Tente novamente.");
            }
        }
    }

    public void calculateWage() {
        Long registration = Utils.readLong("Digite a matrícula do funcionário que deseja excluir: \n", false);
        for (int i = 0; i < personsList.size(); i++) {
            Person p = personsList.get(i);
            if(p instanceof Professor){
                Professor prof = (Professor) p;
                if(prof.getRegistration().equals(registration)){
                    Printer.printList.add("Professsor(a) de matrícula " + registration + " tem o salário de R$" + prof.calculateWage() + "\n");
                    return;
                }
            }
            if(p instanceof AdminTechnician){
                AdminTechnician tec = (AdminTechnician) p;
                if(tec.getRegistration().equals(registration)){
                    Printer.printList.add("Técnico(a) de matrícula " + registration + " tem o salário de R$" +  String.format("%.2f", tec.calculateWage()) + "\n");
                    return;
                }
            }
        }
        Printer.printList.add("Não existe nenhum professor com essa matrícula cadastrato no sistema!\n");
    }

    public void professorRegister() {
        try {
            System.out.println("Cadastro de Professor");
            String name = Utils.readString("Nome: ", true);
            String cpf = Utils.readCPF();
            String department = Utils.readString("Departamento: ", true);
            Integer workingHours = Utils.readInt("Carga Horária: ", true);
            Long registration = Utils.readRegistration("Matrícula: ");
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

    public void technicianRegister() {
        try {
            System.out.println("Cadastro de Técnico Administrativo");
            String name = Utils.readString("Nome: ", true);
            String cpf = Utils.readCPF();
            String department = Utils.readString("Departamento: ", true);
            Integer workingHours = Utils.readInt("Carga Horária: ", true);
            Long registration = Utils.readRegistration("Matrícula: ");
            Gender gender = Utils.readGender("Gênero: ");
            LocalDate birthDate = Utils.readLocalDate("Data de Nascimento: ");
            LocalDate entryDate = Utils.readLocalDate("Data de Entrada: ");
            Address address = Utils.readAddress("Endereço: ");
            Level level = Utils.readLevel("Nível do Técnico: ");
            Education education = Utils.readEducation("Formação: ");
            Boolean hazardousCondition = Utils.readBoolean("O função do técnico possui insalubridade?\n" +
                                                            "1 - Sim\n" +
                                                            "2 - Não\n", "Insalubridade: ");
            Boolean benefitedRole = Utils.readBoolean("O função do técnico é gratificada?\n" +
                                                            "1 - Sim\n" +
                                                            "2 - Não\n", "Função Gratificada: ");

            personsList.add(new AdminTechnician(name,
                                        cpf,
                                        birthDate,
                                        gender,
                                        address,
                                        registration,
                                        2500.0,
                                        department,
                                        workingHours,
                                        entryDate,
                                        level,
                                        education,
                                        hazardousCondition,
                                        benefitedRole));
            System.out.println("Cadastro do técnico realizado com sucesso!");
            Printer.printList.clear();
        } catch (NoSuchElementException e) {
            System.out.println("\n\nEntrada interrompida. Programa Encerrado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printAllProfessors() {
        if(personsList.isEmpty()) {
            Printer.printList.add("Não existe nenhum professor cadastrado no sistema!");
            return;
        };
        Printer.printList.add("------------------------------");
        for (Person person : personsList) {
            if(person instanceof Professor){
                Professor prof = (Professor) person;
                Printer.printList.add("Nome: " + prof.getName());
                Printer.printList.add("Disciplinas ministradas: ");
                for (int i = 0; i < prof.getSubjects().size(); i++) {
                    Printer.printList.add("  - " + prof.getSubjects().get(i));
                }
                Printer.printList.add("Matricula: " + prof.getRegistration());
                Printer.printList.add("------------------------------");
            }
        }
    }

    public void printAllAdminTechnician() {
        if(personsList.isEmpty()) {
            Printer.printList.add("Não existe nenhum técnico administrativo cadastrado no sistema!");
            return;
        };
        Printer.printList.add("------------------------------");
        for (Person person : personsList) {
            if(person instanceof AdminTechnician){
                AdminTechnician tec = (AdminTechnician) person;
                Printer.printList.add("Nome: " + tec.getName());
                Printer.printList.add("Matricula: " + tec.getRegistration());
                Printer.printList.add("------------------------------");
            }
        }
    }

    public void deleteProfessor() {
        Long registration = Utils.readLong("Digite a matrícula do professor que deseja excluir: \n", false);
        for (int i = 0; i < personsList.size(); i++) {
            Person p = personsList.get(i);
            if(p instanceof Professor){
                Professor prof = (Professor) p;
                if(prof.getRegistration().equals(registration)){
                    personsList.remove(i);
                    Printer.printList.add("Professsor(a) de matrícula " + registration + " deletado com sucesso!\n");
                    return;
                }
            }
        }
        Printer.printList.add("Não existe nenhum professor com essa matrícula cadastrato no sistema!\n");
    }

    public void deleteTechnician() {
        Long registration = Utils.readLong("Digite a matrícula do técnico que deseja excluir: \n", false);
        for (int i = 0; i < personsList.size(); i++) {
            Person p = personsList.get(i);
            if(p instanceof AdminTechnician){
                AdminTechnician tec = (AdminTechnician) p;
                if(tec.getRegistration().equals(registration)){
                    personsList.remove(i);
                    Printer.printList.add("Técnico(a) de matrícula " + registration + " deletado com sucesso!\n");
                    return;
                }
            }
        }
        Printer.printList.add("Não existe nenhum técnico com essa matrícula cadastrato no sistema!\n");
    }

    public void searchProfessor() {
        Long registration = Utils.readLong("Digite a matrícula do professor que deseja buscar: \n", false);
        for (int i = 0; i < personsList.size(); i++) {
            Person p = personsList.get(i);
            if(p instanceof Professor){
                Professor prof = (Professor) p;
                if(prof.getRegistration().equals(registration)){
                    Printer.printList.add("Professor encontrado: \n");
                    Printer.printList.add("Nome: " + prof.getName());
                    Printer.printList.add("CPF: " + prof.getCpf().substring(0, 3) + "." +
                                                prof.getCpf().substring(3, 6) + "." +
                                                prof.getCpf().substring(6, 9) + "-" +
                                                prof.getCpf().substring(9));
                    Printer.printList.add("Gênero: " + prof.getGender());
                    Printer.printList.add("Formação: " + prof.getProfessorEducation());
                    Printer.printList.add("Nível: " + prof.getProfessorLevel());
                    Printer.printList.add("Matrícula: " + prof.getRegistration());
                    Printer.printList.add("Disciplinas ministradas: ");
                    for (int j = 0; j < prof.getSubjects().size(); j++) {
                        Printer.printList.add("  - " + prof.getSubjects().get(j));
                    }
                    return;
                }
            }
        }
        Printer.printList.add("Não existe nenhum professor com essa matrícula cadastrato no sistema!\n");
    }


    public void searchTechnician() {
        Long registration = Utils.readLong("Digite a matrícula do técnico que deseja buscar: \n", false);
        for (int i = 0; i < personsList.size(); i++) {
            Person p = personsList.get(i);
            if(p instanceof AdminTechnician){
                AdminTechnician tec = (AdminTechnician) p;
                if(tec.getRegistration().equals(registration)){
                    Printer.printList.add("Técnico encontrado: \n");
                    Printer.printList.add("Nome: " + tec.getName());
                    Printer.printList.add("CPF: " + tec.getCpf().substring(0, 3) + "." +
                                                tec.getCpf().substring(3, 6) + "." +
                                                tec.getCpf().substring(6, 9) + "-" +
                                                tec.getCpf().substring(9));
                    Printer.printList.add("Gênero: " + tec.getGender());
                    Printer.printList.add("Formação: " + tec.getTechnicianEducation());
                    Printer.printList.add("Nível: " + tec.getTechnicianLevel());
                    Printer.printList.add("Matrícula: " + tec.getRegistration());
                    Printer.printList.add("Insalubridade: " + (tec.getHazardousCondition() ? "Sim" : "Não"));
                    Printer.printList.add("Função Gratificada: " + (tec.getBenefitedRole() ? "Sim" : "Não"));
                    return;
                }
            }
        }
        Printer.printList.add("Não existe nenhum técnico com essa matrícula cadastrato no sistema!\n");
    }

    // Salva o ArrayList no arquivo binário quando o programa for fechado pelo menu.
    public void saveWhenCloseApp() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/bin/persons.bin"))) {
                oos.writeObject(personsList);
                System.out.println("Arquivo binário salvo com sucesso!\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
}
