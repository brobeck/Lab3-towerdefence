package edu.chl.hajo.td.model;

import edu.chl.hajo.td.model.creeps.Creep;
import edu.chl.hajo.td.util.Point2D;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.System.out;

/*
 *    A Wave of creeps
 *    - All creeps of same kind
 *    - All follow same path.
 *
 */

public class Wave {
    @Getter
    private final Creep prototype;
    @Getter
    private final long delay1;
    @Getter
    private final long spawnDelay;
    @Getter
    private long lastSpawnedCreep = 0;
    @Getter
    private final int maxCreeps;
    @Getter
    private List<Creep> creeps = new ArrayList<>();
    @Getter
    private List<Creep> finishedCreeps = new ArrayList<>();
    @Getter
    private List<Creep> deadCreeps = new ArrayList<>();



    // TODO

    public Wave(int maxCreeps, long delay1, long spawnDelay, Creep prototype) {
        this.maxCreeps = maxCreeps;
        this.delay1 = delay1;
        this.spawnDelay = spawnDelay;
        this.prototype = new Creep(prototype);
    }

    public void move() {
        Iterator<Creep> creepIterator = creeps.iterator();
        while (creepIterator.hasNext()) {
            Creep creep = creepIterator.next();

            Point2D creepPos              = creep.getPos();
            List<Point2D> creepPathPoints = creep.getPath().getPoints();
            Point2D lastPoint             = creepPathPoints.get(creepPathPoints.size() - 1);
            double creepSpeed             = creep.getSpeed();

            boolean isFinished = creepPos.epsilonEquals(lastPoint, creepSpeed);

            if (isFinished) {
                finishedCreeps.add(creep);
                creepIterator.remove();
                continue;
            }

            creep.move();
        }
    }

    public void spawn(long now) {
        if (now - lastSpawnedCreep > spawnDelay) {
            int totalCreeps = creeps.size() + finishedCreeps.size() + deadCreeps.size();

            if (totalCreeps < maxCreeps) {
                lastSpawnedCreep = now;
                creeps.add(new Creep(prototype));
            }
        }
    }

}
