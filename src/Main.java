import java.util.*;
import java.lang.AssertionError;

public class Main {
    public static void main(String[] args) {

        Node<Faculty> testNode_a = new Node<Faculty>(null, null, null,
                null, new Faculty(4, "a"), 4, 4);
        Node<Faculty> testNode_b = new Node<Faculty>(null, null, null,
                null, new Faculty(2, "b"), 2, 2);
        Node<Faculty> testNode_c = new Node<Faculty>(null, null, null,
                null, new Faculty(5, "c"), 5, 5);
        Node<Faculty> testNode_d = new Node<Faculty>(null, null, null,
                null, new Faculty(1, "d"), 1, 1);
        Node<Faculty> testNode_e = new Node<Faculty>(null, null, null,
                null, new Faculty(6, "e"), 6, 6);
        Node<Faculty> testNode_f = new Node<Faculty>(null, null, null,
                null, new Faculty(3, "f"), 3, 3);

        /** OUR TESTS START HERE **/
        TwoThreeTree testTree = new TwoThreeTree();
        testTree.Insert(testTree.getRoot(), testNode_a); //faculty "a" has id 4
        testTree.Insert(testTree.getRoot(), testNode_b); //faculty "b" has id 2
        testTree.Insert(testTree.getRoot(), testNode_c); //faculty "c" has id 5
        testTree.Insert(testTree.getRoot(), testNode_d); //faculty "d" has id 1
        testTree.Insert(testTree.getRoot(), testNode_e); //faculty "e" has id 6
        testTree.Insert(testTree.getRoot(), testNode_f); //faculty "f" has id 3
        testTree.printTree();
        System.out.println();

        // search for nodes in edges and in middle
        testTree.Search(testTree.getRoot(),testNode_d);
        testTree.Search(testTree.getRoot(),testNode_f);
        // search for 6 while it exists
        testTree.Search(testTree.getRoot(),testNode_e);
        testTree.Delete(testNode_e); // removing 6
        testTree.printTree();
        System.out.println();
        // search for 6 while it doesn't exist
        testTree.Search(testTree.getRoot(),testNode_e);

        testTree.Delete(testNode_d); // removing 1
        testTree.printTree();
        System.out.println();

        testTree.Delete(testNode_f); // removing 3
        testTree.printTree();
        System.out.println();


        Node<Faculty> testNode_aH = new Node<Faculty>(null, null, null,
                null, new Faculty(4, "a"), 10, 4);
        Node<Faculty> testNode_bH = new Node<Faculty>(null, null, null,
                null, new Faculty(2, "b"), 12, 2);
        Node<Faculty> testNode_cH = new Node<Faculty>(null, null, null,
                null, new Faculty(5, "c"), 8, 5);
        Node<Faculty> testNode_dH = new Node<Faculty>(null, null, null,
                null, new Faculty(1, "d"), 10, 1);
        Node<Faculty> testNode_eH = new Node<Faculty>(null, null, null,
                null, new Faculty(6, "e"), 8, 6);
        Node<Faculty> testNode_fH = new Node<Faculty>(null, null, null,
                null, new Faculty(3, "f"), 5, 3);


        DynamicHeap testHeap = new DynamicHeap();
        testHeap.heapInsert(testNode_aH); //faculty "a" has 10 points
        testHeap.heapInsert(testNode_bH); //faculty "b" has 12 points. They win the tournament
        testHeap.heapInsert(testNode_cH); //faculty "c" has 8 points
        testHeap.heapInsert(testNode_dH); //faculty "d" has 10 points
        testHeap.heapInsert(testNode_eH); //faculty "e" has 8 points
        testHeap.heapInsert(testNode_fH); //faculty "f" has 5 points


        testHeap.heapIncreaseKey(6, testNode_fH); // draw between f and c, f still last
        testHeap.heapIncreaseKey(11, testNode_eH); // draw between f and c, bumps c above e


        /** OUR TESTS ENDS HERE **/

        /** Initializing the tournament **/
        TechnionTournament tournament = new TechnionTournament();
        tournament.init();
        /** End of initializing the Tournament **/

        /** Adding faculties to the Tournament **/
        Map<Integer, String> faculties = new HashMap<>();
        faculties.put(1, "CS");
        faculties.put(2, "EE");
        faculties.put(3, "IE");
        faculties.put(4, "BME");

        for (Map.Entry<Integer, String> f : faculties.entrySet()) {
            Faculty faculty = new Faculty(f.getKey(), f.getValue());
            tournament.addFacultyToTournament(faculty);
        }

        /** Adding players to the Tournament **/
        Player player = new Player(0, "");
        String[] names = {"Asil", "Yuval", "Noga", "Adam", "Yuval", "Ziyech", "Moshe", "Amit", "Amir", "Omer", "Maroon", "Ido"};
        int[] faculties_id = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6};
        for (int i = 0; i < names.length; i++) {
            player.setId(i);
            player.setName(names[i]);
            tournament.addPlayerToFaculty(faculties_id[i], player);
        }


