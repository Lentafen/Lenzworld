import java.awt.Color;

public class Gui {

    static Color grassColor = new Color(144, 238, 144);
    static Color plantColor = new Color(0, 128, 0);
    static Color rabbitColor = new Color(119, 107, 98);
    static Color foxColor = new Color(255, 92, 0);
    
    // Initiates Gui an grass background.
    public static void initGui() {
        
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(900, 900);
        StdDraw.setXscale(0, 1000);
        StdDraw.setYscale(0, 1000);
        StdDraw.setPenColor(grassColor);
        StdDraw.filledSquare(500.0, 500.0, 500.0);
        initOrganisms();
        StdDraw.show();
    }

    public static void initOrganisms() {
        for (int i = 0; i < Lenzworld.n; i++) {
            drawOrganism(Lenzworld.ecosystem.get(i));
        }
    }

    // Draws a single organism onto canvas.
    public static void drawOrganism(Organism organism) {

        if (organism instanceof Fox) {
            StdDraw.setPenColor(foxColor);
        } else if (organism instanceof Rabbit) {
            StdDraw.setPenColor(rabbitColor);
        } else if (organism instanceof Plant) {
            StdDraw.setPenColor(plantColor);
        }

        StdDraw.filledCircle(organism.position[0], organism.position[1], 5);
    }

    // Earases an organism from the canvas.
    public static void earaseOrganism(Organism organism) {

        StdDraw.setPenColor(grassColor);
        StdDraw.filledCircle(organism.position[0], organism.position[1], 6);

    }

    // Animates the movents of the organism by erasing its prviouse self and re drawing in new position.
    public static void animateMovement(Organism organism, boolean updateVelocity) {

        Gui.earaseOrganism(organism);
        if (updateVelocity) ((Mamal) organism).updateVelocity();
        ((Mamal) organism).updatePosition();
        Gui.drawOrganism(organism);
    }

    public static void main(String[] args) {
        initGui();
    }
}
