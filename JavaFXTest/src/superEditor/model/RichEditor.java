package superEditor.model;/**
 * Created by Administrator on 2017/11/28.
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.embed.swing.SwingNode;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class RichEditor extends RSyntaxTextArea {

    public String getFilePathProperty() {
        return filePathProperty.get();
    }

    public StringProperty filePathPropertyProperty() {
        return filePathProperty;
    }

    public void setFilePathProperty(String filePathProperty) {
        this.filePathProperty.set(filePathProperty);
    }

    public File getFile() {
        return file;
    }

    private final StringProperty filePathProperty = new SimpleStringProperty();

    public RichEditor(String filePath) {
        super();

        //当路径发生改变时触发（更新内容）
        filePathProperty.addListener((ov, oldVal, newVal) -> {
            //System.out.print(newVal);
            if (newVal != null && !newVal.equals(oldVal)) {
                this.file = new File(newVal);
                this.loadFile();
            }
        });

        filePathProperty.setValue(filePath);
        setStyle();
    }

    protected void setStyle() {
        this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        this.setCodeFoldingEnabled(true);//代码折叠

        //设置字体
        this.setFont(new Font("Consolas 宋体 楷体", Font.PLAIN, 25));
        //设置背景颜色，包括行号区
        this.setBackground(Color.decode("#2B2B2B"));
        //光标所在行的颜色
        this.setCurrentLineHighlightColor(Color.decode("#323232"));
        //设置光标的颜色
        this.setCaretColor(Color.decode("#BABABA"));
        //前景色
        this.setForeground(Color.decode("#BABABA"));
        //选中是的颜色
        this.setSelectionColor(Color.decode("#214283"));
        //边框线颜色
        this.setMarginLineColor(Color.gray);

        //用于给文本区添加滚动条pane
        RTextScrollPane sp = new RTextScrollPane(this);
        //垂直滚动条
        JScrollBar vsb = sp.getVerticalScrollBar();
        //设置滚动条的样式，
        //VerticalScrollBarUI重写了BasicScrollBarUI基类的四个函数，现实更换样式
        //参数是一个描述滚动条的int变量，水平方向和垂直方向
        MyScrollBarUI vsbUI = new MyScrollBarUI(MyScrollBarUI.VERTICAL_SCROLLBAR);
        vsb.setUI(vsbUI);

        JScrollBar hsb = sp.getHorizontalScrollBar();
        MyScrollBarUI hsbUI = new MyScrollBarUI(MyScrollBarUI.HORIZONTAL_SCROLLBAR);
        hsb.setUI(hsbUI);

        createSwingContent(textNode, sp);

    }

    public SwingNode getTextNode() {
        return textNode;
    }

    private File file;
    private final SwingNode textNode = new SwingNode();

    private void createSwingContent(final SwingNode textNode, final RTextScrollPane sp) {

        //将基于Swing的RSyntaxTextArea转换成和JavaFx兼容的节点
        SwingUtilities.invokeLater(() -> textNode.setContent(sp));
    }

    private void loadFile() {
        File tempFile = file;
        StringBuffer sb = new StringBuffer();//可以更快拼接字符串
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(tempFile));
            String tempStr;
            //读取文本里面的所有内容
            while ((tempStr = br.readLine()) != null) {
                sb.append(tempStr);
                sb.append("\r\n");

            }
            //加载到文本区
            this.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public void saveFile() {
    }
}
