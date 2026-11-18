import itumulator.world.Location;
import itumulator.world.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    World w;
    @BeforeEach
    public void setUp() {
        w = new World(2);
    }
    @Test
    public void personMovesDuringDay() {
        Person p = new Person(); //Personen vi prøver at teste
        Location l = new Location(0, 0); //Lokationen vi brueger
        w.setDay(); //Vi sørger for at det er dag, da dette er vores test case
        w.setCurrentLocation(l); // Vi lader som on vi er nået til dette objekt
        w.setTile(l, p); // og placere personen på lokationen
        p.act(w); // vi udøver 'act' metoden

        Location n = w.getLocation(p); // lokation for personen
        assertNull(w.getTile(l)); // vi forventer personen har bevæget sig.
        assertNotNull(n); // altså at den nye lokatione ikke er tom
        assertNotEquals(l,n); // og at den gamle og nye lokation ikke er det samme
    }
    @Test
    public void personDeletesDuringNight() {
        Person p = new Person();
        Location l = new Location(0, 0);
        w.setDay(); // sørger for det dag til at starte med
        w.setCurrentLocation(l);
        w.setTile(l, p); // og placere personen på lokationen

        assertTrue(w.contains(p)); // tjekker om der er en person

        w.setNight(); //gør det til nat da det er her vi tester funktionalitet
        p.act(w); // vi udøver 'act' metoden

        assertFalse(w.contains(p)); // tjekker at der nu ikke er nogen person

    }
    @Test
    public void noMoreSpace() {
        Location l = new Location(0,0);
        Person p = new Person();
        w.setDay();
        w.setCurrentLocation(l);

        w.setTile(l, p);
        w.setTile(new Location(1,0), new Person());
        w.setTile(new Location(0,1), new Person());
        w.setTile(new Location(1,1), new Person());

        assertDoesNotThrow(() -> p.act(w)); // tjekker om den thrower en exception når p acter
        assertEquals(l, w.getLocation(p)); // tjekker om p stadig er på samme plads
    }
    @Test
    public void followTest() {
        w = new World(10);
        Person p = new Person();
        Dog d = new Dog(p);
        p.d = d;
        w.setTile(new Location(0,0), p);
        w.setTile(new Location(0,1), d);
        w.setCurrentLocation(w.getLocation(p));
        for(int i = 0; i < 10; i++) {
            p.act(w);
            assertTrue(w.getSurroundingTiles(w.getLocation(p)).contains(w.getLocation(d))); /* tjekker
             om hunden altid er på en tile der ligger op til en person */
        }
    }
    @Test
    public void rightAmountOfDogs() {
        int dogs = 0;
        for(int i = 0; i < 1000; i++) { /* køre 1000 test med 1 person der for lov at bevæge sig en gang
        da koden er bygget til at hver gang en person bevæger sig så er der 10% chance for de får en hund */

            w = new World(2);
            w.setDay();

            Person p = new Person();
            Location l = new Location(0,0);
            w.setTile(l, p);
            w.setCurrentLocation(l);

            p.act(w);

            if(p.d != null && w.contains(p.d)) {
                dogs++; /* tjekker om de har en hund efter de flyttede sig og tilføjer 1 til dogs hvis
                de havde fået en hund*/
            }

        }
        assertTrue(dogs > 80 && dogs < 120);

    }
}
