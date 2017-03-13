/**
 * Created by Christophe Assante on 10/03/2017.
 */

package RenderingEngine;

import Physics.*;
import processing.core.PApplet;

public class Renderer extends PApplet {

    Mass masses[];
    Link links[];
    Simulation simulation = Simulation.getInstance();

    static public void main() {
        PApplet.main("Renderer");
    }

    public void setup() {
        size(1280, 720, P3D);
        background(0, 0, 0);
        smooth();

        this.masses = new Mass[10];
        this.links = new Link[9];

        this.masses[0] = new FixedMass(50, 500);
        this.masses[this.masses.length - 1] = new FixedMass(((this.masses.length - 1) + 1) * 50, 500);

        for (int i = 1; i < this.masses.length - 1; i++) {
            this.masses[i] = new MovingMass((i + 1) * 50, 500);
        }

        for (int i = 0; i < this.masses.length - 1; i++) {
            this.links[i] = new Link(masses[i], masses[i + 1]);
        }

        simulation.attachContext(this);
        simulation.attachMasses(masses);
        simulation.attachLinks(links);

        masses[1].getPosition().y = 525;
    }

    public void  draw() {
        simulation.update();
        clear();
        simulation.draw();
    }

}
