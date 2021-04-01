import VisiCode.Internals.Bitmap;
import VisiCode.Internals.Vector2;

import java.awt.*;

public class ParticleBoard {

    Bitmap bmp;
    Dimension size;
    Particle[][] state;
    Vector2[][] forceGrid;
    float delta; //the deltaTime this frame
    int update;

    public ParticleBoard(Bitmap bmp) {
        this.bmp = bmp;
        this.size = bmp.GetSize();
        this.update = 0;
        state = new Particle[size.width][size.height];
        forceGrid = new Vector2[size.width][size.height];

        for(int y = 0; y < size.height; y++) {
            for(int x = 0; x < size.width; x++) {
                state[x][y] = new Air(new Vector2(x, y));
                forceGrid[x][y] = new Vector2(0, 0.15f); //simple gravity
            }
        }
    }

    public void Spawn(int x, int y) {
        if(validPos(x, y) && state[x][y].ID == 0) {
            state[x][y] = new Powder(new Vector2(x, y));
            state[x][y].velocity.Add(forceGrid[x][y]);
        }
    }

    public void Update(float delta) {
        this.delta = delta;
        this.update ^= 1;
        for(int y = 0; y < size.height; y++) {
            for(int x = 0; x < size.width; x++) {
                if(state[x][y].ID != 0 && state[x][y].updated != this.update) {
                    ParticlePhysics(x, y);
                }
            }
        }
    }

    public void DrawState() {
        for(int y = 0; y < size.height; y++) {
            for(int x = 0; x < size.width; x++) {
                bmp.DrawPixel(x, y, state[x][y].trueColor.ARGB());
            }
        }
    }

    private void ParticlePhysics(int x, int y) {
        state[x][y].velocity.Add(forceGrid[x][y]); //apply the force here
        state[x][y].position.Add(state[x][y].velocity.Mul(this.delta)); //move the floating position of the particle

        state[x][y].updated = this.update; //set the particles updated flag
        //move in the direction of the force and calculate target position
        float final_x, final_y;
        Vector2 deltaVel = state[x][y].velocity;
        final_x = deltaVel.x + x;
        final_y = deltaVel.y + y;

        //Stepping code//

        //While the current pos is not equal to the target position
        while(final_x != x && final_y != y) {

            //Check to see if a "step" in that direction will collide with something

                //if so, stop

                //else take the step
        }

        /////////////////

        //check if this target position is a valid position (inside the state grid)
        if(validPos((int)final_x, (int)final_y)) {
            Particle temp = state[(int)final_x][(int)final_y];
            state[(int)final_x][(int)final_y] = state[x][y];
            state[x][y] = temp;
        }
        else {
            state[x][y] = new Air(new Vector2(x, y));
        }
    }

    private boolean validPos(int x, int y) {
        return x >= 0 && x < bmp.GetSize().width && y >= 0 && y < bmp.GetSize().height;
    }
}
