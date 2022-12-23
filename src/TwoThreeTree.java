public class TwoThreeTree<T> {
    private Node<T> root; //Savir

    /**
     * using the inf and -inf "towers" to save checks
     * @param root
     */
    public TwoThreeTree(Node<T> root){
        this.root = root;
        this.root.leftChild = new Node<>(root,null,Integer.MIN_VALUE);
        this.root.middleChild = new Node<>(root,null,Integer.MAX_VALUE);
        this.root.key = Integer.MAX_VALUE;
    }
    public TwoThreeTree(){
        this(new Node<>(null,null,Integer.MAX_VALUE));
    }
    public Node<T> getRoot(){
        return this.root;
    }

    /**
     * update the key of current parent to be the max key of his children
     * @param parent
     */
    private void UpdateKey(Node<T> parent){
        if (parent.middleChild == null){parent.key = parent.leftChild.key; return;} //assuming we call the function not on a leaf
        if(parent.rightChild == null){parent.key = parent.middleChild.key; return;}
        parent.key = parent.rightChild.key;
    }


    /**
     * set the children received as parameters in order
     * @param parent
     * @param left
     * @param middle
     * @param right
     */
    private void SetChildren(Node<T> parent, Node<T> left, Node<T> middle, Node<T> right){
        parent.leftChild = left;
        parent.middleChild = middle;
        parent.rightChild = right;
        if(left != null) left.parent = parent;
        if(middle != null) middle.parent = parent;
        if(right != null) right.parent = parent;
        UpdateKey(parent);
    }


    /**
     * activated when a split is needed.
     * creates a new parent node and splits the children between the two parents
     * @param parent
     * @param newChild
     * @return new parent node
     */
    private Node<T> Split(Node<T> parent, Node<T> newChild){
        Node<T> splitNode = new Node<>();
        if (newChild.key<parent.leftChild.key){ // need to insert child to leftChild and shift everyone right
            SetChildren(splitNode,parent.middleChild,parent.rightChild,null);
            SetChildren(parent,newChild,parent.leftChild,null);
        }
        else if(newChild.key<parent.middleChild.key){
            SetChildren(splitNode,parent.middleChild,parent.rightChild,null);
            SetChildren(parent,parent.leftChild,newChild,null);
        }
        else if(newChild.key < parent.rightChild.key){ //if we are in the split then the parent has three children
            SetChildren(splitNode,newChild,parent.rightChild,null);
            SetChildren(parent,parent.leftChild,parent.middleChild,null);
        }
        else{
            SetChildren(splitNode,parent.rightChild,newChild,null);
            SetChildren(parent,parent.leftChild,parent.middleChild,null);
        }
        return splitNode;
    }


    /**
     * activated after the node's destination is found
     * inserts the node or calls split if the current parent node is full.
     * @param parent
     * @param newChild
     * @return new node created after spilt or null if there was no split
     */
    private Node<T> Insert_And_Split(Node<T> parent, Node<T> newChild){
        if (parent.parent!=null){
            parent=parent.parent;
        }
        if(parent.leftChild == null) { // We are in the root and there are no children yet
            SetChildren(parent,newChild,null,null);
            return null;
        }
        if(parent.middleChild == null){ // Found one leaf under the parent - We can insert without split
            if(parent.leftChild.key < newChild.key) {
                SetChildren(parent,parent.leftChild,newChild,null);
                return null;
            }
            SetChildren(parent,newChild,parent.leftChild,null);
            return null;
        }
        // No condition was met so far - Need a split since tree is full
        if(parent.rightChild == null){
            if(parent.middleChild.key < newChild.key){
                SetChildren(parent,parent.leftChild,parent.middleChild,newChild);
            }
            else if(parent.leftChild.key < newChild.key){
                SetChildren(parent,parent.leftChild,newChild,parent.middleChild);
            }
            else {SetChildren(parent,newChild,parent.leftChild,parent.middleChild);}
            return null;
        }
        return Split(parent,newChild);
        }

    /**
     * finds the place in the tree where node should be inserted
     * calls Insert_And_Split() to insert the node there
     * updates the keys throughout the tree after insertion
     * @param root
     * @param node
     */
    public void Insert(Node<T> root, Node<T> node){
        while(root.leftChild != null){ //stop when found a leaf
            if(node.key < root.leftChild.key){
                root=root.leftChild;
            }
            else if(node.key < root.middleChild.key){
                root=root.middleChild;
            }
            else{root = root.rightChild;}
        }

        Node<T> parentSave = root.parent;
        Node<T> newNode = Insert_And_Split(root,node); // found
        while(parentSave != this.root){ //update the keys of the tree
            if(newNode != null){
                newNode = Insert_And_Split(parentSave,newNode);
            }
            UpdateKey(parentSave);
            parentSave = parentSave.parent;
        }
        if(newNode != null){
            Node<T> newRoot = new Node<>(null,null,0);
            SetChildren(newRoot,parentSave,newNode,null);
            this.root = newRoot;
        }

    }


    public void Delete(Node<T> Node){} //TODO - Shachar
    public void UpdateNode(Node<T> Node){} //TODO - Ilan
    public Node<T> Search(int key){return null;} //TODO - Build and check if needed other types of search. Ilan




    /** Prints funcs for testings **/

    private void recursivePrint(Node<T> node){
        if (node.leftChild == null){System.out.print(node.key + " "); return;}
        recursivePrint(node.leftChild);
        if(node.middleChild !=null){recursivePrint(node.middleChild);}
        if(node.rightChild !=null){recursivePrint(node.rightChild);}
    }
    public void printTree(){
        recursivePrint(this.root);
    }
}
