package se.alphadev.reactive;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import se.alphadev.reactive.FileGenerator;

@RestController
public class Controller {

    private FileGenerator fileGenerator;

    public Controller(FileGenerator fileGenerator) {
        this.fileGenerator = fileGenerator;
    }

    @RequestMapping(path = "/generate", method = RequestMethod.POST)
    @ResponseBody
    public void createDataFile() {
        fileGenerator.generate();
    }
}
