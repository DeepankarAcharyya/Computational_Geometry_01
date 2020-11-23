package cg;
import java.lang.Math;

public class point {
    float x;
    float y;

    public point(){
        this.x=0;
        this.y=0;
    }

    public point(float x,float y){
        this.x = x;
        this.y = y;
    }

    double distance(point p) {
        return Math.sqrt((p.x - this.x) * (p.x - this.x) + (p.y - this.y) * (p.y - this.y));
    }

    void print(){
        System.out.println("X="+this.x+" Y="+this.y);
        System.out.println();
    }

    float getX(){
        return this.x;
    }

    float getY(){
        return this.y;
    }
}
