package main;

import client.Client;
import server.Server;

public class Main implements Runnable{

	public Main() {
		new Thread(this).start();
		new Server();
	}
	
	public static void main(String[] args){
		new Main();
	}
	
	public void run() {
		new Client();
	}

}
