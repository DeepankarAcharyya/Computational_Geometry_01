package cg;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.util.*;

public class circles extends JPanel{
    int x = 0;
    int y = 0;
    int r=0;
    ArrayList<point> s;
    Vector<point> p;

    public circles(point c, float r, ArrayList<point> s, Vector<point> p) {
        this.x=(int) c.x;
        this.y=(int) c.y;
        this.r=(int) r;
        this.s=s;
        this.p=p;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillOval(this.x-this.r, this.y-this.r, 2*this.r, 2*this.r);

        g.setColor(Color.BLUE);
        for(point tmp:this.s){
            g.fillOval((int)tmp.getX(),(int)tmp.getY(),8,8);
        }

        g.setColor(Color.RED);
        g.fillOval(this.x,this.y,8,8);
        g.drawLine(this.x,this.y,(int)p.get(0).getX(),(int)p.get(0).getY());
        g.drawLine(this.x,this.y,(int)p.get(1).getX(),(int)p.get(1).getY());
    }

    public static void plot_output(point c, float r, ArrayList<point> s, Vector<point> p) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("The final Output:");
                circles co = new circles(c,r,s,p);
                frame.add(co);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(900, 900);
                frame.setVisible(true);
            }
        });
    }
}


