package iterador;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

import javax.imageio.ImageIO;

public class Itera implements Runnable {

	final static int TOTAL = 100000, TRASH = 10000;
	private static int WIDTH = 800, HEIGHT = 800;
	private double MIN_X = -2, MAX_X = 2, MIN_Y = -2, MAX_Y = 2;
	private double DIST_X = MAX_X - MIN_X;
	private double DIST_Y = MAX_Y - MIN_Y;

	private final static Color[] COLORS = { Color.RED, Color.BLUE };
	private double a;
	private double b;
	
	Itera(double a, double b) {
		this.a = a;
		this.b = b;
	}

	public void run() {
		
		BufferedImage ret = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D cg = ret.createGraphics();
		cg.setColor(Color.BLACK);
		cg.fillRect(0, 0, WIDTH, HEIGHT);

		int color = 0;

		double x = 0.4;
		double y = 0.4;
		for (int i = 0; i < TOTAL; i++) {
			double x2 = 1 - a * x * x + b * y;
			double y2 = x;
			x = x2;
			y = y2;
			if (i > TRASH) {
				if (!(x < MIN_X || x > MAX_X || y < MIN_Y || y > MAX_Y)) {
					double prop_x = (x - MIN_X) / DIST_X * WIDTH;
					double prop_y = (y - MIN_Y) / DIST_Y * HEIGHT;
					cg.setColor(COLORS[color]);
					color = (color + 1) % COLORS.length;
					cg.fillRect((int) prop_x, (int) prop_y, 1, 1);
				}
			}
		}
		try {
			ImageIO.write(ret, "png", new File("output/itera_" + a + "_" + b
					+ ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
