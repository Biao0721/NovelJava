package myframe;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fun4Frame extends JFrame implements ActionListener{
	private Socket s = null;
	
	private JButton fun1Button = new JButton("保存归类内容");
	private JButton mainButton = new JButton("返回");
	private JPanel  fun4Panel  = new JPanel();
	
	public Fun4Frame(Socket s) {
		this.s = s;
		
		//绑定
		fun1Button.addActionListener(this); 
		mainButton.addActionListener(this);
		fun4Panel.add(fun1Button);
		fun4Panel.add(mainButton);
		
		this.setTitle("功能四");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(fun4Panel,BorderLayout.NORTH);
		this.setSize(800,500);
		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == fun1Button) {
			try {
				OutputStream out = s.getOutputStream();           //获取服务端的输出流，为了向服务端输出数据
				PrintWriter bufw = new PrintWriter(out,true);
				bufw.println(44);
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}else {
			//返回主页面
			try {
				OutputStream out = s.getOutputStream();          //获取服务端的输出流，为了向服务端输出数据
				PrintWriter bufw = new PrintWriter(out,true);
				bufw.println(0);                                 //发送数据给服务端
				this.dispose();
				new MainFrame(s);
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	}
}
