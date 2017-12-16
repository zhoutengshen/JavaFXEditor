package superEditor.controler;

import com.jfoenix.controls.JFXTreeView;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import superEditor.Main;

import java.io.File;

public class MyTreeViewController {

    Main main;
    public String path;
    public MyTreeViewController(Main main, String path) {
        this.path = path;
        this.main  = main;
    }


    public void addTab(File file) {
        if (main.getMyTabPane().getOvFilePaths() != null) {
            int index = main.getMyTabPane().getOvFilePaths().indexOf(file.getPath());
            if (index == -1 && file.isFile()) {
                main.getMyTabPane().getOvFilePaths().add(file.getPath());
            } else {
                main.getMyTabPane().selectTab(index);
            }
        }
    }

    public void buildTree(StackPane stackPane){
        File file = new File(path);
        TreeView<File> treeView = new JFXTreeView<>(new MyTreeItem(file));
        treeView.setCellFactory(new Callback<TreeView<File>, TreeCell<File>>() {
            @Override
            public TreeCell<File> call(TreeView<File> arg0) {
                return new TreeCell<File>(){

                    @Override
                    protected void updateItem(File f, boolean empty) {
                        // TODO Auto-generated method stub
                        super.updateItem(f, empty);
                        if(empty){
                            setText(null);
                            setGraphic(null);
                        }else{
                            setText(f.getName());
                            if(f.isDirectory()){
                                setGraphic(main.getIcon("img/folder.png"));
                            }else if(f.isFile()){
                                setGraphic(main.getIcon("img/document.png"));
                                addEventHandler(MouseEvent.MOUSE_CLICKED,event -> {
                                    TreeCell<File> treeCell = (TreeCell) event.getSource();
                                    File tempFile = treeCell.getTreeItem().getValue();
                                    addTab(tempFile);

                                });
                            }else if(f.getName().endsWith("txt")){
                                setStyle("-fx-font-weight:BOLD");
                            }else{
                                setStyle("-fx-text-fill:#cccccc");
                            }
                        }
                    }
                };
            }
        });
        stackPane.getChildren().add(treeView);
        HBox.setHgrow(treeView, Priority.ALWAYS);
    }

    class MyTreeItem extends TreeItem<File> {
        private boolean notInitialized = true;

        public File getFile() {
            return file;
        }

        private File file;

        public MyTreeItem(final File file) {
            super(file);
        }

        @Override
        public ObservableList<TreeItem<File>> getChildren() {
            if (notInitialized) {
                notInitialized = false;
                if (getValue().isDirectory()) {
                    for (final File file : getValue().listFiles()) {
                        super.getChildren().add(new MyTreeItem(file));
                    }
                }
            }
            return super.getChildren();
        }

        @Override
        public boolean isLeaf() {
            return !getValue().isDirectory();
        }
    }
}
