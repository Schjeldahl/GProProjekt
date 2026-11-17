import java.util.Random;
import java.awt.Color;

import itumulator.executable.DisplayInformation;
import itumulator.executable.Program;
import itumulator.world.Location;
import itumulator.world.World;

public class Main {
    
    public static void main(String[] args) {
        int size = 5;
        int delay = 1000;
        int display_size = 1000;
        
        Random r = new Random();
        
        Program p = new Program(size, display_size, delay);

        World w = p.getWorld();

        Person person = new Person();
    
        Location place = new Location (r.nextInt(size),r.nextInt(size));

        w.setTile(place, person);
        
        DisplayInformation di = new DisplayInformation(Color.red);
        p.setDisplayInformation(Person.class, di);
        
        p.show();
        for (int i = 0; i<200 ; i++) {
            p.simulate();
            if (w.isNight() && w.contains(person)) {
                w.delete(person);
            }
        }
    }

}
