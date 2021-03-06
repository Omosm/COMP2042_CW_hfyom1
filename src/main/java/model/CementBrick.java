package model;

import controller.Brick;
import controller.Crack;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;


/**
 * inherits from superclass Brick
 */
public class CementBrick extends Brick {


    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;

    private Crack crack;
    private Shape brickFace;


    /**
     * constructor of class CementBrick
     *
     * @param point position of individual brick
     * @param size size of brick
     */
    public CementBrick(Point point, Dimension size){
        super(point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS);
        brickFace = super.getBrickFace();
    }

    /**
     * if brick is broken return False, then call impact method
     * if brick is still not broken, then draw crack on brickFace
     *
     * @param point (up, down, left, right) face of ball where it impacts brick
     * @param dir direction of crack
     * @return boolean True or False
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
            crack.makeCrack(point,dir,brickFace);
            updateBrick();
            return false;
        }
        return true;
    }


    /**
     * update brick by drawing crack on brickFace
     */
    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.getBrickFace(),false);
            brickFace = gp;
        }
    }

    /**
     * Restore bricks' brokenFlag, strength, and remove crack on brickFace
     */
    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.getBrickFace();
    }

   /* @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }*/

    /**
     * getter for brickFace
     * @return brickFace
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }
}
