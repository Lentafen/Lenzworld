import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;

public class Lenzworld {

    static HashMap<Integer, Organism> ecosystem = new HashMap<Integer, Organism>();
    static ArrayList<ArrayList<Integer>> distances;      // distances between organisms
    static int n = 0;      // number of organisms

    public static void main(String[] args) {

        distances = new ArrayList<ArrayList<Integer>>();

        insertOrganism(new Fox(n));
        insertOrganism(new Rabbit(n));
        insertOrganism(new Plant(n));

        Gui.initGui();

        boolean simulating = true;

        while (simulating) {

            double loopStart = System.currentTimeMillis();

            for(int i = 0; i < n; i++) {
                
                updateRelativeDistances(i);
                Organism currentOrganism = ecosystem.get(i);

                if (currentOrganism instanceof Fox) {
                    ((Fox) currentOrganism).determineResponses();

                } else if (currentOrganism instanceof Rabbit) {
                    ((Rabbit) currentOrganism).determineResponses();
                }
            }
            StdDraw.show();

            double loopTime = System.currentTimeMillis() - loopStart;
            while (loopTime <= 100) {
                loopTime = System.currentTimeMillis() - loopStart;
            }
        }
    }
    // inserts a given organism into the ecosystem hashMap'
    public static void insertOrganism(Organism organism) {
        
        int key = n;

        ArrayList<Integer> dist = new ArrayList<Integer>();
        dist.ensureCapacity((n + 1) * 2);
        distances.add(key, dist);

        ecosystem.put(key, organism);
        updateRelativeDistances(key);
        if (n > 0) {
            distancesCapacityCheck();
        }
        n++;

    }

    // Takes the array with all organisms and calculates the distance of the indexed organism from all other.
    public static void updateRelativeDistances(int key) {

        for(int i = 0; i <= n; i ++) {

            double distance = calculateDistance(key, i);
            distances.get(key).add(i, Math.round((float)distance));
            distances.get(i).add(key, Math.round((float)distance));
        }
    }

    // Takes 2 indexes in the ecosystem and calculates their distance form each other.
    private static double calculateDistance(int key1, int key2) {

        Organism organism1 =  ecosystem.get(key1);
        Organism organism2 = ecosystem.get(key2);

        double[] position1 = organism1.position;
        double[] position2 = organism2.position;

        double x = Math.abs(position1[0] - position2[0]);
        double y = Math.abs(position1[1] - position2[1]);

        double distance = Math.sqrt(x*x + y*y);

        return distance;
       
    }

    public static void distancesCapacityCheck() {
        
        double capacityRatio = (double) n / distances.size();
        if (capacityRatio >= 0.75) {

            for (int i = 0; i <= n; i ++) {
                distances.get(i).ensureCapacity(n * 2); 
            }
            distances.ensureCapacity(n * 2);
        }
    }
    // sorts the surroundings linked list of organism from closest to furthes surrounding organsim.
    public static LinkedList<int[]> sortSurroundings(LinkedList<int[]> surroundings) {
        
        int size = surroundings.size();

        for (int j = 1; j < size; j++) {

            int value = surroundings.get(j)[1];
            int i = j - 1;

            while ((i > -1) && (surroundings.get(i)[1] > value)) {

                surroundings.set(i + 1, surroundings.get(i));
                i--;
            }

            surroundings.set(i + 1, surroundings.get(j)); 
        }

        return surroundings;
    }

    // Prints distances between all organisms on map
    public static void printDistances() {

         for(int i = 0; i < n; i++) {

            System.out.println("Organism " + i + ":");
            System.out.println();
            System.out.println("    pos: " + ecosystem.get(i).position[0] + ", " + ecosystem.get(i).position[1]);
            System.out.println();
            System.out.println("    distances:");
            for (int j = 0; j < n; j++) {
                System.out.println("        " + j + ": " + distances.get(i).get(j));
            }
        }
        System.out.println();
        System.out.println("########################");
        System.out.println();
    }
}
