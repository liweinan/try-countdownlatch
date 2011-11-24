package net.bluedash.countdownlatch;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SampleServerWithRacingProblem implements SampleServer {

	private boolean running = true;

	private ServerSocket socket;

	public SampleServerWithRacingProblem() throws IOException {
		init();
	}

	private void init() throws IOException {
		socket = new ServerSocket(8080);
	}

	public void accept() {
		while (running) {
			try {
				Socket connection = socket.accept();
				connection.getOutputStream().write(". ".getBytes());
				connection.getOutputStream().flush();
				connection.close();
			} catch (IOException e) {
				System.out.print("\nC");
				return;
			}
		}
		System.out.print("\nG");
	}

	public void close() throws IOException, InterruptedException {
		running = false;
		socket.close();

	}
}