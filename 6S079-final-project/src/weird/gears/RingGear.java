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
        double toothWidth = 2.0*Math.PI*radius/(double)numTeeth;
        double toothAng = 2.0*Math.PI/(double)numTeeth;
        double toothLen = innerT.getLen();
        tooth = new Tooth(toothLen, toothWidth, toothAng);
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
    
    
    // scales the inner teeth
    public boolean toothScale(double ang) {
        // change teeth as necessary
        numTeeth = (int) Math.round((2*Math.PI/ang));
        double angle = 2*Math.PI/(double)numTeeth;
        double circ = 2.0*Math.PI*radius;
        double wid = circ/(double)numTeeth;
        tooth.setAng(tooth.getLen(), wid, angle);     
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
    
    @Override
    public void setThickness(double t) {
        thickness = t;
    }
}
