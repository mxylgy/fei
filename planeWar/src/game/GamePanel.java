package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;//鼠标监听器
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;



//绘制类
public class GamePanel extends JPanel implements MouseMotionListener {
	// 获取英雄机图片
	Image heroImage = Toolkit.getDefaultToolkit().getImage("img/hero.jpg");
	//获取logo图片
	static Image logoImage = Toolkit.getDefaultToolkit().getImage("img/logo.jpg");
	//获取背景图片
	Image back=Toolkit.getDefaultToolkit().getImage("img/fback.jpg");
	// 定义一个集合用来装所有的敌机
	List<Enenmy> enemys = new ArrayList<Enenmy>();
	// 定义一个集合用来装子弹对象
	List<Bullet> bullets = new ArrayList<Bullet>();
	// 定义一个集合用来装爆炸对象
	List<Bomb> bombs = new ArrayList<Bomb>();
	// 英雄机坐标
	int herox = 300;
	int heroy = 400;
	//分数记录变量
	int nub=0;
	// 构造方法
	public GamePanel() {
		// TODO Auto-generated constructor stub
		// 创建10个敌人
		for (int i = 0; i < 10; i++) {
			enemys.add(new Enenmy());
		}
	}

	// 绘制方法
	public void paint(Graphics g) {
		super.paint(g);
		//画出背景
		g.drawImage(back, 0, 0,null);
		//画出得分提示
		g.setColor(Color.white);
		g.setFont(new Font("仿宋", Font.BOLD, 30));
		g.drawString("当前得分"+nub, 35, 30);
		//绘制logo
		g.drawImage(logoImage,0,0,null);
		// 绘制英雄机
		g.drawImage(heroImage, herox, heroy, null);
		// 绘制敌机
		for (int i = 0; i < enemys.size(); i++) {
			// 拿到当前敌机
			Enenmy enenmy = enemys.get(i);
			// 重新绘制
			enenmy.drawImage(g);
		}
		// 绘制子弹
		for (int i = 0; i < bullets.size(); i++) {
			// 拿到子弹
			Bullet bullet = bullets.get(i);
			// 重新绘制
			bullet.drawImage(g);
		}
		// 绘制爆炸图片
		for (int i = 0; i < bombs.size(); i++) {
			// 拿到子弹
			Bomb bomb=bombs.get(i);
			// 重新绘制
			bomb.drawImage(g);
		}
	}

	// 初始变量方法,用于创建组件
	public void init() {
		// 定义子弹出现频率
		int flag = 0;
		while (true) {
			flag++;
			// 每绘制15次画子弹一次
			if (flag % 15 == 0) {
				// 创建子弹,,加是为了子弹在英雄机的中间
				Bullet bullet = new Bullet(herox
						+ (heroImage.getWidth(null) / 2 - 5), heroy);
				// 把子弹添加到集合中
				bullets.add(bullet);
			}
			// 让全部敌机往下移动
			for (int i = 0; i < enemys.size(); i++) {
				// 拿到当前敌机
				Enenmy enenmy = enemys.get(i);
				// 调用方法移动敌机,改变敌机y坐标
				enenmy.move();
				// 当敌机超过窗口时,加入新的敌机
				if (enenmy.getY() > GameMain.height) {
					// 删除敌机
					enemys.remove(enenmy);
					// 加入新的敌机
					enemys.add(new Enenmy());
				}
			}
			// 让子弹往上移动
			for (int i = 0; i < bullets.size(); i++) {
				// 临时子弹
				Bullet tempBullet = bullets.get(i);
				// 移动方法
				tempBullet.move();
			}
			// 删除越界的子弹
			for (int i = 0; i < bullets.size(); i++) {
				// 获取子弹对象
				Bullet bullet = bullets.get(i);
				// 如果越界删除
				if (bullet.getY() < 0) {
					// 删除
					bullets.remove(bullet);
				}
			}
			// 子弹飞机碰撞爆炸
			for (int i = 0; i < enemys.size(); i++) {
				// 获取所有敌机
				Enenmy enenmy = enemys.get(i);
				for (int j = 0; j < bullets.size(); j++) {
					// 获取所有子弹
					Bullet bullet = bullets.get(j);
					// 调用碰撞方法判断
					if (isHit(enenmy, bullet)) {
						// 删除击中敌机
						enemys.remove(enenmy);
						// 加入新敌机(防止敌机全部被消灭)
						enemys.add(new Enenmy());
						// 删除子弹
						bullets.remove(bullet);
						// 创建一个爆炸图片对象
						Bomb bomb = new Bomb(enenmy.getX(), enenmy.getY());
						// 添加到集合中
						bombs.add(bomb);
						//分数增加
						nub+=10;
					}
				}
			}
			//删除爆炸图片
			for (int i = 0; i <bombs.size(); i++) {
				// 获取发生爆炸的图片
				Bomb bomb=bombs.get(i);
				//调用爆炸方法
				bomb.move();
				if (bomb.getCount()>10) {
					bombs.remove(bomb);
				}
				
				
			}
			// 绘制速度
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 绘制
			repaint();
		}

	}

