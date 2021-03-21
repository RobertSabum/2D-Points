import java.util.*;

public class KDTree<Key extends Comparable<Key>, Value>{

    private class Node {
        private Point2D p;
        private Value val;
        private RectHV rect;
        private Node left, right;

        public Node(Point2D p, RectHV rect, Value val) {
            RectHV r = rect;
            if (r == null)
                r = new RectHV(0, 0, 1, 1);
            this.rect = r;
            this.p = p;
        }
    }

    private Node root;
    private int size;
    private Vector<Point2D> keys;

    //construct an empty set of points
    public KDTree() {
        root = null;
        size = 0;
        keys = new Vector<>();
    }

    //is empty?
    public boolean isEmpty() {
        return root == null;
    }

    //size of the tree
    public int size() {
        return size; 
    }

    // going by Y value
    private Node InsertY(Node x, Point2D p, RectHV rect, Value val) {
        if (x == null) {
            size++;
            return new Node(p, rect, val);
        }
        if (x.p.equals(p)) return x;

        RectHV r;
        int compare = Point2D.Y_ORDER.compare(x.p, p);
        if (compare > 0) {
            if (x.left == null)
                r = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), x.p.y());
            else
                r = x.left.rect;
            x.left = InsertX(x.left, p, r, val);
        } else {
            if (x.right == null)
                r = new RectHV(rect.xmin(), x.p.y(), rect.xmax(), rect.ymax());
            else
                r = x.right.rect;
            x.right = InsertX(x.right, p, r, val);
        }

        return x;
    }

    //going by X value
    private Node InsertX(Node x, Point2D p, RectHV rect, Value val) {
        if (x == null) {
            size++;
            return new Node(p, rect, val);
        }
        if (x.p.equals(p)) return x;

        RectHV r;
        int compare = Point2D.X_ORDER.compare(x.p, p);
        if (compare > 0) {
            if (x.left == null)
                r = new RectHV(rect.xmin(), rect.ymin(), x.p.x(), rect.ymax());
            else
                r = x.left.rect;
            x.left = InsertY(x.left, p, r, val);
        } else {
            if (x.right == null)
                r = new RectHV(x.p.x(), rect.ymin(), rect.xmax(), rect.ymax());
            else
                r = x.right.rect;
            x.right = InsertY(x.right, p, r, val);
        }

        return x;
    }

    //insert a point
    public void put(Point2D p, Value val) {
        if(isEmpty()){
            root = InsertX(root, p, null, val);
        }  
        else{
            root = InsertX(root, p, root.rect, val);
        }
        keys.add(p); 
    }

    //checks if point exists
    private Value get(Node x, Point2D p, boolean y) {
        if(x == null){
            return null;
        } 
        if(x.p.equals(p)){
            return x.val;
        }

        int compare;
        if(y){
            compare = Point2D.X_ORDER.compare(x.p, p);
        }
        else{
            compare = Point2D.Y_ORDER.compare(x.p, p);
        } 
        if(compare > 0){
            return get(x.left, p, !y);
        }
        else {
            return get(x.right, p, !y);
        }
    }

    public boolean contains(Point2D p){//returns false if get returns no point
        return get(root, p, true) == null;
    }

    public Value getValue(Point2D p){//gets the value associated with a point
        return get(root, p, true);
    }

    public Vector<Point2D> keys(){//returns the list of points
        return keys;
    }
    

    //uses the pruning rule to recursively go to the side of the subtree that contains the point we care about
    private Point2D nearest(Node x, Point2D p, Point2D test, boolean y) {
        Point2D nearest = test;

        if (x == null) {
            return nearest;
        }
        if (p.distanceSquaredTo(x.p) < p.distanceSquaredTo(nearest)){
            nearest = x.p;
        }
            
        if(y){
            if(x.p.x() < p.x()){
                nearest = nearest(x.right, p, nearest, !y);
                if (x.left != null && (nearest.distanceSquaredTo(p) > x.left.rect.distanceSquaredTo(p))){
                    nearest = nearest(x.left, p, nearest, !y);
                }      
            }
            else{
                nearest = nearest(x.left, p, nearest, !y);
                if(x.right != null && (nearest.distanceSquaredTo(p) > x.right.rect.distanceSquaredTo(p))){
                    nearest = nearest(x.right, p, nearest, !y);
                }
            }
        }
        else{
            if(x.p.y() < p.y()){
                nearest = nearest(x.right, p, nearest, !y);
                if (x.left != null && (nearest.distanceSquaredTo(p) > x.left.rect.distanceSquaredTo(p))){
                    nearest = nearest(x.left, p, nearest, !y);
                }
            }
            else{
                nearest = nearest(x.left, p, nearest, !y);
                if(x.right != null && (nearest.distanceSquaredTo(p) > x.right.rect.distanceSquaredTo(p))){
                    nearest = nearest(x.right, p, nearest, !y);
                }
            }
        }
    
        return nearest;
    }

    //gets the nearest neighbor
    public Point2D nearest(Point2D p) {
        if (isEmpty()) return null;
        return nearest(root, p, root.p, true);
    }
}
