package com.mine.game;

import java.applet.Applet;
import java.awt.*;
import java.awt.desktop.AppForegroundListener;

public class GameApplet extends Applet {

    private Display display = new Display();

    public void init(){
        setLayout(new BorderLayout());
        add(display);
    }
    public void start(){
        display.start();
    }
    public void stop(){
        display.stop();
    }
}
