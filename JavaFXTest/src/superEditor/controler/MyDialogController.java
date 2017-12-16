package superEditor.controler;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import superEditor.Main;

/**
 * Created by Administrator on 2017/12/15.
 */
public class MyDialogController {

    public MyDialogController(){
        System.out.print("MyDialogController()");
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    private Main main;

    private Stage dialogState = new Stage();

    public BorderPane borderPane;


    private void init(){
        dialogState.setScene(new Scene(this.borderPane));
        dialogState.setOnCloseRequest(e->{
            this.Hide();
        });
    }

    public void Show(){
        dialogState.show();

    }
    public void Hide(){
        dialogState.hide();
    }

    @FXML
    private void initialize() {
        System.out.print("initialize()");
        init();
    }

}
