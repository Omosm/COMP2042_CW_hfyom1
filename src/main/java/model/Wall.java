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

import controller.Ball;
import controller.Brick;
import controller.Crack;
import controller.Player;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


public class Wall {


    private Random rnd;
    private Rectangle area;

    public Brick[] bricks;
    public Ball ball;
    public Player player;

    private Brick[][] levels;
    private int level;
    private Level levelcons;

    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;

    /**
     * construct for class Wall which initialize and set values for startPoint, levels, ballCount, area
     * it also initializes a random speed for ball for every new level
     *
     * @param drawArea Rectangle GameFrame where game is rendered/drawn
     * @param brickCount number of bricks in wall(30)
     * @param lineCount lines of bricks in wall(3)
     * @param brickDimensionRatio width-to-height ratio of a single brick
     * @param ballPos position of ball(300, 430)
     */
    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        this.startPoint = new Point(ballPos);
        this.levelcons = new Level();

        levels = levelcons.makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;

        rnd = new Random();

        makeBall(ballPos);
        int speedX,speedY;
        do{
            speedX = rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -rnd.nextInt(3);
        }while(speedY == 0);

        ball.setSpeed(speedX,speedY);

        player = new Player((Point) ballPos.clone(),150,10, drawArea);

        area = drawArea;


    }


    /**
     * Construct a new rubber ball
     * @param ballPos position of ball (300, 430)
     */
    private void makeBall(Point2D ballPos){
        ball = new RubberBall(ballPos);
    }

    /**
     * player and ball move
     */
    public void move(){
        player.move();
        ball.move();
    }

    /**
     * it checks all impacts
     * checks if player bar is impacted by ball
     * check if brick are impacted until broken
     * checks if ball impacted the border of gameFrame
     * check if ball is lost by going out of bounds
     */
    public void findImpacts(){
        if(player.impact(ball)){
            ball.reverseY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
            * because for every model program checks for horizontal and vertical impacts
            */
            brickCount--;
        }
        else if(impactBorder()) {
            ball.reverseX();
        }
        else if(ball.getPosition().getY() < area.getY()){
            ball.reverseY();
        }
        else if(ball.getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }
    }

    /**
     * for switch case condition, it finds the direction of the brick where the ball impacts it
     * then it rebounds the ball in reverse direction
     *
     * @return False if the brick is broken, then call impact method and return brokenFlag
     */
    private boolean impactWall(){
        for(Brick b : bricks){
            switch(b.findImpact(ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.down, Crack.UP);
                case Brick.DOWN_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.up, Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.right, Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.left, Crack.LEFT);
            }
        }
        return false;
    }

    /**
     * check if ball impacts border of gameFrame
     * @return True if the ball goes beyond the left or right edge of the gameFrame
     */
    private boolean impactBorder(){
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    /**
     * getter for brickCount
     * @return number of bricks
     */
    public int getBrickCount(){
        return brickCount;
    }

    /**
     * getter for ballCount
     * @return number of balls
     */
    public int getBallCount(){
        return ballCount;
    }

    /**
     * getter for ballLost
     * @return boolean value of ballLost
     */
    public boolean isBallLost(){
        return ballLost;
    }

    /**
     * it resets the position of player bar and ball
     * it then resets the direction of ball movement, and ballLost to false
     */
    public void ballReset(){
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        int speedX,speedY;
        do{
            speedX = rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -rnd.nextInt(3);
        }while(speedY == 0);

        ball.setSpeed(speedX,speedY);
        ballLost = false;
    }

    /**
     * it restores all the brick
     * it also reset the ballCount to 3 again
     */
    public void wallReset(){
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }

    /**
     * @return true if number of balls is 0
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }

    /**
     * @return true if number of bricks is 0
     */
    public boolean isDone(){
        return brickCount == 0;
    }

    /**
     * it goes to the next level, and it also restores the brickCount back to 31
     */
    public void nextLevel(){
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }

    /**
     * returns True if there is level remaining
     * @return boolean True or False
     */
    public boolean hasLevel(){
        return level < levels.length;
    }

    /**
     * setter for ball's speed X
     * @param s integer value for speedX
     */
    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }

    /**
     * setter for ball's speed Y
     * @param s integer value for speedY
     */
    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    /**
     * reset number of balls to 3
     */
    public void resetBallCount(){
        ballCount = 3;
    }

    /**
     * setter for ballCount
     * @param ballCount number of balls
     */
    public void setBallCount(int ballCount) {this.ballCount = ballCount;}

    /**
     * getter for ballLost
     * @return boolean value of ballLost
     */
    public void setBallLost(boolean ballLost) {this.ballLost = ballLost;}

    /**
     * getter for Bricks
     * @return array of bricks
     */
    public Brick[] getBricks() {return bricks;}

    /**
     * getter for ball
     * @return ball
     */
    public Ball getBall() {return ball;}

    /**
     * getter for player
     * @return player green bar
     */
    public Player getPlayer() {return player;}



}
