package weird.gears;

import java.util.ArrayList;

public class GearSet {
    private ArrayList<Gear> gearList = new ArrayList<Gear>();
    
    // sets up some preset gear sets
    // @set if 1: 2 round gears
    //      if 1:
    //      if 2:
    public GearSet(int type) {
        if (type == 1) {
            // two circular gears
            Tooth tooth = new Tooth(4.0, 2.0, .5);
            Tooth otherTooth = new Tooth(3.0, 1.0, .5);
            Gear fixed = new RoundGear(30.0, 60.0, 10.0, otherTooth);
            Gear unfixed = new RoundGear(100.0, 70.0, 25.0, tooth);
            gearList.add(fixed);
            gearList.add(unfixed);
        } else if (type == 2) {
            
        } else {
        
            // same as 1 -- it's the default
            Tooth tooth = new Tooth(4.0, 2.0, .5);
            Tooth otherTooth = new Tooth(3.0, 1.0, .5);
            Gear fixed = new RoundGear(30.0, 40.0, 10.0, otherTooth);
            Gear unfixed = new RoundGear(100.0, 70.0, 25.0, tooth);
            gearList.add(fixed);
            gearList.add(unfixed);
        }
    }
    
    public GearSet(Gear gear1, Gear gear2) {
        // This is the fixed gear
        gearList.add(gear1);
        gearList.add(gear2);
    }
    
    // returns the fixed gear
    public Gear getFixedGear() {
        return gearList.get(0);
    }
    
    // returns the unfixed gear
    public Gear getUnfixedGear() {
        return gearList.get(1);
    }
    
    // adds a gear to the set
    public void addGear(Gear g) {
        gearList.add(g);
    }
    
    // returns how many gears are in the set
    public int getNumGears() {
        return gearList.size();
    }
    
    // returns the nth gear in the set
    public Gear getNthGear(int n) {
        if (n >= 0 && n < gearList.size()) {
            return gearList.get(n);
        }
        return null;
    }
}
