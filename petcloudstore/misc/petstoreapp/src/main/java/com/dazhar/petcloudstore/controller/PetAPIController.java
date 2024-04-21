package com.dazhar.petcloudstore.controller;

import com.dazhar.petcloudstore.model.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PetAPIController {

    @Autowired
    private Users currentUser;

    @GetMapping("/api/contactus")
    public String contactUs() {
        this.currentUser.getTelemetryClient()
                .trackEvent(String.format("User %s requesting Contact Us", this.currentUser.getUserName()));
        return "Please contact Rhody PetStore at 401-555-5555";
    }

    @GetMapping("/api/sessionid")
    public String sessionId() {
        return this.currentUser.getSessionId();
    }

}
