package server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import name.Name;


public class Server extends JFrame implements Runnable{

	private Socket s = null;
	private ServerSocket ss = null;
	private JTextArea taMsg = new JTextArea("以下是操作记录\n");

	public Server(){
		this.setTitle("服务端");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.yellow);
		this.setSize(600,800);
		this.setVisible(true);
		this.add(taMsg,BorderLayout.CENTER);
		
		try {
			//监听9999端口
			ss = new ServerSocket(9999); //服务器开端口
			s = ss.accept();
			String clientAddress = s.getInetAddress().getHostAddress();
			this.setTitle("客户" + clientAddress + "连接");
			taMsg.append("客户" + clientAddress + "连接\n");
			new Thread(this).start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void run() {
		OutputStream out = null;
		InputStream in = null;
		PrintWriter bufw = null;
		BufferedReader bufr = null;
		
		try {
			
			Name n = new Name();                                                              //生成名字对象
			
			while (true) {
				out = s.getOutputStream();                                                    //向客户端传输数据
				in = s.getInputStream();                                                      //接受客户端数据
				bufw = new PrintWriter(out,true);                                             //写传出数据
				bufr = new BufferedReader(new InputStreamReader(in));                         //读取传入数据
				
				out.flush();
				
				String flag = bufr.readLine();
				System.out.println(flag);
				
				switch (Integer.valueOf(flag)) {

				case 0:
					taMsg.append("已返回\n");
					break;
				
				case 1:
					taMsg.append("以选择功能一\n");
					break;
					
				case 2:
					taMsg.append("以选择功能二\n");
					break;
					
				case 3:
					taMsg.append("以选择功能三\n");
					break;
				
				case 4:
					taMsg.append("以选择功能四\n");
					break;
				
				case 11:
					
					File file1 = new File("C:\\Users\\87776\\Desktop\\HarryPotter\\test");
					File[] fl = file1.listFiles();
					for(File ft : fl) {
						FileInputStream fis = new FileInputStream(ft);
						InputStreamReader isr = new InputStreamReader(fis, "utf-8");
						BufferedReader br = new BufferedReader(isr);
						String line = null;
						
						while((line = br.readLine()) != null) {
							if(!bufw.checkError()) {
								bufw.flush();
								System.out.println(line + "\r\n");
								bufw.println(line + "\r\n");
							}
						}
						taMsg.append("以下载" + ft + "\n");
						fis.close();
						
						bufw.flush();
					}
					bufw.flush();
					bufw.flush();
					break;
					
				case 22:
					n.printSum();
					n.getBarChart();
					
					taMsg.append("已生成柱状图\n");
					break;
				
				case 44:
					n.printAvgNum();
					n.getBarChart(0);
					break;
					
				default:
					n.print(Integer.valueOf(flag) - 31);
					taMsg.append("已生成密度图\n");
							
					break;
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} 
	}
	
	public static void main(String[] args) {
		new Server();
	}
}

