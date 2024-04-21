package com.dazhar.petcloudstore.controller;
import com.dazhar.petcloudstore.model.ContainerEnvironment;
import com.dazhar.petcloudstore.model.Pets;
import com.dazhar.petcloudstore.model.Users;
import com.dazhar.petcloudstore.service.PetCloudStoreService;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;


import com.microsoft.applicationinsights.telemetry.PageViewTelemetry;

@Controller
public class PetCloudWebAppController {
    private static Logger logger = LoggerFactory.getLogger(PetCloudWebAppController.class);

    @Autowired
    private ContainerEnvironment applicationEnvironment;

    @Autowired
    private PetCloudStoreService PetCloudStoreService;

    @Autowired
    private Users currentUser;

    @ModelAttribute
    public void setModel(HttpServletRequest request, Model model, OAuth2AuthenticationToken token) {

        if (this.currentUser.getSessionId() == null) {
            this.currentUser.setSessionId(RequestContextHolder.currentRequestAttributes().getSessionId());
        }

        if (token != null) {
            final OAuth2User user = token.getPrincipal();

            this.currentUser.setUserName((String) user.getAttributes().get("name"));

            this.currentUser.getTelemetryClient()
                    .trackEvent(String.format("PetStoreApp %s logged in, container host: %s",
                            this.currentUser.getUserName(), this.applicationEnvironment.getContainerHostName()));

            model.addAttribute("user", this.currentUser.getUserName());
            model.addAttribute("grant_type", user.getAuthorities());
            model.addAllAttributes(user.getAttributes());
        }

        model.addAttribute("userName", this.currentUser.getUserName());
        model.addAttribute("applicationEnvironment", this.applicationEnvironment);

        model.addAttribute("sessionId", this.currentUser.getSessionId());
        MDC.put("session_Id", this.currentUser.getSessionId());
    }

    @GetMapping(value = "/login")
    public String login(Model model, HttpServletRequest request) throws URISyntaxException {
        logger.info("PetStoreApp /login requested, routing to login view...");

        PageViewTelemetry pageViewTelemetry = new PageViewTelemetry();
        pageViewTelemetry.setUrl(new URI(request.getRequestURL().toString()));
        pageViewTelemetry.setName("login");
        this.currentUser.getTelemetryClient().trackPageView(pageViewTelemetry);
        return "login";
    }

    @GetMapping(value = "/dogbreeds")
    public String dogBreeds(Model model, OAuth2AuthenticationToken token, HttpServletRequest request)
            throws URISyntaxException {
        logger.info("PetStoreApp /dogbreeds requested, routing to dogbreeds view...");

        model.addAttribute("pets", this.PetCloudStoreService.getPets());
        return "dogbreeds";
    }

    @GetMapping(value = "/dogbreeddetails")
    public String dogBreedDetails(Model model, OAuth2AuthenticationToken token, HttpServletRequest request,
            @RequestParam(name = "id") int id) throws URISyntaxException {

        if (null == this.currentUser.getPets()) {
            this.PetCloudStoreService.getPets();
        }

        Pets pets = null;

        try {
            pets = this.currentUser.getPets().get(id - 1);
        } catch (Exception e) {
            this.currentUser.getTelemetryClient().trackException(e);
            pets = new Pets();
        }

        logger.info(String.format(
                "PetStoreApp /dogbreeddetails requested for %s, routing to dogbreeddetails view...", pets.getName()));

        model.addAttribute("pets", pets);

        return "dogbreeddetails";
    }

    @GetMapping(value = "/claims")
    public String claims(Model model, OAuth2AuthenticationToken token, HttpServletRequest request)
            throws URISyntaxException {
        logger.info(String.format("PetStoreApp /claims requested for %s, routing to claims view...",
                this.currentUser.getUserName()));
        return "claims";
    }

    @GetMapping(value = "/slowness")
    public String generateAppInsightsSlowness(Model model, OAuth2AuthenticationToken token,
            HttpServletRequest request) throws URISyntaxException, InterruptedException {
        logger.info("PetStoreApp simulating slowness, routing to home view...");

        PageViewTelemetry pageViewTelemetry = new PageViewTelemetry();
        pageViewTelemetry.setUrl(new URI(request.getRequestURL().toString()));
        pageViewTelemetry.setName("slow operation");
        this.currentUser.getTelemetryClient().trackPageView(pageViewTelemetry);

        Thread.sleep(30000);

        return "home";
    }

    @GetMapping(value = "/exception")
    public String exception(Model model, OAuth2AuthenticationToken token, HttpServletRequest request)
            throws URISyntaxException, InterruptedException {

        NullPointerException npe = new NullPointerException();

        logger.info("PetStoreApp simulating NullPointerException, routing to home view..." + npe.getStackTrace());

        this.currentUser.getTelemetryClient().trackException(npe);

        return "home";
    }

    @GetMapping(value = "/*")
    public String home(Model model, OAuth2AuthenticationToken token, HttpServletRequest request)
            throws URISyntaxException {
        logger.info(String.format("PetStoreApp %s is being routed to home view session %s", this.currentUser.getUserName(),
                this.currentUser.getSessionId()));
        PageViewTelemetry pageViewTelemetry = new PageViewTelemetry();
        pageViewTelemetry.setUrl(new URI(request.getRequestURL().toString()));
        pageViewTelemetry.setName("default");
        this.currentUser.getTelemetryClient().trackPageView(pageViewTelemetry);
        return "home";
    }
}
