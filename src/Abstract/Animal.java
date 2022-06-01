package Abstract;

import Interface.AnimalInterface;

public abstract class Animal implements AnimalInterface{
    protected int currentAge, expectedAge;
    protected double cost;

    public Animal(int currentAge) {
        this.currentAge = currentAge;
    }

    public double getCost() {
        return cost;
    }

    public void increaseCurrentAge() {
        currentAge++;
    }

    public int calculateRemainingLife(){
        //calculates and returns the remaining lifetime in years
        return expectedAge - currentAge;
    }
    
    @Override
    public String toString() {
        return "Animal"
                + "\nCurrent Age: " + currentAge 
                + "\nExpected Age: " + expectedAge + '\n';
    }
}
