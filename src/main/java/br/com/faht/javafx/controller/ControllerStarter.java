package br.com.faht.javafx.controller;

import com.google.inject.Injector;

public interface ControllerStarter {

	AbstractController run(Class<?> controller);

	void setContainer(Injector container);
}
