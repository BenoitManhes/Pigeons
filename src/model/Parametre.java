package model;
import java.awt.Dimension;

public class Parametre {

    // parametre de dimension
    static Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    static int height = (int)dimension.getHeight();
    static int width = (int)dimension.getWidth();

    public static final int UL = height/15;	// UL = 1 unite de longeur
    public static final int PIGEON_SIZE = UL;
    public static final int FOOD_SIZE = UL/2;
    public static final int HEIGHT = 10*UL;
    public static final int WIDTH = 15*UL;

    // parametre temporel (en ms)
    public static final int FOOD_TIME = 5000;
    public static final int FOOD_DESTROY_DELAY = 2000;
    public static final int PIGEON_DELAY = 60;

    // autre
    public static final int PIGEON_SPEED = 4;
    public static final int NB_PIGEON = 3;
    
    // distance de fuite des pigeons
    public static final int DISTANCE_FEAR_MIN = UL;
    public static final int DISTANCE_FEAR_MAX = 2*UL;
}
