public class Organism {

    public double position[] = new double[2];
    public int key;
    public double birthday;

    // Initialise position of organism.
    public void initPosition() {

        double xPos = Math.random() * 1000;
        double yPos = Math.random() * 1000;

        position[0] = xPos;
        position[1] = yPos;

    }

    // Age is returned in seconds.
    public double getAge() {

        double age = System.currentTimeMillis() - birthday;
        age = age/1000;

        return age;
    }
    
    // Return the position of the organism.
    public double[] getPosition() {

        return position;
    }
}
