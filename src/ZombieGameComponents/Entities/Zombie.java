package ZombieGameComponents.Entities;

import GameEngine.EngineComponents.Engine;
import GameEngine.Entity;
import GameEngine.Line;
import GameEngine.Obstacle;
import GameEngine.Point;

import java.util.ArrayList;

public class Zombie extends Entity {
    final double MOVE_SPEED = 2;

    boolean isVisible = false;

    Player player;
    public Zombie(Engine engine, Player player, int tag) {
        super("clearBackGroundSmile.png",
                engine.renderer,
                tag,
                engine.HEIGHT * Math.random(),
                engine.WIDTH * Math.random(),
                10);
        physics.moveSpeed = MOVE_SPEED;
        this.player = player;
    }
    @Override
    public void computePhysics() {
        physics.setDirectionTo(player.physics.position);

        if(player.shaco.isActive && physics.distanceTo(player.physics) > physics.distanceTo(player.shaco.physics) - player.shaco.distanceOffSet)
            physics.setDirectionTo(player.shaco.physics.position);
        physics.computeDisplacement();
        physics.computeFuture();
    }

    @Override
    public void computeCollisionEntity(ArrayList<Entity> entities) {
        for (Entity entity : entities)
            if(entity.collider.collides(this.collider) && TAG != entity.TAG && entity.TAG != -1) {
                Point pushout = this.collider.computeCollisionResponse(entity.collider);
                this.collider.position.plus(pushout);
            }
    }

    @Override
    public void computeCollisionObstacle(ArrayList<Obstacle> obstacles) {
        defaultCollideOnAllObstacle(obstacles);
        isVisible = checkVisibility(obstacles);
    }

    private boolean checkVisibility(ArrayList<Obstacle> obstacles) {
        Line lineToPlayer = new Line(physics.position, player.physics.position);
        for (Obstacle obstacle : obstacles)
            if(obstacle.collider.collides(lineToPlayer))
                return false;
        return true;
    }

    @Override
    public void renderSprite() {
        if(isVisible)
            super.renderSprite();
    }

    @Override
    public void update() {
        super.update();
    }
}
