package controller;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * abstract class Ball inherited by Rubber Ball
 */
abstract public class Ball {

    private Shape ballFace;

    private Point2D center;

    public Point2D up;
    public Point2D down;
    public Point2D left;
    public Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    /**
     * Constructor of ball class
     * initialize and set value for ball's coordinates and speed
     *
     * @param center center coordinate of ball
     * @param radiusA horizontal radius of the ball
     * @param radiusB vertical radius of the ball
     * @param inner inner color of ball(yellow)
     * @param border border color of ball(black)
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center;

        up = new Point2D.Double(center.getX(),center.getY()-(radiusB / 2));
        down = new Point2D.Double(center.getX(),center.getY()+(radiusB / 2));
        left = new Point2D.Double(center.getX()-(radiusA /2),center.getY());
        right = new Point2D.Double(center.getX()+(radiusA /2),center.getY());


        ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     * @param center center coordinate of ball
     * @param radiusA horizontal radius of ball
     * @param radiusB vertical radius of ball
     * @return shape ball
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    /**
     *move the ball to a new location based on speedX and speedY
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    /**
     * set speedX and speedY of ball
     *
     * @param x speedX (horizontal speed)
     * @param y speedY (vertical speed)
     */
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    /**
     * set speedX for ball
     * @param s speed X
     */
    public void setXSpeed(int s){
        speedX = s;
    }

    /**
     * set speedY for ball
     * @param s speed Y
     */
    public void setYSpeed(int s){
        speedY = s;
    }

    /**
     * reverse speedX ball by -1 (so ball goes left)
     */
    public void reverseX(){
        speedX *= -1;
    }

    /**
     * reverse speedY ball by -1 (so ball goes up)
     */
    public void reverseY(){
        speedY *= -1;
    }

    /**
     * getter for ball's border color
     * @return border color of ball (black)
     */
    public Color getBorderColor(){
        return border;
    }

    /**
     * getter for ball's inner color
     * @return inner color of ball (yellow)
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * getter for ball's center position
     * @return center coordinate of ball
     */
    public Point2D getPosition(){
        return center;
    }

    /**
     * getter for ballFace
     * @return outline shape of ball
     */
    public Shape getBallFace(){
        return ballFace;
    }

    /**
     * move the ball to specified point P
     *
     * @param p point p of type Point
     */
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    /**
     * reset up, down, left, right coordinate of ball based on width and height of rectangular frame
     *
     * @param width width of rectangular frame
     * @param height height of rectangular frame
     */
    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    /**
     * getter of speedX of ball
     * @return speedX
     */
    public int getSpeedX(){
        return speedX;
    }

    /**
     *getter of speedY of ball
     * @return speedY
     */
    public int getSpeedY(){
        return speedY;
    }

    /**
     * getter for ball's up coordinate
     * @return up point of ball
     */
    public Point2D getUp() {return up;}

    /**
     * getter for ball's down coordinate
     * @return down point of ball
     */
    public Point2D getDown() {return down;}

    /**
     * getter for ball's left coordinate
     * @return left point of ball
     */
    public Point2D getLeft() {return left;}

    /**
     * getter for ball's right coordinate
     * @return right point of ball
     */
    public Point2D getRight() {return right;}


}
