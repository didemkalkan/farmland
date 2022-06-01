package IsABuilding;

import Abstract.Buildings;

public class Silo extends Buildings{
    private double capacityForFeed;

    public Silo(String material, int numOfFloors) {
        super(material, numOfFloors);
    }

    public double getCapacityForFeed() {
        return capacityForFeed;
    }
    
    @Override
    public double calculateCost() {
        //calculates and assigns the cost of building a silo
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
        capacityForFeed = numOfFloors * 1000;
    }
    
    @Override
    public String getType() {
        return "Silo";
    }
}
