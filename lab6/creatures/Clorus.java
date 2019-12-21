package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;

public class Clorus extends Creature{

    /** red color. */
    private int r = 34;
    /** green color. */
    private int g = 0;
    /** blue color. */
    private int b = 231;
    /** retained energy for itself and baby*/
    private double repEnergyRetained = 0.5;

    private double moveProbability = 0.5;

    private int colorShift = 5;

    /** creates plip with energy equal to E. */
    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    /** creates a plip with energy equal to 1. */
    public Clorus() {
        this(1);
    }

    /** Should always return a color with red = 34, blue = 231, 
      * and green = 0. */
    public Color color() {
        
        return color(r, g, b);
    }

    /** will gain C's energy */
    public void attack(Creature c) {
    	energy += c.energy();
    }

    /** Plips should lose 0.03 units of energy when moving. If you want to
     *  to avoid the magic number warning, you'll need to make a
     *  private static final variable. This is not required for this lab.
     */
    public void move() {
        energy -= 0.03;
        energy = Math.max(energy, 0);
    }


    /** Clorus lose 0.01 energy when staying due to photosynthesis. */
    public void stay() {
        energy -= 0.01;
        energy = Math.max(energy, 0);
    }

    /** Plips and their offspring each get 50% of the energy, with none
     *  lost to the process. Now that's efficiency! Returns a baby
     *  Plip.
     */
    public Clorus replicate() {
        energy = energy * repEnergyRetained;
        double babyEnergy = energy * repEnergyRetained;
        return new Clorus(babyEnergy);

    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> plips = getNeighborsOfType(neighbors, "plip");
        if(empties.size() == 0) {
        	return new Action(Action.ActionType.STAY);
        }
        else if (plips.size() >= 1) {
        	Direction target = HugLifeUtils.randomEntry(plips);
            return new Action(Action.ActionType.ATTACK, target);
        }
        else if (energy >= 1) {
            Direction moveDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE, moveDir);
        }
        else {
            Direction moveDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE, moveDir);
        }
    }
}