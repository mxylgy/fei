package game;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameMain {
	//static修饰可在任何类的任何方法调用
	//窗口宽高
	static int width=600;
	static int height=800;
	public static void main(String[] args) {
		//实现窗口类（JFrame 是带有标题和边框的顶层窗口。
		JFrame frame=new JFrame();
		//设置窗口大小
		frame.setSize(width,height);
		//设置窗口居中
		frame.setLocationRelativeTo(null);
		frame.setIconImage(GamePanel.logoImage);
		//关闭窗口后JVM终止运行
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置标题
		frame.setTitle("小韩飞机大战");
		//创建Jpanel容器
		GamePanel panel =new GamePanel();
		//把Jpanel添加窗口中
		frame.add(panel);
		//设置是否显示窗口
		frame.setVisible(true);
		//添加鼠标监听器
		frame.addMouseMotionListener(panel);
		//初始化容器
		panel.init();
	}
}
