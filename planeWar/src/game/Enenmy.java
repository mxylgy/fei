package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

//敌人小兵对象
public class Enenmy {
	// 小兵的宽度
	private int width;
	// 小兵的高度
	private int height;
	// 小兵的坐标
	private int x;
	private int y;
	// 获取小兵图片
	private Image enemyImage = Toolkit.getDefaultToolkit().getImage(
			"img/enemy.jpg");

	// 无参构造
	public Enenmy() {
		this.width = enemyImage.getWidth(null);
		this.height = enemyImage.getHeight(null);
		// 设置小兵的随机位置
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
		// 敌机移动速度
		this.y += 3;
	}

	public void drawImage(Graphics g) {
		// TODO Auto-generated method stub
		// 绘制敌机
		g.drawImage(enemyImage, x, y, null);
	}
}
