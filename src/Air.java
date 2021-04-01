import VisiCode.Internals.Vector2;

public class Air extends Particle{

    public Air(Vector2 position) {
        super(position);
        this.ID = 0;
        this.baseColor = this.trueColor = Color.Black;
    }

}
