package server;

import java.util.regex.Pattern;

public class Player {
    private String name;
    private String skin;
    private int x = 3;
    private int y = 44;

    private Pattern regex = Pattern.compile("\\d+");

    public void SetNameAndSkin(String name, String skin) {
        this.name = name;
        this.skin = skin;
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
            return name + ";" + skin + ";" + x + ";" + y;
        }
        return "";
    }
}
