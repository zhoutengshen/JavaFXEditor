package superEditor.dom;/**
 * Created by Administrator on 2017/11/26.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;

public class Editor extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane pane = new StackPane();
        pane.getChildren().add(textArea);
        Scene scene = new Scene(pane, Color.BLUE);
        primaryStage.setScene(scene);
        primaryStage.show();
        this.loadFile();
    }

    /***
     *
     * @param file与被操作文件相关的File
     */
    public Editor(File file) {
        this.file = file;
        init();
    }

    public Editor() {
        this.file = new File("C:\\Users\\Administrator\\Desktop\\test\\asdh.txt");
        System.out.print("Editor");
    }

    public void init() {
        textArea = new TextArea();

    }


    /***
     * 加载文件
     * 并显示到文本区
     */
   public void loadFile() {
//        File tempFile = this.file;
//        StringBuffer sb = new StringBuffer();//可以更快拼接字符串
//        BufferedReader br = null;
//        try {
//            br = new BufferedReader(new FileReader(tempFile));
//            String tempStr;
//            //读取文本里面的所有内容
//            while ((tempStr = br.readLine()) != null) {
//                sb.append(tempStr);
//                sb.append("\r\n");
//
//            }
//            System.out.println(sb.toString());
//            //加载到文本区
//            textArea.setText(sb.toString());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null)
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//        }
}

    public File file;

    public TextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }

    TextArea textArea;
}
