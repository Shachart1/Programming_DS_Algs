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
        else if(node.middleChild != null && node.key == node.middleChild.key &&
                node.secondKey < node.middleChild.secondKey){
            max = node.middleChild;
        }
        else max = node;
        if(node.leftChild != null && max.key < node.leftChild.key) max = node.leftChild;

        if (max == node) return;
        switchNodes(node,max);
        heapify(max.parent);
    } //TODO - tests


    /**
     * increase the key of x(node in the tree) to k
     * @param k
     * @param node
     */
    public void heapIncreaseKey(int k, Node<T> x){
        if(k <= x.key){return;} //TODO - add exception here
        x.key = k;
        heapify(x.parent); // need to make sure this is still a heap
    } //TODO - tests

    // we should add a getindex because if we want to use value, T has to be comparable and that is a problem
    // because the faculty class for example is not comparable
    // newsize should be called with the current size + 1
    private void nextInsertionFounder(int newSize, Node<T> node, String a){
        if(node == null){return;}
        else if(node.getIndex() == Math.floor(newSize/2)) {
            if (newSize % 2 == 0) {
                nextInsertionFounder(newSize, node.getLeftChild(), a + "0");
            } else {
                nextInsertionFounder(newSize, node.getMiddleChild(), a + "1");
            }
        }
        else if(node.getIndex() > Math.floor(newSize/2)){
            nextInsertionFounder(newSize, node.getLeftChild(), a + "0");
        }
        else{nextInsertionFounder(newSize,node.getMiddleChild(), a+"1");}
        }

    private Node<T> nextInsertionParent(int newSize, Node<T> node){
        String path = "";
        nextInsertionFounder(newSize, node, path);
        int j = path.length() - 2; // (till the parent)
        int i = 0;
        String zero = "0";
        String one = "1";
        Node<T> parent = null;
        while (i <= j){
            if(path.charAt(i) == '0'){ // why it does not work?
                parent = root.getLeftChild();
            }
            else{
                parent = root.getRightChild();
            }
            i++;
        }
        return parent;
    }
// extract max?
    // increase key should have another argument
    // extractMax might need the structure of the heap
    // heapInsert - if we want to insert a node in a leaf we have to know which one is the right most leaf
    public Node<T> extractMin(){}
    public Node<T> extractMax(){

    }//TODO - Ilan it
    public void heapInsert(Node<T> x){
        if(root == null){root = x;}
        String pathToInsertion = "";
        Node<T> parent = root;
        int j;
        boolean Found = false;
        nextInsertionFounder(this.heapSize + 1, this.getRoot(), pathToInsertion);
        while(Found == false){
            pathToInsertion()

    }
    } //TODO - Ilan
}
