package iterador;

import redis.clients.jedis.Jedis;

public class Client {

	static String NAME = "" + System.currentTimeMillis();

	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
		jedis.sadd("CLIENTES", NAME);
		jedis.hdel("STATS", NAME);
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
				jedis.hincrBy("STATS", NAME, 1);
				new Itera(a, b).run();
			}
		} finally {
			jedis.srem("CLIENTES", NAME);
			jedis.quit();
		}

	}

}
