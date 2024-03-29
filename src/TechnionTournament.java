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


    /**
     * Create three trees with three nodes in each - O(1)
     */
    @Override
    public void init() {
        this.facultyTree = new TwoThreeTree<Faculty>(true);
        this.facultyPoints = new TwoThreeTree<Faculty>(false);
        this.playersTree = new TwoThreeTree<Player>(false);
    }

    /**
     * create two different nodes for facultyTree(by ID) and facultyPoints(by points) - O(1)
     * check if Points tree is empty - O(1)
     * Insert to trees - O(log(n))
     * update LL if needed - O(1)
     * create players array - O(1)
     *
     * @param faculty
     */
    @Override
    public void addFacultyToTournament(Faculty faculty) {
        Node<Faculty> facultyNPoints= new Node<>(new Faculty(faculty.getId(),faculty.getName()) ,
                0,faculty.getId());
        Node<Faculty> facultyN= new Node<>(new Faculty(faculty.getId(),faculty.getName()) ,
                faculty.getId(),0);
        if(this.facultyPoints.isEmpty()){ this.facultyLL = facultyNPoints;} // if empty start the LL
        this.facultyTree.Insert(facultyN,false);
        this.facultyPoints.Insert(facultyNPoints,true);
        // update LL pointer if needed
        if(facultyLL.getLinked() != null){
            facultyLL = facultyLL.getLinked();
        }
        facultyN.playersArray = new Node[11];
    }

    /**
     * find faculty by searching the trees - O(log(n))
     * update LL - O(1)
     * Delete faculty - O(log(n))
     * @param faculty_id
     */
    @Override
    public void removeFacultyFromTournament(int faculty_id) {
        Node<Faculty> removed = this.facultyTree.Search(faculty_id, this.facultyTree.getRoot(),true);
        Node<Faculty> removedP = this.facultyPoints.searchBySecond(removed.getSecondKey(),removed.getKey(),
                this.facultyPoints.getRoot());
        // update LL pointer if needed
        if(facultyLL == removed){
            facultyLL = removed.getPrevLinked();
        }
        this.facultyPoints.Delete(removedP,true);
        this.facultyTree.Delete(removed,false);
    }


    /**
     * Search the tree for the faculty - O(log(n))
     * add player to player's array - O(1)
     * check emptiness of players tree - O(1)
     * Insert player - O(log(m))
     *
     * @param faculty_id
     * @param player
     */
    @Override
    public void addPlayerToFaculty(int faculty_id,Player player) {
        if(this.facultyTree.Search(faculty_id,this.facultyTree.getRoot(),true) == null){return;}
        Node<Faculty> faculty = this.facultyTree.Search(faculty_id, this.facultyTree.getRoot(),true);
        Node<Player> playerNode = new Node<Player>(new Player(player.getId(),player.getName()),
                0, player.getId()); // the goals num is 0
        faculty.addPlayer(playerNode);
        if(this.playersTree.isEmpty()){
            this.playerLL = playerNode;
        }
        this.playersTree.Insert(playerNode,true);
        // update LL pointer if needed
        if(playerLL.getLinked() != null){
            playerLL = playerLL.getLinked();
        }
    }

    /**
     * Search for faculty - O(log(n))
     * remove player from his faculty's array - O(1)
     * @param faculty_id
     * @param player_id
     */
    @Override
    public void removePlayerFromFaculty(int faculty_id, int player_id) {
        Node<Faculty> faculty = this.facultyTree.Search(faculty_id, this.facultyTree.getRoot(),true);
        faculty.removePlayer(player_id);
    }

    /**
     * look for player in array - O(1)
     * Delete player from tree - O(log(m))
     * Insert back after updating goal - O(log(m))
     * update LL if needed - O(1)
     * @param playerID
     * @param array
     */
    private void playerGoal(int playerID, Node[] array){
        Node<Player> temp = null;
        for(int i = 0; i < 11; i++){
            if(array[i]!=null) {
                if (array[i].getSecondKey() == playerID) {
                    temp = array[i];
                }
            }
        }
        if (temp == null){
            return;
        }
        // update LL if needed
        if(playerLL==temp){
            playerLL = temp.getPrevLinked();
        }
        playersTree.Delete(temp,true);
        temp.setKey(temp.getKey()+1);
        playersTree.Insert(temp,true);
        // update LL pointer if needed
        if(playerLL.getLinked() != null){
            playerLL = playerLL.getLinked();
        }
    }


    /**
     * searching for the faculties - O(log(n))
     * calling playerGoal k times - O(k*log(m))
     * Deleting faculties - O(log(n))
     * updating the faculties points and Inserting back - O(log(n))
     * updates to LL - O(1)
     * @param faculty_id1
     * @param faculty_id2
     * @param winner
     * @param faculty1_goals
     * @param faculty2_goals
     */
    @Override
    public void playGame(int faculty_id1, int faculty_id2, int winner,
                         ArrayList<Integer> faculty1_goals, ArrayList<Integer> faculty2_goals) {

        // initialize
        Node<Faculty> home = this.facultyTree.Search(faculty_id1, this.facultyTree.getRoot(),true);
        Node<Faculty> away = this.facultyTree.Search(faculty_id2, this.facultyTree.getRoot(),true);
        Node<Faculty> homeP = facultyPoints.searchBySecond(home.getSecondKey(),home.getKey(),this.facultyPoints.getRoot());
        Node<Faculty> awayP = facultyPoints.searchBySecond(away.getSecondKey(),away.getKey(),this.facultyPoints.getRoot());
        Node<Faculty> winnerFaculty =  null;
        Node<Faculty> winnerFacultyP = null;
        if(winner == 2) {winnerFaculty = away; winnerFacultyP = awayP;}
        if(winner == 1) {winnerFaculty = home; winnerFacultyP = homeP;}

        // update players goals
        int numGoals = faculty1_goals.size();
        for (int i=0; i<numGoals; i++){
            playerGoal(faculty1_goals.get(i),home.playersArray);
        }

        numGoals = faculty2_goals.size();
        for (int i=0; i<numGoals; i++){
            playerGoal(faculty2_goals.get(i),away.playersArray);
        }

        // update faculty points
        if(winner!=0){
            // update LL pointer if needed
            if(facultyLL == winnerFacultyP){
                facultyLL = facultyLL.getPrevLinked();
            }
            facultyPoints.Delete(winnerFacultyP,true);
            // adding 3 points to winner and updating id tree
            winnerFacultyP.setKey(winnerFacultyP.getKey() + 3);
            winnerFaculty.setSecondKey(winnerFacultyP.getKey());
            facultyPoints.Insert(winnerFacultyP,true);
            // update LL pointer if needed
            if(facultyLL.getLinked() != null){
                facultyLL = facultyLL.getLinked();
            }
        }
        else{
            // update LL pointer if needed
            if(facultyLL == away){
                facultyLL = facultyLL.getPrevLinked();
            }
            if(facultyLL == home){
                facultyLL = facultyLL.getPrevLinked();
            }
            facultyPoints.Delete(awayP,true);
            facultyPoints.Delete(homeP,true);
            // update num of points both in points tree and id's tree
            awayP.setKey(awayP.getKey() + 1);
            away.setSecondKey(awayP.getKey());
            homeP.setKey(homeP.getKey()+1);
            home.setSecondKey(homeP.getKey());
            facultyPoints.Insert(homeP,true);
            facultyPoints.Insert(awayP,true);
            // update LL pointer if needed
            while(facultyLL.getLinked() != null){
                facultyLL = facultyLL.getLinked();
            }
        }

    }

    /**
     * LL points at top scorer - getting him and updating is O(1)
     * @param player
     */
    @Override
    public void getTopScorer(Player player) {
        player.setId(this.playerLL.getSecondKey());
        player.setName(this.playerLL.getValue().getName());
    }

    /**
     * search for faculty - O(log(n))
     * get players array - O(1)
     * go through the array(which is constant sized - 11) and find top scorer - O(1)
     *
     * @param faculty_id
     * @param player
     */
    @Override
    public void getTopScorerInFaculty(int faculty_id, Player player) {
        Node<Faculty> found = this.facultyTree.Search(faculty_id, this.facultyTree.getRoot(), true);
        Node<Player>[] playersArray = found.getPlayersArray();
        Node<Player> maybeWinner = null;
        int maxgoals = -1;
        for(int i = 0; i < 11; i++){
            if(playersArray[i] != null) {
                if (playersArray[i].getKey() > maxgoals) {
                    maybeWinner = playersArray[i];
                    maxgoals = playersArray[i].getKey();
                } else if (playersArray[i].getKey() == maxgoals && (playersArray[i].getSecondKey() < maybeWinner.getSecondKey())) {
                    maybeWinner = playersArray[i];
                }
            }
        }
        player.setId(maybeWinner.getSecondKey());
        player.setName(maybeWinner.getValue().getName());
    }

    /**
     * insert into 'faculties' the top k faculties in a given order.
     * insert to array first and then to 'faculties' to resolve order issues.
     * going over k faculties from LL is - O(k)
     * @param faculties
     * @param k
     * @param ascending
     */
    @Override
    public void getTopKFaculties(ArrayList<Faculty> faculties, int k, boolean ascending) {
        Node<Faculty> temp = this.facultyLL;
        Faculty[] array = new Faculty[k];
        // if ascending = True - i goes from 0 to k
        int i=0;
        int stop = k;
        int add = 1;
        // if ascending = False - i goes from k to 0
        if (ascending){i = k-1; stop = -1; add = -1;}
        while (i!=stop) {
            if(temp != null) {
                array[i] = temp.getValue();
                temp = temp.getPrevLinked();
            }
            i += add;
        }
        for (int j=0;j<k;j++) {
            faculties.add(array[j]);
        }
    }

    /**
     * insert into 'players' the top k players in a given order.
     * insert to array first and then to 'players' to resolve order issues.
     * going over k players from LL is - O(k)
     * @param players
     * @param k
     * @param ascending
     */
    @Override
    public void getTopKScorers(ArrayList<Player> players, int k, boolean ascending) {
        Node<Player> temp = this.playerLL;
        Player[] array = new Player[k];
        // if ascending = True - i goes from 0 to k
        int i=0;
        int stop =k;
        int add = 1;
        // if ascending = False - i goes from k to 0
        if (ascending){i = k-1; stop = -1; add = -1;}
        while (i!=stop) {
            if(temp != null) {
                array[i] = temp.getValue();
                temp = temp.getPrevLinked();
            }
            i += add;
        }
        for (int j=0;j<k;j++) {
            players.add(array[j]);
        }
    }

    /**
     * faculties LL points at the winner so getting them is O(1)
     * @param faculty
     */
    @Override
    public void getTheWinner(Faculty faculty) {
        faculty.setId(this.facultyLL.getSecondKey());
        faculty.setName(this.facultyLL.getValue().getName());
    }
}
