package kematjaya.perceptron;

public class Criteria {

    private String name;

    private float value;

    public Criteria(String name, float value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public float getValue()
    {
        return this.value;
    }
}
