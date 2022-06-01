package Abstract;

public abstract class Buildings {
    protected String material;
    protected int id, numOfFloors;
    protected double cost;
    protected static int idS = 100;

    public Buildings(String material, int numOfFloors) {
        this.material = material;
        this.numOfFloors = numOfFloors;
        this.id = idS++;
    }

    public double getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }

    public static void setIdS(int idS) {
        Buildings.idS = idS;
    }

    public static void decreaseIdS(){
        idS --;
    }
    
    public boolean checkId(int id){
        return this.id == id;
    }
    
    public abstract double calculateCost();
    public abstract void calculateCapacity();
    public abstract String getType();

    @Override
    public String toString() {
        return "Building" 
                + "\nMaterial: " + material 
                + "\nNumber of Floors: " + numOfFloors 
                + "\nCost: " + cost + '\n';
    }
}
