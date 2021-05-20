package com.mine.game;

import com.mine.game.graphics.Screen;
import com.mine.game.graphics.Texture;
import com.mine.game.gui.Launcher;
import com.mine.game.input.Controller;
import com.mine.game.input.InputHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;


public class Display extends Canvas implements Runnable {

    public static int width = 800;
    public static int height = 600;

    public static final String TITLE = "Couldn't come up with the name Pre_Alpha 0.5";

    private Thread thread;
    private boolean running = false;

    private Screen screen;
    private Game game;

    private BufferedImage img;

    private int[] pixels;

    private InputHandler input;
    private int newX = 0;
    private int oldX = 0;
    private int newY = 0;
    private int oldY = 0;

    private int fps;

    public static double MouseSpeed;

    public static int selection = 0;

    public Display() {

        game = new Game();
        screen = new Screen(getGameWidth(), getGameHeight());
        img = new BufferedImage(getGameWidth(), getGameHeight(), BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();

        input = new InputHandler();
        addKeyListener(input);
        addMouseListener(input);
        addMouseMotionListener(input);
        addFocusListener(input);
    }

    public static int getGameWidth() {
        return width;
    }

    public static int getGameHeight() {
        return height;
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();

    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void run() {
        int frames = 0;
        double unprocessedSeconds = 0.0;
        long previousTime = System.nanoTime();
        double secondsPerTick = 1 / 60.0;
        int tickCount = 0;
        boolean ticked = false;

        while (running) {
            requestFocus();
            long currentTime = System.nanoTime();
            long passedTime = currentTime - previousTime;
            previousTime = currentTime;
            unprocessedSeconds += passedTime / 1000000000.0;

            while (unprocessedSeconds > secondsPerTick) {
                tick();
                unprocessedSeconds -= secondsPerTick;
                ticked = true;
                tickCount++;
                if (tickCount % 60 == 0) {
                    fps = frames;
                    previousTime += 1000;
                    frames = 0;
                }
                if (ticked) {
                    //render();
                    renderMenu();
                    frames++;
                }
                //render();
            }

            newX = InputHandler.MouseX;
            newY = InputHandler.MouseY;
            if (newX > oldX) {
                //System.out.println("RIGHT");
                Controller.TurnRight = true;
            }
            if (newX < oldX) {
                /// System.out.println("LEFT");
                Controller.TurnLeft = true;
            }
            if (newX == oldX) {
                //System.out.println("STILL");
                Controller.TurnRight = false;
                Controller.TurnLeft = false;
            }
            MouseSpeed = Math.abs(newX - oldX);
            oldX = newX;
            //System.out.println("X:" + InputHandler.MouseX + "Y:" + InputHandler.MouseY);
        }
    }

    private void renderMenu() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 400);
        try {
            g.drawImage(ImageIO.read(Display.class.getResource("/launcher_image.jpg")), 0, 0, 800, 400, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Verdana", 0, 30));
        g.drawString("Play", 700, 90);
        g.dispose();
        bs.show();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        screen.render(game);

        for (int i = 0; i < getGameWidth() * getGameHeight(); i++) {
            pixels[i] = screen.pixels[i];
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(img, 0, 0, getGameWidth() + 10, getGameHeight() + 10, null);
        g.setFont(new Font("Verdana", 2, 20));
        g.setColor(Color.YELLOW);
        g.drawString("FPS:" + fps, 20, 30);
        g.dispose();
        bs.show();
    }

    private void tick() {

        game.tick(input.key);
    }


    public static void main(String[] args) {

        Display display = new Display();
        new Launcher(0, display);

    }

}
