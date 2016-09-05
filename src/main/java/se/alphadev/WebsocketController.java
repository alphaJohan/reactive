package se.alphadev;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/heartrate")
    public HeartrateMessage heartrate() throws Exception {
        return new HeartrateMessage(85);
    }
}
