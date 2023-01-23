package GameEngine;

import GameEngine.Line;
import GameEngine.Point;

import java.util.ArrayList;
import java.util.Arrays;

public class PolygonCollider {
    ArrayList<Line> lines;

    public PolygonCollider(ArrayList<Point> points) {
        lines = new ArrayList<>();
        for (int i = 0; i < points.size(); i++)
            lines.add(new Line(points.get(i), points.get((i + 1) % points.size()).copy()));
    }

    public Line nearestLine(Point position) {
        Line nearestLine = lines.get(0);
        double currentDistance = nearestLine.distanceTo(position);
        for (Line line : lines) {
            double newDistance = line.distanceTo(position);
            if(currentDistance > newDistance) {
                nearestLine = line;
                currentDistance = newDistance;
            }
        }
        return nearestLine;
    }

    public boolean contains(Point position) {
        return xIsInside(position.x, getXIntersectSorted(position.y));
    }

    public double[] getXIntersectSorted(double y) {
        double[] xIntersect = new double[lines.size()];
        int numberOfIntersect = 0;
        for (int i = 0; i < xIntersect.length; i++) {
            xIntersect[i] = lines.get(i).getX(y);
            if(xIntersect[i] != Double.MAX_VALUE)
                numberOfIntersect++;
        }
        Arrays.sort(xIntersect);
        return Arrays.copyOf(xIntersect, numberOfIntersect);
    }

    public double getMaxY() {
        double maxY = lines.get(0).first.y;
        for (Line line : lines)
            if(maxY < line.first.y)
                maxY = line.first.y;
        return maxY;
    }

    public double getMinY() {
        double minY = lines.get(0).first.y;
        for (Line line : lines)
            if(minY > line.first.y)
                minY = line.first.y;
        return minY;
    }

    public double getMaxX() {
        double maxX = lines.get(0).first.x;
        for (Line line : lines)
            if(maxX < line.first.x)
                maxX = line.first.x;
        return maxX;
    }

    public double getMinX() {
        double minX = lines.get(0).first.x;
        for (Line line : lines)
            if(minX > line.first.x)
                minX = line.first.x;
        return minX;
    }

    public boolean xIsInside(double x, double[] xIntersect) {
        if(xIntersect.length % 2 == 1) {
            int offSet = 0;
            for (int i = 0; i + offSet < xIntersect.length; i++) {
                if(i + offSet >= xIntersect.length)
                    break;
                if (xIntersect[i + offSet] == xIntersect[((i + 1 + offSet) % xIntersect.length)])
                    offSet++;
                if (x < xIntersect[i + offSet])
                    return i % 2 == 1;
            }
        }
        else
            for (int i = 0; i < xIntersect.length; i++)
                if(x < xIntersect[i])
                    return i % 2 == 1;

        return false;
    }

    public void translate(Point displacement) {
        lines.forEach(line -> line.translate(displacement));
    }

    public boolean collides(Line lineToPlayer) {
        for (Line line : lines)
            if(line.collides(lineToPlayer))
                return true;
        return false;
    }
}
