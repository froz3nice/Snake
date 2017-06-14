/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public final class Snake implements ActionListener, KeyListener {

    public static int Direction;
    private final int Right = 1;
    private final int Left = 2;
    private final int Up = 3;
    private final int Down = 4;
    public static Point Coordinates;
    public static Point Food;
    public static SnakeRender Render;
    public static Array1<SnakeParts> snk;
    //public static ArrayList<SnakeParts> snake;
    public static Point Head;
    public Timer timer = new Timer(50, this);
    public static int Score;
    public static Boolean GameOver;
    public Boolean Paused = false;
    private final JFrame frame;
    private JPanel textPanel;

    public static void main(String[] args) {
        Snake snake1 = new Snake();
    }

    public Point getCoordinates() {
        return Coordinates;
    }

    public void setCoordinates(int x, int y) {
        Coordinates = new Point(x, y);
    }

    public Snake() {
        frame = new JFrame("Snake");
        //frame.setLayout(new GridBagLayout());
        frame.setSize(700, 700);
        //frame.setBounds(0, 0, 500, 500);
        frame.setResizable(false);
        Render = new SnakeRender();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(Render);
        timer.start();
        
        frame.addKeyListener(this);
        startGame();
    }

    public void startGame() {
        //= new JPanel(new FlowLayout(SwingConstants.LEADING, 10, 10));
        Coordinates = new Point(0, 0);
        //snake = new ArrayList<>();
        snk = new Array1();
        SnakeParts sP = new SnakeParts();
        sP.snakePoint = Coordinates;
        sP.Direction = Direction;
        
        snk.add(sP);
        //snake.add(sP);
        Score = 0;
        Random rnd = new Random();
        int x = rnd.nextInt(450) + 1;
        while (x % 15 != 0) {
            x++;
        }
        int y = rnd.nextInt(450) + 1;
        while (y % 15 != 0) {
            y--;
        }
        Food = new Point(x, y);
        System.out.println(Food.x);
        System.out.println(Food.y);
        Direction = Down;
        GameOver = false;
    }

    public boolean checkTail(int x, int y) {
        for (int i = 0; i < snk.size(); i++) {
          if (snk.get(i).snakePoint.equals(new Point(x, y))) {
                GameOver = true;
                return false;
            } 
        }
       /* for (SnakeParts point : snk){//snake) {
            if (point.snakePoint.equals(new Point(x, y))) {
                GameOver = true;
                return false;
            }
        }*/
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        Render.repaint();
        //System.out.println(Coordinates.x + " " + Coordinates.y);

        if (!GameOver) {
            if (snk.size() > 10 + Score) {
                snk.remo(0);
            }
            SnakeParts sP = new SnakeParts();
            sP.snakePoint = Coordinates;
            sP.Direction = Direction;
            snk.add(sP);
            Head = snk.get(snk.size() - 1).snakePoint;
            
            
            if (Direction == Down && checkTail(Coordinates.x,Coordinates.y+15)) {
                setCoordinates(Coordinates.x, Coordinates.y + 15);
            }
            if (Direction == Up && checkTail(Coordinates.x,Coordinates.y-15)) {
                setCoordinates(Coordinates.x, Coordinates.y - 15);
            }
            if (Direction == Left && checkTail(Coordinates.x-15,Coordinates.y)) {
                setCoordinates(Coordinates.x - 15, Coordinates.y);
            }
            if (Direction == Right && checkTail(Coordinates.x+15,Coordinates.y)) {
                setCoordinates(Coordinates.x + 15, Coordinates.y);
            }
            if (Head.x >= 510 || Head.y >= 510 || Head.x < 0 || Head.y < 0) {
                GameOver = true;
            }
            if (Head.x == Food.x && Head.y == Food.y) {
                Random rnd = new Random();
                int x = rnd.nextInt(450) + 1;
                while (x % 15 != 0) {
                    x++;
                }
                int y = rnd.nextInt(450) + 1;
                while (y % 15 != 0) {
                    y++;
                }
                Food = new Point(x, y);
                Score += 1;
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            if (Direction != Right) {
                Direction = Left;
            }
        }

        if (key == KeyEvent.VK_RIGHT) {
            if (Direction != Left) {
                Direction = Right;
            }
        }

        if (key == KeyEvent.VK_UP) {
            if (Direction != Down) {
                Direction = Up;
            }
        }

        if (key == KeyEvent.VK_DOWN) {
            if (Direction != Up) {
                Direction = Down;
            }
        }
        if (key == KeyEvent.VK_SPACE) {
            GameOver = false;
            startGame();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
