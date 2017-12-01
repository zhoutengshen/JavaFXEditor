package superEditor.dom;/**
 * Created by Administrator on 2017/11/30.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import superEditor.model.MyTabPane;

public class MyTabPaneDome extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MyTabPane tabPane = new MyTabPane();
        tabPane.addTab("C:\\Users\\Administrator\\Desktop\\新建文本文档.txt");
        tabPane.addTab("C:\\Users\\Administrator\\Desktop\\ubantuNode环境搭建.txt");
        //tabPane.removeTab("C:\\Users\\Administrator\\Desktop\\新建文本文档.txt");
        primaryStage.setScene(new Scene(tabPane));
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}
