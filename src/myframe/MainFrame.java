package myframe;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import name.Name;

public class MainFrame extends JFrame implements ActionListener{
	private Socket s;
	
	private JButton fun1Button = new JButton("功能1");
	private JButton fun2Button = new JButton("功能2");
	private JButton fun3Button = new JButton("功能3");
	private JButton fun4Button = new JButton("功能4");
	
	public MainFrame(Socket s) {
		this.s = s;
		
		//四个button进行绑定
		fun1Button.addActionListener(this);
		fun2Button.addActionListener(this);
		fun3Button.addActionListener(this);
		fun4Button.addActionListener(this);
		
		this.setLayout(new FlowLayout());
		
		this.add(fun1Button);
		this.add(fun2Button);
		this.add(fun3Button);
		this.add(fun4Button);
		this.add(new JLabel(new ImageIcon("C:\\Users\\87776\\Desktop\\images (1).jpg")));
		
		this.setTitle("功能选择界面");
		this.setSize(450,250);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public void actionPerformed(ActionEvent e) {
		OutputStream out = null;
		PrintWriter bufw = null;
		try {
			out = s.getOutputStream();          //获取服务端的输出流，为了向服务端输出数据
			bufw = new PrintWriter(out,true);
		} catch (Exception e1) {
			 System.out.println(e1.getMessage());
		}
		
		if(e.getSource() == fun1Button) {
			//显示功能一
			bufw.println(1);                                 //发送数据给服务端
			this.dispose();  
			new Fun1Frame(s);
		}
		else if (e.getSource() == fun2Button) {
			//显示功能二
			bufw.println(2);                                 //发送数据给服务端
			this.dispose();  
			new Fun2Frame(s);
		}
		else if (e.getSource() == fun3Button) {
			//显示功能三
			bufw.println(3);                                 //发送数据给服务端
			this.dispose();
			try {
				new Fun3Frame(s, new Name().getMap());
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			}
		}
		else {
			//显示功能四
			bufw.println(4);                                 //发送数据给服务端
			this.dispose();  
			new Fun4Frame(s);
		}
	}
}
