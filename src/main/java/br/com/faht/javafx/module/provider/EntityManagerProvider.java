package br.com.faht.javafx.module.provider;

import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.inject.Provider;

public class EntityManagerProvider implements Provider<EntityManager> {

	private static final ThreadLocal<EntityManager> ENTITY_MANAGER = new ThreadLocal<EntityManager>();

	private EntityManagerFactory entityManagerFactory;

	public EntityManagerProvider(EntityManagerFactory entityManagerFactory) {

		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public EntityManager get() {

		EntityManager entityManager = ENTITY_MANAGER.get();

		if (Objects.isNull(entityManager)) {
			ENTITY_MANAGER.set(entityManager = entityManagerFactory.createEntityManager());
		}

		return entityManager;
	}

}
