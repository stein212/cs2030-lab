public class SolidCuboid extends Cuboid implements Solid {
    protected final double density;

    public SolidCuboid(
            double height,
            double width,
            double length,
            double density) {
        super(height, width, length);
        this.density = density;
    }

    @Override
    public String toString() {
        return String.format(
                "SolidCuboid [%.2f x %.2f x %.2f] with a mass of %.2f",
                height, width, length, getMass());
    }

    @Override
    public double getDensity() {
        return density;
    }

    @Override
    public double getMass() {
        return density * getVolume();
    }

    @Override
    public SolidCuboid setHeight(double height) {
        return new SolidCuboid(height, width, length, density);
    }

    @Override
    public SolidCuboid setWidth(double width) {
        return new SolidCuboid(height, width, length, density);
    }

    @Override
    public SolidCuboid setLength(double length) {
        return new SolidCuboid(height, width, length, density);
    }
}
