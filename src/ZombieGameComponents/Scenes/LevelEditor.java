package ZombieGameComponents.Scenes;

import GameEngine.EngineComponents.Engine;
import GameEngine.Obstacle;
import GameEngine.ObstacleGenerator;
import GameEngine.ObstacleMover;
import ZombieGameComponents.ZombieGame;

import java.util.ArrayList;

public class LevelEditor extends Scene{
    ArrayList<Obstacle> obstacles = new ArrayList<>();
    ObstacleGenerator obstacleGenerator;
    ObstacleMover obstacleMover;
    public LevelEditor(SceneSelector sceneSelector, Engine engine, ZombieGame zombieGame) {
        super(sceneSelector,engine, zombieGame, "src//Sprites//editorBackGround.png");
        obstacleGenerator = new ObstacleGenerator(obstacles);
        obstacleMover = new ObstacleMover(obstacles);
    }

    public void update() {
        obstacleGenerator.update(engine.input);

        if(!obstacleGenerator.isGenerating)
            obstacleMover.update(engine.input);

        if(engine.input.currentKeyboardInputs[80])
            sceneSelector.currentScene = Scenes.MAINGAME;
    }

    public void render() {
        engine.renderer.drawImage(backGround,0,0);
        obstacles.forEach((polygon -> polygon.render(engine)));
        obstacleGenerator.render(engine);
    }

}
