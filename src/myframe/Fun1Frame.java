package myframe;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fun1Frame extends JFrame implements ActionListener,Runnable{
	private Socket s;
	
	private JButton fun1Button = new JButton("下载小说");
	private JButton mainButton = new JButton("返回");
	private JPanel  fun1Panel  = new JPanel();
	
	public Fun1Frame(Socket s) {
		this.s = s; 
		
		//绑定
		fun1Button.addActionListener(this); 
		mainButton.addActionListener(this);
		fun1Panel.add(fun1Button);
		fun1Panel.add(mainButton);
		
		this.setTitle("功能一");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(fun1Panel,BorderLayout.NORTH);
		this.add(new JLabel(new ImageIcon("C:\\Users\\87776\\Desktop\\download.jpg")));
		this.setSize(450,500);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		
		Thread t = new Thread(this);
		
		if(e.getSource() == fun1Button) {
			try {
				OutputStream out = s.getOutputStream();           //获取服务端的输出流，为了向服务端输出数据	
				PrintWriter bufw = new PrintWriter(out,true);
				
				bufw.println(11);                                 //发送数据给服务端
				this.setTitle("正在下载小说...");
				t.start();
				this.setTitle("小说下载完成");
				bufw.println(0);
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
		}else {
			//返回主页面
			try {
				OutputStream out = s.getOutputStream();          //获取服务端的输出流，为了向服务端输出数据
				PrintWriter bufw = new PrintWriter(out,true);
				bufw.println(0);                                 //发送数据给服务端
				new MainFrame(s);
				t.interrupt();
				this.dispose();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	}
	
	public void run() {
		
		while(true) {
			try {
				FileOutputStream fos = new FileOutputStream("C:\\Users\\87776\\Desktop\\客户端\\HarryPotter.txt");
				OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
				InputStream is = s.getInputStream();      //获取服务端的输入流，为了获取服务端数据
				
				InputStreamReader isr = new InputStreamReader(is, "GB2312");
				BufferedReader bufr = new BufferedReader(isr);
				String line = null;             //读取服务端传出数据
				while((line = bufr.readLine()) != null){
					System.out.println(line);
					osw.write(line);
					osw.write("\r\n");
					osw.flush();
				}
				fos.close();
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}    
		}    //打印服务端数据
	}
}
