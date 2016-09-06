package se.alphadev;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import se.alphadev.johantestar.Person;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class Consumer {

    private SimpMessagingTemplate template;

    @Autowired
    public Consumer(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void consume() {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream input = new FileInputStream("/tmp/data.json");
            List<Person> persons = objectMapper.readValue(input, new TypeReference<List<Person>>(){});

            Flux<Person> flux = Flux.fromIterable(persons);



        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 100; i++) {
            String text = "{ message nr: " + i + " }";
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.template.convertAndSend("/topic/data", text);
        }
    }
}
