package ca.ece.ubc.cpen221.mp5.statlearning;

public class Point {
    private final double ratingGiven;
    private final double feature;
    
    public Point(double feature, double ratingGiven){
        this.ratingGiven = ratingGiven;
        this.feature = feature;
    }
    
    public double getRating(){
        return ratingGiven;
    }
    
    public double getFeature(){
        return feature;
    }
    
}
