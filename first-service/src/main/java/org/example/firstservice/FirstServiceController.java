package org.example.firstservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/first-service")
@RestController
public class FirstServiceController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to First service.";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
        log.info(header);
        return "Hello world in First service.";
    }

    @GetMapping("/check")
    public String check() {
        return "Hi, there. This is a message from First service.";
    }
}
