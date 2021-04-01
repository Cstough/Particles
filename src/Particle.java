import VisiCode.Internals.Vector2;

public class Particle {
    public int ID;
    public Vector2 position, velocity;
    private static int[] colorOffsets = new int[] {0x00, 0x90, 0xF0};
    Color baseColor, trueColor;
    public int updated;

    public Particle(Vector2 position) {
        this.position = position;
        this.velocity = new Vector2(0, 0);
        updated = 0;
    }
}
