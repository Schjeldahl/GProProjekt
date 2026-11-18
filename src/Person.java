import java.util.*;
import itumulator.simulator.Actor;
import itumulator.world.World;
import itumulator.world.Location;

public class Person implements Actor
{
    Dog d;

    @Override
    public void act(World w) {
        Random r = new Random();
        Set<Location> neighbours = w.getEmptySurroundingTiles();
        List<Location> list= new ArrayList<>(neighbours);
        /* if(d != null && w.contains(d)) {
            Location dogLocation = w.getLocation(d);
            Set<Location> nextToDog = w.getSurroundingTiles(dogLocation);
            list.retainAll(nextToDog);
        } */
        if (!list.isEmpty()) {
            Location l = list.get(r.nextInt(list.size()));
            w.move(this, l);
        }
        if(d == null && r.nextInt(10) > 8) {
            Location personLocation = w.getLocation(this);
            List<Location> dogSpots = new ArrayList<>(w.getEmptySurroundingTiles(personLocation));

            if(!dogSpots.isEmpty()) {
                Location dogLocation = dogSpots.get(r.nextInt(dogSpots.size()));
                d = new Dog(this);
                w.setTile(dogLocation, d);
            }
        }
        if(w.isNight()) {
            w.delete(this);
        }
    } 
    
}
