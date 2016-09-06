package se.alphadev.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebsocketController {

    private Consumer consumer;

    @Autowired
    public WebsocketController(Consumer consumer) {
        this.consumer = consumer;
    }

    @RequestMapping(path="/openstream", method= RequestMethod.POST)
    @ResponseBody
    public void openStream() {
        consumer.consume();
    }
}
