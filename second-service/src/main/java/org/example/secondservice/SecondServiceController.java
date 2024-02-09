package org.example.secondservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/second-service")
@RestController
public class SecondServiceController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Second service.";
    }
}
