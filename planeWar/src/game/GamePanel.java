package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;//��������
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;



//������
public class GamePanel extends JPanel implements MouseMotionListener {
	// ��ȡӢ�ۻ�ͼƬ
	Image heroImage = Toolkit.getDefaultToolkit().getImage("img/hero.jpg");
	//��ȡlogoͼƬ
	static Image logoImage = Toolkit.getDefaultToolkit().getImage("img/logo.jpg");
	//��ȡ����ͼƬ
	Image back=Toolkit.getDefaultToolkit().getImage("img/fback.jpg");
	// ����һ����������װ���еĵл�
	List<Enenmy> enemys = new ArrayList<Enenmy>();
	// ����һ����������װ�ӵ�����
	List<Bullet> bullets = new ArrayList<Bullet>();
	// ����һ����������װ��ը����
	List<Bomb> bombs = new ArrayList<Bomb>();
	// Ӣ�ۻ�����
	int herox = 300;
	int heroy = 400;
	//������¼����
	int nub=0;
	// ���췽��
	public GamePanel() {
		// TODO Auto-generated constructor stub
		// ����10������
		for (int i = 0; i < 10; i++) {
			enemys.add(new Enenmy());
		}
	}

	// ���Ʒ���
	public void paint(Graphics g) {
		super.paint(g);
		//��������
		g.drawImage(back, 0, 0,null);
		//�����÷���ʾ
		g.setColor(Color.white);
		g.setFont(new Font("����", Font.BOLD, 30));
		g.drawString("��ǰ�÷�"+nub, 35, 30);
		//����logo
		g.drawImage(logoImage,0,0,null);
		// ����Ӣ�ۻ�
		g.drawImage(heroImage, herox, heroy, null);
		// ���Ƶл�
		for (int i = 0; i < enemys.size(); i++) {
			// �õ���ǰ�л�
			Enenmy enenmy = enemys.get(i);
			// ���»���
			enenmy.drawImage(g);
		}
		// �����ӵ�
		for (int i = 0; i < bullets.size(); i++) {
			// �õ��ӵ�
			Bullet bullet = bullets.get(i);
			// ���»���
			bullet.drawImage(g);
		}
		// ���Ʊ�ըͼƬ
		for (int i = 0; i < bombs.size(); i++) {
			// �õ��ӵ�
			Bomb bomb=bombs.get(i);
			// ���»���
			bomb.drawImage(g);
		}
	}

	// ��ʼ��������,���ڴ������
	public void init() {
		// �����ӵ�����Ƶ��
		int flag = 0;
		while (true) {
			flag++;
			// ÿ����15�λ��ӵ�һ��
			if (flag % 15 == 0) {
				// �����ӵ�,,����Ϊ���ӵ���Ӣ�ۻ����м�
				Bullet bullet = new Bullet(herox
						+ (heroImage.getWidth(null) / 2 - 5), heroy);
				// ���ӵ���ӵ�������
				bullets.add(bullet);
			}
			// ��ȫ���л������ƶ�
			for (int i = 0; i < enemys.size(); i++) {
				// �õ���ǰ�л�
				Enenmy enenmy = enemys.get(i);
				// ���÷����ƶ��л�,�ı�л�y����
				enenmy.move();
				// ���л���������ʱ,�����µĵл�
				if (enenmy.getY() > GameMain.height) {
					// ɾ���л�
					enemys.remove(enenmy);
					// �����µĵл�
					enemys.add(new Enenmy());
				}
			}
			// ���ӵ������ƶ�
			for (int i = 0; i < bullets.size(); i++) {
				// ��ʱ�ӵ�
				Bullet tempBullet = bullets.get(i);
				// �ƶ�����
				tempBullet.move();
			}
			// ɾ��Խ����ӵ�
			for (int i = 0; i < bullets.size(); i++) {
				// ��ȡ�ӵ�����
				Bullet bullet = bullets.get(i);
				// ���Խ��ɾ��
				if (bullet.getY() < 0) {
					// ɾ��
					bullets.remove(bullet);
				}
			}
			// �ӵ��ɻ���ײ��ը
			for (int i = 0; i < enemys.size(); i++) {
				// ��ȡ���ел�
				Enenmy enenmy = enemys.get(i);
				for (int j = 0; j < bullets.size(); j++) {
					// ��ȡ�����ӵ�
					Bullet bullet = bullets.get(j);
					// ������ײ�����ж�
					if (isHit(enenmy, bullet)) {
						// ɾ�����ел�
						enemys.remove(enenmy);
						// �����µл�(��ֹ�л�ȫ��������)
						enemys.add(new Enenmy());
						// ɾ���ӵ�
						bullets.remove(bullet);
						// ����һ����ըͼƬ����
						Bomb bomb = new Bomb(enenmy.getX(), enenmy.getY());
						// ��ӵ�������
						bombs.add(bomb);
						//��������
						nub+=10;
					}
				}
			}
			//ɾ����ըͼƬ
			for (int i = 0; i <bombs.size(); i++) {
				// ��ȡ������ը��ͼƬ
				Bomb bomb=bombs.get(i);
				//���ñ�ը����
				bomb.move();
				if (bomb.getCount()>10) {
					bombs.remove(bomb);
				}
				
				
			}
			// �����ٶ�
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// ����
			repaint();
		}

	}

