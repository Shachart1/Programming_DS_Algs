public abstract class DynamicHeap<T> { //Max Heap
    private Node<T> root; //Savir
    private int heapSize;

    public DynamicHeap(Node<T> root){
        this.root = root;
    }
    public DynamicHeap(){
        this(null);
    }
    public Node<T> getRoot(){
        return this.root;
    }
    public abstract void heapify(Node<T> x);
    public abstract void heapIncreaseKey(int k, Node<T> x);
    public abstract Node<T> extractMin();
    public abstract void heapInsert(Node<T> x);
}
