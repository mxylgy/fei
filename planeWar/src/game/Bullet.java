package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

//子弹对象
public class Bullet {
	// 子弹坐标
	private int x;
	private int y;
	// 子弹长宽
	private int width;
	private int height;
	// 子弹图片
	private Image bulletImageIcon = Toolkit.getDefaultToolkit().getImage(
			"img/bullet.jpg");

	// 带参构造
	public Bullet(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.width = bulletImageIcon.getWidth(null);
		this.height = bulletImageIcon.getHeight(null);
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

	// 子弹移动方法
	public void move() {
		// TODO Auto-generated method stub
		this.y -= 3;
	}

	// 子弹绘制方法
	public void drawImage(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(bulletImageIcon, x, y, null);
	}

}
