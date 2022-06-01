package IsABuilding;

import Abstract.Buildings;
import IsAAnimal.Cow;

public class Cowshed extends Buildings{
    private Cow[] cows;
    private static double milkSalePrice = 0;
    private int capacityForCow, numOfCow = 0;

    public Cowshed(String material, int numOfFloors) {
        super(material, numOfFloors);
    }

    public int getCapacityForCow() {
        return capacityForCow;
    }

    public int getNumOfCow() {
        return numOfCow;
    }

    public static double getMilkSalePrice() {
        return milkSalePrice;
    }

    public void removeDeadCows(){
        //removes dead cow
        for(int i = 0; i < numOfCow; i++)
            if(cows[i].calculateRemainingLife() == 0)
                cows[i--] = cows[--numOfCow];
    }
    
    public void aggUpCows(){
        for(int i = 0; i < numOfCow; i++)
            cows[i].increaseCurrentAge();
    }
    
    public static void setMilkSalePrice(double milkSalePrice) {
        Cowshed.milkSalePrice = milkSalePrice;
    }
    
    public boolean addCow(Cow c) {
        //adds cow
        if(numOfCow != capacityForCow){
            cows[numOfCow++] = c;
            return true;
        }
        return false;
    }
    
    public double expectedMilkProduction(){
        //calculates and returns how much milk can be potentially produced with given capacity and cows
        int total = 0;
        for(int i = 0; i < numOfCow; i++)
            total += cows[i].milkForAYear();
        return total;
    }
    
    public double expectedIncome(){
        //calculates and returns the income
        return expectedMilkProduction()* milkSalePrice;
    }
    
    public double milkInKgDaily(){
        //calculates and returns the kg equivalence of the daily milk produced
        double kg = 0;
        
        for(int i = 0; i < numOfCow; i++)
            kg += cows[i].getDailyMilk()* 1.23;
        
        return kg;
    }

    @Override
    public String toString() {
        return id + " - Cowshed " + super.toString() 
                + "Number Of Cow: " + numOfCow + " / " + capacityForCow + "\n";
    }
       
    @Override
    public double calculateCost() {
        //calculates and assigns the cost of building a cowshed
        switch(material){
            case "Wooden":
                cost = 1700 * numOfFloors;
                break;
            case "Stone":
                cost = 2500 * numOfFloors;
                break;
            case "Concrete":
                cost = 3200 * numOfFloors;
        }
        
        return cost;
    }

    @Override
    public void calculateCapacity() {
        //calculates and assigns the capacity of a stable
        capacityForCow = numOfFloors * 8;
        cows = new Cow[capacityForCow];
    }
    
    @Override
    public String getType() {
        return "Cowshed";
    }
}
