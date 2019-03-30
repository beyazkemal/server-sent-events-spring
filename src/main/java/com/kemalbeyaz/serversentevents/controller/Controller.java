package com.kemalbeyaz.serversentevents.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author kemal.beyaz
 */
@RestController
@CrossOrigin(maxAge = 3600)
public class Controller {

    public static final Map<Long, SseEmitter> sses = new ConcurrentHashMap<>();

    @GetMapping("/messages/{id}")
    SseEmitter messages(@PathVariable("id") long id) {
        SseEmitter sseEmitter = new SseEmitter(60 * 1000L);

        // Remove emiiter when it's done. :)
        sseEmitter.onError((t) -> sses.remove(id));
        sseEmitter.onTimeout(() -> sses.remove(id));
        sseEmitter.onCompletion(() -> sses.remove(id));

        sses.put(id, sseEmitter);

        return sseEmitter;
    }

}
