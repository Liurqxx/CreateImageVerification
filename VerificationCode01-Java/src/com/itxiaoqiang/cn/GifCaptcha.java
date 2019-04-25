package com.itxiaoqiang.cn;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Gif��֤����
 */
public class GifCaptcha extends Captcha {

	public GifCaptcha() {
	}

	public GifCaptcha(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	public GifCaptcha(int width, int height, int len) {
		this(width, height);
		setLen(len);
	}

	public GifCaptcha(int width, int height, int len, Font font) {
		this(width, height, len);
		setFont(font);
	}

	@Override
	public boolean out(OutputStream os) {
		checkAlpha();
		boolean ok;
		try {
			char[] rands = textChar(); // ��ȡ��֤������
			GifEncoder gifEncoder = new GifEncoder();
			gifEncoder.start(os);
			gifEncoder.setQuality(180);
			gifEncoder.setDelay(100);
			gifEncoder.setRepeat(0);
			BufferedImage frame;
			Color fontcolor[] = new Color[len];
			for (int i = 0; i < len; i++) {
				fontcolor[i] = new Color(20 + num(110), 20 + num(110),
						20 + num(110));
			}
			for (int i = 0; i < len; i++) {
				frame = graphicsImage(fontcolor, rands, i);
				gifEncoder.addFrame(frame);
				frame.flush();
			}
			gifEncoder.finish();
			ok = true;
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ok;
	}

	/**
	 * �������ͼ
	 * 
	 * @param fontcolor
	 *            ���������ɫ
	 * @param strs
	 *            �ַ�����
	 * @param flag
	 *            ͸����ʹ��
	 * @return BufferedImage
	 */
	private BufferedImage graphicsImage(Color[] fontcolor, char[] strs, int flag) {
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		// ��䱳����ɫ
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, width, height);
		// �����
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(new BasicStroke(1.3f, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_BEVEL));
		// ������ԲȦ
		drawOval(4, g2d);
		// �����������
		drawLine(2, g2d);
		// ����֤��
		int hp = (height - font.getSize()) >> 1;
		int h = height - hp;
		int w = width / strs.length;
		int sp = (w - font.getSize()) / 2;
		for (int i = 0; i < strs.length; i++) {
			AlphaComposite ac3 = AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, getAlpha(flag, i));
			g2d.setComposite(ac3);
			g2d.setColor(fontcolor[i]);
			// ��������
			int x = i * w + sp + num(3);
			int y = h - num(3, 6);
			// �����������
			if (x < 8) {
				x = 8;
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
			g2d.setFont(font.deriveFont(num(2) == 0 ? Font.PLAIN : Font.ITALIC));
			g2d.drawString(String.valueOf(strs[i]), x, y);
		}
		g2d.dispose();
		return image;
	}

	/**
	 * ��ȡ͸����,��0��1,�Զ����㲽��
	 * 
	 * @param i
	 * @param j
	 * @return ͸����
	 */
	private float getAlpha(int i, int j) {
		int num = i + j;
		float r = (float) 1 / (len - 1);
		float s = len * r;
		return num >= len ? (num * r - s) : num * r;
	}

}
