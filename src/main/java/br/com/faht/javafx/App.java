package br.com.faht.javafx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import br.com.faht.javafx.controller.AbstractController;
import br.com.faht.javafx.module.JavaFXModule;
import javafx.stage.Stage;

public class App {

	private AbstractController mainController;

	private Class<? extends AbstractController> controller;

	private List<AbstractModule> modules = new ArrayList<>();

	private Injector container;

	public App(Stage stage) {

		registerModule(new JavaFXModule(stage));
	}

	public void start() {

		container = Guice.createInjector(com.google.inject.Stage.DEVELOPMENT, modules);

		mainController = (AbstractController) container.getInstance(controller);
		mainController.setContainer(container);
		mainController.onCreate(null);
	}

	public void stop() {

		mainController.onStop();

		modules.forEach(action -> {

			try {
				((java.io.Closeable) action).close();
			} catch (IOException e) {
			}
		});

		System.exit(0);
	}

	public void registerController(Class<? extends AbstractController> controller) {

		this.controller = controller;
	}

	public void registerModule(AbstractModule module) {

		modules.add(module);
	}
}
