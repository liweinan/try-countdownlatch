package net.bluedash.countdownlatch;

import java.io.IOException;

public class UseSampleServer {

	private SampleServer server;

	public static void main(String[] args) throws IOException,
			InterruptedException {
		new UseSampleServer().doMain(args);

	}

	public void doMain(String[] args) throws IOException, InterruptedException {

		if (args[0].equals("WithProblem")) {
			System.out.println("SampleServerWithRacingProblem");
			server = new SampleServerWithRacingProblem();
		} else if (args[0].equals("WithoutProblem")) {
			System.out.println("SampleServerWithoutRacingProblem");
			server = new SampleServerWithoutRacingProblem();
		} else {
			System.out.println("\n* Please configure which SampleServer to use: [WithProblem, WithoutProblem].");
			System.exit(1);
		}

		ServerWorker serverWorker = new ServerWorker(server);

		Thread serverThread = new Thread(serverWorker);
		serverThread.start();

		// 开启5个客户端连接
		for (int i = 0; i < 5; i++) {
			ClientWorker clientWorker = new ClientWorker();
			Thread clientThread = new Thread(clientWorker);
			clientThread.start();
		}

		// 给客户端时间5秒钟时间去跑起来，防止服务器过早地关闭
		Thread.currentThread().sleep(5000);

		// 服务关闭
		server.close();
		System.out.print("\nQ");
	}
}
