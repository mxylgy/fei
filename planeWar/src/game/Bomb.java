package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

//��ը����
public class Bomb {
	// ��ըxy����
	private int x;
	private int y;
	// ��ը���
	private int width;
	private int height;
	// ��ըͼƬ
	private Image bombIamg = Toolkit.getDefaultToolkit().getImage(
			"img/bzxg.jpg");
	// ɾ���Ĵ���
	private int count;

	// ����
	public Bomb(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.width = bombIamg.getWidth(null);
		this.height = bombIamg.getHeight(null);

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

	public Image getBombIamg() {
		return bombIamg;
	}

	public void setBombIamg(Image bombIamg) {
		this.bombIamg = bombIamg;
	}

	// ��ըͼƬ���Ʒ���
	public void drawImage(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(bombIamg, x, y, null);
	}

	public  void move() {
		// TODO Auto-generated method stub
		count++;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
