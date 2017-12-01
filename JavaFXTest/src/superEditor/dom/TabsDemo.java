package superEditor.dom;

import com.jfoenix.controls.JFXTabPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import superEditor.model.RichEditor;

import java.security.SecureRandom;

public class TabsDemo extends Application {

    private static final String TAB_0 = "Tab 0";
    private static final String TAB_01 = "Tab 01";
    private static final String msg = "我是"+TAB_0;
    private final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


        primaryStage.setTitle("Tabs");

        JFXTabPane tabPane = new JFXTabPane();
        Tab tab = new Tab();
        tab.setText(msg);
        RichEditor editor = new RichEditor(null);
        tab.setContent(editor.getTextNode());
        tabPane.setStyle("-fx-background-color: #2B2B2B");
        tab.setStyle("-fx-background-color:#2B2B2B");
        tabPane.getTabs().add(tab);
        tabPane.setPrefHeight(500);
        Tab tab1 = new Tab();
        tab1.setText(TAB_01);
        RichEditor editor1 = new RichEditor(null);
        tab1.setContent(editor1.getTextNode());
        tabPane.getTabs().add(tab1);

        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();

        MenuBar bar = new MenuBar();
        Menu menu = new Menu();
        menu.setText("Click_ME");
        MenuItem item = new MenuItem("New a Tab");
        item.setOnAction((o)->{
            Tab temp = new Tab();

            int count = tabPane.getTabs().size();
            temp.setText(msg + count);
            RichEditor tempEditor = new RichEditor("C:\\Users\\Administrator\\Desktop\\test\\asdh.txt");
            //tempEditor.loadFile();
            temp.setContent(tempEditor.getTextNode());
            tabPane.getTabs().add(temp);
            tabPane.getSelectionModel().select(temp);
        });
        menu.getItems().add(item);
        MenuItem item1 = new MenuItem("item1");
        item1.setOnAction(event -> {
            System.out.print(".....");
            editor.setFilePathProperty("C:\\Users\\Administrator\\Desktop\\ubantuNode环境搭建.txt");
        });
        menu.getItems().add(item1);
        bar.getMenus().add(menu);
        BorderPane root = new BorderPane();
        root.setTop(bar);
        root.setCenter(tabPane);
        Scene scene = new Scene(root, 700, 500);
        //scene.getStylesheets().add(TabsDemo.class.getResource("css/jfoenix-components.css").toExternalForm());


        primaryStage.setFullScreen(true);
        primaryStage.setTitle("JFX Tabs Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
