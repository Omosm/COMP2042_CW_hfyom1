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
package view;

import model.GameFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;


/**
 * HomeMenu is a JComponent pops up first when program is initialized
 * HomeMenu holds start, exit, info buttons and player name input
 */
public class HomeMenu extends JComponent implements MouseListener, MouseMotionListener {

    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "Brick Destroy";
    private static final String CREDITS = "Version 0.1";
    private static final String START_TEXT = "Start";
    private static final String MENU_TEXT = "Exit";
    private static final String INFO_TEXT = "Info";

    public static String pnames = "";

    private static final Color BG_COLOR = Color.BLUE.darker();
    private static final Color BORDER_COLOR = new Color(93, 169, 255);
    private static final Color DASH_BORDER_COLOR = new  Color(204, 249, 255);
    private static final Color TEXT_COLOR = new Color(0, 0, 0);
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    private Rectangle menuFace;
    private Rectangle startButton;
    private Rectangle menuButton;
    private Rectangle infoButton;
    private Rectangle PlayerName;


    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;

    private GameFrame owner;

    private boolean startClicked;
    private boolean menuClicked;
    private boolean playerClicked;
    PlayerName playerNam= new PlayerName();
    private boolean infoClicked;

    private Image backgroundImage;


    /**
     * Constructor for class HomeMenu that construct HomeMenu as a JComponent
     * and set values for buttons size, font size, font style
     *
     * @param owner GameFrame
     * @param area Dimension size for HomeMenu(450, 300)
     */
    public HomeMenu(GameFrame owner,Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;


        try {
            backgroundImage = ImageIO.read(new File("src/main/resources/Purple.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.setPreferredSize(area);

        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        startButton = new Rectangle(btnDim);
        menuButton = new Rectangle(btnDim);
        infoButton = new Rectangle(btnDim);
        PlayerName = new Rectangle(btnDim);

        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        greetingsFont = new Font("Noto Mono",Font.PLAIN,25);
        gameTitleFont = new Font("Noto Mono",Font.BOLD,40);
        creditsFont = new Font("Monospaced",Font.PLAIN,10);
        buttonFont = new Font("Monospaced",Font.PLAIN,startButton.height-2);



    }


    /**
     * this is a built-in paint method, so paint() doesn't have to be called explicitly,
     * it will be called automatically everytime constructor for HomeMenu is called
     * paint method draws all component in HomeMenu
     * @param g graphics object
     */
    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }


    /**
     * draws component on menuFace by calling the following method: drawContainer, drawText, drawButton
     * @param g2d graphics2D object
     */
    public void drawMenu(Graphics2D g2d){

        drawContainer(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        //method call
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);

        playerNam.setVisible(true);
    }

    /**
     * draw the background, and border for HomeMenu
     * @param g2d graphics2D object
     */
    private void drawContainer(Graphics2D g2d){
        Color prev = g2d.getColor();

        g2d.setColor(BG_COLOR);
        g2d.fill(menuFace);

        Stroke tmp = g2d.getStroke();

        g2d.setStroke(borderStoke_noDashes);
        g2d.setColor(DASH_BORDER_COLOR);
        g2d.draw(menuFace);

        JTextField d =new JTextField();
        d.setSize(12,21);
        d.setLocation(20,20);
        this.add(d);

        g2d.setStroke(borderStoke);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(tmp);

        g2d.setColor(prev);
        g2d.drawImage(backgroundImage, 0, 0, null);
    }

    /**
     * draw the texts, greetings, gameTitle, credits in specific location on HomeMenu
     * @param g2d graphics2D object
     */
    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS,frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE,frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS,frc);

        int sX,sY;

        sX = (int)(menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 9);

        g2d.setFont(greetingsFont);
        g2d.drawString(GREETINGS,sX,sY);

        sX = (int)(menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 1.2;//add 10% of String height between the two strings

        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE,sX,sY);

        sX = (int)(menuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS,sX,sY);


    }

    /**
     * draw the buttons, start, exit, info and player input in order on HomeMenu screen
     * @param g2d graphics2D object
     */
    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(START_TEXT,frc);
        Rectangle2D mTxtRect = buttonFont.getStringBounds(MENU_TEXT,frc);
        Rectangle2D iTxtRect = buttonFont.getStringBounds(INFO_TEXT,frc);
        Rectangle2D pTxtRect = buttonFont.getStringBounds("Player:",frc);

