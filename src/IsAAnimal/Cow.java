package IsAAnimal;

import Abstract.Animal;

public class Cow extends Animal{
    private double dailyMilk;

    public Cow(int currentAge, double dailyMilk) {
        super(currentAge);
        this.dailyMilk = dailyMilk;
        cost = 700;
        expectedAge = 15;
    }

    public double getDailyMilk() {
        return dailyMilk;
    }
    
    public double milkForAYear(){
        //calculates the number of kgs a horse can tranport in a year
        return dailyMilk * 360;
    }

    @Override
    public String toString() {
        return "Cow " + super.toString() 
                + "\nDaily Milk: " + dailyMilk + '\n';
    }
}
