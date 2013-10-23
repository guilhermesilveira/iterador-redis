package iterador;

import redis.clients.jedis.Jedis;

public class ParallelCalc {

	static double DELTA = 0.0001;
	static double MIN = 1.1;
	static double MAX = 1.7;
	public static void main(String[] args) {
		
		Jedis jedis = new Jedis("localhost");
		jedis.del("PARAMS");

		double b = 0.2;
		int vezes = (int)((MAX - MIN) / DELTA);
		System.out.println("Calculando " + vezes);
		Timer timer = new Timer(vezes);
		for (double a = MIN; a < MAX; a += DELTA) {
			jedis.rpush("PARAMS", a+ "::" +b);
		}
		while(true) {
			Long missing = jedis.llen("PARAMS");
			if(missing==0) break;
			System.out.println("Missing " + missing);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		jedis.quit();
	}

}
