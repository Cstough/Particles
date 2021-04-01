import VisiCode.*;
import VisiCode.Internals.Scene;
import VisiCode.Window;

import java.awt.*;

public class Driver {

    public static void main(String[] args) {

        Dimension size = new Dimension(200, 150);

        Window.Init();
        Window.Config().SetClearColor(Color.Black.ARGB());
        Window.Config().SetTitle("Force Particle Testing");
        Window.Config().SetScreenSize(size);
        Window.Config().SetScreenScale(6);

        Scene scene = new Scene();
        Window.AddScene(scene);

        scene.AddGameObject(new ParticleScreen(size));

        Window.Run();
    }

}
