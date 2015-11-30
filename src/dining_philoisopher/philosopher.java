package dining_philoisopher;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import dining_philoisopher.Coordinator;
import dining_philoisopher.Fork;
import dining_philoisopher.ResetException;
import dining_philoisopher.Table;

class Philosopher extends Thread {
    private static final Color THINK_COLOR = Color.yellow;
    private static final Color WAIT_COLOR = Color.red;
    private static final Color EAT_COLOR = Color.green;
    private static final double THINK_TIME = 4.0;
    private static final double FUMBLE_TIME = 2.0;
       
    private static final double EAT_TIME = 3.0;

    private Coordinator c;
    private Table t;
    private static final int XSIZE = 50;
    private static final int YSIZE = 50;
    private int x;
    private int y;
    private Fork left_fork;
    private Fork right_fork;
    private Random prn;
    private Color color;
    

    public Philosopher(Table T, int cx, int cy,
                       Fork lf, Fork rf, Coordinator C) {
        t = T;
        x = cx;
        y = cy;
        left_fork = lf;
        right_fork = rf;
        c = C;
        prn = new Random();
        color = THINK_COLOR;
    }

   
    public void run() {
        for (;;) {
            try {
                if (c.gate()) delay(EAT_TIME/2.0);
                think();
                if (c.gate()) delay(THINK_TIME/2.0);
                hunger();
                if (c.gate()) delay(FUMBLE_TIME/2.0);
                eat();
            } catch(ResetException e) { 
                color = THINK_COLOR;
                t.repaint();
            }
        }
    }

  
    
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x-XSIZE/2, y-YSIZE/2, XSIZE, YSIZE);
    }

    
    
    private static final double FUDGE = 0.2;
    private void delay(double secs) throws ResetException {
        double ms = 1000 * secs;
        int window = (int) (2.0 * ms * FUDGE);
        int add_in = prn.nextInt() % window;
        int original_duration = (int) ((1.0-FUDGE) * ms + add_in);
        int duration = original_duration;
        for (;;) {
            try {
                Thread.sleep(duration);
                return;
            } catch(InterruptedException e) {
                if (c.isReset()) {
                    throw new ResetException();
                } else {       
                    c.gate();   
                    duration = original_duration / 2;
                   
                   
                }
            }
        }
    }

    private void think() throws ResetException {
        color = THINK_COLOR;
        t.repaint();
        delay(THINK_TIME);
    }

    private void hunger() throws ResetException {
        color = WAIT_COLOR;
        t.repaint();
        delay(FUMBLE_TIME);
       
        yield();   
      
    }

    private void eat() throws ResetException {
    	if(left_fork.checkValue()==1 && right_fork.checkValue()==1){
    		left_fork.eat();
    		right_fork.eat();
        color = EAT_COLOR;
        t.repaint();
        left_fork.acquire(x, y);
        right_fork.acquire(x, y);
        delay(EAT_TIME);
        left_fork.release();
        yield();   
        right_fork.release();
        left_fork.done();
        right_fork.done();
    	}
    }
}
