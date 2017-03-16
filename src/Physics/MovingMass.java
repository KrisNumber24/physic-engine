package Physics;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * Created by Utilisateur on 13/03/2017.
 */
public class MovingMass extends Mass {

    public MovingMass(PVector position) {
        super(position);
    }

    public MovingMass(float x, float y, float z) {
        this(new PVector(x, y, z));
    }

    public void processLeapFrog(float h) {
        this.speed.add(
                h / this.mass * this.force.x,
                h / this.mass * this.force.y,
                h / this.mass * this.force.z
        );

        this.position.add(PVector.mult(this.speed, h));

        this.force.mult(0);
    }

    public void processGravity(PVector gravity) {
        this.force.add(gravity);
    }

    public void draw(PApplet ctx) {
        ctx.stroke(255, 0, 0);
        ctx.strokeWeight(0);
        ctx.point(position.x, position.y, position.z);
    }

}
