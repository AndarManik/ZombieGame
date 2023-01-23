package ZombieGameComponents.Entities;

import GameEngine.EngineComponents.Engine;
import GameEngine.EngineComponents.Input;
import GameEngine.Entity;
import GameEngine.Obstacle;
import ZombieGameComponents.Entities.Player;

import java.util.ArrayList;

public class Shaco extends Entity {
    public double distanceOffSet = 300;
    Player player;
    double MOVE_SPEED = 3;
    Input input;

    boolean isActive = false;
    int activeTickCounter = 0;
    int maxTickCounter = 300;
    public Shaco(Engine engine, Player player) {
        super("Shaco.png", engine.renderer, -1, player.physics.position.y, player.physics.position.x, 10);
        this.input = engine.input;
        this.player = player;
    }

    @Override
    public void computePhysics() {
        if(!isActive)
            return;

        double[] playerInput = input.getPlayerArrow();

        if(playerInput[0] == 0 && playerInput[1] == 0)
            physics.moveSpeed = 0;
        else
            physics.moveSpeed = MOVE_SPEED;

        physics.directionRad = Math.atan2(playerInput[1], playerInput[0]);

        physics.computeDisplacement();
        physics.computeFuture();
    }

    @Override
    public void computeCollisionEntity(ArrayList<Entity> entities) {
        if(!isActive)
            return;

        defaultCollideOnAllEntity(entities);
    }

    @Override
    public void computeCollisionObstacle(ArrayList<Obstacle> obstacles) {
        if(!isActive)
            return;

        defaultCollideOnAllObstacle(obstacles);
    }

    @Override
    public void update() {
        if(!isActive)
            return;

        super.update();

        if(activeTickCounter > maxTickCounter)
            isActive = false;

        activeTickCounter++;
    }

    @Override
    public void renderSprite() {
        if(!isActive)
            return;
        super.renderSprite();
    }

    public void activate(){
        isActive = true;
        activeTickCounter = 0;
        physics.position.set(player.physics.position);
        physics.future.set(player.physics.position);
    }
}
