package GameEngine;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite {
    public int width, height;
    public int[] spriteData;

    public Sprite(String filePath) {
        BufferedImage image = null;

        try {image = ImageIO.read(Sprite.class.getResourceAsStream(filePath));} catch (IOException ignored) { }

        width = image.getWidth();
        height = image.getHeight();
        spriteData = image.getRGB(0,0, width, height, null, 0, width);

        image.flush();
    }
}
