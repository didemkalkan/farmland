package SystemAndMain;

import Abstract.Animal;
import Abstract.Buildings;
import IsAAnimal.Chicken;
import IsAAnimal.Cow;
import IsAAnimal.Horse;
import IsABuilding.Coop;
import IsABuilding.Cowshed;
import IsABuilding.Silo;
import IsABuilding.Stable;
import java.util.ArrayList;

public class BuildingSys {
    public static ArrayList<Buildings> list = new ArrayList();
    public static double budget, expectedIncome;
    public static int yearsPassed = 0;
    
    public static void turnBackToDefault(){
        list.removeAll(list);
        yearsPassed = 0;
        Buildings.setIdS(100);
    }
    
    public static int addAnimal(Buildings b, Animal a){
        //this method is used to buy animals to different buildings
        if(a instanceof Chicken){
            if(!((Coop) b).addChicken((Chicken) a))
                return -1;
            if((budget - a.getCost()) < 0)
                return 0;
            budget -= a.getCost();
            return 1;
        }
        else if(a instanceof Horse){
            if(!((Stable) b).addHorse((Horse) a))
                return -1;
            if((budget - a.getCost()) < 0)
                return 0;
            budget -= a.getCost();
            return 1;
        }
        else{
            if(!((Cowshed) b).addCow((Cow) a))
                return -1;
            if((budget - a.getCost()) < 0)
                return 0;
            budget -= a.getCost();
            return 1;
        }         
    }
    
    public static boolean areThereAnimals(){
        for(Buildings b: list)
            if(b instanceof Coop)
                if(((Coop) b).getNumOfChicken() != 0)
                    return true;
            else if(b instanceof Cowshed)
                if(((Cowshed) b).getNumOfCow() != 0)
                    return true;
        return false;
    }
    
    public static boolean isThereSilo(){
        for(Buildings b: list)
            if(b instanceof Silo)
                return true;
        return false;
    }
    
    public static boolean addBuilding(String type, String material, int numOfFloors){
        //adds a building with given type and substract cost from budget
        Buildings b = null;
        
        switch(type){
            case "Coop":
                b = new Coop(material, numOfFloors);
                break;
            case "Stable":
                b = new Stable(material, numOfFloors);
                break;
            case "Silo":
                b = new Silo(material, numOfFloors);
                break;
            case "Cowshed":
                b = new Cowshed(material, numOfFloors);
        }
        
        b.calculateCost();
        if((budget - b.getCost()) < 0){
            Buildings.decreaseIdS();
            return false;
        }
        else{
            budget -= b.getCost();
            b.calculateCapacity();
            list.add(b);
            
            return true;
        }
    }
    
    public static String display(){
        String res = "";
        
        for(Buildings b: list)
            if(!(b instanceof Silo))
                res += b.toString() + "\n";
        
        return res;
    }
    
    public static String[] getBuildingNames(){
        String[] buildingNames = new String[list.size()];
        int cnt = 0;
        
        for(Buildings b: list)
            buildingNames[cnt++] = b.getId() + " - " + b.getType();
        
        return buildingNames;
    }
    
    public static boolean removeBuilding(int id){
        for(Buildings b: list)
            if(b.checkId(id)){
                list.remove(b);
                return true;
            }
        return false;
    }
    
    public static Buildings searchBuilding(int id){
        for(Buildings b: list)
            if(b.getId() == id)
                return b;
        return null;
    }
    
    public static double calculateExpectedIncome(){
        //calculates and assigns expected income for the farm;
        expectedIncome = 0;
        
        for(Buildings b: list)
            if(b instanceof Coop)
                expectedIncome += ((Coop) b).expectedIncome();
            else if(b instanceof Cowshed)
                expectedIncome += ((Cowshed) b).expectedIncome();
        
        return expectedIncome;
    }
    
    public static double isHorsesDailyEnough(){
        //checks whether horses are enough to carry daily production and returns how much kg left uncarried
        double dailyKg = 0, dailyTransport = 0;
        
        for(Buildings b: list)
            if(b instanceof Coop)
                dailyKg += ((Coop) b).eggsInKgDaily();
            else if(b instanceof Cowshed)
                dailyKg += ((Cowshed) b).milkInKgDaily();
            else if(b instanceof Stable)
                dailyTransport += ((Stable) b).transportationCapacityKgDaily();
        
        if(dailyKg > dailyTransport)
            return dailyKg - dailyTransport;
        return 0;
    }
    
    public static boolean isSiloCapacityEnough(){
        double capacity = 0;
        
        for(Buildings b: list)
            if(b instanceof Silo)
                capacity += ((Silo) b).getCapacityForFeed();
        
        if(capacity < totalRequiredDailyFeed())
            return false;
        return true;
    }
    
    public static double totalRequiredDailyFeed(){
        double feedKg = 0;
        
        for(Buildings b: list)
            if(b instanceof Coop)
                feedKg += ((Coop) b).getNumOfChicken() * 0.1;
            else if(b instanceof Cowshed)
                feedKg += ((Cowshed) b).getNumOfCow() * 10;
            else if(b instanceof Stable)
                feedKg += ((Stable) b).getNumbOfHorse() * 7;
        
        return feedKg;
    }
    
    public static double totalYearlyFeedCost(){
        double requiredYearlyFeed = totalRequiredDailyFeed() * 360;
        
        return requiredYearlyFeed / 5; //1000 kg for 200 dolars
    }

    public static boolean passAYear(){
        //calculates if the year was successful or not, if success, changes budget
        double res = budget + calculateExpectedIncome() - totalYearlyFeedCost();
        
        if(res >= 0){
            budget += calculateExpectedIncome() - totalYearlyFeedCost();
            yearsPassed++;
            
            for(Buildings b: list)
                if(b instanceof Coop){
                    ((Coop) b).aggUpChicken();
                    ((Coop) b).removeDeadChicken();
                }
                else if(b instanceof Cowshed){
                    ((Cowshed) b).aggUpCows();
                    ((Cowshed) b).removeDeadCows();
                }
                else if(b instanceof Stable){
                    ((Stable) b).aggUpHorses();
                    ((Stable) b).removeDeadHorse();
                }
            
            return true;
        }
        return false;    
    }
}
