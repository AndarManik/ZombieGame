package GameEngine;

public class Physics {
    public Point position, future, displacement;
    public double directionRad;
    public double moveSpeed;
    public Physics(double initX, double initY) {
        position = new Point(initX, initY);
        future = new Point(initX, initY);
        displacement = new Point(0,0);
        directionRad = 0;
        moveSpeed = 5;
    }
    public void update() {
        position.set(future);
    }
    public void computeDisplacement(){
        displacement.x = Math.cos(directionRad) * moveSpeed;
        displacement.y = Math.sin(directionRad) * moveSpeed;
    }

    public void computeFuture(){
        future.set(position.plus(displacement));
    }

    public void setDirectionTo(Point point) {
        double heightDifference = point.y - position.y;
        double widthDifference = point.x - position.x;

        directionRad = Math.atan2(heightDifference , widthDifference);
    }

    public double distanceTo(Physics physics) {
        double diffX = position.x - physics.position.x;
        double diffY = position.y - physics.position.y;

        return Math.sqrt(diffX * diffX + diffY * diffY);
    }
}

