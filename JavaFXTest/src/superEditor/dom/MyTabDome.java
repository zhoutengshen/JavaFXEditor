package superEditor.dom;/**
 * Created by Administrator on 2017/12/1.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import superEditor.model.MyTab;
import superEditor.model.MyTabPane;

public class MyTabDome extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MyTab tab = new MyTab("C:\\Users\\Administrator\\Desktop\\新建文本文档.txt");
        MyTab tab1 = new MyTab("C:\\Users\\Administrator\\Desktop\\ubantuNode环境搭建.txt");
        TabPane tabPane = new MyTabPane();
        tabPane.getTabs().addAll(tab,tab1);
        Scene scene = new Scene(tabPane);
        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