	// ��갴����������϶�ʱ����
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();// ������x���긳ֵ��x
		int y = e.getY();// ������y���긳ֵ��y
		// ����Ϊ��Ӣ�ۻ�����������
		heroy = y - (heroImage.getWidth(null) / 2 + 45);// ������y��ֵ��Ӣ�ۻ�
		herox = x - (heroImage.getHeight(null) / 2 + 5);// ������x��ֵ��Ӣ�ۻ�
		// ���x+Ӣ�ۻ���x>���ڿ�ȣ�ʱ�����������ƶ�
		if ((x + heroImage.getWidth(null)) > GameMain.width + 20) {
			herox = GameMain.width - heroImage.getWidth(null) - 20;
		}
		// �����������ƶ�
		if ((x - heroImage.getWidth(null)) < 0) {
			herox = heroImage.getWidth(null) - 60;
		}
		// �����������ƶ�
		if ((y + heroImage.getHeight(null)) > GameMain.height + 28) {
			heroy = GameMain.height - heroImage.getHeight(null) - 50;
		}
		// �����������ƶ�
		if ((y - heroImage.getWidth(null) - 20) < 0) {
			heroy = heroImage.getHeight(null) - 60;
		}
		repaint();// �����仯�����»���
	}

	// ������ƶ�������ϵ��ް�������ʱ����
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();// ������x���긳ֵ��x
		int y = e.getY();// ������y���긳ֵ��y
		// ����Ϊ��Ӣ�ۻ�����������
		heroy = y - (heroImage.getWidth(null) / 2 + 45);// ������y��ֵ��Ӣ�ۻ�
		herox = x - (heroImage.getHeight(null) / 2 + 5);// ������x��ֵ��Ӣ�ۻ�
		// ���x+Ӣ�ۻ���x>���ڿ�ȣ�ʱ�����������ƶ�
		if ((x + heroImage.getWidth(null)) > GameMain.width + 20) {
			herox = GameMain.width - heroImage.getWidth(null) - 20;
		}
		// �����������ƶ�
		if ((x - heroImage.getWidth(null)) < 0) {
			herox = heroImage.getWidth(null) - 60;
		}
		// �����������ƶ�
		if ((y + heroImage.getHeight(null)) > GameMain.height + 28) {
			heroy = GameMain.height - heroImage.getHeight(null) - 50;
		}
		// �����������ƶ�
		if ((y - heroImage.getWidth(null)) < 0 + 20) {
			heroy = heroImage.getHeight(null) - 60;
		}
		repaint();// �����仯�����»���
	}

	// ��ײ����
	public boolean isHit(Enenmy e, Bullet b) {
		// ��ȡ�л�����
		Rectangle rect = new Rectangle(e.getX(), e.getY(), e.getWidth(),
				e.getHeight());
		// ��ȡ�ӵ������м�λ�õĵ�
		Point p = new Point(b.getX() + b.getWidth() / 2, b.getY()
				+ b.getHeight());
		// �ж�rect�Ƿ��������p�����ز���ֵ
		return rect.contains(p);
	}

}
