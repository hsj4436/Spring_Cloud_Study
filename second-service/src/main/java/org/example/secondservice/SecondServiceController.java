package org.example.secondservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/second-service")
@RestController
public class SecondServiceController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Second service.";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header) {
        log.info(header);
        return "Hello world in Second service.";
    }

    @GetMapping("/check")
    public String check() {
        return "Hi, there. This is a message from Second service.";
    }
}
