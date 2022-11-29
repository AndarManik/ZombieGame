package GameEngine;

import java.awt.image.DataBufferInt;

public class Renderer {
    public int renderWidth, renderHeight;
    public int[] renderData;

    public Renderer(Engine engine) {
        renderWidth = engine.WIDTH;
        renderHeight = engine.HEIGHT;
        renderData = ((DataBufferInt)engine.window.image.getRaster().getDataBuffer()).getData();
    }

    public void setPixel(int x, int y, int value) {
        if(x < 0 || x >= renderWidth || y < 0 || y >= renderHeight || value == 0xffff00ff) {
            return;
        }

        renderData[x + y * renderWidth] = value;
    }
    
    public void drawImage(Sprite sprite, int offsetX, int offsetY) {

        for (int y = 0; y < sprite.height; y++)
            for (int x = 0; x < sprite.width; x++)
                setPixel(x + offsetX, y + offsetY, sprite.spriteData[x + y * sprite.width]);
    }
}
