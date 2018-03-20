package br.com.faht.javafx.action;

/**
 * Created by guilherme.faht on 14/03/2018.
 */
public abstract class AbstractAction {

    protected abstract void action();

    protected void preAction() {};

    protected void postAction() {};

    protected void actionFailure() {};

    public void actionPerformend() {

        try {

            preAction();
            action();
            postAction();

        } catch (RuntimeException e) {

            actionFailure();

            throw e;
        }

    }

}
