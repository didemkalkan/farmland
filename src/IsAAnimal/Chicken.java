package IsAAnimal;

import Abstract.Animal;

public class Chicken extends Animal{
    private int dailyEgg;

    public Chicken(int currentAge, int dailyEgg) {
        super(currentAge);
        this.dailyEgg = dailyEgg;
        cost = 35;
        expectedAge = 5;
    }

    public int getDailyEgg() {
        return dailyEgg;
    }
    
    public int eggForAYear(){
        //calculates the number of eggs a chicken can produce in a year
        return dailyEgg * 360;
    }

    @Override
    public String toString() {
        return "Chicken " + super.toString() 
                + "Number of Daily Eggs: " + dailyEgg + '\n';
    } 
}
