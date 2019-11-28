

public class Planet {
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;
	double G = 6.67 * Math.pow(10, -11);

	public Planet(double xP, double yP, double xV,
              double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double x2 = Math.pow(xxPos - p.xxPos, 2);
		double y2 = Math.pow(yyPos - p.yyPos, 2);
		double r = Math.sqrt(x2 + y2);
		return r;
	}
 
	public double calcForceExertedBy(Planet p) {
		double r = this.calcDistance(p);
		double fnet = (G * mass * p.mass)/(r*r);

		return fnet;
	}

	public double calcForceExertedByX(Planet p) {
		double forcex;
		double fnet = this.calcForceExertedBy(p);
		double r = this.calcDistance(p);
		double dx = (p.xxPos-xxPos);
		forcex = fnet*(dx/r);

		return forcex;
	}

	public double calcForceExertedByY(Planet p) {
		double forcey;
		double fnet = this.calcForceExertedBy(p);
		double r = this.calcDistance(p);
		double dy = (p.yyPos-yyPos);
		forcey = fnet*(dy/r);

		return forcey;
	}

	public double calcNetForceExertedByX(Planet[] p) {
		int n = 0;
		double fx = 0;
		while (n<p.length) {
			if(this.equals(p[n])) {

			}
			else {
				fx = fx + calcForceExertedByX(p[n]);
			}
			n++;
		}
		return fx;
	}

	public double calcNetForceExertedByY(Planet[] p) {
		int n = 0;
		double fy = 0;
		while (n<p.length) {
			if(this.equals(p[n])) {

			}
			else {
				fy = fy + calcForceExertedByY(p[n]);
			}
			n++;
		}
		return fy;
	}

	public void update(double dt, double fx, double fy) {
		double ax = fx/mass;
		double ay = fy/mass;
		xxVel = xxVel + dt*ax;
		yyVel = yyVel + dt*ay;
		xxPos = xxPos + xxVel*dt;
		yyPos = yyPos + yyVel*dt; 
	}

	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
	}

}
