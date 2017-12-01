package exemple;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawer.DrawerDirection;
import com.jfoenix.controls.JFXDrawersStack;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;

public class DrawerDemo extends Application {

    private static final String LEFT = "LEFT";
    private static final String TOP = "TOP";
    private static final String RIGHT = "RIGHT";
    private static final String BOTTOM = "BOTTOM";

    @Override
    public void start(Stage stage) {
        FlowPane content = new FlowPane();
        JFXButton rightButton = new JFXButton(RIGHT);

        content.getChildren().addAll(rightButton);
        content.setMaxSize(200, 200);

        JFXDrawer rightDrawer = new JFXDrawer();
        StackPane rightDrawerPane = new StackPane();
        rightDrawerPane.getChildren().add(new JFXButton("Right Content"));
        rightDrawer.setDirection(DrawerDirection.RIGHT);
        rightDrawer.setDefaultDrawerSize(150);
        rightDrawer.setSidePane(rightDrawerPane);
        rightDrawer.setOverLayVisible(false);
        rightDrawer.setResizableOnDrag(true);
        JFXDrawersStack drawersStack = new JFXDrawersStack();
        drawersStack.setContent(content);
        rightButton.addEventHandler(MOUSE_PRESSED, e -> drawersStack.toggle(rightDrawer));


        final Scene scene = new Scene(drawersStack, 800, 800);

    }

    public static void main(String[] args) {
        launch(args);
    }

}
