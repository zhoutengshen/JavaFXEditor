package superEditor;/**
 * Created by Administrator on 2017/11/18.
 */



import javafx.application.Application;
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
import superEditor.controler.MyDialogController;
import superEditor.controler.MyTreeViewController;
import superEditor.controler.RootController;
import superEditor.model.MyTabPane;

import java.io.IOException;

public class Main extends Application {


    //所有控制器
    public MyTreeViewController getMyTreeViewController() {
        return myTreeViewController;
    }
    public void setMyTreeViewController(MyTreeViewController myTreeViewController) {
        this.myTreeViewController = myTreeViewController;
    }
    public MyTabPane getMyTabPane() {
        return myTabPane;
    }
    public void setMyTabPane(MyTabPane myTabPane) {
        this.myTabPane = myTabPane;
    }
    public MyDialogController getMyDialogController() {
        return myDialogController;
    }
    public void setMyDialogController(MyDialogController myDialogController) {
        this.myDialogController = myDialogController;
    }
    public RootController getRootController() {
        return rootController;
    }

    private MyTabPane myTabPane;
    private MyDialogController myDialogController;
    private MyTreeViewController myTreeViewController;
    private RootController rootController;



    public ObservableList<String> getOvFilePaths() {
        return ovFilePaths;
    }
    public void setOvFilePaths(ObservableList<String> ovFilePaths) {
        this.ovFilePaths = ovFilePaths;
    }
    private ObservableList<String> ovFilePaths;


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    private Stage primaryStage;


    public Node getIcon(String iconName) {
        return new ImageView(new Image(this.getClass().getResourceAsStream(iconName)));
    }

    public static void main(String[] args) {
        launch(args);
    }

    public BorderPane dialogPane;


    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;

        //主界面
        Rectangle2D rectangle2D = Screen.getPrimary().getBounds();
        primaryStage.setX(rectangle2D.getMinX());
        primaryStage.setY(rectangle2D.getMinY());
        primaryStage.setWidth(rectangle2D.getWidth());
        primaryStage.setHeight(rectangle2D.getHeight());

        //初始化所所有控制器
        initController();

        primaryStage.show();
    }

    //初始化所有控制器
    private void initController() throws IOException {


        //初始化MyPane
        myTabPane = new MyTabPane();
        ovFilePaths = myTabPane.getOvFilePaths();
        /* / \  *
         *  |   *
         *  |   *
         *依赖  *
         * ******/
        //初始化root控制器
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/root.fxml"));
        BorderPane root = loader.load();
        Scene scene = new Scene(root);
        this.primaryStage.setScene(scene);
        rootController = loader.getController();
        rootController.setMain(this);
        rootController.init();


        //初始化//TreeView
        myTreeViewController = new MyTreeViewController(this, "./");


    }

}
