package iterador;

public class Timer {

	int t;
	double tempos;
	final int vezes;
	Timer(int vezes) {
		this.vezes = vezes;
		
	}
	public void time(Runnable r) {
		tempos += countTime(r);
		t++;
		if(t==100) {
			double estimado = (tempos / t) * vezes;
			System.out.println("Tempo estimado: " + estimado);
		}
	}
	
	public static double countTime(Runnable r) {
		long start = System.currentTimeMillis();
		r.run();
		long end = System.currentTimeMillis();
		return (end- start) / 1000.0;
	}

}
