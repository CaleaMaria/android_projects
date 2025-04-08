package eu.ase.ro.damapp;

public class Review {
    private String description;
    private float rate;

    public Review(String description, float rate) {
        this.description = description;
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return description + " (" + rate + ")";
    }
}
