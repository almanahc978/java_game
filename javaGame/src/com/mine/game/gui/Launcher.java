package com.mine.game.gui;

import com.mine.game.Config;
import com.mine.game.Display;
import com.mine.game.RunGame;

import javax.swing.*;
import java.awt.*;


public class Launcher extends JFrame {

    protected JPanel contentPane = new JPanel();

    protected Config config = new Config();

    private JButton play, options, help, quit;
    private Rectangle rplay, roptions, rhelp, rquit;

    private int width = 800;
    private int height = 400;

    protected int button_width = 80;
    protected int button_height = 40;

    public Launcher(int id, Display display) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setUndecorated(true);
        setTitle("Launcher");
        setSize(new Dimension(width, height));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //getContentPane().add(contentPane);
        add(display);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        contentPane.setLayout(null);
        if (id == 0) {
            drawButtons();
        }
        display.start();
        repaint();
    }

    private void drawButtons() {


        play = new JButton("Play");
        options = new JButton("Options");
        help = new JButton("Help");
        quit = new JButton("Quit");

        rplay = new Rectangle((width / 2) - (button_width / 2), 40, button_width, button_height);
        roptions = new Rectangle((width / 2) - (button_width / 2), 90, button_width, button_height);
        rhelp = new Rectangle((width / 2) - (button_width / 2), 140, button_width, button_height);
        rquit = new Rectangle((width / 2) - (button_width / 2), 190, button_width, button_height);

        play.setBounds(rplay);
        options.setBounds(roptions);
        help.setBounds(rhelp);
        quit.setBounds(rquit);

        contentPane.add(play);
        contentPane.add(options);
        contentPane.add(help);
        contentPane.add(quit);

        play.addActionListener(e -> {
            config.loadConfiguration("resources/settings/config.xml");
            dispose();
            new RunGame();
        });
        options.addActionListener(e -> {
            dispose();
            new Options();
        });
        help.addActionListener(e -> {

        });
        quit.addActionListener(e -> System.exit(0));
    }


}
