package IsABuilding;

import Abstract.Buildings;
import IsAAnimal.Horse;

public class Stable extends Buildings{
    private Horse[] horses;
    private int capacityForHorse, numbOfHorse = 0;

    public Stable(String material, int numOfFloors) {
        super(material, numOfFloors);
    }

    public int getCapacityForHorse() {
        return capacityForHorse;
    }

    public int getNumbOfHorse() {
        return numbOfHorse;
    }
    
    public boolean addHorse(Horse h) {
        //adds horse
        if(numbOfHorse != capacityForHorse){
            horses[numbOfHorse++] = h;
            return true;
        }
        return false;
    }
    
    public void removeDeadHorse(){
        //removes dead horses
        for(int i = 0; i < numbOfHorse; i++)
            if(horses[i].calculateRemainingLife() == 0)
                horses[i--] = horses[--numbOfHorse];
    }
    
    public void aggUpHorses(){
        for(int i = 0; i < numbOfHorse; i++)
            horses[i].increaseCurrentAge();
    }
    
    public double transportationCapacityKgDaily(){
        //calculates and returns how much kg can be potentially carried by horses in stable
        double kg = 0;
        
        for(int i = 0; i < numbOfHorse; i++)
            kg += horses[i].getDailyTransport();
        
        return kg;
    }

    @Override
    public String toString() {
        return id + " - Stable " + super.toString() 
                + "Number of Horses: " + numbOfHorse + " / " + capacityForHorse + '\n';
    }   

    @Override
    public double calculateCost() {
        //calculates and assigns the cost of building a stable
        switch(material){
            case "Wooden":
                cost = 2500 * numOfFloors;
                break;
            case "Stone":
                cost = 3300 * numOfFloors;
                break;
            case "Concrete":
                cost = 4000 * numOfFloors;
        }
        
        return cost;
    }

    @Override
    public void calculateCapacity() {
        //calculates and assigns the capacity of a stable
        capacityForHorse = numOfFloors * 4;
        horses = new Horse[capacityForHorse];
    }
    
    @Override
    public String getType() {
        return "Stable";
    }
}
