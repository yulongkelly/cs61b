

public class NBody {


	public static double readRadius(String file) {
		In in = new In(file);
		in.readInt();
		double radius = in.readDouble();

		return radius;
	}// I don't want get anything out of the method, so I will use static

	public static Planet[] readPlanets(String file) {
		In in = new In(file);
		int length = in.readInt();
		in.readDouble();
		Planet[] allp = new Planet[length];

		int n = 0;
		while(n < length){
			double px = in.readDouble();
			double py = in.readDouble();
			double vx = in.readDouble();
			double vy = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			allp[n] = new Planet(px, py, vx, vy, m, img);
			n++;
		}

		return allp;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double r = NBody.readRadius(filename);
		Planet[] allp = NBody.readPlanets(filename);
		/**
		 * draw background
		 */
		StdDraw.setScale(-r, r);
		StdDraw.clear();
		StdDraw.picture(0, 0, "./images/starfield.jpg");
		//draw all th planets
		for(int n = 0; n<allp.length;n++) {
			allp[n].draw();
		}
		//Animation
		StdDraw.enableDoubleBuffering();


		for(double time = 0; time<T; time++) {
			double[] xForces = new double[allp.length];
			double[] yForces = new double[allp.length];
			// create an array which stores all x forces for each planet
			for(int m = 0; m<xForces.length; m++) {
				for (int n = 0; n < xForces.length; n++) {
					xForces[m] = allp[m].calcNetForceExertedByX(allp);
				}
			}
			// create an array which stores all y forces for each planet
			for(int m = 0; m<yForces.length; m++) {
				for (int n = 0; n < yForces.length; n++) {
					yForces[m] = allp[m].calcNetForceExertedByY(allp);
				}
			}

			// update each planet
			for(int n = 0; n<allp.length; n++) {
				allp[n].update(dt, xForces[n], yForces[n]);
			}
			StdDraw.picture(0, 0, "./images/starfield.jpg");
			for(int n = 0; n<allp.length;n++) {
					allp[n].draw();
			}
				StdDraw.show();
				StdDraw.pause(10);
				time = time + dt;
			}
		}
}
