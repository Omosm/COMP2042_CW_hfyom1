package brick;

import ball.Ball;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created by filippo on 04/09/16.
 *
 */
abstract public class Brick  {

    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;


    private String name;
    private Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;


    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    public void impact(){
        strength--;
        broken = (strength == 0);
    }

    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }


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


    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    protected Shape makeBrickFace(Point pos,Dimension size){
        return new Rectangle(pos,size);
    }
    public abstract Shape getBrick();

    public Color getBorderColor(){
        return  border;
    }

    public Color getInnerColor(){
        return inner;
    }

    public final boolean isBroken(){
        return broken;
    }

    public Shape getBrickFace() {
        return brickFace;
    }


}





