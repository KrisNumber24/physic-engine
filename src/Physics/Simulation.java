package Physics;

import RenderingEngine.Renderer;
import processing.core.PVector;

/**
 * Created by Christophe on 13/03/2017.
 */
public class Simulation {

    private static Simulation instance = null;

    private Renderer ctx; // Graphic context

    private PVector gravity = new PVector(0, -9.1f, 0);
    private float fe        = 100;
    private float h         = 1 / fe;
    private float alpha     = 0.7f;
    private float k         = alpha * fe * fe;
    private float z         = (float) (alpha / 10.f * fe);

    private Mass masses[];
    private Link links[];

    private Simulation() {

    }

    public static Simulation getInstance() {
        if (instance == null) {
            instance = new Simulation();
        }
        return instance;
    }

    public void attachContext(Renderer ctx) {
        this.ctx = ctx;
    }

    public void attachMasses(Mass[] masses) {
        this.masses = masses;
    }

    public void attachLinks(Link[] links) {
        this.links = links;
        for (Link link : this.links) {
            link.setK(this.k);
        }
    }

    public void update() {
        for (Mass mass : masses) {
            mass.processLeapFrog(this.h);
        }

        for (Link link : links) {
            link.processRessort();
            link.processFrein();
        }
    }

    public void draw() {
        for (Link link : links) {
            link.draw(ctx);
        }

        for (Mass mass : masses) {
            mass.draw(ctx);
        }
    }

    public float getFe() {
        return fe;
    }

    public void setFe(float fe) {
        this.fe = fe;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public float getK() {
        return k;
    }

    public void setK(float k) {
        this.k = k;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public Mass[] getMasses() {
        return masses;
    }

    public void setMasses(Mass[] masses) {
        this.masses = masses;
    }

    public Link[] getLinks() {
        return links;
    }

    public void setLinks(Link[] links) {
        this.links = links;
    }
}
