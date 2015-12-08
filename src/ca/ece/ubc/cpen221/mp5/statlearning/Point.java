package ca.ece.ubc.cpen221.mp5.statlearning;

public class Point {
    private final double ratingGiven;
    private final double feature;
    //Rep Invariant: 0.0 <= ratingGiven <= 5.0
    
    /**
     * Constructor for the Point class - used to represent a point on a regression graph, where
     * x is the input feature value (eg. price) and y is the output rating
     * @param feature 
     *          the x value of a Point (value of a feature)
     * @param ratingGiven 
     *          the y value of a Point (rating)
     */
    public Point(double feature, double ratingGiven){
        this.ratingGiven = ratingGiven;
        this.feature = feature;
    }
    
    /**
     * This method returns the rating of a Point (the y value)
     * @return a double representing the rating of a Point
     */
    public double getRating(){
        return ratingGiven;
    }
    
    /**
     * This method returns the feature of a Point (the x value)
     * 
     * @return a double representing the feature of a Point
     */
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
