import java.util.ArrayList;

public class TechnionTournament implements Tournament{
    /**
     * facultyTree - TwoThreeTree of faculties sorted by ID's. each node has a max DynamicHeap
     * of that faculty's player sorted by goals and a TwoThreeTree of the players sorted by ID's.
     *
     * goalScorers - Max DynamicHeap of all players(including free agents) sorted by goals.
     *
     * points - Max DynamicHeap of all faculties sorted by points.
     */
    private TwoThreeTree<Faculty> facultyTree;
    private TwoThreeTree<Faculty> facultyPoints;
    private Node<Faculty> facultyLL; // faculty LL pointed at by the leaves of facultyPoints
    private TwoThreeTree<Player> playersTree; // all-players tree sorted by goals,ID(for tiebreaker)
    private Node<Player> playerLL; // player LL pointed at by the leaves of playerTree


    TechnionTournament(){};


    @Override
    public void init() {
        this.facultyTree = new TwoThreeTree<Faculty>();
        this.facultyPoints = new TwoThreeTree<Faculty>();
        this.playersTree = new TwoThreeTree<Player>();
    }

    @Override
    public void addFacultyToTournament(Faculty faculty) {
        Node<Faculty> facultyN= new Node<>(faculty ,0,faculty.getId());
        if(this.facultyTree.isEmpty()){ this.facultyLL = facultyN;}
        this.facultyTree.Insert(facultyN);
        this.facultyPoints.Insert(facultyN);
    }

    @Override
    public void removeFacultyFromTournament(int faculty_id) {
        Node<Faculty> removed = this.facultyTree.Search(faculty_id, this.facultyTree.getRoot(),true);
        this.facultyTree.Delete(removed);
    }


    @Override
    public void addPlayerToFaculty(int faculty_id,Player player) {
        Node<Faculty> faculty = this.facultyTree.Search(faculty_id, this.facultyTree.getRoot(),true);
        Node<Player> playerNode = new Node<Player>(player, 0, player.getId()); // the goals num is 0
        faculty.addPlayer(playerNode);
        if(this.playersTree.isEmpty()){
            this.playerLL = playerNode;
        }
        this.playersTree.Insert(playerNode);
    }

    @Override
    public void removePlayerFromFaculty(int faculty_id, int player_id) {
        Node<Faculty> faculty = this.facultyTree.Search(faculty_id, this.facultyTree.getRoot(),true);
        faculty.removePlayer(player_id);
    }

    private void playerGoal(int playerID, Node<Faculty> faculty){
        Node<Player> temp;
        temp = playersTree.Search(playerID, playersTree.getRoot(),true);
        playersTree.Delete(temp);
        temp.setKey(temp.getKey()+1);
        playersTree.Insert(temp);

        Node<Player> tempP;
        for(int j=0;j<11;j++){
            tempP = faculty.playersArray[j];
            if(playerID == tempP.getSecondKey())tempP.setKey(tempP.getKey()+1);
        }
    }


    @Override
    public void playGame(int faculty_id1, int faculty_id2, int winner,
                         ArrayList<Integer> faculty1_goals, ArrayList<Integer> faculty2_goals) {

        // initialize
        Node<Faculty> home = facultyTree.Search(faculty_id1,this.facultyTree.getRoot(),true);
        Node<Faculty> away = facultyTree.Search(faculty_id2,this.facultyTree.getRoot(),true);
        Node<Faculty> winnerFaculty =  null;
        if(winner == 2) winnerFaculty = away;
        if(winner == 1) winnerFaculty = home;

        // update players goals
        int numGoals = faculty1_goals.size();
        for (int i=0; i<numGoals; i++){
            playerGoal(faculty1_goals.get(i),home);
        }

        numGoals = faculty2_goals.size();
        for (int i=0; i<numGoals; i++){
            playerGoal(faculty2_goals.get(i),away);
        }

        // update faculty points
        if(winner!=0){
            facultyPoints.Delete(winnerFaculty);
            winnerFaculty.setKey(winnerFaculty.getKey() + 3);
            facultyPoints.Insert(winnerFaculty);
        }
        else{
            facultyPoints.Delete(away);
            facultyPoints.Delete(home);
            away.setKey(away.getKey() + 1);
            home.setKey(home.getKey()+1);
            facultyPoints.Insert(home);
            facultyPoints.Insert(away);
        }

    } //TODO - together Monday Mivney

    @Override
    public void getTopScorer(Player player) {
        player.setId(this.playerLL.getSecondKey());
        player.setName(this.playerLL.getValue().getName());
    }

    @Override
    public void getTopScorerInFaculty(int faculty_id, Player player) {
        Node<Faculty> found = this.facultyTree.Search(faculty_id, this.facultyTree.getRoot(), true);
        Node<Player>[] playersArray = found.getPlayersArray();
        Node<Player> maybeWinner = null;
        int maxgoals = -1;
        for(int i = 0; i < 11; i++){
            if(playersArray[i].getKey() > maxgoals){
                maybeWinner = playersArray[i];
                maxgoals = playersArray[i].getKey();
            }
            else if(playersArray[i].getKey() == maxgoals && (playersArray[i].getSecondKey() < maybeWinner.getSecondKey())){
                maybeWinner = playersArray[i];
            }
        }
        player.setId(maybeWinner.getSecondKey());
        player.setName(maybeWinner.getValue().getName());
    }

    /**
     * insert into 'faculties' the top k faculties in a given order.
     * @param faculties
     * @param k
     * @param ascending
     */
    @Override
    public void getTopKFaculties(ArrayList<Faculty> faculties, int k, boolean ascending) {
        Node<Faculty> temp = this.facultyLL;
        // if ascending = True - i goes from 0 to k
        int i=0;
        int stop =k;
        int add = 1;
        // if ascending = False - i goes from k to 0
        if (!ascending){i = k; stop = 0; add = -1;}
        while (i!=stop) {
            if(temp != null) {
                faculties.add(i, temp.getValue());
                temp = temp.getLinked();
            }
            i += add;
        }
    }

    @Override
    public void getTopKScorers(ArrayList<Player> players, int k, boolean ascending) {
        Node<Player> temp = this.playerLL;
        // if ascending = True - i goes from 0 to k
        int i=0;
        int stop =k;
        int add = 1;
        // if ascending = False - i goes from k to 0
        if (!ascending){i = k; stop = 0; add = -1;}
        while (i!=stop) {
            if(temp != null) {
                players.add(i, temp.getValue());
                temp = temp.getLinked();
            }
            i += add;
        }
    }
    //TODO - Think how to combine the two

    @Override
    public void getTheWinner(Faculty faculty) {
        faculty.setId(this.facultyLL.getSecondKey());
        faculty.setName(this.facultyLL.getValue().getName());
    }

    ///TODO - add below your own variables and methods
}
