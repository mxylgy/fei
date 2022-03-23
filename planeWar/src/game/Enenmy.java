package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

//����С������
public class Enenmy {
	// С���Ŀ��
	private int width;
	// С���ĸ߶�
	private int height;
	// С��������
	private int x;
	private int y;
	// ��ȡС��ͼƬ
	private Image enemyImage = Toolkit.getDefaultToolkit().getImage(
			"img/enemy.jpg");

	// �޲ι���
	public Enenmy() {
		this.width = enemyImage.getWidth(null);
		this.height = enemyImage.getHeight(null);
		// ����С�������λ��
		Random random = new Random();
		random.nextInt(10);
		this.x = random.nextInt(GameMain.width - 2 * width) + width;
		this.y = -random.nextInt(GameMain.height);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public void move() {
		// TODO Auto-generated method stub
		// �л��ƶ��ٶ�
		this.y += 3;
	}

	public void drawImage(Graphics g) {
		// TODO Auto-generated method stub
		// ���Ƶл�
		g.drawImage(enemyImage, x, y, null);
	}
}
