import weka.classifiers.Evaluation;
import weka.classifiers.trees.ADTree;
import weka.classifiers.trees.BFTree;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.REPTree;
import weka.core.Instances;
import weka.core.neighboursearch.BallTree;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

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

        reader = new BufferedReader(new FileReader("hr.arff"));
        readerTest = new BufferedReader(new FileReader("test.arff"));
        Instances train = null;
        Instances test = null;
        try {
            train = new Instances(reader);
            test = new Instances(readerTest);
            train.setClassIndex(train.numAttributes() - );
            test.setClassIndex(test.numAttributes() - 1);

            String[] options = new String[1];
            options[0] = "-U";

            J48 j48Tree = new J48();
            j48Tree.setOptions(options);
            j48Tree.buildClassifier(train);
            System.out.println("J48 Tree!!!");
            System.out.println(j48Tree.toString());

//            BFTree ballTree = new BFTree();
//            ballTree.setOptions(options);
//            ballTree.buildClassifier(train);
//            System.out.println("ADTree Tree!!!");
//            System.out.println(ballTree.toString());

//            HoeffdingTree hofTree = new HoeffdingTree();
//            hofTree.setOptions(options);
//            hofTree.buildClassifier(train);
//            System.out.println("Hoeffding Treee");
//            System.out.println(hofTree.toString());

            REPTree repTree = new REPTree();
            repTree.setOptions(options);
            repTree.buildClassifier(train);
            System.out.println("REPTree");
            System.out.println(repTree.toString());


            Evaluation evalJ48Tree = new Evaluation(train);
            Evaluation evaladTree = new Evaluation(train);
            //Evaluation evalHofTree = new Evaluation(train);
            Evaluation evalRepTree = new Evaluation(train);


            evalJ48Tree.evaluateModel(j48Tree, test);
            System.out.println(evalJ48Tree.toSummaryString("\nResults\n======\n", false).substring(0, 83));

            /*evalJ48Tree.evaluateModel(ballTree, test);
            System.out.println(evaladTree.toSummaryString("\nResults\n======\n", false).substring(0, 83));*/


//            evalHofTree.evaluateModel(hofTree, test);
//            System.out.println(evalHofTree.toSummaryString("\nResults\n======\n", false).substring(0, 83));

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
