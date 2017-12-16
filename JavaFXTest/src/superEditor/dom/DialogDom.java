package superEditor.dom;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * Created by Administrator on 2017/12/16.
 */
public class DialogDom extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        JFXDialog dialog = new JFXDialog();
        dialog.setPrefSize(100,100);
        dialog.setContent(new Region());
        JFXButton btn = new JFXButton("CLick");
        btn.setOnAction(e->{
            dialog.show();
        });
        primaryStage.setScene(new Scene(btn));
        primaryStage.show();
    }
}
