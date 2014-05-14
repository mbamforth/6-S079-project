package weird.gears;

// abstract class for all gear objects
public abstract class Gear {
    // constructor
    public Gear() {
        
    }
    // returns the tooth of a regular gear or null if a weird gear
    public abstract Tooth getTooth();
    
    // scales the radius
    public abstract boolean radiusScale(double val);
    
    // scales the tooth
    public abstract boolean toothScale(double ang);
    
    // gets the number of teeth
    public abstract int getNumTeeth();
    
    // gets the radius if there is one
    public abstract double getRadius();
}
