package weird.gears;

// spline object
public class Spline {
    public static double[][] points;
    public static final double[][] bSplineCoefficients = {{-1./6 ,  3./6 , -3./6 , 1./6 },
                                                    { 3./6 , -6./6 ,  3./6 , 0.   },
                                                    {-3./6 ,  0.   ,  3./6 , 0.   },
                                                    { 1./6 ,  4./6 ,  1./6 , 0.   }};
    
    // creates b-spline
    public Spline(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        points = new double[4][2];
        points[0][0] = x1;
        points[0][1] = y1;
        points[1][0] = x2;
        points[1][1] = y2; 
        points[2][0] = x3;
        points[2][1] = y3; 
        points[3][0] = x4;
        points[3][1] = y4; 
    }
    
    // returns the points
    public double[][] getPoints() {
        return points;
    }
}