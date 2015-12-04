package ca.ece.ubc.cpen221.mp5.statlearning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ca.ece.ubc.cpen221.mp5.Restaurant;
import ca.ece.ubc.cpen221.mp5.Review;
import ca.ece.ubc.cpen221.mp5.User;
import ca.ece.ubc.cpen221.mp5.server.RestaurantDB;

public class AlgorithmsLeastSquares {
    private final static int SXX_INDEX = 0;
    private final static int SYY_INDEX = 1;
    private final static int SXY_INDEX = 2;

    /**
     * This method implements a least-squares linear regression to approximate
     * the relationship between the input feature function and the ratings given
     * by the given User.
     * 
     * @param u
     *            User for whom we want to predict ratings.
     * @param db
     *            Database containing all the restaurants, users, and reviews.
     * @param featureFunction
     *            MP5Function for finding a given feature (eg. price)
     * @return a feature function that predicts the users ratings as well as a
     *         regression quality estimate (r_squared).
     * 
     */
    public static MP5Function getPredictor(User u, RestaurantDB db, MP5Function featureFunction) {
        String userID = u.getUserID();
        List<Restaurant> allRestaurants = db.getAllRestaurantDetails();
        List<Review> allReviews = db.getAllReviewDetails();

        List<Point> allPoints = new LinkedList<Point>();

        // iterate through all reviews to find reviews written by the given user
        for (Review currentReview : allReviews) {
            if (currentReview.getUserID().equals(userID)) {
                // once we found a review, find the business id of the
                // restaurant the user reviewed and find the rating
                // the user gave this retaurant
                String currentBusinessID = currentReview.getBusinessID();
                Restaurant currentRestaurant = db.findRestaurantIterator(currentBusinessID);

                // feature of the given restaurant we want to analyze (eg.
                // price)
                double feature = featureFunction.f(currentRestaurant, db);
                // rating given by the given user (in stars)
                double ratingGivenByUser = currentReview.getStars();

                Point newPoint = new Point(feature, ratingGivenByUser);
                allPoints.add(newPoint);

            }

        }

        double sumOfSquaresXX;
        double sumOfSquaresYY;
        double sumOfSquaresXY;

        double b;
        double a;
        double rSquared;

        PredictorFF predictorFunction;

        // If allPoints is empty, or if there is only one point, what do we do?
        Point meanPoint = calculateMeans(allPoints);

        List<Double> sumOfSquares = calculateSumOfSquares(meanPoint, allPoints);
        sumOfSquaresXX = sumOfSquares.get(SXX_INDEX);
        sumOfSquaresYY = sumOfSquares.get(SYY_INDEX);
        sumOfSquaresXY = sumOfSquares.get(SXY_INDEX);

        b = sumOfSquaresXY / sumOfSquaresXX;
        a = meanPoint.getRating() - b * meanPoint.getFeature();

        rSquared = Math.pow(sumOfSquaresXY, 2) / (sumOfSquaresXX * sumOfSquaresYY);

        predictorFunction = new PredictorFF(a, b, rSquared, featureFunction);

        return predictorFunction;
    }

    /**
     * This method takes a user and a list of feature function, and returns the feature function
     * with the highest r_squared value.
     * 
     * @param u
     *          User that we want to find the best predictor for.
     * @param db
     *          Database that the User and Restaurants are in.
     * @param featureFunctionList
     *          A List of MP5Functions from which we want to calculate the best predictor.
     *          
     * @return a MP5Function that is the best predictor for the give User (lowest r_squared value). If there are 
     *          multiple featureFunctions that produce the same r_squared, it will return the first featureFunction
     *          in the given featureFunctionList.
     */
    public static MP5Function getBestPredictor(User u, RestaurantDB db, List<MP5Function> featureFunctionList) {
        MP5Function bestPredictor = null;
        
        double rSquared = 0.0;
        
        for(MP5Function currentFunction : featureFunctionList){
            PredictorFF predictorFF = (PredictorFF) getPredictor(u, db, currentFunction);
            if(predictorFF.getRSquared() > rSquared){
                rSquared = predictorFF.getRSquared();
                bestPredictor = predictorFF;
            }
        }
        
        
        return bestPredictor;
    }

    /**
     * This method calculates the mean x (feature) and mean y (rating) value
     * given a List of Points.
     * 
     * @param points
     *            A List of Points we want to find the means of, cannot be an
     *            empty List.
     * @return a Point representing the mean values of x and y of the given List
     *         of Points.
     */
    public static Point calculateMeans(List<Point> points) {
        double sumOfX = 0.0;
        double sumOfY = 0.0;
        double numOfPoints = points.size();

        for (Point currentPoint : points) {
            sumOfX += currentPoint.getFeature();
            sumOfY += currentPoint.getRating();
        }

        return new Point(sumOfX / numOfPoints, sumOfY / numOfPoints);
    }

    /**
     * This method calculates the sum of squares of the given set of points
     * relative to the given mean point.
     * 
     * @param meanPoint
     *            a Point representing the average of all the points in the
     *            points List
     * @param points
     *            all the points we want to find the sum of squares of
     * @return a List of Doubles representing the Sxx, Syy, Sxy
     */
    public static List<Double> calculateSumOfSquares(Point meanPoint, List<Point> points) {
        List<Double> sumOfSquares = new ArrayList<Double>();

        double Sxx = 0.0;
        double Syy = 0.0;
        double Sxy = 0.0;

        double yi = 0.0;
        double xi = 0.0;

        double xMean = meanPoint.getFeature();
        double yMean = meanPoint.getRating();

        for (Point currentPoint : points) {
            xi = currentPoint.getFeature();
            yi = currentPoint.getRating();

            Sxx += (xi - xMean);
            Syy += (yi - yMean);
            Sxy += (xi - xMean) * (yi - yMean);

        }

        Sxx = Math.pow(Sxx, 2);
        Syy = Math.pow(Syy, 2);

        sumOfSquares.add(SXX_INDEX, Sxx);
        sumOfSquares.add(SYY_INDEX, Syy);
        sumOfSquares.add(SXY_INDEX, Sxy);

        return Collections.unmodifiableList(sumOfSquares);

    }
}
