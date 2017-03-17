package Objects;

import Physics.CollisionLink;
import Physics.FixedMass;
import Physics.SpringLink;
import Physics.Mass;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * Created by Christophe Assante on 16/03/2017.
 */
public class Sphere extends PhysicObject{

    private PVector center;
    private float radius;

    public Sphere(PVector center, float radius) {
        this.center = center;
        this.radius = radius;
        this.initMesh();
    }

    @Override
    public void initMesh() {
        this.masses = new Mass[1];
        this.masses[0] = new FixedMass(this.center);
    }

    @Override
    public void setup(PApplet ctx) {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(PApplet ctx) {
        ctx.fill(0, 125, 255);
        ctx.translate(center.x, center.y, center.z);
        ctx.sphere(radius);
        super.draw(ctx);
    }

    public void attachObject(PhysicObject object) {
        for (Mass mass : object.getMasses()) {
            this.links.add(new CollisionLink(mass, this.masses[0], this.radius));
        }
    }
}
