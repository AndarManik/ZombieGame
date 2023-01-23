package GameEngine;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(Point toSet) {
        this.x = toSet.x;
        this.y = toSet.y;
    }

    public Point plus(Point displacement) {
        this.x += displacement.x;
        this.y += displacement.y;
        return this;
    }

    public double distanceTo(Point coordinate) {
        return Math.sqrt(coordinate.x * coordinate.x + coordinate.y * coordinate.y);
    }

    public Point getTranslation(Point point) {
        return new Point(point.x - x, point.y - y);
    }

    public Point copy() {
        return new Point(x,y);
    }
}
