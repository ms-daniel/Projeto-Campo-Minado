package server;

public class Player {
    private String name;
    private int x;
    private int y;

    public void SetName(String name) {
        this.name = name;
    }

    public void SetCoordinates(String x, String y) {
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return name + ";" + x + ";" + y + ";";
    }
}
