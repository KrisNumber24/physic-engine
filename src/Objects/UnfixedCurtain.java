package Objects;

import Physics.Link;
import Physics.SpringLink;
import Physics.Mass;
import Physics.MovingMass;
import processing.core.PVector;

import java.util.ArrayList;

/**
 * Created by Christophe Assante on 16/03/2017.
 */
public class UnfixedCurtain extends Curtain {

    public UnfixedCurtain(PVector position, int width, int height, int particleSpace) {
        super(position, width, height, particleSpace);
    }

    public UnfixedCurtain(float x, float y, float z, int width, int height, int particleSpace) {
        super(x, y, z, width, height, particleSpace);
    }

    @Override
    public void initMesh() {
        this.masses = new Mass[this.width * this.height];
        this.links = new ArrayList<Link>();

        float firstX = this.getPosition().x - (this.width / 2) * this.particleSpace + this.particleSpace / 2.f;
        float firstY = this.position.y;
        float firstZ = this.getPosition().z - (this.height / 2) * this.particleSpace + this.particleSpace / 2.f;

        for (int y = 0; y < this.height; y ++) {
            for (int x = 0; x < this.width; x ++) {

                float massPosX = firstX + x * this.particleSpace;
                float massPosY = firstY;
                float massPosZ = firstZ + y * this.particleSpace;

                MovingMass tmpMass = new MovingMass(massPosX, massPosY, massPosZ);
                this.masses[y * this.width + x] = tmpMass;

                if (x > 0) {
                    links.add(new SpringLink(this.masses[y * this.width + x - 1], this.masses[y * this.width + x]));
                }
                if (x > 1) {
                    links.add(new SpringLink(this.masses[y * this.width + x - 2], this.masses[y * this.width + x]));
                }
                if (y > 0) {
                    links.add(new SpringLink(this.masses[(y - 1) * this.width + x], this.masses[y * this.width + x]));
                }
                if (y > 1) {
                    links.add(new SpringLink(this.masses[(y - 2) * this.width + x], this.masses[y * this.width + x]));
                }
                if (x > 0 && y > 0) {
                    links.add(new SpringLink(this.masses[(y - 1) * this.width + x - 1], this.masses[y * this.width + x]));
                }
                if (x < this.width && y > 0) {
                    links.add(new SpringLink(this.masses[(y - 1) * this.width + x + 1], this.masses[y * this.width + x]));
                }
            }
        }
    }
}
