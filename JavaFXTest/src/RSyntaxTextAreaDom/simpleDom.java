package RSyntaxTextAreaDom;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;
import superEditor.dom.DemoScrollBarUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 2017/11/28.
 */
public class simpleDom extends JFrame {
    public simpleDom() {

        JPanel cp = new JPanel(new BorderLayout());

        RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
        //textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);
        textArea.setCurrentLineHighlightColor(Color.decode("#323232"));
        RTextScrollPane sp = new RTextScrollPane(textArea);
        sp.getGutter().setBackground(Color.decode("#313335"));
        JScrollBar bar = sp.getVerticalScrollBar();
        bar.setUI(new DemoScrollBarUI());
        cp.add(sp);
        setContentPane(cp);
        setTitle("Text Editor Demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        textArea.setBackground(Color.decode("#2B2B2B"));

    }

    public static void main(String[] args) {
        // Start all Swing applications on the EDT.
        SwingUtilities.invokeLater(() -> new simpleDom().setVisible(true));
    }
}
