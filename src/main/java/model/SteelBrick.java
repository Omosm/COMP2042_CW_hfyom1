/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package model;

import controller.Brick;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


public class SteelBrick extends Brick {

    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Random rnd;
    private Shape brickFace;

    /**
     * constructor of class SteelBrick which set value for bricks' position, size, color, strength, brokenFlag in superclass Brick
     *
     * @param point position of individual brick
     * @param size size of brick
     */
    public SteelBrick(Point point, Dimension size){
        super(point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        rnd = new Random();
        brickFace = super.getBrickFace();
    }

    /**
     * if the random generated double is less than STEEL_PROBABILITY, then steel brick is broken
     */
    public void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            super.impact();
        }
    }

    /**
     * if brick is broken return False, then call impact method and return brokenFlag
     *
     * @param point (up, down, left, right) face of ball where it impacts brick
     * @param dir direction of crack
     * @return boolean True or False
     */
    public  boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        impact();
        return  super.isBroken();
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
        return brickFace;
    }

    /**
     * getter for rnd
     * @return Random rnd
     */
    public Random getRnd() {return rnd;}

}
