package weird.gears;

import java.util.ArrayList;

public class GearSet {
    private ArrayList<Gear> gearList = new ArrayList<Gear>();
    
    // sets up some preset gear sets
    // @set if 0: 2 round gears
    //      if 1:
    //      if 2:
    public GearSet(int set) {
        if (set == 0) {
            Gear fixed = new RoundGear();
            Tooth tooth = new Tooth(2.0, 2.0, .5);
            Gear unfixed = new RoundGear(23.0, 0.0, 10.0, tooth);
            gearList.add(fixed);
            gearList.add(unfixed);
        } else if (set == 1) {
            Tooth tooth = new Tooth(2.0, 2.0, .5);
            Gear fixed = new RoundGear(0.0, 0.0, 25.0, 12);
            Gear unfixed = new RoundGear(23.0, 0.0, 15.0, tooth);
            gearList.add(fixed);
            gearList.add(unfixed);
        } else {
            
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
}
