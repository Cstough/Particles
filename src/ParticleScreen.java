import VisiCode.Internals.Bitmap;
import VisiCode.Internals.GameObject;
import VisiCode.Internals.Graphics;
import VisiCode.Window;

import java.awt.*;

public class ParticleScreen extends GameObject {

    Bitmap frame;
    ParticleBoard board;

    public ParticleScreen(Dimension size) {
        frame = new Bitmap(size);
        board = new ParticleBoard(frame);
    }

    @Override
    public void Update(float v) {

        if(Window.Input().GetLeftMouseDown()) {
            board.Spawn(Window.Input().GetMousePosition().x, Window.Input().GetMousePosition().y);
        }

        board.Update(v);
        board.DrawState();
    }

    @Override
    public void Render(Graphics graphics) {
        graphics.DrawSprite(frame, 0, 0);
    }
}
