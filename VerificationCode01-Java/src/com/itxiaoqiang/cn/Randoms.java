package com.itxiaoqiang.cn;

import java.util.Random;

/**
 * �����������
 */
public class Randoms {
	protected static final Random RANDOM = new Random();
	// ������֤���ַ�.ȥ����O��I�����׻�������ĸ
	public static final char ALPHA[] = { '2', '3', '4', '5', '6', '7', '8',
			'9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'G', 'K', 'M', 'N',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b',
			'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	protected static final int numMaxIndex = 8; // ���ֵ�������������������ֵ
	protected static final int charMinIndex = numMaxIndex; // �ַ�����С������������Сֵ
	protected static final int charMaxIndex = ALPHA.length; // �ַ���������������������ֵ
	protected static final int upperMinIndex = charMinIndex; // ��д�ַ���С����
	protected static final int upperMaxIndex = upperMinIndex + 23; // ��д�ַ��������
	protected static final int lowerMinIndex = upperMaxIndex; // Сд��ĸ��С����
	protected static final int lowerMaxIndex = charMaxIndex; // Сд��ĸ�������

	/**
	 * ����������֮��������
	 * 
	 * @param min
	 *            ��Сֵ
	 * @param max
	 *            ���ֵ
	 * @return �����
	 */
	public static int num(int min, int max) {
		return min + RANDOM.nextInt(max - min);
	}

	/**
	 * ����0-num�������,������num
	 * 
	 * @param num
	 *            ���ֵ
	 * @return �����
	 */
	public static int num(int num) {
		return RANDOM.nextInt(num);
	}

	/**
	 * ����ALPHA�е�����ַ�
	 * 
	 * @return ����ַ�
	 */
	public static char alpha() {
		return ALPHA[num(ALPHA.length)];
	}

	/**
	 * ����ALPHA�е�0λ����numλ������ַ�
	 * 
	 * @param num
	 *            ���ڼ�λ����
	 * @return ����ַ�
	 */
	public static char alpha(int num) {
		return ALPHA[num(num)];
	}

	/**
	 * ����ALPHA�е�minλ����maxλ������ַ�
	 * 
	 * @param min
	 *            �ӵڼ�λ��ʼ
	 * @param max
	 *            ���ڼ�λ����
	 * @return ����ַ�
	 */
	public static char alpha(int min, int max) {
		return ALPHA[num(min, max)];
	}
}