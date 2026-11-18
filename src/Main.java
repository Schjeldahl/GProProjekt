import java.util.Random;
import java.awt.Color;

import itumulator.executable.DisplayInformation;
import itumulator.executable.Program;
import itumulator.world.Location;
import itumulator.world.World;

public class Main {
    
    public static void main(String[] args) {
        int size = 15;
        int delay = 1000;
        int display_size = 1000;
        int amount = 10;
        
        Random r = new Random();
        
        Program p = new Program(size, display_size, delay);

        World w = p.getWorld();

        for(int i = 0; i < amount; i++) {
            int x = r.nextInt(size);
            int y = r.nextInt(size);

            Location l = new Location(x,y);

            while(!w.isTileEmpty(l)) {
                x = r.nextInt(size);
                y = r.nextInt(size);
                l = new Location(x,y);
            }
            w.setTile(l, new Person());
        }
        
        DisplayInformation di = new DisplayInformation(Color.red);
        p.setDisplayInformation(Person.class, di);

        DisplayInformation dog = new DisplayInformation(Color.blue);
        p.setDisplayInformation(Dog.class, dog);
        p.show();
        for (int i = 0; i<200 ; i++) {
            p.simulate();
        }
    }

}
