package com.dazhar.petcloudstore.model;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.microsoft.applicationinsights.TelemetryClient;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SuppressWarnings("serial")
public class Users implements Serializable {
	private String username = "Guest";
	private String sessionId = null;

	// intentionally avoiding spring cache to ensure service calls are made each
	// time to show telemetry with APIM requests
	private List<Pets> pets;

	@Autowired(required = false)
	private transient TelemetryClient telemetryClient;

	@PostConstruct
	private void initialize() {
		if (this.telemetryClient == null) {
			this.telemetryClient = new com.dazhar.petcloudstore.service.PetCloudStoreTelemetryClient();
		}
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getUserName() {
		return username;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public TelemetryClient getTelemetryClient() {
		return this.telemetryClient;
	}

	public List<Pets> getPets() {
		return pets;
	}

	public synchronized void setPets(List<Pets> pets) {
		this.pets = pets;
	}
}
