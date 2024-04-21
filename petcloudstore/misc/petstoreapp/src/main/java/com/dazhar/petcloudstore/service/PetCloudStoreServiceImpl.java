package com.dazhar.petcloudstore.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import com.dazhar.petcloudstore.model.ContainerEnvironment;
import com.dazhar.petcloudstore.model.Pets;
import com.dazhar.petcloudstore.model.Users;

@Component
public class PetCloudStoreServiceImpl implements PetCloudStoreService {
	@Autowired
	private Users sessionUser;

	@Autowired
	private ContainerEnvironment containerEnvironment;

	private WebClient webClient = null;

	@PostConstruct
	public void initialize() {
		this.webClient = WebClient.builder().baseUrl(this.containerEnvironment.getPetStoreServiceURL()).build();
	}

	@Override
	public Collection<Pets> getPets() {
		this.sessionUser.getTelemetryClient().trackEvent(String.format(
				"PetStoreApp %s is requesting to retrieve pets from the PetStoreService", this.sessionUser.getUserName()));
		try {
			List<Pets> pets = this.webClient.get().uri("/v2/pet/findByStatus?status={status}", "available")
					.header("session-id", this.sessionUser.getSessionId()).accept(MediaType.APPLICATION_JSON)
					.header("Ocp-Apim-Subscription-Key", this.containerEnvironment.getPetStoreServiceSubscriptionKeyL())
					.header("Ocp-Apim-Trace", "true").retrieve()
					.bodyToMono(new ParameterizedTypeReference<List<Pets>>() {
					}).block();
			// use this for look up on details page, intentionally avoiding spring cache to
			// ensure service calls are made each
			// time to show Telemetry with APIM requests
			this.sessionUser.setPets(pets);
			return pets;
		} catch (WebClientException wce) {
			this.sessionUser.getTelemetryClient().trackException(wce);
		}
		return new ArrayList<Pets>();
	}
}
