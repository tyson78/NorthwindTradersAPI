package com.pluralsight.northwindtraders.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping(path="/", method= RequestMethod.GET)
    String sayHello(@RequestParam(defaultValue = "World") String country){
        return "Hello " + country + "!";
        /* ex: http://localhost:8080/?country=usa
        * outputs: Hello usa! */
    }



}
