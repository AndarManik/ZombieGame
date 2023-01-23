package ZombieGameComponents.Entities;

import GameEngine.EngineComponents.Engine;

import GameEngine.EngineComponents.Input;
import GameEngine.Entity;
import GameEngine.Obstacle;
import GameEngine.Point;
import ZombieGameComponents.Scenes.MainGame;

import java.util.ArrayList;

public class Player extends Entity {
    int maxFramesTouchingZombie = 30;
    int framesTouchingZombie;
    double MOVE_SPEED = 3.2;
    Input input;
    public Shaco shaco;

    boolean isPointerControl = true;

    public Player(Engine engine) {
        super("dva.png",
                engine.renderer,
                0,
                engine.HEIGHT / 2,
                engine.WIDTH / 2,
                15);
        framesTouchingZombie = 0;
        input = engine.input;
    }
    @Override
    public void computePhysics() {

        if(input.currentKeyboardInputs[86] && !input.previousKeyboardInputs[86])
            isPointerControl = !isPointerControl;

        if(isPointerControl)
            pointerPhysicsControl();
        else
            wasdPhysicsControl();

        physics.computeDisplacement();
        physics.computeFuture();
    }

    private void pointerPhysicsControl() {
        physics.moveSpeed = MOVE_SPEED;
        physics.setDirectionTo(input.getMousePoint());
    }

    private void wasdPhysicsControl() {
        double[] playerInput = input.getPlayerWASD();

        if(playerInput[0] == 0 && playerInput[1] == 0)
            physics.moveSpeed = 0;
        else
            physics.moveSpeed = MOVE_SPEED;

        physics.directionRad = Math.atan2(playerInput[1], playerInput[0]);
    }

    @Override
    public void computeCollisionEntity(ArrayList<Entity> entities) {
        boolean collided = false;
        for (Entity entity : entities)
            if(entity.collider.collides(this.collider) && TAG != entity.TAG && entity.TAG != -1) {
                Point pushout = this.collider.computeCollisionResponse(entity.collider);
                this.collider.position.plus(pushout);
                collided = true;
            }
        if(collided)
            framesTouchingZombie++;
        else
            framesTouchingZombie = 0;
    }

    @Override
    public void computeCollisionObstacle(ArrayList<Obstacle> obstacles) {
        defaultCollideOnAllObstacle(obstacles);
    }

    @Override
    public void update() {
        super.update();

        if(input.currentKeyboardInputs[16])
            MOVE_SPEED = 3.5;
        else
            MOVE_SPEED = 3.5;

        if(input.currentKeyboardInputs[32] && !shaco.isActive)
            shaco.activate();
    }

    public void setShaco(Shaco shaco) {
        this.shaco = shaco;
    }

    public boolean isDead() {
        return framesTouchingZombie >= maxFramesTouchingZombie;
    }
}
