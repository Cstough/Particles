import VisiCode.Internals.Vector2;

public class Particle {
    public int ID;
    public Vector2 position, velocity;
    public byte[] color;
    public int updated;
    public boolean Gravity;

    //Fluid
    public float volume;
    public float flowRate;

    public Particle(Vector2 position) {
        this.position = position;
        this.velocity = new Vector2(0, 0);
        updated = 0;
        flowRate = 0.3f;
    }

    public byte[] getColor() {
        return this.color;
    }
}
