package models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PersonsDB {
    private static PersonsDB personsDB;
    ArrayList<Person> persons;

    private PersonsDB() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/bin/persons.bin"))) {
            @SuppressWarnings("unchecked")
            ArrayList<Person> list = (ArrayList<Person>) ois.readObject();
            this.persons = list;
            System.out.println("ArrayList<Person> carregado: " + list);
        } catch (Exception e) {
            this.persons = new ArrayList<>();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/bin/persons.bin"))) {
                oos.writeObject(this.persons);
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

    public ArrayList<Person> getPersons(){
        return this.persons;
    }
}
