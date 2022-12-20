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
    public void Insert(Node<T> Node){} //TODO - Shachar
    public void Delete(Node<T> Node){} //TODO - Shachar
    public void UpdateNode(Node<T> Node){} //TODO - Ilan
    public Node<T> Search(int key){return null;} //TODO - Build and check if needed other types of search. Ilan
}
