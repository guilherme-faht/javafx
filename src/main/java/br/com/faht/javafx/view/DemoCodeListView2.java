package br.com.faht.javafx.view;

import java.util.List;

import br.com.faht.javafx.domain.model.Demo;
import br.com.faht.javafx.view.component.DemoTable;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by guilherme.faht on 14/03/2018.
 */
public class DemoCodeListView2 extends Stage {

    private Scene mainScene;

    private DemoTable table;

    private Button btnNew;

    private Button btnRefresh;

    private Button btnFind;

    private MenuItem menuAbout;


    public DemoCodeListView2() {

        onCreate();

        VBox panel = new VBox();
        mainScene = new Scene(panel);
        mainScene.getStylesheets().add("style.css");

        HBox boxButtons = getButtonsBox();
        boxButtons.setPadding(new Insets(5, 5, 5, 5));

        panel.getChildren().addAll(boxButtons, table);

        setTitle("Lista Demo");
        setHeight(600);
        setWidth(800);
        setScene(mainScene);
        setResizable(false);
        centerOnScreen();
        initModality(Modality.APPLICATION_MODAL);
        show();
    }

    private void onCreate() {

        table = new DemoTable();

        btnNew = new Button("Novo");
        btnNew.getStyleClass().add("buttonGreen");
        btnNew.setId("new");

        btnFind = new Button("Buscar");
        btnFind.getStyleClass().add("buttonLarge");
        btnFind.setId("find");

        btnRefresh = new Button("Atualizar");
        btnRefresh.getStyleClass().add("buttonWhite");
        btnRefresh.setId("refresh");
    }

    public void addTransition() {

        disableButtonBar(true);

        FadeTransition transition = new FadeTransition(Duration.millis(2000), table);
        transition.setFromValue(0.2);
        transition.setToValue(1);
        transition.setAutoReverse(true);
        transition.play();

        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                disableButtonBar(false);
            }
        });
    }

    private HBox getButtonsBox() {

        HBox box = new HBox();
        box.getChildren().addAll(btnNew, btnFind, btnRefresh);
        box.getStyleClass().add("buttonBarMain");

        return box;
    }

    public Button getNewButton() {

        return btnNew;
    }

    public Button getRefreshButton() {

        return btnRefresh;
    }

    public Button getFindButton() {

        return btnFind;
    }

    public DemoTable getTable() {

        return table;
    }

    public MenuItem getMenuAbout() {

        return menuAbout;
    }

    public void refreshTable(List<Demo> demos) {

        table.reload(demos);
    }

    private void disableButtonBar(boolean disable) {

        btnNew.setDisable(disable);
        btnFind.setDisable(disable);
        btnRefresh.setDisable(disable);
    }

}
