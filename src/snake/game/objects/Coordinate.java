

package snake.game.objects;


public class Coordinate {
    int x;
    int y;

    public Coordinate(Coordinate obj){
        this.x = obj.x;
        this.y = obj.y;
    }
    
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinate other = (Coordinate) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Coordinate{" + "x=" + x + ", y=" + y + '}';
    }
    
    
}
