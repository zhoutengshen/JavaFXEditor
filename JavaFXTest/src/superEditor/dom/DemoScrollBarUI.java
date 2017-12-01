package superEditor.dom;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * 自定义滚动条UI
 *
 * @author SongFei
 * @date 2015年5月18日
 */
public class DemoScrollBarUI extends BasicScrollBarUI {

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
        g.setColor(Color.decode("#3C3F42"));
        // 画一个圆角矩形
        // 这里面前四个参数就不多讲了，坐标和宽高
        // 后两个参数需要注意一下，是用来控制角落的圆角弧度
        g.drawRoundRect(5, 0, 6, thumbBounds.height - 1, 5, 5);
        // 消除锯齿
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.addRenderingHints(rh);
        // 半透明
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        // 设置填充颜色，这里设置了渐变，由下往上
        // g2.setPaint(new GradientPaint(c.getWidth() / 2, 1, Color.GRAY, c.getWidth() / 2, c.getHeight(), Color.GRAY));
        // 填充圆角矩形
        g2.fillRoundRect(5, 0, 6, thumbBounds.height - 1, 5, 5);
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

/**
 * DemoScrollBarUI的测试类
 *
 */
class ScrollBarUITest extends JFrame{
    public static void main(String[] args) {
        ScrollBarUITest test = new ScrollBarUITest();
        test.setVisible(true);
    }

    public ScrollBarUITest(){
        this.setSize(300,400);
        this.setBackground(Color.decode("#2B2B2B"));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        DemoScrollBarUI demoScrollBarUI = new DemoScrollBarUI();
        JPanel panel = new JPanel();
        panel.add(new JButton("Click"));
        panel.add(new JButton("Click"));
        JScrollBar bar = new JScrollBar();
        bar.setUI(demoScrollBarUI);
        panel.add(bar);
        add(panel);

    }
}

