package edu.chl.hajo.td.model;

import edu.chl.hajo.td.model.creeps.Creep;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static edu.chl.hajo.td.model.TowerDefence.TILE_SIZE;
import static java.lang.System.out;
import static org.junit.Assert.*;

/*
        JUnit testing of model
 */
public class ModelTest {

    @Test
    public void testPath() throws Exception {
         List<String> strPts = Arrays.asList(
                "0,3", "3,3", "3,9", "8,9", "8,4", "12,4",
                "12,12", "3,12", "3,17", "17,17",
                "17,6", "20,6");
        Path p = new Path(0, strPts, TILE_SIZE);

        Creep c = new Creep(p);
        out.println(c.getPos());
        c.move();
        out.println(c.getPos());
        c.move();
        out.println(c.getPos());
        c.move();
        out.println(c.getPos());
        c.move();
        out.println(c.getPos());
        c.move();
        out.println(c.getPos());
        c.move();
        out.println(c.getPos());
        c.move();
        out.println(c.getPos());
        c.move();
        out.println(c.getPos());
        c.move();
        out.println(c.getPos());
        c.move();
        out.println(c.getPos());
        c.move();
        out.println(c.getPos());
        c.move();
        out.println(c.getPos());
        c.move();
        out.println(c.getPos());
        c.move();
        out.println(c.getPos());
        c.move();
        out.println(c.getPos());



        assertTrue(p.get() != null);

        // etc.
    }

    // Add more tests as needed

}