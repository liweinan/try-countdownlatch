package net.bluedash.countdownlatch;

import java.io.IOException;

public interface SampleServer {
	public void accept();

	public void close() throws IOException, InterruptedException;
}
