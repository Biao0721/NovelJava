package myframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import name.Name.MainName;

public class Fun3Frame extends JFrame implements ActionListener, FocusListener{	
	private Socket s; 
	private Map<Integer, MainName> map;
	
	private JButton fun3Button = new JButton("保存密度图");	
	private JButton mainButton = new JButton("返回");
	private JPanel  fun3Panel  = new JPanel();
	private JTextField tfMsg   = new JTextField();
	private JTextArea  taMsg   = new JTextArea("以下是十个人名对应的序号\n");
	
	public Fun3Frame(Socket s, Map<Integer, MainName> map) {
		this.s = s;
		this.map = map;
		
		Font font = new Font("黑体", 0, 20);
		taMsg.setFont(font);
		
		this.setLayout(new FlowLayout());
		
		tfMsg.setText("请输入你想查询人的序号");
		tfMsg.setForeground(Color.GRAY);
		
		//绑定
		fun3Button.addActionListener(this);
		mainButton.addActionListener(this);
		tfMsg.addFocusListener(this);
		fun3Panel.add(fun3Button);
		fun3Panel.add(mainButton);
		
		this.setTitle("功能三");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(fun3Panel,BorderLayout.NORTH);
		this.add(tfMsg);
		this.add(taMsg);
		this.setSize(800,325);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		for (int i = 0; i < 10; i++) {
			taMsg.append((i + 1) + ":" + map.get(i).getName() + "\n");
		}
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == fun3Button) {
			try {
				OutputStream out = s.getOutputStream();                               //获取服务端的输出流，为了向服务端输出数据
				PrintWriter bufw = new PrintWriter(out,true);
				if(tfMsg.getText() == null) {
					taMsg.append("请输入你想查询人的序号");
				}else if (tfMsg.getText().length() > 1) {
					bufw.println(40);
					System.out.println();
				} else {
					bufw.println(3 + tfMsg.getText());                                 //发送数据给服务端
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}else {
			//返回主页面
			try {
				OutputStream out = s.getOutputStream();                                //获取服务端的输出流，为了向服务端输出数据
				PrintWriter bufw = new PrintWriter(out,true);
				bufw.println(0);                                                       //发送数据给服务端
				new MainFrame(s);
				this.dispose();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
	}
	
	public void focusGained(FocusEvent e) {
		//当获得焦点时
		String temp = tfMsg.getText();
		if(temp.equals("请输入你想查询人的序号")) {
			tfMsg.setText("");
			tfMsg.setForeground(Color.black);
		}
	}

	public void focusLost(FocusEvent e) {
		//当失去焦点时
		String temp = tfMsg.getText();
		if(temp.equals("")) {
			tfMsg.setForeground(Color.GRAY);
			tfMsg.setText("请输入你想查询人的序号");
		}
	}
}