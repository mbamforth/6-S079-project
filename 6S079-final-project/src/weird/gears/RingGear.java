package weird.gears;

// A ring gear is the outer gear in a planetary gear set
public class RingGear extends Gear {
    double originX;
    double originY;
    double radius;
    double thickness;
    Tooth innerTooth;
    Tooth outerTooth;
    boolean hasOuterTeeth;
    
    // Constructor
    public RingGear (double x, double y, double rad, double thick, Tooth innerT, 
            Tooth outerT, boolean hasOuterT) {
        originX = x;
        originY = y;
        radius = rad;
        thickness = thick;
        innerTooth = innerT;
        outerTooth = outerT;
        hasOuterTeeth = hasOuterT;
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
    public void setInnerTooth(Tooth t) {
        innerTooth = t;
    }
    
    // Gets the (inner) tooth
    @Override
    public Tooth getTooth() {
        return innerTooth;
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
    
    // Sets the outer tooth
    public void setOuterTooth(Tooth t) {
        outerTooth = t;
    }
    
    // Scales based on the radius
    public boolean radiusScale(double newRad) {
        // change teeth and radius as necessary
        
        return true;
    }
    
    // Scales based on the radius
    public boolean innerToothScale(Tooth t) {
        // change teeth as necessary
        
        return true;
    }   
    
    // Scales based on the radius
    public boolean outerToothScale(Tooth t) {
        // change teeth as necessary
        
        return true;
    }   
    
    // scales the inner teeth
    public boolean toothScale(double ang) {
        // change inner teeth as necessary
        
        return true;
    }

    @Override
    public int getNumTeeth() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setOrigin(double x, double y) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setRadius(double rad) {
        // TODO Auto-generated method stub
        
    }
}
