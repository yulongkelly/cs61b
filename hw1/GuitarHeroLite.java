/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHeroLite {
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);
    private static final double CONCERT_D = 110;
    private static final double CONCERT_E = 220;
    private static final double CONCERT_F = 880;


    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        synthesizer.GuitarString stringA = new synthesizer.GuitarString(CONCERT_A);
        synthesizer.GuitarString stringC = new synthesizer.GuitarString(CONCERT_C);
        synthesizer.GuitarString stringD = new synthesizer.GuitarString(CONCERT_D);
        synthesizer.GuitarString stringE = new synthesizer.GuitarString(CONCERT_E);
        synthesizer.GuitarString stringF = new synthesizer.GuitarString(CONCERT_F);


        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (key == 'a') {
                    stringA.pluck();
                } else if (key == 'c') {
                    stringC.pluck();
                } else if (key == 'd') {
                    stringD.pluck();
                } else if (key == 'e') {
                    stringE.pluck();
                } else if (key == 'f') {
                    stringF.pluck();
                } 
            }

        /* compute the superposition of samples */
            double sample = stringA.sample() + stringC.sample() + stringD.sample()+stringE.sample()+stringF.sample();

        /* play the sample on standard audio */
            StdAudio.play(sample);

        /* advance the simulation of each guitar string by one step */
            stringA.tic();
            stringC.tic();
            stringD.tic();
            stringE.tic();
            stringF.tic();
        }
    }
}

