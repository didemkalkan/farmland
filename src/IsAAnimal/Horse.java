package IsAAnimal;

import Abstract.Animal;

public class Horse extends Animal{
    private double dailyTransport;

    public Horse(int currentAge, double dailyTransport) {
        super(currentAge);
        this.dailyTransport = dailyTransport;
        cost = 1000;
        expectedAge = 17;
    }

    public double getDailyTransport() {
        return dailyTransport;
    }

    @Override
    public String toString() {
        return "Horse " + super.toString()
                + "\nDaily Transport: " + dailyTransport + '\n';
    }
}
