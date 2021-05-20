package com.mine.game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static com.mine.game.Display.*;

public class RunGame {

    public RunGame() {
        BufferedImage cursor = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blank = Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0, 0), "blank");
        Display game = new Display();
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(TITLE);
        frame.setSize(Display.getGameWidth(), Display.getGameHeight());
        //frame.getContentPane().setCursor(blank);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.add(game);

        game.start();
    }
}
