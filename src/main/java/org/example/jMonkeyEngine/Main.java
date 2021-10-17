package org.example.jMonkeyEngine;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.debug.Arrow;
import com.jme3.scene.shape.Box;

import java.util.List;
import java.util.UUID;

public class Main extends SimpleApplication {

    public static void main(String[] args) {
        new Main().start();
    }

    @Override
    public void simpleInitApp() {
        drawMesh(new Arrow(new Vector3f(100, 0, 0)), ColorRGBA.Red);
        drawMesh(new Arrow(new Vector3f(0, 100, 0)), ColorRGBA.Green);
        drawMesh(new Arrow(new Vector3f(0, 0, 100)), ColorRGBA.Blue);

        List.of(
                        new Column(0, 0, 1),
                        new Column(1, 2, 3),
                        new Column(2, 1, 4),
                        new Column(2, 2, 5),
                        new Column(2, 3, 3),
                        new Column(3, 3, 3)
                )
                .forEach(point ->
                        drawMesh(new Box(0.5F, 0.5f, point.z() / 2), ColorRGBA.randomColor(), new Vector3f(point.x() - 0.5f, point.y() - 0.5f, point.z() / 2))
                );
    }

    private void drawMesh(Mesh mesh, ColorRGBA color) {
        drawMesh(mesh, color, Vector3f.ZERO);
    }

    private void drawMesh(Mesh mesh, ColorRGBA color, Vector3f position) {
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", color);

        Geometry geom = new Geometry(UUID.randomUUID().toString(), mesh);
        geom.setMaterial(mat);
        geom.move(position);

        rootNode.attachChild(geom);
    }
}
