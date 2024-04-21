package com.dazhar.petcloudstore.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.dazhar.petcloudstore.model.Pets;

@Service
public interface PetCloudStoreService {
	public Collection<Pets> getPets();
}