        g2d.setFont(buttonFont);

        int x = (menuFace.width - startButton.width) / 2;
        int y =(int) ((menuFace.height - startButton.height) * 0.55);

        startButton.setLocation(x,y);

        x = (int)(startButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(startButton.getHeight() - txtRect.getHeight()) / 2;

        x += startButton.x;
        y += startButton.y + (startButton.height * 0.9);




        if(startClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(startButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(START_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(startButton);
            g2d.drawString(START_TEXT,x,y);
        }

        x = startButton.x;
        y = startButton.y;

        y *= 1.2;

        menuButton.setLocation(x,y);




        x = (int)(menuButton.getWidth() - mTxtRect.getWidth()) / 2;
        y = (int)(menuButton.getHeight() - mTxtRect.getHeight()) / 2;

        x += menuButton.x;
        y += menuButton.y + (startButton.height * 0.9);

        if(menuClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(menuButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(MENU_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(menuButton);
            g2d.drawString(MENU_TEXT,x,y);
        }

        x = menuButton.x;
        y = startButton.y;
        x -= 0;
        y += 100;
        PlayerName.setLocation(x,y);
        x = (int)(PlayerName.getWidth() - pTxtRect.getWidth()) / 2;
        y = (int)(PlayerName.getHeight() - pTxtRect.getHeight()) / 2;

        x += PlayerName.x;
        y += PlayerName.y + (menuButton.height * 0.9);


        if(playerClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(PlayerName);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString("Player:",x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(PlayerName);
            g2d.drawString("Player:",x,y);
        }

        x = menuButton.x;
        y = menuButton.y;
        y *= 1.16;
        infoButton.setLocation(x,y);

        //set the location for Exit text inside start button
        x = (int)(infoButton.getWidth() - iTxtRect.getWidth()) / 2;
        y = (int)(infoButton.getHeight() - iTxtRect.getHeight()) / 2;
        x += infoButton.x;
        y += infoButton.y + (menuButton.height * 0.9);

        //if menuButton is clicked
        //this set the color of button to light green, and draw it out
        //also set color of Start text to white, and draw it out
        //else, just draw the button normally
        if(infoClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(infoButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(INFO_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(infoButton);
            g2d.drawString(INFO_TEXT,x,y);
        }

    }

    /**
     * method is only activated when buttons are clicked(pressed and released)
     * if startButton is clicked then gameBoard is initialized
     * if menuButton is clicked then quit program, and "Goodbye user" is printed
     * if infoButton is clicked, infoFrame will pop up
     * @param mouseEvent mouse action
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)){

            pnames= playerNam.pname;
            //sv.SaveScorename(pname);
            playerNam.setVisible(false);
            playerNam.dispose();

           owner.enableGameBoard();

        }
        else if(menuButton.contains(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
        else if(infoButton.contains(p)){
            new InfoMenu();
        }
    }

    /**
     * if either buttons are pressed, then set Clicked to true, then repaint the button to white
     * @param mouseEvent mouse action
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)){
            startClicked = true;
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);

        }
        else if(menuButton.contains(p)){
            menuClicked = true;
            repaint(menuButton.x,menuButton.y,menuButton.width+1,menuButton.height+1);
        }
        else if(infoButton.contains(p)){
            infoClicked = true;
            repaint(infoButton.x,infoButton.y,infoButton.width+1,infoButton.height+1);
        }
    }

    /**
     * if either button are pressed, and then released outside the button,
     * then set Clicked to false, then repaint the buttons to original color
     * @param mouseEvent mouse action
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(startClicked ){
            startClicked = false;
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);
        }
        else if(menuClicked){
            menuClicked = false;
            repaint(menuButton.x,menuButton.y,menuButton.width+1,menuButton.height+1);
        }
        else if(infoClicked){
            infoClicked = false;
            repaint(infoButton.x,infoButton.y,infoButton.width+1,infoButton.height+1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * when mouse hover the buttons, mouse cursor will turn into hand cursor
     * @param mouseEvent mouse action
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p) || menuButton.contains(p) || infoButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}
