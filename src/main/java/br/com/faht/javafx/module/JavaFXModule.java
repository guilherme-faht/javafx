package br.com.faht.javafx.module;

import java.io.Closeable;

import com.google.inject.AbstractModule;

import javafx.stage.Stage;

public class JavaFXModule extends AbstractModule implements Closeable {

	private Stage stage;

	public JavaFXModule(Stage stage) {

		this.stage = stage;
	}

	@Override
	protected void configure() {

		bind(javafx.stage.Stage.class).toInstance(stage);
	}

	@Override
	public void close() {
		
		stage = null;
	}
	
}
