/**
 * Created by Christophe Assante on 10/03/2017.
 */

package RenderingEngine;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import Physics.*;
import processing.core.PApplet;
import processing.core.PVector;

public class Renderer extends PApplet {

    private Mass masses[];
    private List<Link> links;
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


        int flagWidth =     50;
        int flagHeight =    48;
        int flagCenterX =   flagWidth / 2;
        int flagCenterY =   flagHeight / 2;
        int flagSpace =     10;

        int firstPosX =  0; //- flagCenterX * flagSpace + flagSpace / 2;
        int firstPosY =  -200;  //- flagCenterY * flagSpace  + flagSpace / 2;

        this.masses = new Mass[flagHeight * flagWidth];
        this.links = new ArrayList<Link>();

        for (int y = 0; y < flagHeight; y ++) {
            for (int x = 0; x < flagWidth; x ++) {

                int massPosX = firstPosX + x * flagSpace;
                int massPosy = firstPosY + y * flagSpace;

                if (x == 0) {
                    FixedMass tmpMass = new FixedMass(massPosX, firstPosY, massPosy);
                    this.masses[y * flagWidth + x] = tmpMass;
                }
                else {
                    MovingMass tmpMass = new MovingMass(massPosX, firstPosY, massPosy);
                    this.masses[y * flagWidth + x] = tmpMass;
                }

                if (x > 0) {
                    links.add(new Link(this.masses[y * flagWidth + x - 1], this.masses[y * flagWidth + x]));
                }
                if (y > 0) {
                    links.add(new Link(this.masses[(y - 1) * flagWidth + x], this.masses[y * flagWidth + x]));
                }
            }
        }

        simulation.attachContext(this);
        simulation.attachMasses(masses);
        simulation.attachLinks(links.toArray(new Link[links.size()]));

        //masses[1].getPosition().y = 20;
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
