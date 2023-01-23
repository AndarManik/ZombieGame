package ZombieGameComponents.Scenes;

import GameEngine.EngineComponents.Engine;
import GameEngine.EngineComponents.Sprite;
import ZombieGameComponents.ZombieGame;

public abstract class Scene {
    SceneSelector sceneSelector;
    Engine engine;
    ZombieGame zombieGame;
    Sprite backGround;

    public Scene(SceneSelector sceneSelector, Engine engine, ZombieGame zombieGame, String backGroundFile){
        this.sceneSelector = sceneSelector;
        this.engine = engine;
        this.zombieGame = zombieGame;
        backGround = new Sprite(backGroundFile);
    }
    public abstract void update();
    public abstract void render();
}
