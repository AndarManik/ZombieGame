package GameEngine;

import GameEngine.EngineComponents.Engine;
import GameEngine.EngineComponents.Input;

import java.util.ArrayList;

public class ObstacleGenerator {
    ArrayList<Obstacle> obstacles;
    public ArrayList<Point> points = new ArrayList<>();
    public boolean isGenerating = false;

    public ObstacleGenerator(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public void start() {
        points = new ArrayList<>();
        isGenerating = true;
    }

    public Obstacle generate() {
        Obstacle obstacle = new Obstacle(points);

        points = new ArrayList<>();
        isGenerating = false;

        return obstacle;
    }

    public void addPoint(int x, int y) {
        points.add(new Point(x, y));
    }

    public void render(Engine engine) {
        for (int i = 0; i < points.size() - 1; i++) {
            engine.renderer.drawLine(points.get(i), points.get(i + 1), 0xfff000f0);
        }
    }

    public void update(Input input) {
        if(input.currentKeyboardInputs[84] && !isGenerating)
            start();

        if(isGenerating && input.currentMouseInputs[1] && !input.previousMouseInputs[1])
            addPoint(input.mouseX, input.mouseY);

        if(input.currentKeyboardInputs[89] && isGenerating && points.size() != 0)
            obstacles.add(generate());

    }
}
