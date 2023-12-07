import java.util.LinkedList;

public class Fox extends Mamal{

    // Position of new fox is assigned randomly.
    public Fox(int newKey) {
        
        initPosition();
        initVelocity();
        birthday = System.currentTimeMillis();
        key = newKey;

        survivalResponse = new boolean[2]; // eat/drink, breed.
        survivalResponse[0] = false;
        survivalResponse[1] = false;
    }

    // Construct new fox after breeding with parants' position.
    public Fox(double xPos, double yPos, int newKey) {

        position[0] = xPos;
        position[1] = yPos;

        initVelocity();

        birthday = System.currentTimeMillis();
        key = newKey;

        survivalResponse = new boolean[2]; // eat/drink, breed.
        survivalResponse[0] = false;
        survivalResponse[1] = false;
    }

    // Populates survival responses array based on surroundings.
    public void determineResponses() {
        
        LinkedList<int[]> surroundings = senseSurroundings();

        for (int i = 0; i < surroundings.size(); i++) {
            
            int surroundingKey = surroundings.get(i)[0];

            if(Lenzworld.ecosystem.get(surroundingKey) instanceof Rabbit) {
                survivalResponse[0] = true;

            } else if(Lenzworld.ecosystem.get(surroundingKey) instanceof Fox) {
                survivalResponse[1] = true;
            }
        } 
        if (survivalResponse[0]) {
            eatResponse(surroundings);
            
        } else walk();
    }

    // Flees from fox if in vicinity
    public void eatResponse(LinkedList<int[]> surroundings) {

        for (int i = 0; i < surroundings.size(); i++) {

            int surroundingKey = surroundings.get(i)[0];
            if(Lenzworld.ecosystem.get(surroundingKey) instanceof Rabbit) {

                double[] foxPosition = Lenzworld.ecosystem.get(key).position;
                double[] rabbitPosition = Lenzworld.ecosystem.get(surroundingKey).position;

                double[] rabbitRfox = new double[2];
                rabbitRfox[0] = rabbitPosition[0] - foxPosition[0];
                rabbitRfox[1] = rabbitPosition[1] - foxPosition[1];

                velocity[0] = maxVelocity * (rabbitRfox[0] / Math.abs(rabbitRfox[0]));
                System.out.println("fox new vel: " + velocity[0]);
                velocity[1] = maxVelocity * (rabbitRfox[1] / Math.abs(rabbitRfox[1]));

                Gui.animateMovement(Lenzworld.ecosystem.get(key), true);

                break;
            }

        }
    }
}