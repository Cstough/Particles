import ParticleStates.ParticleState;
import VisiCode.Internals.Bitmap;
import VisiCode.Internals.Vector2;

import java.awt.*;

public class ParticleBoard {

    Bitmap bmp;
    Dimension size;
    Particle[][] state;
    Vector2[][] forceGrid;
    ParticleState[] particleStates;
    float delta; //the deltaTime this frame
    int update;

    final int AIR = 0;
    final int POWDER = 1;

    public ParticleBoard(Bitmap bmp) {
        this.bmp = bmp;
        this.size = bmp.GetSize();
        this.update = 0;
        state = new Particle[size.width][size.height]; //Instantiating state grid
        forceGrid = new Vector2[size.width][size.height]; //Instantiating force grid

        particleStates = new ParticleState[2];
        particleStates[0] = new ParticleStates.Air();
        particleStates[1] = new ParticleStates.Powder();

        //Initializing the state and force grids
        for(int y = 0; y < size.height; y++) {
            for(int x = 0; x < size.width; x++) {
                state[x][y] = new Particle(new Vector2(x, y));
                Transform(x, y, 0);
                forceGrid[x][y] = new Vector2(0, 0.15f); //simple gravity
            }
        }

        /*
        for(int i = 0; i < 10; i++) {
            //state[100][100+i] = new Block(new Vector2(100, 100+i));
            //state[102][100+i] = new Block(new Vector2(102, 100+i));
            Transform(100, 100+i, 2);
            Transform(102, 100+i, 2);
        }
        //state[101][109] = new Block(new Vector2(101, 109));
        Transform(101, 109, 2);

         */
    }

    /**
     * Spawns the basic particle(for now) onto the position <x,y> if that position is air.
     * @param x X comp.
     * @param y Y comp.
     */
    public void Spawn(int x, int y) {
        //Can only spawn powder in air
        if(validPos(x, y) && state[x][y].ID == 0) {
            Transform(x, y, 1);
            state[x][y].velocity.Add(forceGrid[x][y]);
        }
    }

    /**
     * Done before rendering. Updates the particles that need updating on the state grid.
     * @param delta
     */
    public void Update(float delta) {
        this.delta = delta;
        this.update ^= 1;
        //Naive approach, Ill replace this with a tree or something later for a speed-up
        for(int y = 0; y < size.height; y++) {
            for(int x = 0; x < size.width; x++) {
                if(state[x][y].Gravity && state[x][y].updated != this.update) {
                    FluidPhysics(x, y);
                }
            }
        }
    }

    /**
     * Performs fluid physics operations on the particle at position <x, y>
     * @param x X comp.
     * @param y Y comp.
     */
    private void FluidPhysics(int x, int y) {
        if(validPos(x, y+1) && state[x][y+1].ID == AIR) {
            state[x][y].updated = update;
            Swap(x, y, x, y+1);
        }
    }

    /**
     * Draws the state of the state grid onto the bitmap for rendering.
     */
    public void DrawState() {
        for(int y = 0; y < size.height; y++) {
            for(int x = 0; x < size.width; x++) {
                bmp.DrawPixel(x, y, state[x][y].getColor());
            }
        }
    }

    /**
     * Helper function to check if a position is within the state grid. Helps prevent null references.
     * @param x X comp.
     * @param y Y comp.
     * @return boolean result for validity.
     */
    private boolean validPos(int x, int y) {
        return x >= 0 && x < bmp.GetSize().width && y >= 0 && y < bmp.GetSize().height;
    }

    /**
     * Swaps 2 particles in the state grid.
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    private void Swap(int x1, int y1, int x2, int y2) {
        Particle p = state[x1][y1];
        state[x1][y1] = state[x2][y2];
        state[x2][y2] = p;
    }

    /**
     * Re-init a particle in the state grid as a new particle type using the ParticleStates. This avoids creating new particle objects.
     * @param x X comp.
     * @param y Y comp.
     * @param material Material ID
     */
    private void Transform(int x, int y, int material) {
        if(validPos(x, y)) {
            ParticleState ps = particleStates[material];
            Particle p = state[x][y];
            p.ID = ps.ID;
            p.color = ps.color;
            p.Gravity = ps.Gravity;
            p.volume = ps.volume;
            p.flowRate = ps.flowRate;
        }
    }
}
