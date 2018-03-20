package br.com.faht.javafx.module;

import java.io.Closeable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import br.com.faht.javafx.module.provider.EntityManagerFactoryProvider;
import br.com.faht.javafx.module.provider.EntityManagerProvider;

public class JpaModule extends AbstractModule implements Closeable {

	private String persistenceUnit;
	
	private EntityManagerFactoryProvider provider;

	public JpaModule(String persistenceUnit) {

		this.persistenceUnit = persistenceUnit;
	}

	@Override
	protected void configure() {
		
		provider = new EntityManagerFactoryProvider(persistenceUnit);
		
		bind(EntityManagerFactory.class)
			.annotatedWith(Names.named(persistenceUnit))
			.toProvider(provider)
			.asEagerSingleton();
		
		bind(EntityManager.class).annotatedWith(Names.named(persistenceUnit))
			.toProvider(new EntityManagerProvider(provider.get()));		
	}

	@Override
	public void close() {
		
		provider.get().close();
		persistenceUnit = null;
		provider = null;
	}
}
