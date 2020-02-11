public class SolidShape3D implements Solid {
    private final Shape3D shape3d;
    private final Material material;

    public SolidShape3D(Shape3D shape3d, Material material) {
        this.shape3d = shape3d;
        this.material = material;
    }

    @Override
    public String toString() {
        return String.format("Solid%s with a mass of %.2f", shape3d.toString(), getMass());
    }

    @Override
    public double getSurfaceArea() {
        return shape3d.getSurfaceArea();
    }
   
    @Override
    public double getVolume() {
        return shape3d.getVolume();
    }

    @Override
    public double getDensity() {
        return material.density;
    }

    @Override
    public double getMass() {
        return getDensity() * getVolume();
    }
}
