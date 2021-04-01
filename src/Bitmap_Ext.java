import VisiCode.Internals.Bitmap;
import java.awt.*;

public class Bitmap_Ext extends Bitmap {

    public Bitmap_Ext() {
        super();
    }

    public Bitmap_Ext(Dimension size) {
        super(size);
    }

    public void DrawPixel(int x, int y, Color c) {
        super.DrawPixel(x, y, c.ARGB());
    }

    public Color GetPixel(int x, int y) {
        if (x >= 0 && x <= this.GetSize().width - 1 && y >= 0 && y <= this.GetSize().height - 1) {
            int index = (y * this.GetSize().width + x) * 4;
            byte[] comps = this.GetComponents();
            return new Color(new byte[]{comps[index], comps[index+1], comps[index+2], comps[index+3]});
        }
        return null;
    }
}
