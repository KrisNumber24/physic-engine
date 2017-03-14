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

    public MovingMass(float x, float y) {
        super(new PVector(x, y));
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

    public void draw(PApplet ctx) {
        ctx.strokeWeight(0);
        ctx.stroke(255, 0, 0);
        ctx.strokeWeight(7);
        ctx.point(position.x, position.y, position.z);
    }

}
