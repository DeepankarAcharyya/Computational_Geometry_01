package cg;
import java.util.*;

import static cg.circles.plot_output;

import java.lang.Math;

public class cg_assignment {

    public static void print_point_array_list(ArrayList<point> s){
        for(point obj : s){
            obj.print();
        }
    }

    public static ArrayList<point> sort_points_x(ArrayList<point> s){
        s.sort(Comparator.comparing(point::getX));
        return s;
    }

    public static ArrayList<point> sort_points_y(ArrayList<point> s){
        s.sort(Comparator.comparing(point::getY));
        return s;
    }

    public static int orientation(point p, point q, point r){
        float val = (q.y-p.y)*(r.x-q.x)-(q.x-p.x)*(r.y-q.y);
        if (val==0)
            return 0; //collinear
        else
            return (val>0)? 1:-1; //1: clockwise and -1: anti-clockwise
    }

    public static Vector<point> convex_hull(ArrayList<point> s){
        Vector<point> hull = new Vector<point>();
        int p,q,n;
        p=0;
        n=s.size();
        do{
            hull.add(s.get(p));
            q=(p+1)%n;
            for(int i=0;i<n;i++){
                if(orientation(s.get(p),s.get(i),s.get(q))==-1)
                    q=i;
            }
            p=q;
        }
        while(p!=0);

        return hull;
    }

    public static Vector<point> select_two_points(Vector<point> s){
        int n=s.size();

        if(n==0) {
            System.out.println("Error!! No Convex Hull Found");
            System.exit(0);
        }

        point p;
        double dis;
        int index_min=0;
        double min_dis=s.get(0).distance(s.get(1));

        for(int i=0;i<n-1;i++){
            p=s.get(i);
            dis=p.distance(s.get(i+1));

            if(dis<min_dis){
                min_dis=dis;
                index_min=i;
            }
        }

        Vector<point> result = new Vector<point>(2);
        result.add(s.get(index_min));
        result.add(s.get(index_min+1));

        return result;
    }

    public static double cal_safe_radius(Vector<point> s){
        int min_x=0,min_y=0,max_x=0,max_y=0;
        int n=s.size();
        //part 1 : min x
        for(int i=0;i<n;i++){
            if(s.get(i).x<s.get(min_x).x){
                min_x=i;
            }
        }
        //part 2 : max x
        for(int i=0;i<n;i++){
            if(s.get(i).x>s.get(max_x).x){
                max_x=i;
            }
        }
        //part 3 : min y
        for(int i=0;i<n;i++){
            if(s.get(i).y<s.get(min_y).y){
                min_y=i;
            }
        }
        //part 4 : max y
        for(int i=0;i<n;i++){
            if(s.get(i).y>s.get(max_y).y){
                max_y=i;
            }
        }

        //calculate the distances between the extreme points
        double d1,d2,r;
        d1=s.get(min_x).distance(s.get(max_x));
        d2=s.get(min_y).distance(s.get(max_y));

        r=(d1>d2)?d1:d2;

        return r;
    }

    public static point find_centre(Vector<point> s, double r, ArrayList<point> ref){

        //http://mathforum.org/library/drmath/view/53027.html

        float q=(float)s.get(0).distance(s.get(1));
        float x1,x2,y1,y2,x3,y3;
        x1=s.get(0).getX();
        y1=s.get(0).getY();
        x2=s.get(1).getX();
        y2=s.get(1).getY();
        x3=(x1+x2)/2;
        y3=(y1+y2)/2;
        float q2=q/2;

        float cx1,cx2,cy1,cy2;
        double d1=0,d2=0;

        cx1= (float)(x3 + (Math.sqrt(r*r - q2*q2))*(Math.abs(y1-y2)/q));
        cy1= (float)(y3 + (Math.sqrt(r*r - q2*q2))*(Math.abs(x1-x2)/q));

        point c1=new point(cx1,cy1);

        cx2= (float)(x3 - (Math.sqrt(r*r - q2*q2))*(Math.abs(y1-y2)/q));
        cy2= (float)(y3 - (Math.sqrt(r*r - q2*q2))*(Math.abs(x1-x2)/q));

        point c2=new point(cx2,cy2);
        point c = c1;

        for(point p1:ref) {
            d1 = c1.distance(p1);
            if(d1>r) {
                c = c2;
                break;
            }
            d2 = c2.distance(p1);
            if(d2>r){
                c=c1;
                break;
            }
        }
        return c;
    }

    public static void main(String[] args) {

        System.out.println("STEP 1: User Input");
        gui_tool input_tool = new gui_tool();
        while(input_tool.isDisplayable()==true){
            //this loop is to ensure the input thread finish taking input
            System.out.print("");
        }

        System.out.println("STEP 2: Sorting according to the x-co-ordinates of the points.");
        ArrayList<point> sorted_points=sort_points_x(input_tool.point_list);

        System.out.println("STEP 3: Calculating the Convex Hull.");
        Vector<point> convexhull = convex_hull(sorted_points);

        for(point tmp:convexhull){
            tmp.print();
        }

        System.out.println("STEP 4: Selecting 2 points.");
        Vector<point> points_circum=select_two_points(convexhull);
        System.out.println("Selected points on the circumference are:");
        points_circum.get(0).print();
        points_circum.get(1).print();

        System.out.println("STEP 5: Calculating the safe radius.");
        double radius=cal_safe_radius(convexhull);
        System.out.println("Radius = "+radius);

        System.out.println("STEP 6: Calculating the centre point.");
        point centre = find_centre(points_circum,radius, sorted_points);
        System.out.println("The centre is found out to be :");
        centre.print();

        System.out.println("STEP 7: Plotting the circle and the input points.");
        plot_output(centre, (float)radius, sorted_points, points_circum);

    }
}
