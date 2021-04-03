package ParticleStates;

public class Air extends ParticleState{
    public Air() {
        ID = 0;
        color = new byte[] {(byte)0xFF, (byte)0x00, (byte)0x00, (byte)0x00};
        Gravity = false;
        volume = 1.0f;
        flowRate = 0.0f;
    }
}
