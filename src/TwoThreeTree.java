public abstract class TwoThreeTree<T> {
    private Node<T> root; //Savir

    public TwoThreeTree(Node<T> root){
        this.root = root;
    }
    public TwoThreeTree(){
        this(null);
    }
    public Node<T> getRoot(){
        return this.root;
    }
    public abstract void Insert(Node<T> Node);
    public abstract void Delete(Node<T> Node);
    public abstract void UpdateNode(Node<T> Node);
    public abstract Node<T> Search(Node<T> Node);
}
