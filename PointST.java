import java.util.*;

public class PointST<Value> {

		private RedBlackBST<Point2D, Value> points; //Red black tree that holds points with Point2D as keys

    // construct an empty symbol table of points 
    public PointST(){
			points = new RedBlackBST<Point2D, Value>();
		}

    // is the symbol table empty? 
    public boolean isEmpty(){
			return points.isEmpty();
		}

    // number of points
    public int size(){
			return points.size();
		}

    // associate the value val with point p
    public void put(Point2D p, Value val){
			points.put(p, val);
		}

    // value associated with point p 
    public Value get(Point2D p){
			return points.get(p);
		}

    // does the symbol table contain point p? 
    public boolean contains(Point2D p){
			return points.contains(p);
		}

    // all points in the symbol table 
    public Iterable<Point2D> points(){
      return points.keys();
		}

    // all points that are inside the rectangle (or on the boundary) 
    public Iterable<Point2D> range(RectHV rect){//creates a vector and loops through the tree adding all points in range to the vector O(n)
      Vector<Point2D> pointsinrange = new Vector<>();
      for(Point2D p : points()){
        if(rect.contains(p)){
          pointsinrange.add(p);
        }
      }
      return pointsinrange;
		}

    // a nearest neighbor of point p; null if the symbol table is empty 
    public Point2D nearest(Point2D p){//loops through tree and compares each point distance to current nearest point. uses distance squared to save time. O(n)
      if(this.isEmpty()){//if there are no points
        return null;
      }
      else{//if there are points
        Point2D currentnearest =  points.min();
        for(Point2D test : points()){
          if(p.distanceSquaredTo(test) < p.distanceSquaredTo(currentnearest)){//if the distance of the testing point is less than the current nearest point
            currentnearest = test;
          }
        }
        return currentnearest;
      }
      
    }
    

    // unit testing (required)
    public static void main(String[] args){
      PointST<Integer> st = new PointST<>();
        for(int i=0; i<100; i++){
            Point2D p = new Point2D(Math.random(), Math.random());
            st.put(p, i);
        }
        System.out.println(st.points());
        System.out.println();
        System.out.println(st.nearest(new Point2D(0, 0)));
        System.out.println();
        RectHV rect = new RectHV(0, 0, 0.25, 0.25);
        System.out.println(st.range(rect));
		}

}