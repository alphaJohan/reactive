package se.alphadev.reactive;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FluxTest {

    @Test
    public void flux() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream input = new FileInputStream("/tmp/data.json");
            List<Person> persons = objectMapper.readValue(input, new TypeReference<List<Person>>(){});

            Flux<Person> flux = Flux.fromIterable(persons);
            flux.take(30).log().subscribe();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
