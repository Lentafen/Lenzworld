public class Plant extends Organism {

    public Plant(int newKey) {
        
        initPosition();
        birthday = System.currentTimeMillis(); 
        key = newKey;
    }

    public Plant(double xPos, double yPos, int newKey) {
        
        position[0] = xPos;
        position[1] = yPos;

        birthday = System.currentTimeMillis(); 
        key = newKey;
    }
}