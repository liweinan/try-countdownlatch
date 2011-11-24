package net.bluedash.countdownlatch;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class SampleServerWithoutRacingProblem implements SampleServer{

	private boolean running = true;

	private ServerSocket socket;

	private CountDownLatch lock = new CountDownLatch(1);

	public SampleServerWithoutRacingProblem() throws IOException {
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
		lock.countDown();
		System.out.print("\nG");
	}

	public void close() throws IOException, InterruptedException {
		running = false;
		lock.await();
		socket.close();
	}

}
