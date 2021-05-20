package com.mine.game;

import com.mine.game.input.Controller;
import com.mine.game.level.Level;

import java.awt.event.KeyEvent;

public class Game {

    public int time;
    public Controller controls;
    public Level level;

    public Game() {
        controls = new Controller();
        level = new Level(20, 20);
    }

    public void tick(boolean[] key) {
        time++;

        boolean forward = key[KeyEvent.VK_W];
        boolean back = key[KeyEvent.VK_S];
        boolean right = key[KeyEvent.VK_D];
        boolean left = key[KeyEvent.VK_A];
        boolean sprint = key[KeyEvent.VK_SHIFT];
        boolean crouch = key[KeyEvent.VK_CONTROL];
        boolean jump = key[KeyEvent.VK_SPACE];
        boolean walking = key[KeyEvent.VK_ALT];

        controls.tick(forward, back, left, right, sprint, crouch, jump, walking);
    }

}
