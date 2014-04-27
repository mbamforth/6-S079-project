package weird.gears;

// A tooth is a little thing that sticks out from a gear.
public class Tooth {
    private double length;
    private double width;
    // in radians because that's what Java.lang.math uses for its trig functions
    private double angle;
    private Spline spline;
    
    // Tooth constructor
    public Tooth(double len, double wid, double ang) {
        length = len;
        width = wid;
        angle = ang;
        double x1 = 0;
        double y1 = 0;
        double x2 = 0.6*width/2.0;
        double y2 = 0;
        double x3 = 0.3*width/2.0;
        double y3 = length;
        double x4 = width/2.0;
        double y4 = length;
        spline = new Spline(x1, y1, x2, y2, x3, y3, x4, y4);
    }

    // returns the angle that the tooth takes up
    public double getAng() {
        return angle;
    }
    
    // returns the width of the tooth
    public double getWidth() {
        return width;
    }
    
    // returns the length of the tooth
    public double getLen() {
        return length;
    }
    
    // returns the spline of the tooth
    public Spline getToothSpline() {
        return spline;
    }
    
    // changes the tooth based on the new angle
    public void setAng(double ang, double rad) {
        double numTeeth = 2*Math.PI/ang;
        width = 2*Math.PI*rad/numTeeth;
        angle = ang;
        double x1 = 0;
        double y1 = 0;
        double x2 = 0.6*width/2.0;
        double y2 = 0;
        double x3 = 0.3*width/2.0;
        double y3 = length;
        double x4 = width/2.0;
        double y4 = length;
        spline = new Spline(x1, y1, x2, y2, x3, y3, x4, y4);
    }
}
