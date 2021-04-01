public class Color {

    private byte[] argb;

    public Color(byte[] argb) {
        this.argb = argb;
    }

    public byte[] ARGB() {return this.argb;}

    public boolean colorEquals(Color c) {
        return java.util.Arrays.equals(this.argb, c.argb);
    }

    public static Color Black;
    public static Color White;
    public static Color Red;
    public static Color Green;
    public static Color Blue;
    public static Color Gray;
    static{
        Black = new Color(new byte[]{(byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00});
        White = new Color(new byte[]{(byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff});
        Red = new Color(new byte[]{(byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00});
        Green = new Color(new byte[]{(byte)0xff, (byte)0x00, (byte)0xff, (byte)0x00});
        Blue = new Color(new byte[]{(byte)0xff, (byte)0x00, (byte)0x00, (byte)0xff});
        Gray = new Color(new byte[]{(byte)0xff, (byte)0x7f, (byte)0x7f, (byte)0x7f});
    }

}
