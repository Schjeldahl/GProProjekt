import java.util.*;
import itumulator.simulator.Actor;
import itumulator.world.World;
import itumulator.world.Location;

public class Person implements Actor
{
    @Override
    public void act(World w) {
        Random r = new Random();
        Set<Location> neighbours = w.getEmptySurroundingTiles();
        List<Location> list= new ArrayList<>(neighbours);
        Location l = list.get(r.nextInt(list.size()));
        w.move(this,l);
            
    } 
    
}
