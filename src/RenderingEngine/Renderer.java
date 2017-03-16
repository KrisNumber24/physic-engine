/**
 * Created by Christophe Assante on 10/03/2017.
 */

package RenderingEngine;

import java.awt.event.MouseEvent;
import Physics.*;
import processing.core.PApplet;
import processing.core.PVector;

public class Renderer extends PApplet {

    private Mass masses[];
    private Link links[];
    private int windowWidth = 1280;
    private int windowHeight = 720;
    private float camRotation = 0;

    private Simulation simulation = Simulation.getInstance();

    static public void main() {
        PApplet.main("Renderer");
    }

    public void setup() {
        size(windowWidth, windowHeight, P3D);
        background(0, 0, 0);
        smooth();

        this.masses = new Mass[10];
        this.links = new Link[9];

        int firstPosX = - 4 * 50 - 25;

        this.masses[0] = new FixedMass(firstPosX, 0);
        this.masses[this.masses.length - 1] = new FixedMass(-firstPosX, 0);

        for (int i = 1; i < this.masses.length - 1; i++) {
            this.masses[i] = new MovingMass(i * 50 + firstPosX, 0);
        }

        for (int i = 0; i < this.masses.length - 1; i++) {
            this.links[i] = new Link(masses[i], masses[i + 1]);
        }
        
        simulation.attachContext(this);
        simulation.attachMasses(masses);
        simulation.attachLinks(links);

        masses[1].getPosition().y = 20;
    }

    public void  draw() {
        camRotation += 0.00005;
        simulation.update();
        clear();
        translate(windowWidth * 0.5f, windowHeight * 0.5f);
        rotate(degrees(camRotation), 0, 1, 0);
        simulation.draw();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        camRotation = emouseX * 0.001f;
    }
}
