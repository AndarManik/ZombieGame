package ZombieGameComponents;

import GameEngine.EngineComponents.Engine;
import GameEngine.EngineComponents.AbstractGame;

import ZombieGameComponents.Scenes.*;
public class ZombieGame implements AbstractGame {
    Engine engine;
    public SceneSelector sceneSelector;
    @Override
    public void initialize(Engine engine) {
        this.engine = engine;
        sceneSelector = new SceneSelector(engine, this);
    }

    @Override
    public void update() {
        sceneSelector.update();
    }

    @Override
    public void render() {
        sceneSelector.render();
    }
}
