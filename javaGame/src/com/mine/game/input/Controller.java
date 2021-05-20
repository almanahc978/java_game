package com.mine.game.input;

import com.mine.game.Display;

public class Controller {

    public double x, y, z, rotation, xa, za, rotationa;
    public static boolean TurnLeft = false;
    public static boolean TurnRight = false;
    public static boolean walk = false;
    public static boolean crouchWalk = false;
    public static boolean sprintWalk = false;

    public void tick(boolean forward, boolean back, boolean left, boolean right, boolean sprint, boolean crouch, boolean jump, boolean walking) {
        double rotationSpeed = 0.001 * Display.MouseSpeed;
        double rotationSpeedArrows = 0.001;
        double walkSpeed = 0.5;
        double jumpHeight = 0.5;
        double crouchHeight = 0.33;
        double xMove = 0;
        double zMove = 0;

        if (forward) {
            zMove++;
            walk = true;
        }
        if (back) {
            zMove--;
            walk = true;
        }
        if (left) {
            xMove--;
            walk = true;
        }
        if (right) {
            xMove++;
            walk = true;
        }
        if (TurnLeft) {
            if (InputHandler.MouseButton == 3) {
            } else {

                walk = true;
                rotationa -= rotationSpeed;
            }

        }
        if (TurnRight) {
            if (InputHandler.MouseButton == 3) {
            } else {

                walk = true;
                rotationa += rotationSpeed;
            }
        }
        if (jump) {
            sprint = false;
            y += jumpHeight;
        }
        if (crouch) {
            crouchWalk = true;
            sprint = false;
            y -= crouchHeight;
            walkSpeed -= 0.4;
        }
        if (walking) {
            sprint = false;
            walkSpeed -= 0.3;
        }
        if (sprint) {
            walkSpeed += 0.5;
            sprintWalk = true;
        }
        if (!forward && !right && !left && !right && !back && !TurnLeft && !TurnRight) {
            walk = false;
        }
        if (!crouch) {
            crouchWalk = false;
        }
        if (!sprint) {
            sprintWalk = false;
        }

        xa += (xMove * Math.cos(rotation) + zMove * Math.sin(rotation)) * walkSpeed;
        za += (zMove * Math.cos(rotation) - xMove * Math.sin(rotation)) * walkSpeed;
        x += xa;
        y *= 0.9;
        z += za;
        xa *= 0.1;
        za *= 0.1;
        rotation += rotationa;
        rotation *= 0.8;

    }
}
