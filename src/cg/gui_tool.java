package cg;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;

public class gui_tool extends JFrame implements MouseListener {
    ArrayList<point> point_list=new ArrayList<>();

    gui_tool(){
        this.addMouseListener(this);
        this.setSize(800,800);
        this.setResizable(false);
        this.setTitle("Enter the Input Points by clicking on the screen:");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        JButton button=new JButton("Next");
        button.setBounds(400,650,80,60);
        button.addActionListener(e -> {
            this.dispose();
        });

        this.add(button);
    }

    public void mouseClicked(MouseEvent event){
        Graphics g=getGraphics();
        g.setColor(Color.BLUE);
        g.fillOval(event.getX(),event.getY(),10,10);
        int x=event.getX();
        int y=event.getY();
        System.out.println("x = "+ x + " y = " + y);
        point new_point=new point(x,y);
        this.point_list.add(new_point);
    }

    public void mouseEntered(MouseEvent event){}
    public void mouseExited(MouseEvent event){}
    public void mousePressed(MouseEvent event){}
    public void mouseReleased(MouseEvent event){}

}
