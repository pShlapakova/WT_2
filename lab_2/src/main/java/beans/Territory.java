package beans;

import java.io.Serializable;

public class Territory
        implements Serializable {
    private int id;
    private int x;
    private int y;
    private int width;
    private int height;

    public Territory() {
        x = 0;
        y = 0;
        width = 0;
        height = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x >= 0)
            this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y >= 0)
            this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width > 0)
            this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height > 0)
            this.height = height;
    }
}
