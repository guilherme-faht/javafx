package br.com.faht.javafx;

import java.util.Locale;

import br.com.faht.javafx.controller.DemoListController;
import br.com.faht.javafx.module.JpaModule;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by guilherme.faht on 14/03/2018.
 */
public class AppBoot extends Application {

    private App app;

    @Override
    public void start(Stage stage) throws Exception {

        Locale.setDefault(new Locale("pt","BR"));
        
        app = new App(stage);
        app.registerModule(new JpaModule("persistenceUnitName1"));
        app.registerModule(new JpaModule("persistenceUnitName2"));
        app.registerController(DemoListController.class);
        app.start();
    }

    @Override
    public void stop() throws Exception {

        app.stop();
    }

    public static void main(String[] args) {

        AppBoot.launch(args);
    }
}
