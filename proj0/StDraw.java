public class StDraw {
	public static String img;

	public static void drawbackground(double r) {
		StdDraw.setScale(-r, r);
		StdDraw.clear();
		StdDraw.picture(0, 0, "./images/starfield.jpg");
	}
}