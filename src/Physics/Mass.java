/**
 * Created by Christophe Assante on 12/03/2017.
 */

package Physics;

import processing.core.PVector;
import processing.core.PApplet;

public abstract class Mass {

    protected PVector position;
    protected PVector speed = new PVector(0, 0, 0); // vitesse
    protected PVector force = new PVector(0, 0, 0); // force
    protected float mass = 1; // masse

    public Mass(PVector position) {
        this.position = position;
    }

    public abstract void processLeapFrog(float h);

    public abstract void processGravity(PVector gravity);

    public abstract void draw(PApplet ctx);

    public PVector getPosition() {
        return position;
    }

    public void setPosition(PVector position) {
        this.position = position;
    }

    public PVector getSpeed() {
        return speed;
    }

    public void setSpeed(PVector speed) {
        this.speed = speed;
    }

    public PVector getForce() {
        return force;
    }

    public void setForce(PVector force) {
        this.force = force;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }
}
