package Physics;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * Created by Utilisateur on 13/03/2017.
 */
public class FixedMass extends Mass {

    public FixedMass(PVector position) {
        super(position);
    }

    public FixedMass(float x, float y, float z) {
        super(new PVector(x, y, z));
    }

    public void processLeapFrog(float h) {
        this.speed.mult(0);
    }

    public void processGravity(PVector gravity) {
        this.force.add(0, 0, 0);
    }

    public void draw(PApplet ctx) {
        ctx.strokeWeight(0);
        ctx.stroke(0, 100, 200);
        ctx.strokeWeight(5);
        ctx.point(position.x, position.y, position.z);
    }

}
