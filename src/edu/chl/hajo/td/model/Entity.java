package edu.chl.hajo.td.model;

import edu.chl.hajo.td.util.Point2D;
import edu.chl.hajo.td.util.Vector2D;
import lombok.Getter;
import lombok.Setter;

abstract public class Entity {

    @Setter
    @Getter
    private Point2D pos;    // Center position

    @Getter
    @Setter
    private double width; // Upper right corner: x - width / 2

    @Getter
    @Setter
    private double height;

    private static final Vector2D INIT_DIR = new Vector2D(-1, 0);

    @Getter
    @Setter
    private Vector2D dir = INIT_DIR;

    public void findDir(Point2D targetPoint) {
        dir = new Vector2D(targetPoint.getX() - getX(),targetPoint.getY() - getY());
    }

    public void addPos(Vector2D v) {
        pos = pos.add(v);
    }

    public double getX() {
        return pos.getX();
    }


    public double getY() {
        return pos.getY();
    }
}
