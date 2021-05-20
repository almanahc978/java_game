package com.mine.game.gui;

import com.mine.game.Config;
import com.mine.game.Display;

import javax.swing.*;
import java.awt.*;

public class Options extends Launcher {

    private int width = 550;
    private int height = 450;

    private JButton ok;
    private Choice resolution;

    private Rectangle rok, rresolution;

    private JTextField twidth, theight;
    private JLabel lwidth, lheight;

    int w = 0;
    int h = 0;

    public Options() {
        super(1, new Display());
        setTitle("Options");
        setSize(new Dimension(width, height));
        setLocationRelativeTo(null);

        drawButtons();
    }

    public void drawButtons() {

        ok = new JButton("OK");
        resolution = new Choice();
        twidth = new JTextField();
        theight = new JTextField();
        lwidth = new JLabel("Width: ");
        lheight = new JLabel("Height: ");


        rok = new Rectangle(width - 100, height - 70, button_width, button_height - 10);
        rresolution = new Rectangle(50, 80, 80, 25);
        twidth.setBounds(60, 150, 60, 20);
        theight.setBounds(60, 180, 60, 20);
        lwidth.setBounds(30, 150, 120, 20);
        lheight.setBounds(30, 180, 120, 20);


        ok.setBounds(rok);
        resolution.setBounds(rresolution);

        resolution.add("640,480");
        resolution.add("800,600");
        resolution.add("1024,768");
        resolution.select(1);

        contentPane.add(ok);
        contentPane.add(resolution);
        contentPane.add(twidth);
        contentPane.add(theight);
        contentPane.add(lwidth);
        contentPane.add(lheight);

        ok.addActionListener(e -> {
            dispose();
            new Launcher(0, new Display());
            config.saveConfiguration("width", parseWidth());
            config.saveConfiguration("height", parseHeight());
        });
    }

    private void drop() {
        int selection = resolution.getSelectedIndex();
        if (selection == 0) {
            w = 640;
            h = 480;
        }
        if (selection == 1) {
            w = 800;
            h = 600;
        }
        if (selection == 2) {
            w = 1024;
            h = 768;
        }
    }

    private int parseWidth() {
        try {
            int w = Integer.parseInt(twidth.getText());
            return w;
        } catch (NumberFormatException e) {
            drop();
            return w;
        }
    }

    private int parseHeight() {
        try {
            int h = Integer.parseInt(theight.getText());
            return h;
        } catch (NumberFormatException e) {
            drop();
            return w;
        }
    }

}
