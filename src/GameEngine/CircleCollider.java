package GameEngine;

public class CircleCollider {
    double radius;
    public Point position;
    public CircleCollider(double radius, Point position) {
        this.radius = radius;
        this.position = position;
    }

    public boolean collides(CircleCollider collider) {
        double distanceBetween = calculateDistanceBetweenPoints(position.x,position.y,collider.position.x,collider.position.y);
        return distanceBetween < radius + collider.radius;
    }

    private double calculateDistanceBetweenPoints(
            double x1,
            double y1,
            double x2,
            double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    public Point computeCollisionResponse(CircleCollider collider) {
        double heightDifference = collider.position.y - position.y;
        double widthDifference = collider.position.x - position.x;

        double direction = Math.atan2(heightDifference , widthDifference);

        double distanceBetween = calculateDistanceBetweenPoints(position.x,position.y,collider.position.x,collider.position.y);

        double intercectiondistance =  distanceBetween - (radius + collider.radius);

        return new Point(Math.cos(direction) * intercectiondistance / 2 , Math.sin(direction) * intercectiondistance / 2);
    }

    public Point computeCollisionResponse(Obstacle obstacle) {
        Line nearestLine = obstacle.collider.nearestLine(position);
        double distanceTo = nearestLine.distanceTo(position);
        boolean isCenterInside = obstacle.collider.contains(position);

        if(distanceTo > radius && !isCenterInside)
            return new Point(0,0);


        double perpendicularSlope = -1.0 / nearestLine.slope();

        double xDistanceToPush = 1 / Math.sqrt(1 + perpendicularSlope * perpendicularSlope) *
                ((isCenterInside) ? radius + distanceTo : radius - distanceTo);


        Point testerPoint = new Point(1,perpendicularSlope);
        testerPoint.plus(position);

        boolean slopeIncreasesDistance = nearestLine.distanceTo(testerPoint) > distanceTo;

        if(isCenterInside) {
            if(slopeIncreasesDistance)
                return new Point(-1 * xDistanceToPush, -1 * perpendicularSlope * xDistanceToPush);
            else
                return new Point(xDistanceToPush, perpendicularSlope * xDistanceToPush);
        }
        else {
            if(slopeIncreasesDistance)
                return new Point(xDistanceToPush, perpendicularSlope * xDistanceToPush);
            else
                return new Point(-1 * xDistanceToPush, -1 * perpendicularSlope * xDistanceToPush);
        }
    }
}
