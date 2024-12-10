package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Address;
import models.enums.Level;
import models.enums.Education;
import models.enums.Gender;

public class Utils {

    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt(String message, boolean addPrintList){
        int value;
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                //clearConsole();
                System.out.println("Valor inválido. Tente novamente.");
                scanner.next();
            }
        }
        if(!message.isEmpty() && addPrintList){
            Printer.printList.add(message + value);
            Printer.print(true);
        }
        return value;
    }

    public static long readLong(String message){
        long value;
        while (true) {
            System.out.print(message);
            if (scanner.hasNextLong()) {
                value = scanner.nextLong();
                Printer.printList.add(message + value);
                scanner.nextLine();
                break;
            } else {
                clearConsole();
                System.out.println("Valor inválido. Tente novamente.");
                scanner.next();
            }
        }
        Printer.print(true);
        return value;
    }

    public static double readDouble(String message){
        double value;
        while (true) {
            System.out.print(message);
            if (scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                Printer.printList.add(message + value);
                scanner.nextLine();
                break;
            } else {
                clearConsole();
                System.out.println("Valor inválido. Tente novamente.");
                scanner.next();
            }
        }
        Printer.print(true);
        return value;
    }

    public static String readString(String message, boolean addPrintList){
        System.out.print(message);
        String readedString = scanner.nextLine();
        if(addPrintList){
            Printer.printList.add(message + readedString);
            Printer.print(true);
        }
        return readedString;
    }

    public static String readCPF() {
        String cpf;
        while (true) {
            cpf = readString("CPF: ", false);
            if(cpf.length() == 11 && cpf.matches("\\d+")){
                Printer.printList.add("CPF: " + cpf.substring(0, 3) + "." +
                                                cpf.substring(3, 6) + "." +
                                                cpf.substring(6, 9) + "-" +
                                                cpf.substring(9));
                Printer.print(true);
                break;
            }
            Printer.print(true);
            System.out.println("CPF inválido. Tente novamente.\n");
        }
        return cpf;
    }

    public static Gender readGender(String message){
        while (true) {
            System.out.println(message);
            System.out.println("Digite o número correspondente ao genêro do funcionário:");
            System.out.print("1 - Masculino \n2 - Feminino\n3 - Outro\n");
            try {
                int id = readInt("", false);
                Gender gender = Gender.fromId(id);
                Printer.printList.add(message + gender.toString());
                Printer.print(true);
                return gender;
            } catch (IllegalArgumentException e) {
                clearConsole();
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }

    public static LocalDate readLocalDate(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            try {
                LocalDate date = LocalDate.parse(input, formatter);
                Printer.printList.add(message + input);
                Printer.print(true);
                return date;
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Por favor, tente novamente.");
            }
        }
    }

    public static List<String> readSubjects() {
        List<String> subjects = new ArrayList<>();

        int value;
        Printer.printList.add("Disciplinas: ");
        while (true) {
            System.out.println("Disciplina ministrada: ");
            String subj = readString("", false);
            subjects.add(subj);
            Printer.printList.add("    - " + subj);

            Printer.print(true);
            System.out.println("Deseja cadastrar mais uma disciplina? ");
            System.out.print("1 - Sim\n2 - Não\n");

            value = readInt("", false);
            if(value == 2) break;
            if(value != 1){
                Printer.print(true);
                System.out.println("\nValor inválido. Tente novamente");
            }
        }
        return subjects;
    }


    public static Address readAddress(String message) {   
        String city = readString("Cidade: ", true);
        String neighborhood = readString("Bairro: ", true);
        String street = readString("Rua: ", true);
        Integer number = readInt("Número: ", true);
        String cep = readString("CEP: ", true);
        Printer.print(true);
        return new Address(street, number, neighborhood, city, cep);
    }

    public static Level readLevel(String message) {   
        while (true) {
            System.out.println(message);
            System.out.println("Digite o número correspondente ao genêro do funcionário:");
            System.out.print("1 - I \n" + 
                            "2 - II\n" +
                            "3 - III\n" + 
                            "4 - IV\n" +
                            "5 - V\n" +
                            "6 - VI\n" +
                            "7 - VII\n" +
                            "8 - VIII\n");
            try {
                int id = readInt("", false);
                Level level = Level.fromId(id);
                Printer.printList.add(message + level.toString());
                Printer.print(true);
                return level;
            } catch (IllegalArgumentException e) {
                clearConsole();
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }

    public static Education readEducation(String message) {   
        while (true) {
            System.out.println(message);
            System.out.println("Digite o número correspondente ao genêro do funcionário:");
            System.out.print("1 - Especialização\n" + 
                            "2 - Mestrado\n" +
                            "3 - Doutorado\n");
            try {
                int id = readInt("", false);
                Education education = Education.fromId(id);
                Printer.printList.add(message + education.toString());
                Printer.print(true);
                return education;
            } catch (IllegalArgumentException e) {
                clearConsole();
                System.out.println("Valor inválido. Tente novamente.");
            }
        }
    }

    public static void clearConsole() {
        String command = isWindows() ? "cmd /c cls" : "clear";
        try {
            new ProcessBuilder(command.split(" ")).inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Não foi possível limpar o terminal.");
        }
    }

    public static boolean isWindows() {
        String operatingSystem = System.getProperty("os.name").toLowerCase();
        return operatingSystem.contains("win");
    }
}
