package br.com.faht.javafx.view.component;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Data;

import java.util.List;
import java.util.Objects;

import br.com.faht.javafx.domain.model.Demo;

/**
 * Created by guilherme.faht on 14/03/2018.
 */
public class DemoTableView extends TableView<DemoTableView.DemoItem> {

    private ObservableList<DemoItem> demos;

    public DemoTableView() {

        TableColumn<DemoItem, String> idCol = new TableColumn<>("Código");
        idCol.setMinWidth(200);
        idCol.setCellValueFactory(new PropertyValueFactory<DemoItem, String>("id"));

        TableColumn<DemoItem, String> idName = new TableColumn<>("Nome");
        idCol.setMinWidth(400);
        idCol.setCellValueFactory(new PropertyValueFactory<DemoItem, String>("name"));

        demos = FXCollections.observableArrayList();
        setItems(demos);

        getColumns().addAll(idCol, idName);
    }

    public void reload(final List<Demo> demos) {

        this.demos.clear();

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                for (Demo demo : demos) {
                    DemoItem item = new DemoItem(demo);
                    DemoTableView.this.demos.add(item);
                }
            }

        });
    }

    public Demo getSelectedItem() {

        DemoItem item = getSelectionModel().getSelectedItem();

        if (Objects.nonNull(item)) {
            return item.toDemo();
        }

        return null;
    }

    @Data
    public static class DemoItem {

        private final SimpleStringProperty id;

        private final SimpleStringProperty name;


        private DemoItem(Demo demo) {

            id = new SimpleStringProperty(String.valueOf(demo.getId()));
            name = new SimpleStringProperty(demo.getName());
        }

        public Demo toDemo() {

            return new Demo()
                    .setId(Integer.valueOf(id.get()))
                    .setName(name.get());
        }
    }
}
