package com.mine.game.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Texture {
    public static Render floor = loadBitmap("/textures/textures.png");

    public static Render loadBitmap(String fileName) {
        try {
            BufferedImage image = ImageIO.read(Texture.class.getResource(fileName));
            int width = image.getWidth();
            int height = image.getHeight();
            Render result = new Render(width, height);
            image.getRGB(0, 0, width, height, result.pixels, 0, width);
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
