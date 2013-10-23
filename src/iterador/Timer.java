package iterador;

public class Timer {

	int t;
	double tempos;
	final int vezes;
	Timer(int vezes) {
		this.vezes = vezes;
		
	}
	public void time(Runnable r) {
		long start = System.currentTimeMillis();
		r.run();
		long end = System.currentTimeMillis();
		tempos += (end- start) / 1000.0;
		t++;
		if(t==100) {
			double estimado = (tempos / t) * vezes;
			System.out.println("Tempo estimado: " + estimado);
		}
	}

}
