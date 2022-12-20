import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle{

    Random random;
    int xVelocity;
    int yVelocity;
    int initialSpeed = 3;

    Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        //random direction of ball
        random = new Random();
        //when touch the paddle go to another side?
        int randomXDirection = random.nextInt(1);
        if (randomXDirection == 0) {
            randomXDirection--;
            setXDirection(randomXDirection*initialSpeed);
        }

        int randomYDirection = random.nextInt(1);
        if (randomYDirection == 0) {
            randomYDirection--;
            setYDirection(randomYDirection*initialSpeed);
        }
    }
    //direction on x coordinate
    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }
    //direction on y coordinate
    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }
    //moving on x and y coordination
    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    //draw white ball
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, height, width);
    }

}
