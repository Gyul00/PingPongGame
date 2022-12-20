import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Paddle extends Rectangle{ //classes can be extended

    int id;
    int yVelocity; //skorost
    int speed = 10;

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
    }

    //to control paddles with keys

    //when we press key
    public void keyPressed(KeyEvent e) {
        switch (id) {
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W) {
                    setYDirection(-speed);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_S) {
                    setYDirection(speed);
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP) {
                    setYDirection(-speed);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                    setYDirection(speed);
                    move();
                }
                break;
        }
    }

    //to stop moving when we release the key
    public void keyReleased(KeyEvent e) {
        switch (id) {
            case 1: //for blue paddle
                if(e.getKeyCode()==KeyEvent.VK_W) {
                    setYDirection(0); //tr da e 0 da spre pri otpuskane na kopcheto
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_S) {
                    setYDirection(0);
                    move();
                }
                break;
            case 2: //for red paddle
                if(e.getKeyCode()==KeyEvent.VK_UP) {
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                    setYDirection(0);
                    move();
                }
                break;
        }
    }
    //direction of paddles on y coordination
    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }
    //to move paddles
    public void move() {
        y = y + yVelocity;
    }

    //set colors of paddles
    public void draw(Graphics g) {
        if(id==1)
            g.setColor(Color.BLUE);
        else
            g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }
}
