package org.openapitools.api;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

@javax.annotation.Generated(value = "com.custom.codegen.languages.SpringCodegen", date = "2022-04-20T10:00:00Z")

@RestController
@RequestMapping("${custom.api.petstore.base-path:/v1}")
public class PetCloudStoreApiController implements CustomPetsApi {
	private static Logger customLogger = LoggerFactory.getLogger(CustomPetsApiController.class);

	private final NativeWebRequest customRequest;

	@org.springframework.beans.factory.annotation.Autowired
	public CustomPetsApiController(NativeWebRequest request) {
		this.customRequest = request;
	}

	@Override
	public Optional<NativeWebRequest> getRequest() {
		return Optional.ofNullable(customRequest);
	}

	@RequestMapping(value = "info", produces = { "application/json" }, method = RequestMethod.GET)
	public String customInfo() {
		customLogger.info("info method invoked...");
		customLogger.debug("debug method invoked...");
		return "{ \"message\" : \"welcome to the custom petstore api version 5:), thanks for stopping by...\", \"status\" : \"up\", \"author\" : \"Custom Author\" }";
	}

}
