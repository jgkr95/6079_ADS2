import java.util.Scanner;
public class Solution {

    public static void main(String[] args) {
        // Self loops are not allowed...
        // Parallel Edges are allowed...
        // Take the Graph input here...
        Scanner scan = new Scanner(System.in);
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(vertices);
        for (int i = 0; i < edges; i++) {
            String[] tokens = scan.nextLine().split(" ");
            Edge e = new Edge(Integer.parseInt(tokens[0]),
                              Integer.parseInt(tokens[1]),
                              Double.parseDouble(tokens[2]));
            ewg.addEdge(e);
        }


        String caseToGo = scan.nextLine();
        switch (caseToGo) {
        case "Graph":
            //Print the Graph Object.
            System.out.println(ewg);
            break;

        case "DirectedPaths":
            // Handle the case of DirectedPaths, where two integers are given.
            // First is the source and second is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] paths = scan.nextLine().split(" ");
            DijkstraSP d = new DijkstraSP(ewg, Integer.parseInt(paths[0]));
            // if ((int) d.distance(Integer.parseInt(paths[1])) == 0) {
            //     System.out.println("No Path Found.");
            // } else {
                System.out.format("%.1f",d.distance(Integer.parseInt(paths[1])));
                 break;

        case "ViaPaths":
            // Handle the case of ViaPaths, where three integers are given.
            // First is the source and second is the via is the one where path should pass throuh.
            // third is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            break;

        default:
            break;
        }

    }
}