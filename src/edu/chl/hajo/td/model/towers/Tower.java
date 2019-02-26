package edu.chl.hajo.td.model.towers;

import edu.chl.hajo.td.model.Entity;
import edu.chl.hajo.td.model.creeps.Creep;
import edu.chl.hajo.td.util.Point2D;
import edu.chl.hajo.td.util.Vector2D;
import lombok.Getter;
import lombok.Setter;

import static edu.chl.hajo.td.model.TowerDefence.ONE_SEC;
import static edu.chl.hajo.td.model.TowerDefence.TENTH_SEC;

/*
        Basic GunTower

 */
public class Tower extends Entity {

    @Getter
    private final double range;    // Max distance to fire
    @Getter
    private final long coolDown;  // Time before next shoot
    @Getter
    private final int firePower;

    @Getter
    @Setter
    private long lastShot;

    public Tower(Point2D pos) {
        this.range = 120;
        this.coolDown = TENTH_SEC * 5;
        this.firePower = 100;

        super.setPos(pos);
        super.setWidth(20);
        super.setHeight(20);
    }

}
