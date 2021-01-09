package kematjaya.perceptron;

import java.util.ArrayList;
import java.util.Collection;

public class Data {

    private Collection<Criteria> criteria;

    private float target;

    public Data() {
        this.criteria = new ArrayList<>();
    }

    public void addCriteria(Criteria criteria) {
        this.criteria.add(criteria);
    }

    public Collection<Criteria> getCriterias() {
        return this.criteria;
    }

    public void setTarget(float target) {
        this.target = target;
    }

    public float getTarget() {
        return this.target;
    }
}
