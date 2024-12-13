package models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PersonsDB {
    private static PersonsDB personsDB;
    private static ArrayList<Person> persons;

    private PersonsDB() {
        String basePath = System.getProperty("user.dir");
        String filePath = Paths.get(basePath, "bin", "persons.bin").toString();
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            @SuppressWarnings("unchecked")
            ArrayList<Person> list = (ArrayList<Person>) ois.readObject();
            PersonsDB.persons = list;
        } catch (Exception e) {
            PersonsDB.persons = new ArrayList<>();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
                oos.writeObject(PersonsDB.persons);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException("Erro ao criar o arquivo binário: " + ex.getMessage());
            }
        }
    }


    public static PersonsDB getInstance(){
        if(personsDB == null) personsDB = new PersonsDB();
        return personsDB;
    }

    public static boolean existByCPF(String cpf){
        for (Person person : persons) {
            if(person.getCpf().equals(cpf)){
                return true;
            }
        }
        return false;
    }

    public static boolean existByRegistration(Long registration){
        for (Person person : persons) {
            if(person.getRegistration().equals(registration)){
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Person> getPersons(){
        return persons;
    }
}