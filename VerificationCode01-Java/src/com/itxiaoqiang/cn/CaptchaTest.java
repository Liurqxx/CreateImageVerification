package com.itxiaoqiang.cn;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 测试类 Created
 */
public class CaptchaTest {

	@Test
	public void test() throws Exception {

		for (int i = 0; i < 5; i++) {
			SpecCaptcha specCaptcha = new SpecCaptcha();
			// 纯大写字母
			specCaptcha.setCharType(Captcha.TYPE_ONLY_UPPER);
			System.out.println(specCaptcha.text());
			specCaptcha.out(new FileOutputStream(new File(
					"C:\\Users\\hello_liu\\Desktop\\java\\aa" + i + ".png")));
		}

	}

	@Test
	public void testGIf() throws Exception {

		for (int i = 0; i < 5; i++) {
			// GIf图
			GifCaptcha gifCaptcha = new GifCaptcha();
			System.out.println(gifCaptcha.text());
			gifCaptcha.out(new FileOutputStream(new File(
					"C:\\Users\\hello_liu\\Desktop\\java\\cc" + i + ".gif")));
		}

	}

	@Test
	public void testHan() throws Exception {

		for (int i = 0; i < 5; i++) {
			// 中文验证码
			ChineseCaptcha chineseCaptcha = new ChineseCaptcha();
			System.out.println(chineseCaptcha.text());
			chineseCaptcha.out(new FileOutputStream(new File(
					"C:\\Users\\hello_liu\\Desktop\\java\\dd" + i + ".png")));
		}

	}

	@Test
	public void testGifHan() throws Exception {

		for (int i = 0; i < 5; i++) {
			// 中文GIF图
			ChineseGifCaptcha chineseGifCaptcha = new ChineseGifCaptcha();
			System.out.println(chineseGifCaptcha.text());
			chineseGifCaptcha.out(new FileOutputStream(new File(
					"C:\\Users\\hello_liu\\Desktop\\java\\ee" + i + ".gif")));
		}

	}

}
