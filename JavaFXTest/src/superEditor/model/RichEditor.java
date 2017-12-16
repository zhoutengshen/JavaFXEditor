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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class RichEditor extends RSyntaxTextArea {

    public boolean textHasChange = false;

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

    int index = 0;

    public RichEditor(String filePath) {
        super();

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
                    saveFile();

                }
            }
        });

        this.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                textHasChange = true;
            }
        });

        //当路径发生改变时触发（更新内容）
        filePathProperty.addListener((ov, oldVal, newVal) -> {
            //System.out.print(newVal);
            if (newVal != null && !newVal.equals(oldVal)) {
                this.file = new File(newVal);
                try {
                    this.loadFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    private boolean isLoadingFile = false;

    private void loadFile() throws IOException {
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

        isLoadingFile = true;

    }


    public void saveFile() {

        if (this.file == null)
            return;


        FileWriter writer = null;
        try {
            writer = new FileWriter(this.file);
            String txt = this.getText();
            writer.write(txt);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        textHasChange = false;

    }


    /**
     * Created by Administrator on 2017/11/29.
     */
    public static class MyScrollBarUI extends BasicScrollBarUI {

        public static final int VERTICAL_SCROLLBAR = 1;
        public static final int HORIZONTAL_SCROLLBAR = 2;

        //滚动条方向,默认垂直方向
        private int ORIENTATION = 1;

        /**
         * 表明是垂直还是水平位置的滚动条
         *
         * @param orientation 滚动条方向，默认垂直方向
         */
        public MyScrollBarUI(int orientation) {
            ORIENTATION = orientation;
        }

        @Override
        protected void configureScrollBarColors() {

            // 滑道
            trackColor = Color.decode("#2B2B2B");
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            super.paintTrack(g, c, trackBounds);

        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            // 把绘制区的x，y点坐标定义为坐标系的原点
            // 这句一定一定要加上啊，不然拖动就失效了
            g.translate(thumbBounds.x, thumbBounds.y);
            // 设置把手颜色
            g.setColor(Color.decode("#3C3F41"));

            // 消除锯齿
            Graphics2D g2 = (Graphics2D) g;
            RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.addRenderingHints(rh);
            // 半透明
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            // 设置填充颜色，这里设置了渐变，由下往上
            //g2.setPaint(new GradientPaint(c.getWidth() / 2, 1, Color.GRAY, c.getWidth() / 2, c.getHeight(), Color.GRAY));

            //绘制垂直方向的滚动条
            if (VERTICAL_SCROLLBAR == ORIENTATION) {
                // 画一个圆角矩形
                // 这里面前四个参数就不多讲了，坐标和宽高
                // 后两个参数需要注意一下，是用来控制角落的圆角弧度
                g.drawRoundRect(5, 0, 6, thumbBounds.height - 1, 5, 5);
                // 填充圆角矩形
                g2.fillRoundRect(5, 0, 6, thumbBounds.height - 1, 5, 5);
            }//否者绘制水平方向的
            else {
                g.drawRoundRect(0, 5, thumbBounds.width - 1, 6, 5, 5);
                g.drawRoundRect(0, 5, thumbBounds.width - 1, 6, 5, 5);
            }
        }

        @Override
        protected void paintDecreaseHighlight(Graphics g) {
            super.paintDecreaseHighlight(g);
        }

        @Override
        protected void paintIncreaseHighlight(Graphics g) {
            super.paintIncreaseHighlight(g);
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            JButton button = new JButton();
            button.setBackground(Color.decode("#2B2B2B"));
            button.setBorder(null);
            return button;
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            JButton button = new JButton();
            button.setBackground(Color.decode("#2B2B2B"));
            button.setBorder(null);
            return button;
        }

    }
}
