package net.bluedash.countdownlatch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientWorker implements Runnable {

	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(100); // 防止客户端请求太快，消耗大量系统资源
				Socket socket = new Socket("127.0.0.1", 8080);
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				String line;
				while ((line = rd.readLine()) != null) {
					System.out.print(line);
				}
				socket.close();
			}
		} catch (Exception ignore) {
		}
	}

}
