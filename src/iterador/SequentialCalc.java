package iterador;

public class SequentialCalc {


	static double DELTA = 0.0001;
	static double MIN = 1.1;
	static double MAX = 1.7;
	public static void main(String[] args) {
		double b = 0.2;
		int  vezes = (int) ((MAX - MIN) / DELTA);
		System.out.println("Calculando " + vezes);
		Timer timer = new Timer(vezes);
		for (double a = MIN; a < MAX; a += DELTA) {
			timer.time(new Itera(a,b));
		}
	}

}
