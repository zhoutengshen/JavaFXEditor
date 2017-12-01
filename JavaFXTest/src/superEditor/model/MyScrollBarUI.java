package superEditor.model;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * Created by Administrator on 2017/11/29.
 */
public class MyScrollBarUI extends BasicScrollBarUI {

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
        if(VERTICAL_SCROLLBAR == ORIENTATION){
            // 画一个圆角矩形
            // 这里面前四个参数就不多讲了，坐标和宽高
            // 后两个参数需要注意一下，是用来控制角落的圆角弧度
            g.drawRoundRect(5, 0, 6, thumbBounds.height - 1, 5, 5);
            // 填充圆角矩形
            g2.fillRoundRect(5, 0, 6, thumbBounds.height - 1, 5, 5);
        }//否者绘制水平方向的
        else{
            g.drawRoundRect(0, 5, thumbBounds.width-1, 6, 5, 5);
            g.drawRoundRect(0, 5, thumbBounds.width-1, 6, 5, 5);
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