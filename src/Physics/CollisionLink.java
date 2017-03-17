package Physics;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * Created by Utilisateur on 16/03/2017.
 */
public class CollisionLink extends Link {

    private float boundingRadius;
    private float epsilon = 0.01f;

    public CollisionLink(Mass m1, Mass m2, float boundingRadius) {
        super(m1, m2);
        this.boundingRadius = boundingRadius;
    }

    @Override
    public void update() {
        float m1m2 = m1.getPosition().dist(m2.getPosition());

        if (Math.abs(m1m2) < boundingRadius) {
            PVector repulseForce = PVector.sub(this.m1.getPosition(), this.m2.getPosition());
            repulseForce.normalize();

            float speedForce = (float) Math.sqrt(
                    m1.getSpeed().x * m1.getSpeed().x
                  + m1.getSpeed().y * m1.getSpeed().y
                  + m1.getSpeed().z * m1.getSpeed().z
            );

            repulseForce.mult(speedForce);

            // revert speed of particle
//            this.m1.getSpeed().sub(m1.getSpeed());
//            this.m2.getSpeed().sub(m1.getSpeed());

            // revert gravity
            this.m1.getForce().sub(m1.getForce());
            this.m2.getForce().sub(m2.getForce());

            m1.getSpeed().add(repulseForce);
        }
    }

    @Override
    public void draw(PApplet ctx) {
        ctx.strokeWeight(0);
        super.draw(ctx);
    }
}
