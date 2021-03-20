import java.util.Comparator;

public class PointST<Value> {

		private RedBlackBST<Point2D> points;



    // construct an empty symbol table of points 
    public PointST(){
			points = new RedBlackBST<Point2D>();
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
			points.contains(p);
		}

    // all points in the symbol table 
    public Iterable<Point2D> points(){

		}

    // all points that are inside the rectangle (or on the boundary) 
    public Iterable<Point2D> range(RectHV rect){

		}

    // a nearest neighbor of point p; null if the symbol table is empty 
    public Point2D nearest(Point2D p){

		}

    // unit testing (required)
    public static void main(String[] args){

		}

}