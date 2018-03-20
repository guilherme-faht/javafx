package br.com.faht.javafx.event;

/**
 * Created by guilherme.faht on 14/03/2018.
 */
public abstract class AbstractEventListener<T extends AbstractEvent<?>> {

	public abstract void handleEvent(AbstractEvent<?> event);

}
