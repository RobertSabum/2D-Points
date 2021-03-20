import java.util.Comparator;
import java.util.Vector;

public class PointST<Value> {

		private RedBlackBST<Point2D, Value> points;

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
      Vector<Point2D> allpoints = new Vector<>();

      return allpoints;
		}

    // all points that are inside the rectangle (or on the boundary) 
    public Iterable<Point2D> range(RectHV rect){
      Vector<Point2D> pointsinrange = new Vector<>();

      return pointsinrange;

		}

    // a nearest neighbor of point p; null if the symbol table is empty 
    public Point2D nearest(Point2D p){
      return p;
		}

    // unit testing (required)
    public static void main(String[] args){
      
		}

}