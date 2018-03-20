package br.com.faht.javafx.view.component;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.List;

import br.com.faht.javafx.domain.model.Demo;

/**
 * Created by guilherme.faht on 14/03/2018.
 */
public class DemoTable extends VBox {

    private DemoTableView tableView;

    public DemoTable() {

        tableView = new DemoTableView();
        this.getChildren().addAll(tableView);
        this.setPadding(new Insets(5, 5, 5, 5));
    }

    public void reload(List<Demo> demos) {
        tableView.reload(demos);
    }

    public void setMouseEvent(EventHandler<MouseEvent> event) {
        tableView.setOnMouseClicked(event);
    }

    public Demo getDemoSelected() {
        return tableView.getSelectedItem();
    }
}