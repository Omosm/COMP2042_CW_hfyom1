package model;

import controller.Brick;

import java.awt.*;
import java.awt.Point;


/**
 * inherits from superclass Brick
 */
public class ClayBrick extends Brick {

    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;




    /**
     * constructor of class ClayBrick
     * @param point position of individual brick
     * @param size size of brick
     */
    public ClayBrick(Point point, Dimension size){
        super(point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    /*@Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }*/

    /**
     * getter for brickFace
     * @return brickFace
     */
    @Override
    public Shape getBrick() {
        return super.getBrickFace();
    }


}
