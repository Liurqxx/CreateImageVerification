package com.itxiaoqiang.cn;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

public class ChineseCaptcha extends ChineseCaptchaAbstract {

    public ChineseCaptcha() {
        super();
    }

    public ChineseCaptcha(int width, int height) {
        this();
        setWidth(width);
        setHeight(height);
    }

    public ChineseCaptcha(int width, int height, int len) {
        this(width, height);
        setLen(len);
    }

    public ChineseCaptcha(int width, int height, int len, Font font) {
        this(width, height, len);
        setFont(font);
    }

    /**
     * ������֤��
     *
     * @param out �����
     * @return �Ƿ�ɹ�
     */
    @Override
    public boolean out(OutputStream out) {
        checkAlpha();
        return graphicsImage(textChar(), out);
    }

    /**
     * ������֤��ͼ��
     *
     * @param strs ��֤��
     * @param out  �����
     * @return boolean
     */
    private boolean graphicsImage(char[] strs, OutputStream out) {
        boolean ok;
        try {
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D) bi.getGraphics();
            AlphaComposite ac3;
            int len = strs.length;
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, width, height);
            // �����
            g.setColor(color());
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int hp = (height - font.getSize()) >> 1;
            int h = height - hp;
            int w = width / strs.length;
            int sp = (w - font.getSize()) / 2;
            // ���ַ���
            for (int i = 0; i < len; i++) {
                // ��������
                int x = i * w + sp + num(-5, 5);
                int y = h + num(-5, 5);
                if (x < 5) {
                    x = 5;
                }
                if (x + font.getSize() > width) {
                    x = width - font.getSize();
                }
                if (y > height) {
                    y = height;
                }
                if (y - font.getSize() < 0) {
                    y = font.getSize();
                }
                g.setFont(font.deriveFont(num(2) == 0 ? Font.PLAIN : Font.ITALIC));
                g.drawString(String.valueOf(strs[i]), x, y);
            }
            // �����������
            g.setStroke(new BasicStroke(1.25f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
            ac3 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);  // ָ��͸����
            g.setComposite(ac3);
            drawLine(2, g.getColor(), g);
            // ������ԲȦ
            drawOval(5, g.getColor(), g);
            ImageIO.write(bi, "png", out);
            out.flush();
            ok = true;
        } catch (IOException e) {
            ok = false;
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ok;
    }
}
