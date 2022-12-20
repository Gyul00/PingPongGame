import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements  Runnable{ //Runnable is interface (interfaces can be implemented)

    //size of game panel
    static  final int GAME_WIDTH = 1000;
    static  final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555)); //to be every time 5/9
    static  final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    //size of ball
    static  final int BALL_DIAMETER = 20;
    //size of paddles
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    //we instantiate all properties to can use them after
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    //constructor
    GamePanel() {
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true); //to be focused on the ball
        this.addKeyListener(new AL()); //for monitor-keyboard related events
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start(); //start game
    }

    //instantiate ball and paddle with their coordinates
    //new ball
    public void newBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH/2) - (BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
    }

    public void newPaddles() {
        paddle1 = new Paddle(0,(GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT,1);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH,(GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT,2);
    }

    //for visualize all of this things-without this we have a gray panel
    public void paint(Graphics g) {
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }

    //draw all of this items
    public  void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    //to can move paddles and ball
    public void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
    }


    public void checkCollision() {
        //to not go the ball out of a screen
        if(ball.y <= 0) { //if ball reach the end of the screen
            ball.setYDirection(-ball.yVelocity); //returns on the other direction
        }
        //same thing here from the other side of screen
        if (ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }
        //if the ball touch paddles to not go out of screen
        if(ball.intersects(paddle1)) { //if ball touch paddle1
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //optional for more difficulty(увеличава се скоростта)
            if(ball.yVelocity > 0)
                ball.yVelocity++; //optional for more difficulty
            else //if ball don't touch paddle
                ball.yVelocity--; //(намалява се скоростта) velocity
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        //same for ball2
        if(ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //optional for more difficulty(uvelichava se skorostta)
            if(ball.yVelocity > 0)
                ball.yVelocity++; //optional for more difficulty
            else
                ball.yVelocity--;
            ball.setXDirection(-
                    ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        //to not go out paddles from screen
        if(paddle1.y <= 0)
            paddle1.y = 0;
        if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
        if(paddle2.y <= 0)
            paddle2.y = 0;
        if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
        //give a player 1 point and creates new paddles & ball
        if (ball.x <= 0) { //ako topcheto izleze ot strana na player2
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("Player 2 ->"+score.player2);
        }
        else if (ball.x >= GAME_WIDTH-BALL_DIAMETER) { //ako topcheto izleze ot strana na player1
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("Player 1 ->"+score.player1);
        }
        if (score.player1>=10){
            System.out.println("Player 1 win!");
            System.exit(0);
        }
        else if (score.player2>=10){
            System.out.println("Player 2 win!");
            System.exit(0);
        }
    }


    public  void run() {
        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0; //if we decrease(намалим) this value the ball moves slower
        double ns = 1000000000 / amountOfTicks; //how time our program is open
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns; //elapsed(изтеклото) game time
            lastTime = now;
            if(delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    //declaration of methods to press and release buttons
    public  class  AL extends  KeyAdapter{
        public  void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public  void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }

}
