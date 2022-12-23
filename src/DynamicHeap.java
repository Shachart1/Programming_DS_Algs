public class DynamicHeap<T> { //Max Heap
    private Node<T> root; //Savir
    private int heapSize;
    /** Assuming throughout the right child is null always **/


    public DynamicHeap(Node<T> root){
        this.root = root;
        heapSize=1; //TODO - heap size can be bigger if root has children
    }
    public DynamicHeap(){
        this(null);
        this.heapSize = 0;
    }
    public Node<T> getRoot(){
        return this.root;
    }


    /**
     * switch node and max
     * @param node
     * @param max
     */
    private void switchNodes(Node<T> node, Node<T> max){
        max.parent = node.parent;
        node.parent = max;
        if(max == node.leftChild) { // switching left child and node
            Node<T> temp = node.middleChild;
            temp.parent = max;
            node.leftChild = max.leftChild;
            node.middleChild = max.middleChild;
            max.leftChild = node;
            max.middleChild = temp;
        }
        else{ // switching middle child and node
            Node<T> temp = node.leftChild;
            temp.parent = max;
            node.middleChild = max.middleChild;
            node.leftChild = max.leftChild;
            max.middleChild = node;
            max.leftChild = temp;
        }
    }

    /**
     * assuming the subtrees rooted in node.leftChild and node.middleChild are heaps we will sort
     * the three nodes(with the parameter node) so that the max is the new parent and now the subtree
     * rooted in the parent is a heap.
     * Will recursively activate heapify up the tree to make sure the entire tree is still a heap
     * @param node
     */
    public void heapify(Node<T> node){
        if(node == null){return;} // Switched the root and got his parent - null
        Node<T> max;
        // saving max out of node and its children
        if(node.middleChild != null && node.key < node.middleChild.key) max = node.middleChild;
        else max = node;
        if(node.leftChild != null && max.key < node.leftChild.key) max = node.leftChild;

        if (max == node) return;
        switchNodes(node,max);
        heapify(max.parent);
    } //TODO - tests


    public void heapIncreaseKey(int k, Node<T> node){
        if(k <= node.key){return;} //TODO - add exception here
        node.key = k;
        heapify(node.parent); // need to make sure this is still a heap
    } //TODO - tests


    public Node<T> extractMin(){return null;} //TODO - Ilan
    public void heapInsert(Node<T> x){} //TODO - Ilan
}
