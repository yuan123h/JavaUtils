package com.test.java.nio;

import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.util.StringUtils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class Test1 {
	public static final Log LOG = LogFactory.getLog("com.test.java.nio.Test1");

	private ServerSocketChannel acceptChannel = null;
	private int backlogLength = 128;
	private Selector selector = null; // the selector that we use for the server
	private Reader[] readers = null;
	private int readThreads = 10;
	private ExecutorService readPool;

	boolean running = true;

	public static void main(String[] args) throws IOException {
		new Test1().test();
	}

	public void test() throws IOException {
		String bindAddress = "localhost";
		int port = 9999;

		InetSocketAddress address = new InetSocketAddress(bindAddress, port);
		// Create a new server socket and set to non blocking mode
		acceptChannel = ServerSocketChannel.open();
		acceptChannel.configureBlocking(false);

		// Bind the server socket to the local host and port
		bind(acceptChannel.socket(), address, backlogLength);
		port = acceptChannel.socket().getLocalPort(); // Could be an ephemeral
														// port
		// create a selector;
		selector = Selector.open();

		readers = new Reader[readThreads];
		readPool = Executors.newFixedThreadPool(
				readThreads,
				new ThreadFactoryBuilder()
						.setNameFormat("IPC Reader %d on port " + port)
						.setDaemon(true).build());
		for (int i = 0; i < readThreads; ++i) {
			Reader reader = new Reader();
			readers[i] = reader;
			readPool.execute(reader);
		}
		try {
			Thread.currentThread().sleep(100*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void bind(ServerSocket socket, InetSocketAddress address,
			int backlog) throws IOException {
		try {
			socket.bind(address, backlog);
		} catch (BindException e) {
			BindException bindException = new BindException(
					"Problem binding to " + address + " : " + e.getMessage());
			bindException.initCause(e);
			throw bindException;
		} catch (SocketException e) {
			// If they try to bind to a different host's address, give a better
			// error message.
			if ("Unresolved address".equals(e.getMessage())) {
				throw new UnknownHostException("Invalid hostname for server: "
						+ address.getHostName());
			}
			throw e;
		}
	}

	private class Reader implements Runnable {
		private volatile boolean adding = false;
		private final Selector readSelector;

		Reader() throws IOException {
			this.readSelector = Selector.open();
		}

		public void run() {
			LOG.info("Starting " + getName());
			try {
				doRunLoop();
			} finally {
				try {
					readSelector.close();
				} catch (IOException ioe) {
					LOG.error("Error closing read selector in " + getName(),
							ioe);
				}
			}
		}

		private synchronized void doRunLoop() {
			while (running) {
				SelectionKey key = null;
				try {
					readSelector.select();
					while (adding) {
						this.wait(1000);
					}

					Iterator<SelectionKey> iter = readSelector.selectedKeys()
							.iterator();
					while (iter.hasNext()) {
						key = iter.next();
						iter.remove();
						if (key.isValid()) {
							if (key.isReadable()) {
								doRead(key);
							}
						}
						key = null;
					}
				} catch (InterruptedException e) {
					if (running) { // unexpected -- log it
						LOG.info(getName() + " unexpectedly interrupted: "
								+ StringUtils.stringifyException(e));
					}
				} catch (IOException ex) {
					LOG.error("Error in Reader", ex);
				}
			}
		}

		/**
		 * This gets reader into the state that waits for the new channel to be
		 * registered with readSelector. If it was waiting in select() the
		 * thread will be woken up, otherwise whenever select() is called it
		 * will return even if there is nothing to read and wait in
		 * while(adding) for finishAdd call
		 */
		public void startAdd() {
			adding = true;
			readSelector.wakeup();
		}

		public synchronized SelectionKey registerChannel(SocketChannel channel)
				throws IOException {
			return channel.register(readSelector, SelectionKey.OP_READ);
		}

		public synchronized void finishAdd() {
			adding = false;
			this.notify();
		}
	}

	String getName() {
		return null;
	}

	void doRead(SelectionKey key) throws InterruptedException {
		System.out.println("read...");
		// int count = 0;
		// Connection c = (Connection)key.attachment();
		// if (c == null) {
		// return;
		// }
		// c.setLastContact(System.currentTimeMillis());
		//
		// try {
		// count = c.readAndProcess();
		// } catch (InterruptedException ieo) {
		// throw ieo;
		// } catch (Exception e) {
		// LOG.warn(getName() + ": readAndProcess threw exception " + e +
		// ". Count of bytes read: " + count, e);
		// count = -1; //so that the (count < 0) block is executed
		// }
		// if (count < 0) {
		// if (LOG.isDebugEnabled())
		// LOG.debug(getName() + ": disconnecting client " +
		// c.getHostAddress() + ". Number of active connections: "+
		// numConnections);
		// closeConnection(c);
		// // c = null;
		// }
		// else {
		// c.setLastContact(System.currentTimeMillis());
		// }
	}

}
