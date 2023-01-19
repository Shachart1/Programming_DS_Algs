public class Node<T> {
    Node<T> parent;
    Node<T> leftChild;
    Node<T> middleChild;
    Node<T> rightChild;
    int key;
    int secondKey;
    T value; // Player or Faculty
    Node<T> linkedNode;
    Node<T> prevLinked;
    Node<Player>[] playersArray = null;

    // Constructors
    public Node(Node<T> parent,Node<T> leftChild,Node<T> middleChild,Node<T> rightChild, T value, int key, int secondKey){
        this.parent = parent;
        this.leftChild = leftChild;
        this.middleChild = middleChild;
        this.rightChild = rightChild; //In TwoThreeTree always null
        this.value = value;
        this.key = key;
        this.secondKey = secondKey;
        Node<T> linkedNode = new Node<>(value,key,secondKey);
    }

    public Node(Node<T> parent, Node<T> leftChild,Node<T> middleChild,T value, int key, int secondKey){
        this(parent,leftChild,middleChild,null, value, key,secondKey);
    }
    public Node(Node<T> parent,Node<T> leftChild,T value, int key, int secondKey){
        this(null,parent,leftChild,null,value,key, secondKey);
    }
    public Node(Node<T> parent,T value, int key, int secondKey){
        this(parent,null,null,null,value,key,secondKey);
    }
    public Node(T value, int key, int secondKey){
        this(null,null,null,null,value,key,secondKey);
    }
    public Node(int key, int secondKey){
        this(null,null,null,null,null,key,secondKey);
    }
    public Node() {
        this(null,null, null, null,null,Integer.MIN_VALUE,Integer.MIN_VALUE);
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

    public int getSecondKey(){return this.secondKey;}

    public void setSecondKey(int key){this.secondKey=key;}

    public T getValue(){return this.value;}

    public void setValue(T key){this.value=key;}

    public Node<T> getLinked(){return this.linkedNode;}

    public void setLinked(Node<T> newLink){this.linkedNode = newLink;}

    public Node<T> getPrevLinked(){return this.prevLinked;}

    public void setPrevLinked(Node<T> node){this.prevLinked=node;}

    /**
     * return the greater in compare by two keys. if equal return 'other'
     * @param other - given node to compare to
     * @return
     */
    public Node<T> compareKeys(Node<T> other){
        if(this.key > other.getKey()){return this;}
        else if(this.key < other.key){return other;}
        else if(this.secondKey > other.secondKey){return this;}
        return other;
    }

}
