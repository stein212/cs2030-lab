Material glass = new Material("Glass",2.5)

Material steel = new Material("Steel",7870)

new SolidShape3D(new Cuboid(2,2,2),glass)

new SolidShape3D(new Cuboid(2,2,2),steel)

new SolidShape3D(new Cuboid(2,2,2),steel).getMass()

new SolidShape3D(new Cuboid(2,2,2).setHeight(3),glass)

new SolidShape3D(new Sphere(2),glass)

new SolidShape3D(new Sphere(2),steel)

new SolidShape3D(new Sphere(2),steel).getMass()

new SolidShape3D(new Sphere(2).setRadius(3),glass)

/exit

