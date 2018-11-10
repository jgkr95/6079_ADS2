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
            DijkstraUndirectedSP d = new DijkstraUndirectedSP(ewg, Integer.parseInt(paths[0]));
            if (!d.hasPathTo(Integer.parseInt(paths[1]))) {
                System.out.println("No Path Found.");
            } else {
                System.out.format("%.1f", d.distTo(Integer.parseInt(paths[1])));
            }
            break;

        case "ViaPaths":
            // Handle the case of ViaPaths, where three integers are given.
            // First is the source and second is the via is the one where path should pass throuh.
            // third is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] tokens2 = scan.nextLine().split(" ");
      DijkstraUndirectedSP sp1 = new DijkstraUndirectedSP(
        ewg, Integer.parseInt(tokens2[0]));
      DijkstraUndirectedSP sp2 = new DijkstraUndirectedSP(
        ewg, Integer.parseInt(tokens2[1]));
      String str = "";
      if (sp1.hasPathTo(Integer.parseInt(tokens2[2]))) {
        int i = 0;
        System.out.println(sp1.distTo(Integer.parseInt(tokens2[1]))
         + sp2.distTo(Integer.parseInt(tokens2[2])));
        for (Integer e : sp1.pathTo(Integer.parseInt(tokens2[1]))) {
          str += e + " ";
        }
        for (Integer e : sp2.pathTo(Integer.parseInt(tokens2[2]))) {
          if (i != 0) {
            str += e + " ";
          }
          i++;
        }
        System.out.println(str.substring(0, str.length() - 1));
      } else {
        System.out.println("No Path Found.");
      }
      break;
        default:
            break;
        }

    }
}