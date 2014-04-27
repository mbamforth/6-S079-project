package weird.gears;

// A round gear is a regular, circular gear
public class RoundGear extends Gear {
    double originX;
    double originY;
    double radius;
    Tooth tooth;
    int numTeeth;
    
    // empty constructor for testing things
    public RoundGear() {
        originX = 0.0;
        originY = 0.0;
        radius = 10.0;
        tooth = new Tooth(2.0, 2.5, 0.2);
        numTeeth = (int) (2*Math.PI/tooth.getAng());
    }
    
    // Constructor with pre-made Tooth
    public RoundGear (double x, double y, double rad, Tooth t) {
        originX = x;
        originY = y;
        radius = rad;
        tooth = t;
        numTeeth = (int) (2*Math.PI/tooth.getAng());
    }
    
    // Constructor with # teeth
    public RoundGear (double x, double y, double rad, int teeth) {
        originX = x;
        originY = y;
        radius = rad;
        numTeeth = teeth;
        double toothLen = (radius / 5.0);
        double toothAng = 2*Math.PI/teeth;
        double toothWidth = 2*Math.PI*radius/teeth;
        tooth = new Tooth(toothLen, toothWidth, toothAng);
    }
    
    // Sets the origin
    public void setOrigin(double x, double y) {
        originX = x;
        originY = y;
    }
    
    // Sets the radius
    public void setRadius(double r) {
        radius = r;
    }
    
    // Sets the tooth
    public void setTooth(Tooth t) {
        tooth = t;
    }
    
    // Gets the tooth
    @Override
    public Tooth getTooth() {
        return tooth;
    }   
    
    // returns the number of teeth
    public int getNumTeeth() {
        return numTeeth;
    }
    
    // Scales based on the radius
    public boolean radiusScale(double newRad) {
        // change teeth and radius as necessary
        radius = newRad;
        double toothWidth = tooth.getWidth();
        double circum = radius*2*Math.PI;
        double teeth = circum/toothWidth;
        numTeeth = (int) teeth;
        return true;
    }
    
    // Scales based on the tooth
    public boolean toothScale(double ang) {
        // change teeth as necessary
        tooth.setAng(ang, radius);
        numTeeth = (int) (2*Math.PI/ang);
        return true;
    } 
}
