package kematjaya.perceptron;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[]args) {

        Perceptron perceptron = new Perceptron(1, 0);

        // set start weight
        Collection<Weight> weights = new ArrayList<>();
        weights.add(new Weight("x1", 0));
        weights.add(new Weight("x2", 0));

        // set data
        Data data1 = new Data();
        data1.addCriteria(new Criteria("x1", 0));
        data1.addCriteria(new Criteria("x2", 0));
        data1.setTarget(0);
        perceptron.addData(data1);

        Data data2 = new Data();
        data2.addCriteria(new Criteria("x1", 0));
        data2.addCriteria(new Criteria("x2", 1));
        data2.setTarget(0);
        perceptron.addData(data2);

        Data data3 = new Data();
        data3.addCriteria(new Criteria("x1", 1));
        data3.addCriteria(new Criteria("x2", 0));
        data3.setTarget(0);
        perceptron.addData(data3);

        Data data4 = new Data();
        data4.addCriteria(new Criteria("x1", 1));
        data4.addCriteria(new Criteria("x2", 1));
        data4.setTarget(1);
        perceptron.addData(data4);

        try {

            Collection<Weight> weight = perceptron.training(weights, 10);

            System.out.println("====== > Prediction <==========");
            System.out.println("Data:");
            for(var c: data4.getCriterias()) {
                System.out.println(c.getName() + ": " + c.getValue());
            }

            float result = perceptron.prediction(data4, weight);
            System.out.println("Prediction Result: " + result);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }


    }
}
