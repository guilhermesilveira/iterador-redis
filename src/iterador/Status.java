package iterador;

import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class Status {
	
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
		try {
			while (true) {
				Set<String> members = jedis.smembers("CLIENTES");
				System.out.println(members);
				Map<String, String> stats = jedis.hgetAll("STATS");
				for(String client : stats.keySet()) {
					System.out.println(client + ": " + stats.get(client));
				}
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} finally {
			jedis.quit();
		}

	}

}
