import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame{

    GamePanel panel = new GamePanel();

    GameFrame(){
        panel = new GamePanel(); //we instantiate the class
        this.add(panel);
        this.setTitle("Ping Pong Game");
        this.setResizable(false); //not to resize a game panel
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //for exit button of the game
        this.pack();//sizes(оразмерява) the frame so that all of its contents are at the preferred dimensions
        this.setVisible(true); //make the frame appear on the screen
        this.setLocationRelativeTo(null); //for position of frame
    }
}
