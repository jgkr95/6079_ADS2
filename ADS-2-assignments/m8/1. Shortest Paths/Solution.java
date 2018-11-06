import java.util.Scanner;
import java.util.HashMap;
/**
 *class for solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
    }
    /**
     * Main method to handle the input.
     *time complexity is O(E + V)
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> map
        = new HashMap<String, Integer>();
        String[] tokens = sc.nextLine().split(" ");
        int edges = Integer.parseInt(tokens[1]);
        String[] vertices = sc.nextLine().split(" ");
        for (int i = 0; i < vertices.length; i++) {
            map.put(vertices[i], i);
        }
        Edge edge;
        EdgeWeightedGraph ewg
        = new EdgeWeightedGraph(vertices.length);
        for (int i = 0; i < edges; i++) {
            String[] directPath = sc.nextLine().split(" ");
            edge = new Edge(map.get(directPath[0]),
                               map.get(directPath[1]),
                               Double.parseDouble(directPath[2]));
            ewg.addEdge(edge);
        }
        int queries = Integer.parseInt(sc.nextLine());
        DijkstraSP d;
        for (int i = 0; i < queries; i++) {
            String[] check = sc.nextLine().split(" ");
            int source = map.get(check[0]);
            d = new DijkstraSP(ewg, source);
            System.out.println((int) d.distance(map.get(check[1])));
        }
    }
}
