package web.api;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.SMOreg;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


public class Regression {
    public static void main(String[] args) throws Exception{
        DataSource source = new DataSource("house.arff");
        Instances dataset = source.getDataSet();
        //Sets the class index of the set. If the class index is negative there is assumed to be no class.
        dataset.setClassIndex(dataset.numAttributes()-1);
        /**
         * linear regression model
         */
        //Class for using linear regression for prediction.
        //Uses the Akaike criterion for model selection, and is able to deal with weighted instances
        LinearRegression lr = new LinearRegression();
        //Builds a regression model for the given data.
        lr.buildClassifier(dataset);
        //System.out.println(lr);
        
        //Class for evaluating machine learning models
        Evaluation lreval = new Evaluation(dataset);
        //Evaluates a classifier with the options given in an array of strings
        lreval.evaluateModel(lr, dataset);
        System.out.println(lreval.toSummaryString());
        //prints a summary description of the classifier evaluation
        /**
         * svm regression model
         */
        SMOreg smoreg = new SMOreg();
        smoreg.buildClassifier(dataset);
        Evaluation svmregeval = new Evaluation(dataset);
        svmregeval.evaluateModel(smoreg, dataset);
        System.out.println(svmregeval.toSummaryString());

    }
}

//search for user shuchengc
