package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;

/** An implementation of a motile pacifist photosynthesizer.
 *  @author Josh Hug
 */
public class Plip extends Creature {

    /** red color. */
    private int r = 99;
    /** green color. */
    private int g = 255;
    /** blue color. */
    private int b = 76;
    /** retained energy for itself and baby*/
    private double repEnergyRetained = 0.5;

    private double moveProbability = 0.5;

    private int colorShift = 5;

    private double maxEnergy;

    /** creates plip with energy equal to E. */
    public Plip(double e) {
        super("plip");
        energy = e;
        maxEnergy =e;
    }

    /** creates a plip with energy equal to 1. */
    public Plip() {
        this(1);
        maxEnergy = 1;
    }

    /** Should return a color with red = 99, blue = 76, and green that varies
     *  linearly based on the energy of the Plip. If the plip has zero energy,
     *  it should have a green value of 63. If it has max energy, it should
     *  have a green value of 255. The green value should vary with energy
     *  linearly in between these two extremes. It's not absolutely vital
     *  that you get this exactly correct.
     */
    @Override
    public Color color() {
        if (energy == maxEnergy) {
            g = 255;
        } else if (energy == 0) {
            g = 63;
        } else {
            g += HugLifeUtils.randomInt(-colorShift, colorShift);
            g = Math.min(g, 255);
            g = Math.max(g, 0);
        }
        return color(r, g, b);
    }

    /** Do nothing with C, Plips are pacifists. */
    @Override
    public void attack(Creature c) {
    }

    /** Plips should lose 0.15 units of energy when moving. If you want to
     *  to avoid the magic number warning, you'll need to make a
     *  private static final variable. This is not required for this lab.
     */
    @Override
    public void move() {
        energy -= 0.15;
        energy = Math.min(energy, maxEnergy);
        energy = Math.max(energy, 0);
        g += HugLifeUtils.randomInt(-colorShift, colorShift);
    }


    /** Plips gain 0.2 energy when staying due to photosynthesis. */
    @Override
    public void stay() {
        energy += 0.2;
        energy = Math.min(energy, maxEnergy);
        energy = Math.max(energy, 0);
        g += HugLifeUtils.randomInt(-colorShift, colorShift);
    }

    /** Plips and their offspring each get 50% of the energy, with none
     *  lost to the process. Now that's efficiency! Returns a baby
     *  Plip.
     */
    @Override
    public Plip replicate() {
        energy = energy * repEnergyRetained;
        g += HugLifeUtils.randomInt(-colorShift, colorShift);
        double babyEnergy = energy * repEnergyRetained;
        return new Plip(babyEnergy);

    }

    /** Plips take exactly the following actions based on NEIGHBORS:
     *  1. If no empty adjacent spaces, STAY.
     *  2. Otherwise, if energy >= 1, REPLICATE.
     *  3. Otherwise, if any Cloruses, MOVE with 50% probability.
     *  4. Otherwise, if nothing else, STAY
     *
     *  Returns an object of type Action. See Action.java for the
     *  scoop on how Actions work. See SampleCreature.chooseAction()
     *  for an example to follow.
     */
    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> cloruses = getNeighborsOfType(neighbors, "clorus");
        if(empties.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }
        if (energy >= 1) {
            Direction moveDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE, moveDir);
        }

        if (cloruses.size() >= 1 && empties.size() >= 1) {
            if (HugLifeUtils.random() < moveProbability) {
                Direction moveDir = HugLifeUtils.randomEntry(empties);
                return new Action(Action.ActionType.MOVE, moveDir);
            }
        }
        return new Action(Action.ActionType.STAY);
    }

}
