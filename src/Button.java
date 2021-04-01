import VisiCode.Internals.Bitmap;
import VisiCode.Internals.GameObject;
import VisiCode.Internals.Graphics;
import VisiCode.Window;

public class Button extends GameObject {

    Bitmap buttonSprite, buttonSpriteClicked, currentSprite;
    GameObject parent;
    Vector2Int position, bounds;

    public Button(Vector2Int position, GameObject parent) {
        this.position = position;
        this.parent = parent;
        this.bounds = new Vector2Int(0, 0);
    }

    public void SetSprites(Bitmap unclicked, Bitmap clicked)
    {
        this.buttonSprite = unclicked;
        this.buttonSpriteClicked = clicked;
        this.currentSprite = buttonSprite;
        this.bounds = new Vector2Int(buttonSprite.GetSize().width, buttonSprite.GetSize().height);
    }

    boolean MouseInside() {
        Vector2Int mpos = new Vector2Int(Window.Input().GetMousePosition().x, Window.Input().GetMousePosition().y);
        return (mpos.x >= position.x && mpos.x < position.x + bounds.x && mpos.y >= position.y && mpos.y < position.y + bounds.y);
    }

    @Override
    public void Update(float v) {

    }

    @Override
    public void Render(Graphics graphics) {
        graphics.DrawSprite(currentSprite, this.position.x, this.position.y);
    }
}
