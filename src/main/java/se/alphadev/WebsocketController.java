package se.alphadev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebsocketController {

    private SimpMessagingTemplate template;

    @Autowired
    public WebsocketController(SimpMessagingTemplate template) {
        this.template = template;
    }


    @RequestMapping(path="/heartrate", method= RequestMethod.POST)
    @ResponseBody
    public void heartrate() {
        for (int i = 0; i < 10; i++) {
            String text = "{ message nr: " + i + " }";
            this.template.convertAndSend("/topic/heartrates", text);
        }
    }
}
