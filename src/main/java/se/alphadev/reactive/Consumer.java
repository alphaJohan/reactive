package se.alphadev.reactive;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import se.alphadev.reactive.Person;

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

            Flux.fromIterable(persons)
                    .take(30)
                    .map(Person::getName)
                    .doOnNext(s -> this.template.convertAndSend("/topic/data", s))
                    .subscribe();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
