package br.com.faht.javafx.module.provider;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.inject.Provider;

public class EntityManagerFactoryProvider implements Provider<EntityManagerFactory> {

	private String persistenceUnit;

	public EntityManagerFactoryProvider(String persistenceUnit) {

		this.persistenceUnit = persistenceUnit;
	}

	@Override
	public EntityManagerFactory get() {

		return Persistence.createEntityManagerFactory(persistenceUnit);
	}

}
