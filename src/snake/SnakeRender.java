/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SnakeRender extends JPanel {

    private final int Right = 1;
    private final int Left = 2;
    private final int Up = 3;
    private final int Down = 4;
    private BufferedImage endtop = null;
    private BufferedImage endbot = null;
    private BufferedImage endright = null;
    private BufferedImage endleft = null;
    private BufferedImage headtop = null;
    private BufferedImage headbot = null;
    private BufferedImage headright = null;
    private BufferedImage headleft = null;
    private BufferedImage one = null;
    private BufferedImage two = null;
    private BufferedImage three = null;
    private BufferedImage four = null;
    private BufferedImage horizontal = null;
    private BufferedImage vertical = null;

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, 510, 510);
        try {
            endtop = ImageIO.read(new File("./endtop.gif"));
            endbot = ImageIO.read(new File("./endbot.png"));
            endright = ImageIO.read(new File("./endright.png"));
            endleft = ImageIO.read(new File("./endleft.gif"));
            headtop = ImageIO.read(new File("./headtop.png"));
            headbot = ImageIO.read(new File("./headbot.png"));
            headright = ImageIO.read(new File("./headright.png"));
            headleft = ImageIO.read(new File("./headleft.png"));
            one = ImageIO.read(new File("./1.png"));
            two = ImageIO.read(new File("./2.png"));
            three = ImageIO.read(new File("./3.png"));
            four = ImageIO.read(new File("./4.png"));
            horizontal = ImageIO.read(new File("./horizontal.png"));
            vertical = ImageIO.read(new File("./vertical.png"));
        } catch (IOException ex) {
            Logger.getLogger(SnakeRender.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.setColor(Color.ORANGE);
        g.fillRect(Snake.Food.x, Snake.Food.y, 15, 15); 
        //g.drawImage(food, Snake.Food.x, Snake.Food.y, 15, 15, null);
        String string = "Score: " + Snake.Score * 10;
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString(string, 520, 50);
        int number = 0;
        for (int i = 0; i < Snake.snk.size();i++) {
            SnakeParts snake = Snake.snk.get(i);
            /*  number++;
            if(number % 2 == 0){
               g.setColor(new Color(255,0,0));   
            }else{
                 g.setColor(new Color(255,255,0));   
            }
            Random rand = new Random();
            float r = rand.nextFloat();
            float G = rand.nextFloat();
            float b = rand.nextFloat();
            g.fillRect(p.x,p.y, 15, 15);   */
            if (i == Snake.snk.size() - 1) {
                if (Snake.Direction == Right) {
                    g.drawImage(headright, snake.snakePoint.x, snake.snakePoint.y, 15, 15, null);
                }
                if (Snake.Direction == Left) {
                    g.drawImage(headleft, snake.snakePoint.x, snake.snakePoint.y, 15, 15, null);
                }
                if (Snake.Direction == Up) {
                    g.drawImage(headtop, snake.snakePoint.x, snake.snakePoint.y, 15, 15, null);
                }
                if (Snake.Direction == Down) {
                    g.drawImage(headbot, snake.snakePoint.x, snake.snakePoint.y, 15, 15, null);
                }
            }else if(i == 0){
                if(Snake.snk.get(i).Direction == Down){
                    g.drawImage(endtop, snake.snakePoint.x, snake.snakePoint.y, 15, 15, null);
                }
                if(Snake.snk.get(i).Direction == Up){
                    g.drawImage(endbot, snake.snakePoint.x, snake.snakePoint.y, 15, 15, null);
                }
                if(Snake.snk.get(i).Direction == Right){
                    g.drawImage(endleft, snake.snakePoint.x, snake.snakePoint.y, 15, 15, null);
                }
                if(Snake.snk.get(i).Direction == Left){
                    g.drawImage(endright, snake.snakePoint.x, snake.snakePoint.y, 15, 15, null);
                }
            }
            if(i != 0 && i != Snake.snk.size() - 1){
                if(snake.Direction == Down && Snake.snk.get(i-1).Direction == Right
                        || snake.Direction == Left && Snake.snk.get(i-1).Direction == Up){
                     g.drawImage(two, snake.snakePoint.x, snake.snakePoint.y, 15, 15, null);
                }
                if(snake.Direction == Down && Snake.snk.get(i-1).Direction == Left
                        || snake.Direction == Right && Snake.snk.get(i-1).Direction == Up){
                     g.drawImage(one, snake.snakePoint.x, snake.snakePoint.y, 15, 15, null);
                }
                if(snake.Direction == Right && Snake.snk.get(i-1).Direction == Down
                        || snake.Direction == Up && Snake.snk.get(i-1).Direction == Left){
                     g.drawImage(three, snake.snakePoint.x, snake.snakePoint.y, 15, 15, null);
                }
                if(snake.Direction == Left && Snake.snk.get(i-1).Direction == Down
                        || snake.Direction == Up && Snake.snk.get(i-1).Direction == Right){
                     g.drawImage(four, snake.snakePoint.x, snake.snakePoint.y, 15, 15, null);
                }
                 if(snake.Direction == Left && Snake.snk.get(i-1).Direction == Left
                        || snake.Direction == Right && Snake.snk.get(i-1).Direction == Right){
                     g.drawImage(horizontal, snake.snakePoint.x, snake.snakePoint.y, 15, 15, null);
                }
                  if(snake.Direction == Up && Snake.snk.get(i-1).Direction == Up
                        || snake.Direction == Down && Snake.snk.get(i-1).Direction == Down){
                     g.drawImage(vertical, snake.snakePoint.x, snake.snakePoint.y, 15, 15, null);
                }              
            }

        }

        if (Snake.GameOver) {
            String gameOver = "Game OVER! Press SPACE TO PLAY AGAIN N00b";
            g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
            g.setColor(Color.red);
            g.drawString(gameOver, 50, 300);

        }
    }

}
