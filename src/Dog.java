import java.util.*;
import itumulator.simulator.Actor;
import itumulator.world.Location;
import itumulator.world.World;
//import itumulator.world.Location;

public class Dog implements Actor {
    private final Person owner;
    public Dog(Person owner) {
        this.owner = owner;
    }


    @Override
    public void act(World w) {
        if(!w.contains(owner)) {
            w.delete(this);
            return;
        }
        Location ownerLocation = w.getLocation(owner);
        List<Location> newSpot = new ArrayList<>(w.getEmptySurroundingTiles(ownerLocation));
        Random r = new Random();
        if(!newSpot.isEmpty())
            w.move(this, newSpot.get(r.nextInt(newSpot.size())));


    }
}
