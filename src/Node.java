public class Node<T> {
    Node<T> leftSon;
    Node<T> middleSon;
    Node<T> rightSon;

    // Constructors
    public Node(Node<T> leftSon,Node<T> middleSon,Node<T> rightSon){
        this.leftSon = leftSon;
        this.middleSon = middleSon;
        this.rightSon = rightSon;
    }
    public Node(Node<T> leftSon,Node<T> middleSon){
        this(leftSon,middleSon,null);
    }
    public Node(Node<T> leftSon){
        this(leftSon,null,null);
    }
    public Node() {
        this(null, null, null);
    }


    // Getters and Setters
    public Node<T> getLeftSon() {
        return leftSon;
    }

    public void setLeftSon(Node<T> leftSon) {
        this.leftSon = leftSon;
    }

    public Node<T> getMiddleSon() {
        return middleSon;
    }

    public void setMiddleSon(Node<T> middleSon) {
        this.middleSon = middleSon;
    }

    public Node<T> getRightSon() {
        return rightSon;
    }

    public void setRightSon(Node<T> rightSon) {
        this.rightSon = rightSon;
    }



}
