package de.unipaderborn.visuflow.debug.monitoring;

import static org.junit.Assert.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Assert;
import org.junit.Test;

import de.unipaderborn.visuflow.Logger;
import de.unipaderborn.visuflow.Visuflow;
import de.unipaderborn.visuflow.model.DataModel;
import de.unipaderborn.visuflow.util.ServiceUtil;

public class MonitoringTest {
	private Logger logger = Visuflow.getDefault().getLogger();
	private Lock lock = new ReentrantLock();
	private boolean running = true;
	private DataModel dataModel = ServiceUtil.getService(DataModel.class);
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private Thread t;
	MonitoringServer m=new MonitoringServer();
	@Test
	public void testStart() {
		logger.info("Monitoring server starting");
		logger.info("Server launcher setting lock");
		lock.lock();
		t = new Thread() {
			@Override
			public void run() {
				try {
					logger.info("Monitoring server setting lock");
					lock.lock();
					serverSocket = new ServerSocket(6543);
					logger.info("Monitoring server unlock");
					lock.unlock();
					clientSocket = serverSocket.accept();

					DataInputStream in = new DataInputStream(clientSocket.getInputStream());
					DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
					while(running) {
						String msgType = in.readUTF();
						if(msgType.equals("CLOSE")) {
							logger.info("Client closed the connection");
							out.writeUTF("OK");
							out.flush();
							//MonitoringTest.this.testStop();
						} else if(msgType.equals("UNIT_UPDATE")) {
							String unitFqn = in.readUTF();
							String inSet = in.readUTF();
							String outSet = in.readUTF();
							dataModel.setInSet(unitFqn, "in", inSet);
							dataModel.setOutSet(unitFqn, "out", outSet);
						}
					}
				} catch (EOFException e) {
					Assert.fail("No more data. The client probably closed the connection");
				} catch (IOException e) {
					Assert.fail("Monitoring server threw an exception");
				}
			}
		};
		t.setDaemon(true);
		t.setName("Analysis Monitoring Server");
		t.start();
		logger.info("Server launcher unlock");
		lock.unlock();
	}

	@Test
	public void testWaitForServer() {
		MonitoringServer ms=new MonitoringServer();
		int milli = 0;
		logger.info("Delegate setting lock");
		try {
			Thread.sleep(1000); // give it a second to start
			lock.tryLock(milli, TimeUnit.MILLISECONDS);
			lock.unlock();
			Assert.assertTrue(ms.waitForServer(milli));
		} catch (InterruptedException e) {
			Assert.fail("Couldn't wait for server to start");
			Assert.assertFalse(ms.waitForServer(milli));
		}
	}

//	@Test
//	public void testStop() {
//		logger.info("Monitoring server stopping");
//		running = false;
////		t.interrupt();
//
//		if(clientSocket != null && !clientSocket.isClosed()) {
//			try {
//				clientSocket.close();
//			} catch (IOException e) {
//				Assert.fail("Couldn't close monitoring server connection");
//			}
//		}
//		if(serverSocket != null && !serverSocket.isClosed()) {
//			try {
//				serverSocket.close();
//			} catch (IOException e) {
//				Assert.fail("Couldn't close monitoring server connection");
//			}
//		}
//	}

}
