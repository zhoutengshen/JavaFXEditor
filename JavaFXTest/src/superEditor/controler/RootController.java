package superEditor.controler;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import superEditor.Main;

import java.io.IOException;

/**
 * Created by Administrator on 2017/11/18.
 */
public class RootController {
    public SplitPane splitPane;
    public StackPane splitLeft;
    public StackPane spliRight;
    public MenuBar menuBar;
    public MenuItem newMenuItem;
    public MenuItem closeMenuItem;
    public MenuItem openMenuItem;

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


    @FXML
    private JFXButton btnFind;
    @FXML
    private JFXButton btnFolder;
    @FXML
    private JFXButton btnSetting;

    private Main main;


    //资源管理器的树形目录
    MyTreeViewController myTreeViewController;

    MyDialogController myDialogController;

    public void init() {
        myTreeViewController = new MyTreeViewController(main, ".");
        treePane = new StackPane();
        myTreeViewController.buildTree(treePane);//目录绑定到Pane里。然后把panne放到父容器

        openMenuItem.setOnAction(e -> {

            //加载新的目录，把u、原来的目录移除
            if (splitLeft.getChildren().size() > 0)
                splitLeft.getChildren().remove(0);
            //从新加载新目录
            myTreeViewController = new MyTreeViewController(main, "C:\\");
            treePane = new StackPane();
            myTreeViewController.buildTree(treePane);
            //刷新
            showTogglePane();
            treeViewHasLoaded = false;
            showTogglePane();


            if (myDialogController == null) {
                myDialogController = main.getMyDialogController();
            }
            myDialogController.Show();
        });

        spliRight.getChildren().add(main.getMyTabPane());
    }

    @FXML
    private void initialize() {
        //设置Splitpane的item在SplitPane大小发生变化时是否变换
        SplitPane.setResizableWithParent(splitPane.getItems().get(0), false);

    }


    private int togglePaneSize = 200;

    public void mousePress(MouseEvent mouseEvent) throws IOException {

        //始终使item1的大小为200
        showTogglePane();

    }


    private StackPane treePane;
    private boolean treeViewHasLoaded = false;

    private void showTogglePane() {
        if (toolglePanstatu) {
            splitPane.setDividerPosition(0, togglePaneSize / this.main.getPrimaryStage().getWidth());
            toolglePanstatu = !toolglePanstatu;
        } else {
            splitPane.setDividerPosition(0, 0);
            toolglePanstatu = !toolglePanstatu;
        }
        if (!treeViewHasLoaded) {
            splitLeft.getChildren().add(0, treePane);
            treeViewHasLoaded = !treeViewHasLoaded;
        }
    }


}
