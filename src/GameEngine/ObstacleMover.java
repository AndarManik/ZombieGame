package GameEngine;

import GameEngine.EngineComponents.Input;

import java.util.ArrayList;

public class ObstacleMover {
    ArrayList<Obstacle> obstacles;
    Obstacle selectedObstacle;
    Point clickPosition;
    public ObstacleMover(ArrayList<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public void update(Input input) {
        if(input.currentMouseInputs[1] && !input.previousMouseInputs[1]) {
            clickPosition = input.getMousePoint();
            selectedObstacle = null;
            for (Obstacle obstacle : obstacles)
                if(obstacle.collider.contains(clickPosition)) {
                    selectedObstacle = obstacle;
                    break;
                }

        }

        if(input.currentMouseInputs[1] && input.previousMouseInputs[1] && clickPosition != null && selectedObstacle != null) {
            Point difference = clickPosition.getTranslation(input.getMousePoint());
            selectedObstacle.translate(difference);
            clickPosition = input.getMousePoint();
        }
    }
}
