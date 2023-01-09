import java.util.ArrayList;
import java.util.LinkedList;

public class TechnionTournament implements Tournament{
    /**
     * facultyTree - TwoThreeTree of faculties sorted by ID's. each node has a max DynamicHeap
     * of that faculty's player sorted by goals and a TwoThreeTree of the players sorted by ID's.
     *
     * goalScorers - Max DynamicHeap of all players(including free agents) sorted by goals.
     *
     * points - Max DynamicHeap of all faculties sorted by points.
     */
    private TwoThreeTree<Faculty> facultyTree; // TODO - in faculty level save player 2-3 tree and player LL
    private LinkedList<Node<Faculty>> facultyLL; // faculty LL pointed at by the leaves of facultyTree
    private TwoThreeTree<Player> playersTree; // all-players tree sorted by goals,ID(for tiebreaker)
    private LinkedList<Node<Faculty>> playerLL; // player LL pointed at by the leaves of playerTree


    TechnionTournament(){};

    @Override
    public void init() {
        this.facultyTree = new TwoThreeTree<Faculty>();
    }

    @Override
    public void addFacultyToTournament(Faculty faculty) {

    }

    @Override
    public void removeFacultyFromTournament(int faculty_id) {

    }

    @Override
    public void addPlayerToFaculty(int faculty_id,Player player) {

    } //TODO - ILAN

    @Override
    public void removePlayerFromFaculty(int faculty_id, int player_id) {

    } //TODO -ILAN

    @Override
    public void playGame(int faculty_id1, int faculty_id2, int winner,
                         ArrayList<Integer> faculty1_goals, ArrayList<Integer> faculty2_goals) {

    } //TODO - together Monday Mivney

    @Override
    public void getTopScorer(Player player) {

    }

    @Override
    public void getTopScorerInFaculty(int faculty_id, Player player) {

    }

    @Override
    public void getTopKFaculties(ArrayList<Faculty> faculties, int k, boolean ascending) {

    } //TODO - SHACHAR

    @Override
    public void getTopKScorers(ArrayList<Player> players, int k, boolean ascending) {

    } //TODO - SHACHAR

    @Override
    public void getTheWinner(Faculty faculty) {

    }

    ///TODO - add below your own variables and methods
}
