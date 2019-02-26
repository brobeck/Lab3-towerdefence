package edu.chl.hajo.td.model.creeps;

import edu.chl.hajo.td.model.Path;
import edu.chl.hajo.td.model.Entity;
import edu.chl.hajo.td.util.Point2D;
import edu.chl.hajo.td.util.Vector2D;
import lombok.Getter;
import lombok.Setter;

import java.util.Iterator;

import static edu.chl.hajo.td.model.TowerDefence.TILE_SIZE;
import static java.lang.System.out;

/*
 *     A basic creep (something that follows a path)
 *     - Follows exactly one path
 *     - Will damage you if arriving at path end
 *     - May be killed by some tower placed by you
 */
public class Creep extends Entity {
    @Getter
    private final Path path;
    @Getter
    private final Iterator<Point2D> pathIterator;
    @Getter
    private Point2D nextPoint;

    @Setter
    @Getter
    private double speed;
    @Getter
    @Setter
    private int hp;  // Current health
    @Getter
    private final int maxHp;      // Needed for % display in GUI
    @Getter
    private final int killPoints;  // Points to player when killed
    @Getter
    private final int damage;      // Damage caused when arriving at (non existing) base of player

    public Creep(double speed, Path path, int maxHp, int killPoints, int damage) {
        this.path = path;
        this.pathIterator = path.getPoints().iterator();
        this.nextPoint = pathIterator.next();

        this.speed = speed;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.killPoints = killPoints;
        this.damage = damage;

        super.setPos(nextPoint);
        super.setWidth(10);
        super.setHeight(10);
    }

    public Creep(Path path) {
        this(1.5, path, 500, 100, 50);
    }

    public Creep(Creep other) {
        this(other.path);
    }

    public void move(){
        if (getPos().epsilonEquals(nextPoint, speed)) {
            nextPoint = pathIterator.next();
            targetDir(nextPoint);
        }

        addPos(getDir().scale(speed));
    }

    public boolean dealDamage(double damageDealt) {
        hp -= damageDealt;
        out.println(hp);
        return hp <= 0; //Did it die?
    }
}
