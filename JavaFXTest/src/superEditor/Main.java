package superEditor;/**
 * Created by Administrator on 2017/11/18.
 */

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import superEditor.controler.RootControler;
import superEditor.model.MyTabPane;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {


    private ObservableList<String> ovFilePaths;
    MyTabPane myTabPane;

    public void addTab(String filePath) {
        if (ovFilePaths != null) {
            int index = ovFilePaths.indexOf(filePath);
            if ( index== -1) {
                ovFilePaths.add(filePath);
            }else{
                myTabPane.selectTab(index);
            }
        }
    }

    private Stage primatiStage;
    private BorderPane root;

    public RootControler getRootControler() {
        return rootControler;
    }

    private RootControler rootControler;

    public Node getIcon(String iconName) {
        return new ImageView(new Image(this.getClass().getResourceAsStream(iconName)));
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primatiStage = primaryStage;
        Rectangle2D rectangle2D = Screen.getPrimary().getBounds();
        primaryStage.setX(rectangle2D.getMinX());
        primaryStage.setY(rectangle2D.getMinY());
        primaryStage.setWidth(rectangle2D.getWidth());
        primaryStage.setHeight(rectangle2D.getHeight());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/root.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        rootControler = loader.getController();
        rootControler.setMain(this);
        rootControler.setStage(primaryStage);
        rootControler.init();
        primaryStage.show();


        myTabPane = new MyTabPane();
        ovFilePaths = myTabPane.getOvFilePaths();

        rootControler.spliRight.getChildren().add(myTabPane);
    }

}
