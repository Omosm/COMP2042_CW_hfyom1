package controller;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

public class Crack {

    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;

    public static final int LEFT = 10;
    public static final int RIGHT = 20;
    public static final int UP = 30;
    public static final int DOWN = 40;
    public static final int VERTICAL = 100;
    public static final int HORIZONTAL = 200;


    private GeneralPath crack;

    private int crackDepth;
    private int steps;
    private static Random rnd;


    /**
     * constructor of class Crack
     * initialize and set value for crackPath, crackDepth, steps
     *
     * @param crackDepth default int value of 1
     * @param steps default int value of 35
     */
    public Crack(int crackDepth, int steps) {

        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;
        rnd = new Random();

    }


    /**
     * getter for crackPath
     * @return crackPath
     */
    public GeneralPath draw() {

        return crack;
    }

    /**
     * remove crackPath in bricks
     */
    public void reset() {
        crack.reset();
    }

    /**
     * first makeCrack(Overload) method
     * to determine the start and end point of where crackPath will be drawn
     *
     * @param point (up, down, left, right) face of ball where it impacts brick
     * @param direction direction of crack
     * @param brickFace brickFace where cracks are drawn onto
     */
    public void makeCrack(Point2D point, int direction, Shape brickFace) {
        Rectangle bounds = brickFace.getBounds();

        Point impact = new Point((int) point.getX(), (int) point.getY());
        Point start = new Point();
        Point end = new Point();


        switch (direction) {
            case LEFT:
                start.setLocation(bounds.x + bounds.width, bounds.y);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                Point tmp = makeRandomPoint(start, end, VERTICAL);
                makeCrack(impact, tmp);

                break;
            case RIGHT:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x, bounds.y + bounds.height);
                tmp = makeRandomPoint(start, end, VERTICAL);
                makeCrack(impact, tmp);

                break;
            case UP:
                start.setLocation(bounds.x, bounds.y + bounds.height);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                tmp = makeRandomPoint(start, end, HORIZONTAL);
                makeCrack(impact, tmp);
                break;
            case DOWN:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x + bounds.width, bounds.y);
                tmp = makeRandomPoint(start, end, HORIZONTAL);
                makeCrack(impact, tmp);

                break;

        }
    }

    /**
     * second makeCrack(Overload method)
     * draw crackPath on brickFace from specified start point to end point
     *
     * @param start location of brick where it is impacted by ball
     * @param end a random end point for crackPath
     */
    protected void makeCrack(Point start, Point end) {

        GeneralPath path = new GeneralPath();


        path.moveTo(start.x, start.y);

        double w = (end.x - start.x) / (double) steps;
        double h = (end.y - start.y) / (double) steps;

        int bound = crackDepth;
        int jump = bound * 5;

        double x, y;

        for (int i = 1; i < steps; i++) {

            x = (i * w) + start.x;
            y = (i * h) + start.y + randomInBounds(bound);

            if (inMiddle(i, CRACK_SECTIONS, steps))
                y += jumps(jump, JUMP_PROBABILITY);

            path.lineTo(x, y);

        }

        path.lineTo(end.x, end.y);
        crack.append(path, true);
    }

    /**
     * return a random number
     * @param bound int value of 1
     * @return random number between 0 and 1
     */
    private int randomInBounds(int bound) {
        int n = (bound * 2) + 1;
        return rnd.nextInt(n) - bound;
    }

    private boolean inMiddle(int i, int steps, int divisions) {
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return (i > low) && (i < up);
    }

    private int jumps(int bound, double probability) {

        if (rnd.nextDouble() > probability)
            return randomInBounds(bound);
        return 0;

    }

    /**
     * return a random coordinate along x-axis or y-axis, which acts as end point for crackPath
     *
     * @param from start point
     * @param to end point
     * @param direction Horizontal or Vertical
     * @return random coordinate
     */
    private Point makeRandomPoint(Point from, Point to, int direction) {

        Point out = new Point();
        int pos;

        switch (direction) {
            case HORIZONTAL:
                pos = rnd.nextInt(to.x - from.x) + from.x;
                out.setLocation(pos, to.y);
                break;
            case VERTICAL:
                pos = rnd.nextInt(to.y - from.y) + from.y;
                out.setLocation(to.x, pos);
                break;
        }
        return out;
    }

}
