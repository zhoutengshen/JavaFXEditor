package superEditor.dom;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 * Created by Administrator on 2017/12/1.
 */
public class DefaultTabPane extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public DefaultTabPane() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);

        tabPane.getTabs().add(new Tab("1"));
        tabPane.getTabs().add(new Tab("2"));
        tabPane.getTabs().add(new Tab("3"));
        Tab tab = new Tab("4");
        primaryStage.setScene(new Scene(tabPane));
        primaryStage.show();
    }
}
