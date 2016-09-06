package se.alphadev.hrm;

import reactor.core.publisher.Flux;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HeartRateMonitorFileReader {

    public void readFile() throws IOException {
        Path file = Paths.get("/Users/johan/Downloads/SimulANT+1.12.0/SimulANT+/Device0.txt");
        Charset charset = Charset.forName("US-ASCII");
        try {
            BufferedReader reader = Files.newBufferedReader(file, charset);
            //Stream<String> foo = Files.lines(file);
            String line = null;
            while (true) {
                Flux<String> flux = Flux.fromStream(reader.lines());
                flux.subscribe(System.out::println);
            }
            //Flux<String> flux = Flux.fromStream(reader.lines());
            //Flux<String> flux = Flux.fromStream(foo);
            //flux.subscribe(System.out::println);

        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

//    public static void main(String[] args) {
//        HeartRateMonitorFileReader apa = new HeartRateMonitorFileReader();
//        try {
//            apa.readFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
