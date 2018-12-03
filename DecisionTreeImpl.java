import weka.classifiers.Evaluation;
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

        reader = new BufferedReader(new FileReader("HR-Em1.arff"));
        readerTest = new BufferedReader(new FileReader("test1.arff"));
        Instances train = null;
        Instances test = null;
        try {
            train = new Instances(reader);
            test = new Instances(readerTest);
            train.setClassIndex(train.numAttributes() - 20);
            test.setClassIndex(test.numAttributes() - 20);

            String[] options = new String[1];
            options[0] = "-U";

            J48 j48Tree = new J48();
            j48Tree.setOptions(options);
            j48Tree.buildClassifier(train);
            System.out.println("J48 Tree!!!");
            System.out.println(j48Tree.toString());

            RandomForest ranForest = new RandomForest();
            ranForest.setOptions(options);
            ranForest.buildClassifier(train);
            System.out.println("ramForest");
            System.out.println(ranForest.toString());

            REPTree repTree = new REPTree();
            repTree.setOptions(options);
            repTree.buildClassifier(train);
            System.out.println("REPTree");
            System.out.println(repTree.toString());


            Evaluation evalJ48Tree = new Evaluation(train);
            Evaluation evalranForest = new Evaluation(train);
            Evaluation evalRepTree = new Evaluation(train);

            evalJ48Tree.evaluateModel(j48Tree, test);
            System.out.println(evalJ48Tree.toSummaryString("\nResults\n======\n", false).substring(0, 83));

            evalranForest.evaluateModel(ranForest, test);
            System.out.println(evalranForest.toSummaryString("\nResults\n======\n", false).substring(0, 83));

            evalRepTree.evaluateModel(repTree, test);
            System.out.println(evalRepTree.toSummaryString("\nResults\n======\n", false).substring(0, 83));

            // display classifier
            final javax.swing.JFrame jf =
                    new javax.swing.JFrame("Weka Classifier Tree Visualizer: J48");
            jf.setSize(500, 400);
            jf.getContentPane().setLayout(new BorderLayout());
            TreeVisualizer tv = new TreeVisualizer(null,
                    j48Tree.graph(),
                    new PlaceNode2());
            jf.getContentPane().add(tv, BorderLayout.CENTER);
            jf.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosing(java.awt.event.WindowEvent e) {
                    jf.dispose();
                }
            });

            jf.setVisible(true);
            tv.fitToScreen();

            RandomForest rf = new RandomForest();
            Remove rm = new Remove();
            rm.setAttributeIndices("1");
            FilteredClassifier fc = new FilteredClassifier();
            fc.setFilter(rm);
            fc.setClassifier(rf);
            try {
                fc.buildClassifier(test);
                for (int i = 0; i < test.numInstances(); i++) {
                    test.instance(i).setClassMissing();
                    double pred = fc.classifyInstance(test.instance(i));
                    System.out.print("given value: " + test.classAttribute().value((int) test.instance(i).classValue()));
                    System.out.println("---predicted value: " + test.classAttribute().value((int) pred));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

/*
RandomTree
RandomForest
LMT
DecisionStump
*/


/*//create and build the classifier
            j48Tree = new J48();

                    j48Tree.buildClassifier(test);

                    //load new dataset
                    DataSource source1 = new DataSource("test1.arff");
                    Instances test1 = source1.getDataSet();

                    //set class index to the last attribute
                    test1.setClassIndex(test1.numAttributes() - 1);

                    //loop through the new dataset and make predictions
                    System.out.println(test1.numInstances());
                    for (int i = 0; i < test1.numInstances(); i++) {

        test1.instance(i).setClassMissing();
        //call classifyInstance, which returns a double value for the class
        double cls = j48Tree.classifyInstance(test1.instance(i));
        test1.instance(i).setClassValue(cls);
        if (cls > 0) {
        System.out.println("true");
        } else
        System.out.println("false");
        }*/





