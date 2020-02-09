package myframe;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fun2Frame extends JFrame implements ActionListener{
	private Socket s;
	
	private JButton fun2Button = new JButton("生成柱状图");
	private JButton mainButton = new JButton("返回");
	private JPanel  fun2Panel  = new JPanel();
	
	public Fun2Frame(Socket s) {
		this.s = s;
		
		//绑定
		fun2Button.addActionListener(this);  
		mainButton.addActionListener(this);
		fun2Panel.add(fun2Button);
		fun2Panel.add(mainButton);
		
		this.setTitle("功能二");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(fun2Panel,BorderLayout.NORTH);
		this.add(new JLabel(new ImageIcon("C:\\Users\\87776\\Desktop\\images.jpg")));
		this.setSize(450,300);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == fun2Button) { 
			try {
				OutputStream out = s.getOutputStream();           //获取服务端的输出流，为了向服务端输出数据
				PrintWriter bufw = new PrintWriter(out,true);
				
				bufw.println(22);                                 //发送数据给服务端
				this.setTitle("正在下载柱状图...");
				this.setTitle("柱状图下载完成");
				bufw.println(0);
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}else if (e.getSource() == mainButton) {
			//返回主页面
			try {
				OutputStream out = s.getOutputStream();          //获取服务端的输出流，为了向服务端输出数据
				PrintWriter bufw = new PrintWriter(out,true);
				bufw.println(0);                                 //发送数据给服务端
				System.out.println("功能二已返回\n");
				new MainFrame(s);
				this.dispose();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	}
}