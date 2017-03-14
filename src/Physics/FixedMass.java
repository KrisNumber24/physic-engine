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

    public FixedMass(float x, float y) {
        super(new PVector(x, y));
    }

    public void processLeapFrog(float h) {
        this.speed.mult(0);
    }

    public void draw(PApplet ctx) {
        ctx.strokeWeight(0);
        ctx.stroke(255, 0, 127);
        ctx.strokeWeight(10);
        ctx.point(position.x, position.y, position.z);
    }

}
