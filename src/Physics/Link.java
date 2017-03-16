/**
 * Created by Christophe Assante on 12/03/2017.
 */

package Physics;

import processing.core.PApplet;
import processing.core.PVector;

public class Link {

    private Mass    m1;
    private Mass    m2;
    private float   d0; // base distance between m1 and m2
    private float   z; // I suppose it would be elasticity or something ...
    private float   k; // No idea of what it is ...
    private PVector force;
    private float   epsilon = 0.001f;

    public Link(Mass m1, Mass m2) {
        this.m1 = m1;
        this.m2 = m2;
        this.d0 = m1.getPosition().dist(m2.getPosition());
    }

    public void update() {
        float d = m1.getPosition().dist(m2.getPosition());
        PVector m1m2 = processRessort();
        PVector dv = processFrein();

        if (Math.abs(d - d0) > epsilon) {

            m1.getForce().sub(m1m2);
            m2.getForce().add(m1m2);

            m1.getForce().add(dv);
            m2.getForce().sub(dv);
        }
        else {
            if (m1.getForce().x > m2.getForce().x
             && m1.getForce().y > m2.getForce().y
             && m1.getForce().z > m2.getForce().z) {

                m2.getForce().set(0,0,0);
            }
            else {
                m1.getForce().set(0,0,0);
            }

            if (m1.getSpeed().x > m2.getSpeed().x
             && m1.getSpeed().y > m2.getSpeed().y
             && m1.getSpeed().z > m2.getSpeed().z) {

                m2.getSpeed().set(0,0,0);
            }
            else {
                m1.getSpeed().set(0,0,0);
            }
        }
    }

    public PVector processRessort() {
        float d = m1.getPosition().dist(m2.getPosition());

        float f = - this.k * (1 - this.d0 / d);

        PVector m1m2 = PVector.sub(m2.getPosition(), m1.getPosition());

        m1m2.mult(f);

        return m1m2;
    }

    public PVector processFrein() {
        PVector dv = new PVector(
                m2.getSpeed().x - m1.getSpeed().x,
                m2.getSpeed().y - m1.getSpeed().y,
                m2.getSpeed().z - m2.getSpeed().z
        );

        dv.mult(-this.z);

        return dv;
    }

    public void draw(PApplet ctx) {
        ctx.stroke(125, 125, 125);
        ctx.strokeWeight(1.f);
        ctx.line(m1.getPosition().x, m1.getPosition().y, m1.getPosition().z,
                 m2.getPosition().x, m2.getPosition().y, m2.getPosition().z);
    }

    public Mass getM1() {
        return m1;
    }

    public void setM1(Mass m1) {
        this.m1 = m1;
    }

    public Mass getM2() {
        return m2;
    }

    public void setM2(Mass m2) {
        this.m2 = m2;
    }

    public float getD0() {
        return d0;
    }

    public void setD0(float d0) {
        this.d0 = d0;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getK() {
        return k;
    }

    public void setK(float k) {
        this.k = k;
    }

    public PVector getForce() {
        return force;
    }

    public void setForce(PVector force) {
        this.force = force;
    }
}
