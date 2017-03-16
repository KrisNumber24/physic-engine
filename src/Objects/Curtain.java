package Objects;

import Physics.FixedMass;
import Physics.Link;
import Physics.Mass;
import Physics.MovingMass;
import processing.core.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christophe Assante on 16/03/2017.
 */
public class Curtain extends PhysicObject {

    private PVector position; // curtain-rod's position
    private int width; // width in particles
    private int height; // height in particles
    private int particleSpace; // space between particles
    private float rotation;

    public Curtain(PVector position,int width, int height, int particleSpace) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.particleSpace = particleSpace;

        this.initMesh();
    }

    public Curtain(float x, float y, float z, int width, int height, int particleSpace) {
        this(new PVector(x, y, z), width, height, particleSpace);
    }

    @Override
    public void initMesh() {
        this.masses = new Mass[this.width * this.height];
        this.links = new ArrayList<Link>();

        float firstX = this.getPosition().x - (this.width / 2) * this.particleSpace + this.particleSpace / 2.f;
        float firstY = this.position.y;
        float firstZ = this.position.z;

        for (int y = 0; y < this.height; y ++) {
            for (int x = 0; x < this.width; x ++) {

                float massPosX = firstX + x * this.particleSpace;
                float massPosY = firstY;
                float massPosZ = firstZ + y * this.particleSpace;

                if (y == 0) {
                    FixedMass tmpMass = new FixedMass(massPosX, massPosY, massPosZ);
                    this.masses[y * this.width + x] = tmpMass;
                }
                else {
                    MovingMass tmpMass = new MovingMass(massPosX, massPosY, massPosZ);
                    this.masses[y * this.width + x] = tmpMass;
                }

                if (x > 0) {
                    links.add(new Link(this.masses[y * this.width + x - 1], this.masses[y * this.width + x]));
                }
                if (x > 1) {
                    links.add(new Link(this.masses[y * this.width + x - 2], this.masses[y * this.width + x]));
                }
                if (y > 0) {
                    links.add(new Link(this.masses[(y - 1) * this.width + x], this.masses[y * this.width + x]));
                }
                if (y > 1) {
                    links.add(new Link(this.masses[(y - 2) * this.width + x], this.masses[y * this.width + x]));
                }
                if (x > 0 && y > 0) {
                    links.add(new Link(this.masses[(y - 1) * this.width + x - 1], this.masses[y * this.width + x]));
                }
                if (x < this.width && y > 0) {
                    links.add(new Link(this.masses[(y - 1) * this.width + x + 1], this.masses[y * this.width + x]));
                }
            }
        }
    }

    @Override
    public void setup(PApplet ctx) {
        ctx.rotate(90, 1, 0, 0);



        this.draw(ctx);

        ctx.rotate(-90, 1, 0, 0);
    }

    @Override
    public void update() {

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getParticleSpace() {
        return particleSpace;
    }

    public void setParticleSpace(int particleSpace) {
        this.particleSpace = particleSpace;
    }

    public PVector getPosition() {
        return position;
    }

    public void setPosition(PVector position) {
        this.position = position;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
