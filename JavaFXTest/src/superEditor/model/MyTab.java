package superEditor.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Tab;

/**
 * Created by Administrator on 2017/11/30.
 */
public class MyTab extends Tab {

    private final RichEditor editor = new RichEditor(null);

    public RichEditor getEditor() {
        return editor;
    }

    private final StringProperty filePathProperty = new SimpleStringProperty();

    public String getFilePathProperty() {
        return filePathProperty.get();
    }

    public StringProperty filePathPropertyProperty() {
        return filePathProperty;
    }

    public void setFilePathProperty(String filePathProperty) {
        this.filePathProperty.set(filePathProperty);
    }


    public MyTab(String filePath){
        super();


        this.setOnClosed(e->{
            editor.saveFile();
            System.out.print("保存文件.....");
        });
        //双向绑定
        filePathProperty.bindBidirectional(editor.filePathPropertyProperty());
        filePathProperty.set(filePath);
        this.setContent(editor.getTextNode());
        this.setText(editor.getFile().getName());
    }



}
