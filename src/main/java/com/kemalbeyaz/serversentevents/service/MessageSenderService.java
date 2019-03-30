package com.kemalbeyaz.serversentevents.service;

import com.kemalbeyaz.serversentevents.controller.Controller;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

/**
 * @author kemal.beyaz
 */
@Service
public class MessageSenderService {

    // Just for demo, sending message with two seconds interval
    @Scheduled(fixedRate = 2000)
    private void sendMessage() {
        Controller.sses.forEach((k, sse) -> {

            // Try send message to id
            try {
                Date date = new Date();
                sse.send("Hello World! Time: " + date.getTime() + " - " + k);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
