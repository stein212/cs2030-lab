public class SolidSphere extends Sphere implements Solid {
    protected final double density;

    public SolidSphere(double radius, double density) {
        super(radius);
        this.density = density;
    }

    @Override
    public String toString() {
        return String.format(
                "SolidSphere [%.2f] with a mass of %.2f",
                radius, getMass());
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
    public SolidSphere setRadius(double radius) {
        return new SolidSphere(radius, density);
    }
}
