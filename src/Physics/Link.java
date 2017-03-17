package Physics;

import processing.core.PApplet;

/**
 * Created by Christophe Assante on 16/03/2017.
 */
public abstract class Link {

    protected Mass    m1;
    protected Mass    m2;

    Link() {
        this.m1 = null;
        this.m2 = null;
    }

    public Link(Mass m1, Mass m2) {
        this.m1 = m1;
        this.m2 = m2;
    }

    public abstract void update();

    public void draw(PApplet ctx) {
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

}
