public class DynamicHeap<T> { //Max Heap
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
    public void heapify(Node<T> x){} //TODO - Shachar
    public void heapIncreaseKey(int k, Node<T> x){} //TODO - Shachar
    public Node<T> extractMin(){return null;} //TODO - Ilan
    public void heapInsert(Node<T> x){} //TODO - Ilan
}
