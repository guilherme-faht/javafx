package br.com.faht.javafx.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import br.com.faht.javafx.action.AbstractAction;
import br.com.faht.javafx.domain.model.Demo;
import br.com.faht.javafx.view.DemoCodeListView;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Created by guilherme.faht on 14/03/2018.
 */
public class DemoListController extends AbstractController {
		
    private DemoCodeListView view;
    
    
    @Inject
    public DemoListController(final Stage stage, @Named("persistenceUnitName1") EntityManager em1, @Named("persistenceUnitName2") EntityManager em2) {
            	    	    	
        view = new DemoCodeListView(stage);

        registerAction(view.getNewButton(), new AbstractAction() {

            @Override
            protected void action() {
            	System.out.println(em1.createNativeQuery("SELECT 1").getSingleResult());
            }
        });

        registerAction(view.getFindButton(), new AbstractAction() {

            @Override
            protected void action() {
            	run(DemoListController2.class);
            }
        });

        registerAction(view.getRefreshButton(), new AbstractAction() {

            @Override
            protected void action() {

                refreshTable();
            }
        });

        registerAction(view.getMenuAbout(), new AbstractAction() {

            @Override
            protected void action() {
                // todo
            }
        });

        view.getTable().setMouseEvent(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                if(event.getClickCount() == 2) {

                    Demo demo = view.getTable().getDemoSelected();

                    if(Objects.nonNull(demo)) {
                        // todo
                    }
                }
            }
        });

        refreshTable();
    }

    private void refreshTable() {

        view.addTransition();

        Platform.runLater(new Runnable() {

            @Override
            public void run() {

                List<Demo> demos = new ArrayList<>();
                demos.add(new Demo().setId(1).setName("Demo A"));
                demos.add(new Demo().setId(2).setName("Demo B"));
                demos.add(new Demo().setId(3).setName("Demo C"));
                demos.add(new Demo().setId(4).setName("Demo D"));

                view.refreshTable(demos);
            }
        });
    }
}
