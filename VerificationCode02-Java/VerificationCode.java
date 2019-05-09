
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

public class VerificationCode {

    private int fontSize = 20;// ��֤������ ��Ĭ��20��

    private int width = 250;// ͼƬ��֤���� ��Ĭ��250��

    private int height = 250;// ͼƬ��֤��߶ȣ�Ĭ��250��

    private int charAmt = 5;// ��֤���ַ����� ��Ĭ��5��

    private String keyword;// ��֤��ؼ���

    private List<Pair> position;// �ֺ�����

    private String[] codeSet = {"��ɽ������", "����������", "����������", "������̦��"};

    private int increFactor = width > height ? width / charAmt : height / charAmt;// ��������

    // �����������
    public class Pair {
        private String key;
        private List<Integer> value;

        public Pair(String key, List<Integer> value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<Integer> getValue() {
            return value;
        }

        public void setValue(List<Integer> value) {
            this.value = value;
        }

        public String toString() {
            return key + ":" + value;
        }
    }

    public demo() {
        this.position = new ArrayList<demo.Pair>();
    }

    public demo(int width, int height, int charAmt, int fontSize) {
        this.width = width;
        this.height = height;
        this.charAmt = charAmt;
        this.fontSize = fontSize;
        this.position = new ArrayList<demo.Pair>();
    }

    // ���������ɫ
    private Color getRandColor() {
        Color[] colors = {Color.BLACK, Color.BLUE, Color.GRAY, Color.GREEN, Color.ORANGE, Color.PINK, Color.YELLOW};
        Random random = new Random();
        return colors[random.nextInt(colors.length)];
    }

    // ���Ƹ�����
    private void drawLine(Graphics2D g) {
        Random random = new Random();
        for (int i = 0; i < 60; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            g.setColor(getRandColor());
            g.drawLine(x1, y1, x2, y2);
        }
    }

    // ��ȡ��֤��ͼƬ
    public BufferedImage getVeriCode() {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        drawLine(g);
        g.setColor(Color.RED);
        Font font = new Font("����", Font.BOLD, fontSize);
        g.setFont(font);
        Random random = new Random();
        int x, y, step = 0, codeIndex = random.nextInt(codeSet.length), charIndex;
        char[] charSet = codeSet[codeIndex].toCharArray();
        if (charSet.length != charAmt)
            System.err.println("��֤���ַ����ַ��������� ���������� ��{" + codeSet[codeIndex] + "}");
        for (int i = 0; i < charAmt; i++) {
            charIndex = random.nextInt(charAmt);
            x = random.nextInt(width - increFactor) + increFactor / 2;
            y = random.nextInt((height - increFactor) / charAmt) + step + increFactor / 2;
            g.drawString(String.valueOf(charSet[charIndex]), x, y);
            step += (height - increFactor) / charAmt;
            keyword = String.valueOf(charSet[charIndex]);// �ؼ��ָ�ֵ
            List<Integer> range = new ArrayList<Integer>();
            range.add(x);
            range.add(x + fontSize);
            range.add(y);
            range.add(y + fontSize);
            position.add(new Pair(keyword, range));
        }

        return img;
    }

    public String getKeyword() {
        return keyword;
    }

    public List<Pair> getPosition() {
        return position;
    }

    public static void main(String[] args) {
        demo util = new demo();
        for (int i = 0; i < 10; i++) {
            BufferedImage img = util.getVeriCode();
            try {
                ImageIO.write(img, "jpg", new File("C:\\Users\\hello_liu\\Pictures\\Saved Pictures\\test" + i + ".jpg"));
                System.out.println(util.getKeyword());
                System.out.println(util.getPosition());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}