        /** Playing some games between faculties **/
        ArrayList<Integer> home_faculty_goals = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            home_faculty_goals.add(1);
        }

        ArrayList<Integer> away_faculty_goals = new ArrayList<>();
        away_faculty_goals.add(5);
        away_faculty_goals.add(6);

        tournament.playGame(1, 3, 1, home_faculty_goals, away_faculty_goals);
        tournament.getTopScorer(player);
        boolean expression = player.getId() == 1 && player.getName().equals("Asil");
        Assert(expression);

        home_faculty_goals.clear();
        away_faculty_goals.clear();

        home_faculty_goals.add(6);
        home_faculty_goals.add(6);
        away_faculty_goals.add(7);
        away_faculty_goals.add(8);

        tournament.playGame(3, 4, 1, home_faculty_goals, away_faculty_goals);
        tournament.getTopScorer(player);
        expression = player.getId() == 1 && player.getName().equals("Asil");
        Assert(expression);

        /** Removing Teams from Tournament and getting the best scorers **/
        tournament.removeFacultyFromTournament(2);

        ArrayList<Player> scorers = new ArrayList<>();
        tournament.getTopKScorers(scorers, 2, true);

        expression = scorers.get(0).getId() == 1 && scorers.get(0).getName().equals("Asil");
        Assert(expression);
        expression = scorers.get(1).getId() == 6 && scorers.get(1).getName().equals("Ziyech");
        Assert(expression);

        tournament.getTopScorerInFaculty(1, player);
        expression = player.getId() == 1;
        Assert(expression);


        home_faculty_goals.clear();
        away_faculty_goals.clear();
        tournament.playGame(4, 1, 0, home_faculty_goals, away_faculty_goals);

        tournament.removePlayerFromFaculty(1, 1);
        tournament.getTopScorer(player);

        expression = player.getId() == 1 && player.getName().equals("Asil");
        Assert(expression);

        ArrayList<Faculty> top_faculties = new ArrayList<>();
        tournament.getTopKFaculties(top_faculties, 2, false);

        /** pay attention that until now we have current standing **/
        /*
           ____________________________________
           || Team_id || Team_name || Points ||
           ||_________||___________||________||
           ||    1    ||    CS     ||    4   ||
           ||    3    ||    IE     ||    3   ||
           ||    4    ||    CE     ||    1   ||
           ||_________||___________||________||

         */

        expression = top_faculties.get(0).getId() == 1 && top_faculties.get(0).getName().equals("CS");
        Assert(expression);
        expression = top_faculties.get(1).getId() == 3 && top_faculties.get(1).getName().equals("IE");
        Assert(expression);

        /** Get the winner faculty **/
        Faculty faculty = new Faculty(0, "");
        tournament.getTheWinner(faculty);
        expression = faculty.getId() == 1 && faculty.getName().equals("CS");
        Assert(expression);

        System.out.println("Congratulations You Have passed the Test ");
    }

    public static void Assert(boolean expression) {
        if (!expression) {
            throw new AssertionError();
        }
    }
}