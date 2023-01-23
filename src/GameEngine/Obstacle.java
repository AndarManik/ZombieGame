package GameEngine;

import GameEngine.EngineComponents.Engine;
import GameEngine.EngineComponents.Sprite;

import java.util.ArrayList;
import java.util.Arrays;

public class Obstacle {
    Sprite sprite;
    Point position;

    public PolygonCollider collider;

    public Obstacle(ArrayList<Point> points) {
        collider = new PolygonCollider(points);
        preRenderSprite(collider.lines);
    }

    private void preRenderSprite(ArrayList<Line> lines) {
        double minX = collider.getMinX();
        double maxX = collider.getMaxX();
        double minY = collider.getMinY();
        double maxY = collider.getMaxY();

        double width = maxX - minX;
        double height = maxY - minY;

        sprite = new Sprite((int) width, (int) height);

        int pixelCounter = 0;
        for (double y = minY; y < maxY; y++) {
            int[] singleLineData = getSingleLineData(y, minX, maxX, lines);
            for (int pixelColor : singleLineData) {
                sprite.spriteData[pixelCounter] = pixelColor;
                pixelCounter++;
            }
        }

        position = new Point((maxX + minX) / 2, (maxY + minY) / 2);
    }

    private int[] getSingleLineData(double y, double minX, double maxX, ArrayList<Line> lines) {
        int[] lineData = new int[(int) (maxX - minX)];

        Arrays.fill(lineData, 0xffff00ff);

        double[] xIntersect = collider.getXIntersectSorted(y);

        int pixelCounter = 0;
        for (double x = minX; x < maxX; x++) {
            if (collider.xIsInside(x, xIntersect))
                lineData[pixelCounter] = 0xfff000f0;
            pixelCounter++;
        }

        return lineData;
    }



    public void render(Engine engine) {
        engine.renderer.drawImage(sprite, (int) (position.x - sprite.width / 2), (int) (position.y - sprite.height / 2));
    }

    public void translate(Point displacement) {
        position.plus(displacement);
        collider.translate(displacement);
    }
}
