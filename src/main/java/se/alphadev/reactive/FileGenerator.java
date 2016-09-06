package se.alphadev.reactive;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class FileGenerator {

    public void generate() {
        int counter = 0;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < 20000; i++) {
            Random r = new Random();
            Person.Type[] types = Person.Type.values();
            Person person = new Person(++counter, "apa" + counter, types[r.nextInt(3)]);
            persons.add(person);
        }
        try {
            objectMapper.writeValue(new FileOutputStream("/tmp/data.json"), persons);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            InputStream input = new FileInputStream("/tmp/data.json");
            List<Person> persons = objectMapper.readValue(input,new TypeReference<List<Person>>(){});

//            for (Person person : persons) {
//                System.out.println(person.getType());
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
