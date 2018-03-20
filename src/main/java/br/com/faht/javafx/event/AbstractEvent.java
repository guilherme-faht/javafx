package br.com.faht.javafx.event;

/**
 * Created by guilherme.faht on 14/03/2018.
 */
public abstract class AbstractEvent<Target> {

    private Target target;

    public AbstractEvent(Target target) {
        this.target = target;
    }

    public Target getTarget() {
        return target;
    }
}
