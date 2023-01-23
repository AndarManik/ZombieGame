package GameEngine;

public class Line {
    public Point first, second;
    public Line(Point first, Point second) {
        this.first = first;
        this.second = second;
    }

    public double getX(double y) {
        double lerpVal = (y - second.y) / (first.y - second.y);

        if(lerpVal >= 0 && lerpVal <= 1)
            return first.x * lerpVal + second.x * (1 - lerpVal);
        return Double.MAX_VALUE;
    }

    public double distanceTo(Point position) {
        double a = position.x - first.x;
        double b = position.y - first.y;
        double c = second.x - first.x;
        double d = second.y - first.y;

        double dot = a * c + b * d;
        double len_sq = c * c + d * d;
        double param = -1;
        if (len_sq != 0) //in case of 0 length line
            param = dot / len_sq;

        double xx, yy;

        if (param < 0) {
            xx = first.x;
            yy = first.y;
        }
        else if (param > 1) {
            xx = second.x;
            yy = second.y;
        }
        else {
            xx = first.x + param * c;
            yy = first.y + param * d;
        }

        double dx = position.x - xx;
        double dy = position.y - yy;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double slope() {
        return (second.y - first.y) / (second.x - first.x);
    }

    public void translate(Point displacement) {
        first.plus(displacement);
        second.plus(displacement);
    }

    public boolean collides(Line lineToPlayer) {
        return crossProduct(first, lineToPlayer.first, lineToPlayer.second) * crossProduct(second, lineToPlayer.first, lineToPlayer.second) < 0 &&
                        crossProduct(lineToPlayer.first, first, second) * crossProduct(lineToPlayer.second, first, second) < 0;
    }

    public double crossProduct(Point a, Point b, Point c) {
        return ((b.x - a.x) * (c.y - b.y) - (b.y - a.y) * (c.x - b.x));
    }
}
