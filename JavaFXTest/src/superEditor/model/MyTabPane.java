package superEditor.model;

import com.jfoenix.controls.JFXTabPane;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/30.
 */
public class MyTabPane extends JFXTabPane {

    public List<String> getFilePaths() {
        return filePaths;
    }

    private final List<String> filePaths = new ArrayList<>();

    public ObservableList<String> getOvFilePaths() {
        return ovFilePaths;
    }

    private final ObservableList<String> ovFilePaths = FXCollections.observableList(filePaths);


    private  ObservableList<Tab> ovTabs;
    public MyTabPane() {
        this.setTabClosingPolicy(TabClosingPolicy.ALL_TABS);
        ovTabs = this.getTabs();
        ovFilePaths.addListener((ListChangeListener<String>) c -> {
            //添加了一个路径(添加一个Tab)
            while (c.next()) {

                if (c.wasAdded()) {
                    MyTab tab = new MyTab(c.getList().get(c.getList().size()-1));
                    tab.setOnCloseRequest(e->{
                        ovTabs.remove(tab);
                        ovFilePaths.remove(tab.getFilePathProperty().toString());
                        System.out.print("???");
                    });
                    ovTabs.add(tab);
                    this.getSelectionModel().select(tab);
                }
            }
        });
    }

    public void addTab(String filePath){
       ovFilePaths.add(filePath);
    }

    public void selectTab(int index){
        this.getSelectionModel().select(index);
    }
}
