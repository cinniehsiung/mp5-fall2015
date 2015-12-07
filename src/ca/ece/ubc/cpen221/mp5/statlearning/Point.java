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
    
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Point){
            if(((Point) obj).getFeature() == this.feature && ((Point) obj).getRating() == this.ratingGiven){
                return true;
            }
        }
        return false;       
    }
    
    @Override
    public int hashCode(){
        return (int) (feature*ratingGiven);
    }
}
