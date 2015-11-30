
package dining_philoisopher;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.RootPaneContainer;

import dining_philoisopher.Coordinator;
import dining_philoisopher.Table;
import dining_philoisopher.UI;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;



class UI extends JPanel {
    private final Coordinator c;
    private final Table t;

    private final JRootPane root;
    private static final int externalBorder = 6;

    private static final int stopped = 0;
    private static final int running = 1;
    private static final int paused = 2;

    private int state = stopped;

    // Constructor
    //
    public UI(RootPaneContainer pane, Coordinator C, Table T,
              boolean isApplet) {
        final UI u = this;
        c = C;
        t = T;

        final JPanel b = new JPanel();   // button panel
        b.setBackground(new Color(255, 153, 255));

        final JButton Run = new JButton("Start Dining");
        Run.setBounds(60, 5, 115, 29);
        final JButton pauseButton = new JButton("Stop Dining");
        pauseButton.setBounds(60, 46, 114, 29);
        final JButton resetButton = new JButton("Reset");
        resetButton.setBounds(276, 24, 78, 29);
        

        Run.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c.resume();
                root.setDefaultButton(pauseButton);
            }
        });
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                t.pause();
                root.setDefaultButton(Run);
            }
        });
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                t.reset();
                root.setDefaultButton(Run);
            }
        });
        b.setLayout(null);
        b.add(Run);
        b.add(pauseButton);
        b.add(resetButton);
      

        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(
            externalBorder, externalBorder, externalBorder, externalBorder));
        add(t);
        add(b);

       
        pane.getContentPane().add(this);
        root = getRootPane();
        root.setDefaultButton(Run);
        
        JLabel lblDiningPhilosophersProblem = new JLabel("Dining Philosophers problem");
        lblDiningPhilosophersProblem.setBounds(101, 87, 257, 16);
        b.add(lblDiningPhilosophersProblem);
        
        JLabel lblColorCodes = new JLabel("Color Codes");
        lblColorCodes.setFont(new Font("Lucida Grande", Font.ITALIC, 16));
        lblColorCodes.setBounds(6, 129, 115, 16);
        b.add(lblColorCodes);
        
        JLabel lblYellowThinking = new JLabel("Yellow : Thinking");
        lblYellowThinking.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        lblYellowThinking.setForeground(Color.YELLOW);
        lblYellowThinking.setBounds(6, 161, 169, 16);
        b.add(lblYellowThinking);
       
        JLabel lblBlueHungry = new JLabel("Red : Hungry");
        lblBlueHungry.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        lblBlueHungry.setForeground(new Color(255, 51, 0));
        lblBlueHungry.setBounds(165, 161, 122, 16);
        b.add(lblBlueHungry);
        
        JLabel lblGreenEating = new JLabel("Green : Eating");
        lblGreenEating.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        lblGreenEating.setForeground(new Color(51, 204, 0));
        lblGreenEating.setBounds(299, 161, 122, 16);
        b.add(lblGreenEating);
    }
}
