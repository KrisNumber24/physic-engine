package Physics;

import Objects.PhysicObject;
import RenderingEngine.Renderer;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

/**
 * Created by Christophe on 13/03/2017.
 */
public class Simulation {

    private static Simulation instance = null;

    private Renderer ctx; // Graphic context

    private PVector gravity = new PVector(0, 9.8f, 0);
    private float fe        = 50;
    private float h         = 1/fe;
    private float alpha     = 0.2f;
    private float k         = alpha * fe * fe;
    private float z         = (float) (alpha / 20.f * fe);

    private ArrayList<PhysicObject> objects = new ArrayList<PhysicObject>();

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

    public void attachPhysicObject(PhysicObject obj) {
        this.objects.add(obj);
    }

    public void attachPhysicObjects(PhysicObject objs[]) {
        for (PhysicObject obj : objs) {
            for (Link link : obj.getLinks()) {
                link.setK(this.k);
            }

            this.attachPhysicObject(obj);
        }

    }

    public void update() {
        for (PhysicObject object : this.objects){

            for (Link link : object.getLinks()) {
                link.update();
                link.getM1().processGravity(gravity);
                link.getM2().processGravity(gravity);
            }

            for (Mass mass : object.getMasses()) {
                mass.processLeapFrog(this.h);
            }
        }
    }

    public void draw(PApplet ctx){
        for (PhysicObject object : this.objects) {
            object.draw(ctx);
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
}
