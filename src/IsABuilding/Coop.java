package IsABuilding;

import Abstract.Buildings;
import IsAAnimal.Chicken;

public class Coop extends Buildings{
    private Chicken[] chickens;
    private static double eggSalePrice = 0;
    private int capacityForChicken, numOfChicken = 0;

    public Coop(String material, int numOfFloors) {
        super(material, numOfFloors);
    }

    public int getCapacityForChicken() {
        return capacityForChicken;
    }

    public int getNumOfChicken() {
        return numOfChicken;
    }

    public static double getEggSalePrice() {
        return eggSalePrice;
    }

    public static void setEggSalePrice(double eggSalePrice) {
        Coop.eggSalePrice = eggSalePrice;
    }
    
    public boolean addChicken(Chicken c) {
        //adds chicken
        if (numOfChicken != capacityForChicken){
            chickens[numOfChicken++] = c;
            return true;
        }
        return false;
    }
    
    public void removeDeadChicken(){
        //removes dead chicken
        for(int i = 0; i < numOfChicken; i++)
            if(chickens[i].calculateRemainingLife() == 0)
                chickens[i--] = chickens[--numOfChicken];
    }
    
    public void aggUpChicken(){
        for(int i = 0; i < numOfChicken; i++)
            chickens[i].increaseCurrentAge();
    }
    
    public int expectedEggProduction(){
        //calculates and returns how many eggs can be potentially produced with given capacity and chickens
        int total = 0;
        for(int i = 0; i < numOfChicken; i++)
            total += chickens[i].eggForAYear();
        return total;
    }
    
    public double expectedIncome(){
        //calculates and returns the income
        return expectedEggProduction() * eggSalePrice;
    }
    
    public double eggsInKgDaily(){
        //calculates and returns the kg equivalence of the daily eggs produced
        double kg = 0;
        
        for(int i = 0; i < numOfChicken; i++)
            kg += chickens[i].getDailyEgg() * 0.05;
        
        return kg;
    }

    @Override
    public String toString() {
        return id + " - Coop " + super.toString()
                + "Number of Chickens: " +  numOfChicken + " / " + capacityForChicken + "\n";
    }   
   
    @Override
    public double calculateCost() {
        //calculates and assigns the cost of building a coop
        switch(material){
            case "Wooden":
                cost = 1500 * numOfFloors;
                break;
            case "Stone":
                cost = 2300 * numOfFloors;
                break;
            case "Concrete":
                cost = 3000 * numOfFloors;
        }
        
        return cost;
    }

    @Override
    public void calculateCapacity() {
        //calculates and assigns the capacity of a coop
        capacityForChicken = numOfFloors * 5;
        chickens = new Chicken[capacityForChicken];
    }

    @Override
    public String getType() {
        return "Coop";
    }
}
