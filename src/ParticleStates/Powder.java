package ParticleStates;

public class Powder extends ParticleState{
    public Powder() {
        ID = 1;
        color = new byte[] {(byte)0xFF, (byte)0xDB, (byte)0xC4, (byte)0x79};
        Gravity = true;
        volume = 1.0f;
        flowRate = 0.2f;
    }
}
