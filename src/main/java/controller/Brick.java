package controller;

import controller.Ball;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * abstract class Brick which is inherited by subclasses, ClayBrick, CementBrick, SteelBrick, YellowBrick
 */
abstract public class Brick  {

    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;


    private Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;


    /**
     * constructor of Brick class which initializes and set value for bricks' position, size, color, strength, brokenFlag
     * and also construct Rectangle brick
     *
     * @param pos position of individual brick
     * @param size size of brick
     * @param border border color of brick
     * @param inner inner color of brick
     * @param strength strength of brick
     */
    public Brick(Point pos, Dimension size, Color border, Color inner, int strength){
        broken = false;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    /**
     * impact brick will decrement brick strength, if strength is 0 then brick is broken(true)
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }

    /**
     * if brick is broken return False, then call impact method
     * and return brokenFlag
     *
     * @param point (up, down, left, right) face of ball where it impacts brick
     * @param dir direction of crack
     * @return boolean brokenFlag
     */
    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }


    /**
     *
     * @param b ball b
     * @return integer (UP, DOWN, LEFT, RIGHT)_IMPACT which is switch case condition for method impactWall
     */
    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(getBrickFace().contains(b.right))
            out = LEFT_IMPACT;
        else if(getBrickFace().contains(b.left))
            out = RIGHT_IMPACT;
        else if(getBrickFace().contains(b.up))
            out = DOWN_IMPACT;
        else if(getBrickFace().contains(b.down))
            out = UP_IMPACT;
        return out;
    }


    /**
     * restore bricks' brokenFlag, and strength
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * Construct Rectangle shaped brickFace
     * @param pos position of individual brick
     * @param size size of brick
     * @return Rectangle shaped brick
     */
    public Shape makeBrickFace(Point pos, Dimension size){
        return new Rectangle(pos,size);
    }

    /**
     * abstract method: getter for brickFace
     * @return brickFace
     */
    public abstract Shape getBrick();

    /**
     * getter for brick border color
     * @return border color
     */
    public Color getBorderColor(){
        return  border;
    }

    /**
     * getter for brick inner color
     * @return inner color
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * returns value of brokenFlag
     * @return boolean broken
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * getter for brickFace
     * @return brickFace
     */
    public Shape getBrickFace() {
        return brickFace;
    }


}





