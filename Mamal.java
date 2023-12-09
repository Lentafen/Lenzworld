import java.util.Random;
import java.util.LinkedList;

public class Mamal extends Organism {

    public double velocity[] = new double[2];
    public double acceleration[] = new double[2];

    public int maxVelocity = 5;
    public int maxAcceleration = 1;

    public int hunger;
    public int thirst;

    public int sensoryRange = 130;

    // Surval responses of surroundings in decending priority.
    public boolean[] survivalResponse;

    // Initialises a random velocity for when a new Mamal is constructed.
    public void initVelocity() {

        Random myRandom = new Random();
        double xVel = (myRandom.nextGaussian());
        double yVel = (myRandom.nextGaussian());

        if (xVel < maxVelocity && xVel > maxVelocity * -1) velocity[0] = xVel;
        if (yVel < maxVelocity && yVel > maxVelocity * -1) velocity[1] = yVel;
    }

    // Changes direction in a normally distrubuted fassion, distributed aroung the current direction.
    public void walk() {

        Random myRandom = new Random();

        Double xAcc = (myRandom.nextGaussian());
        if (xAcc < maxAcceleration && xAcc > maxAcceleration * -1) acceleration[0] = xAcc;

        Double yAcc = (myRandom.nextGaussian());
        if (yAcc < maxAcceleration && yAcc > maxAcceleration * -1) acceleration[1] = yAcc;

        Gui.animateMovement(Lenzworld.ecosystem.get(key), true);

    }

    // Updates velocity based on acceleration.
    public void updateVelocity() {

        double xVel = velocity[0] + acceleration[0];
        double yVel = velocity[1] + acceleration[1];

        if (xVel < maxVelocity && xVel > maxVelocity * -1) velocity[0] = xVel;
        if (yVel < maxVelocity && yVel > maxVelocity * -1) velocity[1] = yVel;

    }

    // Updates positions based on velocity.
    public void updatePosition() {

        position[0] = position[0] + velocity[0];
        position[1] = position[1] + velocity[1];

        if (position[0] <= 10 || position[0] >= 990) {
            velocity[0] = velocity[0] * -1;
            acceleration[0] = acceleration[0] * -1;
        }

        if (position[1] <= 10 || position[1] >= 990) {
            velocity[1] = velocity[1] * -1;
            acceleration[1] = acceleration[1] * -1;
        }

        Lenzworld.updateRelativeDistances(key);
    }

    // Checks what organisms are in sensory range from the distances arraylist. returns arraylist of in-range surroundings.
    public LinkedList<int[]> senseSurroundings() {

        LinkedList<int[]> surroundings = new LinkedList<int[]>();
        for (int i = 0; i < Lenzworld.n; i++) {
            if (Lenzworld.distances[key][i] <= sensoryRange && key != i) {
                int[] surrounding = new int[2];
                surrounding[0] = i;
                surrounding[1] = Lenzworld.distances[key][i];

                surroundings.add(surrounding);
            }
        }

        surroundings = Lenzworld.sortSurroundings(surroundings);

        return surroundings;
        
    }

    public int getHunger() {
        return hunger;
    }

    public int getThirst() {
        return thirst;
    }

}
