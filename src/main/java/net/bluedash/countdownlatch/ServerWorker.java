package net.bluedash.countdownlatch;

public class ServerWorker implements Runnable {

	private SampleServer server;

	public ServerWorker(SampleServer server) {
		this.server = server;

	}

	@Override
	public void run() {
		System.out.print("A");
		server.accept();
	}

}
