public class Node<T extends TournamentObjects> {
    Node<T> parent;
    Node<T> leftChild;
    Node<T> middleChild;
    Node<T> rightChild;
    int key;
    T value;

    // Constructors
    public Node(Node<T> parent,Node<T> leftChild,Node<T> middleChild,Node<T> rightChild, T value, int key){
        this.parent = parent;
        this.leftChild = leftChild;
        this.middleChild = middleChild;
        this.rightChild = rightChild; //In TwoThreeTree always null
        this.value = value;
        this.key = key;
    }

    public Node(Node<T> parent, Node<T> leftChild,Node<T> middleChild,T value, int key){
        this(parent,leftChild,middleChild,null, value, key);
    }
    public Node(Node<T> parent,Node<T> leftChild,T value, int key){
        this(null,parent,leftChild,null,value,key);
    }
    public Node(Node<T> parent,T value, int key){
        this(parent,null,null,null,value,key);
    }
    public Node(T value, int key){
        this(null,null,null,null,value,key);
    }
    public Node(int key){
        this(null,null,null,null,null,key);
    }
    public Node() {
        this(null,null, null, null,null,Integer.MIN_VALUE);
    }


    // Getters and Setters
    public Node<T> getParent(){
        return parent;
    }

    public void setParent(Node<T> parent){
        this.parent=parent;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getMiddleChild() {
        return middleChild;
    }

    public void setMiddleChild(Node<T> middleChild) {
        this.middleChild = middleChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public int getKey(){return this.key;}

    public void setKey(int key){this.key=key;}

    public T getValue(){return this.value;}

    public void setValue(T key){this.value=key;}




}
