package kematjaya.perceptron;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class Perceptron {

    private Collection<Data> datas;

    private Collection<Weight> weights;

    private float bias = 0;

    private float learningRate;

    public Perceptron(float learningRate, float bias) {
        this.learningRate = learningRate;
        this.bias = bias;
        this.datas = new ArrayList<>();
    }

    public Collection<Data> getDatas() {
        return this.datas;
    }

    public void addData(Data data) {
        this.datas.add(data);
    }

    public float getBias() {
        return this.bias;
    }

    protected float countY(Data data, Collection<Weight> weights) throws Exception {

        float y = 0;
        for (var criteria: data.getCriterias()) {
            Optional<Weight> w = weights.stream().filter(weight -> {
                return weight.getName() == criteria.getName();
            }).findFirst();

            if (!(w.get() instanceof Weight)) {
                throw new Exception("not match key");
            }

            Weight weight = w.get();

            y += criteria.getValue() * weight.getValue();
        }

        y += this.bias;

        return this.signum(y);
    }

    protected float signum(float number) {
        return number >= 0 ? 1 : 0;
    }

    protected boolean updateWeight(float expect, float actual, Data data) throws Exception {
        if (expect == actual) {
            return true;
        }

        float err = expect - actual;

        Collection<Weight> newWeight = new ArrayList<>();
        for (var weight: this.weights) {

            Optional<Criteria> c = data.getCriterias().stream().filter(criteria -> {
                return weight.getName() == criteria.getName();
            }).findFirst();

            if (!(c.get() instanceof Criteria)) {
                throw new Exception("not match key criteria");
            }

            float w = weight.getValue() + (this.learningRate * err * c.get().getValue());
            newWeight.add(new Weight(weight.getName(), w));
        }

        this.bias = this.bias + (this.learningRate * err);
        this.weights = newWeight;

        return false;
    }

    public Collection<Weight> training(Collection<Weight> weights, int maxEpoch) throws Exception {

        this.weights = weights;
        System.out.println("======> Training Process <=======");
        for(int x = 1; x <=maxEpoch; x++) {

            System.out.println("===== Epoch "+ x + ".:");
            boolean stop = true;
            int i = 1;
            for (var data: this.datas) {
                float y = this.countY(data, this.weights);
                System.out.println("data "+i+": " + y);
                boolean status = this.updateWeight(data.getTarget(), y, data);
                if (status != stop) {
                    stop = false;
                }
                i++;
            }

            System.out.println("================================");

            if (stop) {
                break;
            }
        }

        return this.weights;
    }

    public float prediction(Data data, Collection<Weight> weights) throws Exception {
        return this.countY(data, weights);
    }
}
