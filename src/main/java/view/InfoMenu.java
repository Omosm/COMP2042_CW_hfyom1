package view;

import javax.swing.*;
import java.awt.*;

public class InfoMenu extends JFrame {

    private static final int DEF_WIDTH = 550;
    private static final int DEF_HEIGHT = 450;
    private static final String DEF_TITLE = "Info Guide";
    private JLabel background;
    private ImageIcon kirbyicon;
    private JLabel label;

    /**
     * Constructor of InfoFrame class which initialize the infoFrame
     */
    public InfoMenu(){
        InfoGuideText();
        initialize();
        setBackground();
        this.add(background);
    }

    /**
     * Initialize infoFrame as a frame with specific title, size, layout, visibility
     */
    public void initialize(){
        //this set the title and visible of the frame to true
        this.setTitle(DEF_TITLE);
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setLayout(null);
        this.autoLocate();
        this.setVisible(true);
    }

    /**
     * set an image as background for infoFrame
     */
    private void setBackground(){
        kirbyicon = new ImageIcon(getClass().getResource("/Purple.jpg"));
        background = new JLabel(kirbyicon);
        background.setSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        background.add(label);
    }

    /**
     * so that infoFrame pops up relative to screenSize regardless of any monitor
     */
    private void autoLocate(){
        //this get the screenSize of monitor
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }

    /**
     * set label with text that provides guides for player, and set font size, style for the text
     */
    private void InfoGuideText(){
        label = new JLabel();
        label.setText("<html>" + "<h1>Brick Destroyer Instructions</h1>" +
                "<b>Player only receives 3 balls </b><br/>" +
                "<br/><b>Controls:</b><br/>" +
                "<b>SPACE: </b> start/pause the game<br/>" +
                "<b>A: </b> move the player left<br/>" +
                "<b>D: </b> move the player right<br/>" +
                "<b>ESC: </b> enter/exit pause menu<br/>" +
                "<b>ALT+SHIFT+F1: </b> open debug console<br/>"
        );
        label.setFont(new Font(null, Font.PLAIN, 15));
        label.setBounds(30,30, 450,350);
    }

}
