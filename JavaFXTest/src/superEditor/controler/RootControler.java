package superEditor.controler;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import superEditor.Main;
import superEditor.model.MyTreeView;

import java.io.IOException;

/**
 * Created by Administrator on 2017/11/18.
 */
public class RootControler {
    public SplitPane splitPane;
    public StackPane splitLeft;
    public StackPane spliRight;
    private boolean toolglePanstatu = true;

    public JFXButton getBtnFind() {
        return btnFind;
    }

    public void setBtnFind(JFXButton btnFind) {
        this.btnFind = btnFind;
    }


    public JFXButton getBtnFolder() {
        return btnFolder;
    }

    public void setBtnFolder(JFXButton btnFolder) {
        this.btnFolder = btnFolder;
    }

    public JFXButton getBtnSetting() {
        return btnSetting;
    }

    public void setBtnSetting(JFXButton btnSetting) {
        this.btnSetting = btnSetting;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private JFXButton btnFind;
    @FXML
    private JFXButton btnFolder;
    @FXML
    private JFXButton btnSetting;

    private Main main;

    private Stage stage;

    public RootControler() {
    }

    //资源管理器的树形目录
    MyTreeView viewItem;
    public void init(){
        viewItem = new MyTreeView(main, "C:\\Users\\Administrator\\Desktop\\TreeDirManager");
        treePane = new StackPane();
        viewItem.buildTree(treePane);
    }

    @FXML
    private void initialize() {
        //设置Splitpane的item在SplitPane大小发生变化时是否变换
        SplitPane.setResizableWithParent(splitPane.getItems().get(0), false);

    }


    private int tooglePaneSize = 200;
    public void mousePress(MouseEvent mouseEvent) throws IOException {

        if (toolglePanstatu)
            //始终使item1的大小为200
            showToolglePane();
        else{
            hideToolglePane();
        }

    }

    StackPane treePane;
    public void showToolglePane(){
        splitPane.setDividerPosition(0, tooglePaneSize / stage.getWidth());
        toolglePanstatu = !toolglePanstatu;

        splitLeft.getChildren().add(0,treePane);
    }

    public void hideToolglePane(){
        splitPane.setDividerPosition(0, 0);
        toolglePanstatu = !toolglePanstatu;
    }

}
