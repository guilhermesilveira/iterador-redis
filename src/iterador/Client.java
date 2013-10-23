package iterador;

import redis.clients.jedis.Jedis;

public class Client {

	static int THREADS = 8;

	public static void main(String[] args) {
		for (int i = 0; i < THREADS; i++) {
			Runner runner = new Runner();
			new Thread(runner).start();
		}
	}

	static class Runner implements Runnable {

		@Override
		public void run() {
			Jedis jedis = new Jedis("localhost");
			System.out.println("Ready...");
			try {
				while (true) {
					String param = jedis.lpop("PARAMS");
					if (param == null) {
						try {
							Thread.sleep(1000);
							continue;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					int middle = param.indexOf("::");
					double a = Double.parseDouble(param.substring(0, middle));
					double b = Double.parseDouble(param.substring(middle + 2));
					new Itera(a, b).run();
				}
			} finally {
				jedis.quit();
			}
		}

	}

}
