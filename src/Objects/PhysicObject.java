package Objects;

import Physics.Link;
import Physics.Mass;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christophe Assante on 16/03/2017.
 */
public abstract class PhysicObject {

    protected Mass masses[];
    protected ArrayList<Link> links = new ArrayList<Link>();

    public abstract void initMesh();

    public abstract void setup(PApplet ctx);

    public abstract void update();

    public void draw(PApplet ctx) {
        //ctx.rotate(90, 1, 0, 0);

        for (Link link : links) {
            link.draw(ctx);
        }

        for (Mass mass : masses) {
            mass.draw(ctx);
        }
    }

    public Mass[] getMasses() {
        return masses;
    }

    public void setMasses(Mass[] masses) {
        this.masses = masses;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Link> links) {
        this.links = links;
    }
}
