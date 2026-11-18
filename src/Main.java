import java.util.HashMap;
import java.util.Map;
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

        int amount = 10;

        Person person = new Person();

        for (int i = 0; i < amount; i++) {
            int x = r.nextInt(size);
            int y = r.nextInt(size);

            Location l = new Location(x, y);

            while(!w.isTileEmpty(l)){
                x = r.nextInt(size);
                y = r.nextInt(size);

                l = new Location(x,y);
            }

            w.setTile(l, new Person());
        }
    
        Location place = new Location (r.nextInt(size),r.nextInt(size));
        
        DisplayInformation di = new DisplayInformation(Color.red);
        p.setDisplayInformation(Person.class, di);
        
        p.show();

        System.out.println(w.getEntities());
        for (int i = 0; i<200 ; i++) {
            p.simulate();

                if (w.isNight()){
                    for (Object entity : w.getEntities().keySet()) {
                        if (entity instanceof Person) {
                            w.delete(entity);
                        }
                    }
                }
                if (i==15){
                    System.out.println(w.getEntities());
                }
        }
    }

}
