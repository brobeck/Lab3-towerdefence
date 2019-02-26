package edu.chl.hajo.td.model;

import edu.chl.hajo.td.model.creeps.Creep;
import edu.chl.hajo.td.model.towers.Tower;
import edu.chl.hajo.td.util.Point2D;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/*

    Class representing the full Game

    - GUI will call methods on this class (only)
    - GUI need some getters to display the model (parts of)

 */

public class TowerDefence {

    // Global constants
    public static final int TILE_SIZE = 32;  // 32x32 pixels
    public static final int GAME_SIZE = 20;  // 20x20 tiles
    public static final double GAME_HEIGHT = TILE_SIZE * GAME_SIZE; // 640
    public static final double GAME_WIDTH = GAME_HEIGHT;

    public static final long ONE_SEC = 1_000_000_000;   // Convenient
    public static final long TENTH_SEC = 100_000_000;

    @Getter
    private int points;
    @Getter
    private int damage;

    @Getter
    private final TDMap map;

    @Getter
    private final List<Wave> waves;
    @Getter
    private int waveNr = 0;

    @Getter
    private List<Tower> towers = new ArrayList<>();

    public TowerDefence(TDMap map, List<Wave> waves) {
        this.map = map;
        this.waves = waves;
    }

    // Update the model
    public void update(long now) {
        shootTowers(now);
        waves.get(waveNr).spawn(now);
        waves.get(waveNr).move();
    }

    public void addTower(Tower tower) {
        towers.add(tower);
    }

    public void shootTowers(long now) {
        for(Tower tower : towers) {
            if (now - tower.getLastShot() > tower.getCoolDown()) {
                tower.setLastShot(now);

                Creep target = findTarget(tower);

                if (target != null) {
                    tower.targetDir(target.getPos());
                    boolean dead = target.dealDamage(tower.getFirePower());

                    if (dead) {
                        waves.get(waveNr).kill(target);
                    }
                }
            }
        }
    }

    public Creep findTarget(Tower tower) {
        for(Creep creep : waves.get(waveNr).getCreeps()) {
            double distance = creep.getPos().distance(tower.getPos());

            if(distance < tower.getRange()) {
                return creep;
            }
        }

        return null;
    }

    // TODO


    // ------------ Conversions for GUI ------------------

    public TDTile getTile(double x, double y) {
        return map.getTileFor(x, y);
    }

    public Point2D getPosForTile(TDTile t) {
        return map.getPosFor(t);
    }

    // TODO


}
