package game;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameMain {
	//static���ο����κ�����κη�������
	//���ڿ��
	static int width=600;
	static int height=800;
	public static void main(String[] args) {
		//ʵ�ִ����ࣨJFrame �Ǵ��б���ͱ߿�Ķ��㴰�ڡ�
		JFrame frame=new JFrame();
		//���ô��ڴ�С
		frame.setSize(width,height);
		//���ô��ھ���
		frame.setLocationRelativeTo(null);
		frame.setIconImage(GamePanel.logoImage);
		//�رմ��ں�JVM��ֹ����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ñ���
		frame.setTitle("С���ɻ���ս");
		//����Jpanel����
		GamePanel panel =new GamePanel();
		//��Jpanel��Ӵ�����
		frame.add(panel);
		//�����Ƿ���ʾ����
		frame.setVisible(true);
		//�����������
		frame.addMouseMotionListener(panel);
		//��ʼ������
		panel.init();
	}
}
