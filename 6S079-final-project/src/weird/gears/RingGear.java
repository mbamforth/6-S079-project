package weird.gears;

// A ring gear is the outer gear in a planetary gear set
public class RingGear extends Gear {
    double originX;
    double originY;
    double radius;
    double thickness;
    int numTeeth;
    Tooth tooth;
    
    // Constructor
    public RingGear (double x, double y, double rad, double thick, Tooth innerT) {
        originX = x;
        originY = y;
        radius = rad;
        thickness = thick;
        tooth = innerT;
        numTeeth = (int) (2.0*Math.PI/tooth.getAng());
    }
    
    // Sets the origin
    public void setOrigin(float x, float y) {
        originX = x;
        originY = y;
    }
    
    // Sets the radius
    public void setRadius(float r) {
        radius = r;
    }
    
    // Sets the inner tooth
    public void setTooth(Tooth t) {
        tooth = t;
    }
    
    // Gets the (inner) tooth
    @Override
    public Tooth getTooth() {
        return tooth;
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
    
    // Gets the tooth
    @Override
    public double getRadius() {
        return radius;
    }  

    
    // Scales based on the radius
    public boolean radiusScale(double newRad) {
        // change teeth and radius as necessary
        
        return true;
    }
    
    
    // scales the inner teeth
    public boolean toothScale(double ang) {
        // change inner teeth as necessary
        
        return true;
    }

    @Override
    public int getNumTeeth() {
        return numTeeth;
    }

    @Override
    public void setOrigin(double x, double y) {
        originX = x;
        originY = y;
    }

    @Override
    public void setRadius(double rad) {
        radius = rad;
        
    }

    @Override
    public double getThickness() {
        return thickness;
    }
}
