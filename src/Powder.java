import VisiCode.Internals.Vector2;

public class Powder extends Particle{

    public Powder(Vector2 position) {
        super(position);
        this.ID = 1;
        this.baseColor = new Color(new byte[] {(byte)0xFF, (byte)0xDB, (byte)0xC4, (byte)0x79});
        this.trueColor = this.baseColor;
    }

}