	// 鼠标按键在组件上拖动时调用
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();// 将鼠标的x坐标赋值给x
		int y = e.getY();// 将鼠标的y坐标赋值给y
		// 减是为了英雄机距离鼠标更近
		heroy = y - (heroImage.getWidth(null) / 2 + 45);// 把鼠标的y赋值给英雄机
		herox = x - (heroImage.getHeight(null) / 2 + 5);// 把鼠标的x赋值给英雄机
		// 鼠标x+英雄机的x>窗口宽度，时不能在向右移动
		if ((x + heroImage.getWidth(null)) > GameMain.width + 20) {
			herox = GameMain.width - heroImage.getWidth(null) - 20;
		}
		// 不能在向左移动
		if ((x - heroImage.getWidth(null)) < 0) {
			herox = heroImage.getWidth(null) - 60;
		}
		// 不能在向下移动
		if ((y + heroImage.getHeight(null)) > GameMain.height + 28) {
			heroy = GameMain.height - heroImage.getHeight(null) - 50;
		}
		// 不能在向上移动
		if ((y - heroImage.getWidth(null) - 20) < 0) {
			heroy = heroImage.getHeight(null) - 60;
		}
		repaint();// 发生变化后重新绘制
	}

	// 鼠标光标移动到组件上但无按键按下时调用
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();// 将鼠标的x坐标赋值给x
		int y = e.getY();// 将鼠标的y坐标赋值给y
		// 减是为了英雄机距离鼠标更近
		heroy = y - (heroImage.getWidth(null) / 2 + 45);// 把鼠标的y赋值给英雄机
		herox = x - (heroImage.getHeight(null) / 2 + 5);// 把鼠标的x赋值给英雄机
		// 鼠标x+英雄机的x>窗口宽度，时不能在向右移动
		if ((x + heroImage.getWidth(null)) > GameMain.width + 20) {
			herox = GameMain.width - heroImage.getWidth(null) - 20;
		}
		// 不能在向左移动
		if ((x - heroImage.getWidth(null)) < 0) {
			herox = heroImage.getWidth(null) - 60;
		}
		// 不能在向下移动
		if ((y + heroImage.getHeight(null)) > GameMain.height + 28) {
			heroy = GameMain.height - heroImage.getHeight(null) - 50;
		}
		// 不能在向上移动
		if ((y - heroImage.getWidth(null)) < 0 + 20) {
			heroy = heroImage.getHeight(null) - 60;
		}
		repaint();// 发生变化后重新绘制
	}

	// 碰撞方法
	public boolean isHit(Enenmy e, Bullet b) {
		// 获取敌机区域
		Rectangle rect = new Rectangle(e.getX(), e.getY(), e.getWidth(),
				e.getHeight());
		// 获取子弹坐标中间位置的点
		Point p = new Point(b.getX() + b.getWidth() / 2, b.getY()
				+ b.getHeight());
		// 判断rect是否区域包含p并返回布尔值
		return rect.contains(p);
	}

}
