package org.openapitools.configuration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Custom home redirection to OpenAPI API documentation
 */
@Controller
public class PetCloudStoreHomeController {

    @RequestMapping("/")
    public String customIndex() {
        return "redirect:custom-swagger-ui.html";
    }
}

