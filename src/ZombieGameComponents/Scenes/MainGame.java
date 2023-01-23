package ZombieGameComponents.Scenes;

import GameEngine.EngineComponents.Engine;
import GameEngine.Entity;
import ZombieGameComponents.Entities.Player;
import ZombieGameComponents.Entities.Shaco;
import ZombieGameComponents.Entities.Zombie;
import ZombieGameComponents.ZombieGame;

import java.util.ArrayList;

public class MainGame extends Scene{
    Player player;
    Shaco shaco;
    ArrayList<Entity> entities = new ArrayList<>();

    public MainGame(SceneSelector sceneSelector, Engine engine, ZombieGame zombieGame){
        super(sceneSelector,engine,zombieGame,"src//Sprites//menuBackGround.png");
        this.engine = engine;
        this.zombieGame = zombieGame;
    }

    public void update() {

        if(player.isDead() || engine.input.currentKeyboardInputs[27])
            reset();

        if(engine.tickCounter % 30 == 0)
            addZombie();

        entities.forEach(Entity::computePhysics);
        entities.forEach(entity -> entity.computeCollisionEntity(entities));
        entities.forEach(entity -> entity.computeCollisionObstacle(sceneSelector.editor.obstacles));
        entities.forEach(Entity::update);
    }

    private void reset() {
        sceneSelector.currentScene = Scenes.MAINMENU;
        entities = new ArrayList<>();
    }

    public void render() {
        engine.renderer.drawImage(backGround, 0, 0);
        entities.forEach(Entity::renderSprite);
        sceneSelector.editor.obstacles.forEach(obstacle -> obstacle.render(engine));
    }

    public void addPlayer() {
        player = new Player(engine);
        entities.add(player);

        shaco = new Shaco(engine, player);
        entities.add(shaco);

        player.setShaco(shaco);
    }

    public void addZombie() {
        entities.add(new Zombie(engine, player, entities.size()));
    }

}
