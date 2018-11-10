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
           String[] path = scan.nextLine().split(" ");
      DijkstraUndirectedSP d = new DijkstraUndirectedSP(
        ewg, Integer.parseInt(path[0]));
      DijkstraUndirectedSP d2 = new DijkstraUndirectedSP(
        ewg, Integer.parseInt(path[1]));
      String s = "";
      if (d.hasPathTo(Integer.parseInt(path[2]))) {
        int i = 0;
        System.out.println(d.distTo(Integer.parseInt(path[1]))
         + d2.distTo(Integer.parseInt(path[2])));
        for (Integer e : d.pathTo(Integer.parseInt(path[1]))) {
          s += e + " ";
        }
        for (Integer e : d2.pathTo(Integer.parseInt(path[2]))) {
          if (i != 0) {
            s += e + " ";
          }
          i += 1;
        }
        System.out.println(s.substring(0, s.length() - 1));
      } else {
        System.out.println("No Path Found.");
      }


            break;

        default:
            break;
        }

    }
}