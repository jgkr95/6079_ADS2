import java.util.Scanner;
/**
 * Class for solution.
 */
class Solution {
    /**
     * Constructs the object.
     */
    protected Solution() {

    }
    /**
     * main method to handle the input.
     * Time complexity : O(V).
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices = sc.nextInt();
        int edges = sc.nextInt();
        sc.nextLine();
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(vertices);
        while (sc.hasNextLine()) {
            String[] tokens = sc.nextLine().split(" ");
            Edge e = new Edge(Integer.parseInt(tokens[0]),
                              Integer.parseInt(tokens[1]),
                               Double.parseDouble(tokens[2]));
            ewg.addEdge(e);
        }
        KruskalMST mst = new KruskalMST(ewg);
        System.out.format("%.5f", mst.weight());
    }
}

