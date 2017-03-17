/**
 * Created by Christophe Assante on 10/03/2017.
 */

package RenderingEngine;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import Objects.*;
import Physics.*;
import processing.core.PApplet;
import processing.core.PVector;

public class Renderer extends PApplet {

    private int windowWidth = 1280;
    private int windowHeight = 720;
    private float camRotation = 0;

    private Simulation simulation = Simulation.getInstance();
    private ArrayList<PhysicObject> objects = new ArrayList<PhysicObject>();

    static public void main() {
        PApplet.main("Renderer");
    }

    public void setup() {
        size(windowWidth, windowHeight, P3D);
        background(0, 0, 0);
        smooth();

        this.objects.add(new UnfixedCurtain(new PVector(0, -200, 0), 30, 30, 20));
        Sphere obstacle = new Sphere(new PVector(250,0,0), 100);
        this.objects.add(obstacle);

        obstacle.attachObject(this.objects.get(0));

        for (PhysicObject object : this.objects) {
            object.setup(this);
        }

        simulation.attachContext(this);
        simulation.attachPhysicObjects(this.objects.toArray(new PhysicObject[this.objects.size()]));

//        this.objects.get(0).getLinks().get(105).getM1().getPosition().y += 100;
//        System.out.println("Point 45 => d0 = " + this.objects.get(0).getLinks().get(105).getD0() + " - d = " + this.objects.get(0).getLinks().get(105).getM1().getPosition().dist(this.objects.get(0).getLinks().get(105).getM2().getPosition()));
    }

    public void  draw() {
        camRotation += 0.00005;
        simulation.update();
        clear();
        translate(windowWidth * 0.5f, windowHeight * 0.5f);
        rotate(degrees(camRotation), 0, 1, 0);
        simulation.draw(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        camRotation = emouseX * 0.001f;
    }
}
