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
        double toothLen = 2.0;
        tooth = new Tooth(toothLen, 2.5, 0.2);
        numTeeth = (int) (2.0*Math.PI/tooth.getAng());
        double toothWidth = 2.0*Math.PI*radius/(double)numTeeth;
        double toothAng = 2.0*Math.PI/(double)numTeeth;
        tooth = new Tooth(toothLen, toothWidth, toothAng);
    }
    
    // Constructor with pre-made Tooth
    public RoundGear (double x, double y, double rad, Tooth t) {
        originX = x;
        originY = y;
        radius = rad;
        numTeeth = (int) (2.0*Math.PI/t.getAng());
        double toothWidth = 2.0*Math.PI*radius/(double)numTeeth;
        double toothAng = 2.0*Math.PI/(double)numTeeth;
        tooth = new Tooth(t.getLen(), toothWidth, toothAng);
    }
    
    // Constructor with # teeth
    public RoundGear (double x, double y, double rad, int teeth) {
        originX = x;
        originY = y;
        radius = rad;
        numTeeth = teeth;
        double toothLen = (radius / 4.0);
        double toothWidth = 2.0*Math.PI*radius/(double)numTeeth;
        double toothAng = 2.0*Math.PI/(double)numTeeth;
        tooth = new Tooth(toothLen, toothWidth, toothAng);
        radiusScale(rad);
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
    
    // Gets the tooth
    @Override
    public double getRadius() {
        return radius;
    }  
    
    // Gets the x
    @Override
    public double getX() {
        return originX;
    }  
    
    // Gets the y
    @Override
    public double getY() {
        return originY;
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
        double circum = radius*2.0*Math.PI;
        double teeth = circum/toothWidth;
        numTeeth = (int) Math.round(teeth);
        double desiredCirc = (double)numTeeth*toothWidth;
        radius = desiredCirc/(2*Math.PI);
        double toothAng = 2.0*Math.PI*toothWidth/desiredCirc;
        tooth = new Tooth(tooth.getLen(), toothWidth, toothAng);
        return true;
    }
    
    // Scales based on the tooth
    public boolean toothScale(double ang) {
        // change teeth as necessary
        numTeeth = (int) Math.round((2*Math.PI/ang));
        double angle = 2*Math.PI/(double)numTeeth;
        double circ = 2.0*Math.PI*radius;
        double wid = circ/(double)numTeeth;
        tooth.setAng(tooth.getLen(), wid, angle);     
        return true;
    } 
}
