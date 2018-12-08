import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.*;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.neighboursearch.BallTree;
import weka.filters.unsupervised.attribute.Remove;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;
import weka.core.converters.ConverterUtils.DataSource;

import java.awt.*;
import java.io.*;
import java.text.*;
import java.util.*;

import javax.swing.*;

public class DecisionTreeImpl {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub


        BufferedReader reader = null;
        BufferedReader readerTest = null;

        reader = new BufferedReader(new FileReader("iris.arff"));
        readerTest = new BufferedReader(new FileReader("iris.arff"));
        Instances train = null;
        Instances test = null;
        try {
            train = new Instances(reader);
            test = new Instances(readerTest);
            train.setClassIndex(train.numAttributes() - 1);
            test.setClassIndex(test.numAttributes() - 1);

            Classifier nb = new NaiveBayes();
            nb.buildClassifier(train);

            for(int i = 0; i < test.numInstances(); i++)
            {
                double cls = nb.classifyInstance(test.instance(i));
                test.instance(i).setClassValue(cls);
                System.out.println(cls + " -> " + test.classAttribute().value((int) cls));
            }

            System.out.println("======================================================");

            DataSource source = new DataSource("iris.arff");
            Instances dataset = source.getDataSet();
            dataset.setClassIndex(dataset.numAttributes()-1);
            NaiveBayes nb2 = new NaiveBayes();
            nb2.buildClassifier(dataset);
            Evaluation eval = new Evaluation(dataset);
            eval.evaluateModel(nb2, dataset);
            System.out.println(eval.toSummaryString());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}



