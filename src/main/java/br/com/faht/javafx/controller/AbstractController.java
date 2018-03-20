package br.com.faht.javafx.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.google.inject.Injector;

import br.com.faht.javafx.action.AbstractAction;
import br.com.faht.javafx.event.AbstractEvent;
import br.com.faht.javafx.event.AbstractEventListener;
import br.com.faht.javafx.exception.Reasons;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.MenuItem;

/**
 * Created by guilherme.faht on 14/03/2018.
 */
public abstract class AbstractController implements EventHandler<ActionEvent>, ControllerStarter {
	
	private Injector container;
	
    private AbstractController parent;

    private List<AbstractController> children = new ArrayList<>();

    private Map<String, AbstractAction> actions = new HashMap<>();

	private Map<Class<?>, List<AbstractEventListener<?>>> eventListeners = new HashMap<>();

	
    public void onCreate(AbstractController parent) {

        if (Objects.nonNull(parent)) {
            this.parent = parent;
        }
    }

    protected void registerAction(ButtonBase source, AbstractAction action) {

        registerAction(source.getId(), action);
        source.setOnAction(this);
    }

    protected void registerAction(MenuItem source, AbstractAction action) {

        registerAction(source.getId(), action);
        source.setOnAction(this);
    }

    private void registerAction(String id, AbstractAction action) {

        if (Objects.isNull(id)) {
            throw new RuntimeException(Reasons.NO_ACTION_DEFINED.getDescription());
        }

        this.actions.put(id, action);
    }

    protected void fireEvent(AbstractEvent<?> event) {

        if (Objects.nonNull(eventListeners.get(event.getClass()))) {

            for (AbstractEventListener<?> eventListener : eventListeners.get(event.getClass())) {
                eventListener.handleEvent(event);
            }
        }

        if (Objects.nonNull(parent)) {
            parent.fireEvent(event);
        }
    }

    protected void registerEventListener(Class<?> eventClass, AbstractEventListener<?> eventListener) {

        List<AbstractEventListener<?>> listenersForEvent = eventListeners.get(eventClass);

        if (Objects.isNull(listenersForEvent)) {
            listenersForEvent = new ArrayList<AbstractEventListener<?>>();
        }

        listenersForEvent.add(eventListener);
        eventListeners.put(eventClass, listenersForEvent);
    }

    protected AbstractAction getAction(ActionEvent evt) {

        String actionCommand = null;

        if (evt.getSource() instanceof ButtonBase) {

            ButtonBase button = (ButtonBase) evt.getSource();
            actionCommand = button.getId();

        } else if (evt.getSource() instanceof MenuItem) {

            MenuItem menu = (MenuItem) evt.getSource();
            actionCommand = menu.getId();

        } else {

            throw new RuntimeException(Reasons.INVALID_GENERATOR_EVENT.getDescription());
        }

        return actions.get(actionCommand);
    }

    @Override
    public void handle(ActionEvent evt) {

        try {

            AbstractAction action = getAction(evt);

            if (Objects.nonNull(action)) {
                action.actionPerformend();
            }

        } catch (Exception e) {
            handlerException(e);
        }
    }

    protected void handlerException(Exception e) {

        Alert dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setTitle("Falha na operação");
        dialog.setContentText(e.getMessage());
        dialog.showAndWait();
    }

    public AbstractController getParent() {

        return parent;
    }

    public void onStop() {

        for (AbstractController child : children) {
            child.onStop();
        }
    }
   
    public void setContainer(Injector container) {
    	
    	this.container = container;
    }
    
    public AbstractController run(Class<?> controller) {
    	
    	AbstractController child = (AbstractController) container.getInstance(controller);
    	child.onCreate(this);
    	
    	return child;
    }
}
