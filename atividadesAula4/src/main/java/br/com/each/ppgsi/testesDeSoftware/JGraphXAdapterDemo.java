/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.each.ppgsi.testesDeSoftware;

import com.mxgraph.layout.*;
import com.mxgraph.swing.*;
import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;

import javax.swing.*;
import java.awt.*;
import java.lang.annotation.Annotation;
import java.util.Collection;

/**
 * A demo applet that shows how to use JGraphX to visualize JGraphT graphs. Applet based on
 * JGraphAdapterDemo.
 *
 */
public class JGraphXAdapterDemo extends JApplet
{
    private static final long serialVersionUID = 2202072534703043194L;

    private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

    private JGraphXAdapter<String, DefaultEdge> jgxAdapter;

    /**
     * An alternative starting point for this demo, to also allow running this applet as an
     * application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
        JGraphXAdapterDemo applet = new JGraphXAdapterDemo();
        applet.init();

        JFrame frame = new JFrame();
        frame.getContentPane().add(applet);
        frame.setTitle("JGraphT Adapter to JGraphX Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void init()
    {
        // create a JGraphT graph
        ListenableGraph<String, DefaultEdge> g =
            new DefaultListenableGraph<>(new DefaultDirectedGraph<>(DefaultEdge.class));

        // create a visualization using JGraph, via an adapter
        jgxAdapter = new JGraphXAdapter<>(g);

        setPreferredSize(DEFAULT_SIZE);
        mxGraphComponent component = new mxGraphComponent(jgxAdapter);
        component.setConnectable(false);
        component.getGraph().setAllowDanglingEdges(false);
        component.getGraph().setAllowLoops(true);
        getContentPane().add(component);
        resize(DEFAULT_SIZE);      

        // add some sample data (graph manipulated via JGraphX)
        g.addVertex("V1");
        g.addVertex("V2");
        g.addVertex("V2a");
        g.addVertex("V2b");
        g.addVertex("V3");
        g.addVertex("V4");
        g.addVertex("V5");
        g.addVertex("V6");
        g.addVertex("V6a");
        g.addVertex("V6b");
        g.addVertex("V7");
        g.addVertex("V8");
        g.addVertex("VX");
        
        g.addEdge("V1", "V2");
        g.addEdge("V2", "VX");
        g.addEdge("V1", "V2a");
        
        g.addEdge("V2a", "V3");
        g.addEdge("V3", "VX");
        
        g.addEdge("V2b", "V3");
        g.addEdge("V3", "VX");
        
        g.addEdge("V4", "V5");
        g.addEdge("V5", "VX");
        
        g.addEdge("V6", "V6a");
        g.addEdge("V6a", "V7");
        g.addEdge("V7", "VX");
        
        g.addEdge("V6", "V6a");
        g.addEdge("V6a", "V6b");
        g.addEdge("V6b", "V7");
        g.addEdge("V7", "VX");
        
        g.addEdge("V6", "V6a");
        g.addEdge("V6a", "V6b");
        g.addEdge("V6b", "V8");
        g.addEdge("V8", "VX");
                                       

       
        // positioning via jgraphx layouts
        mxFastOrganicLayout layout = new mxFastOrganicLayout(jgxAdapter);

        // center the circle
        int radius = 100;
//        layout.setX0((DEFAULT_SIZE.width / 2.0) - radius);
//        layout.setY0((DEFAULT_SIZE.height / 2.0) - radius);
//        layout.setRadius(radius);
//        layout.setMoveCircle(true);

        layout.execute(jgxAdapter.getDefaultParent());
        // that's all there is to it!...
    }
}