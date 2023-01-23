package GameEngine;

import GameEngine.EngineComponents.Renderer;
import GameEngine.EngineComponents.Sprite;

import java.util.ArrayList;

public abstract class Entity {
    public final int TAG;
    public Sprite sprite;
    public Physics physics;
    public Renderer renderer;
    public CircleCollider collider;
    public Entity(String spriteFile, Renderer renderer, int tag, double initY, double initX,double r){
        TAG = tag;
        sprite = new Sprite(("src//Sprites//" + spriteFile));
        this.renderer = renderer;
        physics = new Physics(initX, initY);
        collider = new CircleCollider(r, physics.future);
    }
    public abstract void computePhysics();

    public abstract void computeCollisionEntity(ArrayList<Entity> entities);

    public abstract void computeCollisionObstacle(ArrayList<Obstacle> obstacles);
    public void update() {
        physics.update();
        if(physics.position.x < 0 + collider.radius)
            physics.position.x = 0 + collider.radius;
        if(physics.position.x > 1600 - collider.radius)
            physics.position.x = 1600 - collider.radius;
        if(physics.position.y < 0 + collider.radius)
            physics.position.y = 0 + collider.radius;
        if(physics.position.y > 900 - collider.radius)
            physics.position.y = 900 - collider.radius;
    }
    public void renderSprite() {
        renderer.drawImage(sprite, (int) (physics.position.x - sprite.width / 2), (int) (physics.position.y - sprite.height / 2));
    }

    public void defaultCollideOnAllObstacle(ArrayList<Obstacle> obstacles) {
        for (Obstacle obstacle : obstacles) {
            physics.future.plus(collider.computeCollisionResponse(obstacle));
        }
    }

    public void defaultCollideOnAllEntity(ArrayList<Entity> entities) {
        for (Entity entity : entities)
            if(entity.collider.collides(this.collider) && TAG != entity.TAG) {
                Point pushout = this.collider.computeCollisionResponse(entity.collider);
                this.collider.position.plus(pushout);
            }
    }
}
