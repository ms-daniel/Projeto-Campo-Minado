package server;

import java.util.regex.Pattern;

public class Player {
    private String name;
    private int x;
    private int y;

    private Pattern regex = Pattern.compile("\\d+");

    public void SetName(String name) {
        this.name = name;
    }

    public void SetCoordinates(String x, String y) {
        if(regex.matcher(x).matches() && regex.matcher(y).matches()){
            this.x = Integer.parseInt(x);
            this.y = Integer.parseInt(y);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        if(name != null){
            return name + ";" + x + ";" + y + ";";
        }
        return "";
    }
}
