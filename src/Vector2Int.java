public class Vector2Int {

    public int x, y;

    public Vector2Int(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) return false;
        else return this.x == ((Vector2Int)(obj)).x && this.y == ((Vector2Int)(obj)).y;
    }
}
