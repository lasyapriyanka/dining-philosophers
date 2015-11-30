package dining_philoisopher;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;



import java.util.*;
import java.lang.*;
import java.lang.Thread.*;

public class dining extends JApplet {
    private static final int CANVAS_SIZE = 360;
       

    private void start(final RootPaneContainer pane, final boolean isApplet) {
        final Coordinator c = new Coordinator();
        final Table t = new Table(c, CANVAS_SIZE);
        
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    new UI(pane, c, t, isApplet);
                }
            });
        } catch (Exception e) {
            System.err.println("unable to create GUI");
        }
    }

   
    public void init() {
        start(this, true);
    }

   
    public static void main(String[] args) {
        JFrame f = new JFrame("Dining");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dining me = new dining();
        me.start(f, false);
        f.pack();          
        f.setVisible(true);
    }
}