import java.util.LinkedList;

public class Rabbit extends Mamal{

    // Position of new rabbit is assigned randomly.
    public Rabbit(int newKey) {
        
        initPosition();
        birthday = System.currentTimeMillis();
        key = newKey;

        survivalResponse = new boolean[3]; // flight, eat/drink, breed.
        survivalResponse[0] = false;
        survivalResponse[1] = false;
        survivalResponse[2] = false;
    }

    // Construct new rabbit after breeding with parants' position.
    public Rabbit(double xPos, double yPos, int newKey) {

        position[0] = xPos;
        position[1] = yPos;

        birthday = System.currentTimeMillis();

        key = newKey;

        survivalResponse = new boolean[3]; // flight, eat/drink, breed.
        survivalResponse[0] = false;
        survivalResponse[1] = false;
        survivalResponse[2] = false;
    }
    
    // Populates survival responses array based on surroundings.
    public void determineResponses() {
        
        LinkedList<int[]> surroundings = senseSurroundings();

        for (int i = 0; i < surroundings.size(); i++) {
            
            int surroundingKey = surroundings.get(i)[0];

            if(Lenzworld.ecosystem.get(surroundingKey) instanceof Fox) {
                survivalResponse[0] = true;
                break;

            } else if(Lenzworld.ecosystem.get(surroundingKey) instanceof Plant) {
                survivalResponse[1] = true;

            } else if(Lenzworld.ecosystem.get(surroundingKey) instanceof Rabbit) {
                survivalResponse[2] = true;
            }
        }
        if (survivalResponse[0]) {

            flightResponse(surroundings);

        } else walk();
    }

    // Flees from fox if in vicinity
    public void flightResponse(LinkedList<int[]> surroundings) {

        for (int i = 0; i < surroundings.size(); i++) {

            int surroundingKey = surroundings.get(i)[0];
            if(Lenzworld.ecosystem.get(surroundingKey) instanceof Fox) {

                double[] foxPosition = Lenzworld.ecosystem.get(surroundingKey).position;
                double[] rabbitPosition = Lenzworld.ecosystem.get(key).position;

                double[] foxRrabbit = new double[2];
                foxRrabbit[0] = foxPosition[0] - rabbitPosition[0] ;
                foxRrabbit[1] = foxPosition[1] - rabbitPosition[1];

                velocity[0] = -maxVelocity * (foxRrabbit[0] / Math.abs(foxRrabbit[0]));
                velocity[1] = -maxVelocity * (foxRrabbit[1] / Math.abs(foxRrabbit[1]));

                Gui.earaseOrganism(Lenzworld.ecosystem.get(key));
                updatePosition();
                Gui.drawOrganism(Lenzworld.ecosystem.get(key));


                break;

            }

        }
    }
}