
import java.util.Scanner;
import java.util.Arrays;
/**
 * Class for page rank.
 */
class PageRank {
    /**
     * page rank array.
     */
    private double[] pr;
    /**
     * prevoius pagerank values.
     */
    private double[] prevPr;
    /**
     * Incoming edges.
     */
    private Iterable<Integer> incoming;
    /**
     * Constructs the object and calculates values of pagerank array.
     * Time complexity is O(1000(V+E)).
     * @param      dg    { Digraph object }
     */
    PageRank(final Digraph dg) {
        Digraph dgh = dg;
        Digraph dgRev = new Digraph(dg.V());
        dgRev = dg.reverse();
        final int thousand = 1000;
        pr = new double[dg.V()];
        prevPr = new double[dg.V()];
        for (int i = 0; i < dg.V(); i++) {
            pr[i] = 1.0 / dg.V();

        }
        System.arraycopy(pr, 0, prevPr, 0, dg.V());
        //prevPr=Arrays.copyOf(pr,pr.length);

        for (int j = 0; j < thousand; j++) {
            // pr[i]=1/dg.V();


            for (int i = 0; i < dg.V(); i++) {
                pr[i] = 0.0;
                for (Integer w :  dgRev.adj(i)) {
                    pr[i] += prevPr[w] / dgh.outdegree(w);

                }
            }
            if (Arrays.equals(pr, prevPr)) {
                break;
            }
            System.arraycopy(pr, 0, prevPr, 0, dg.V());

        }
    }
    /**
     * Gets the pr value of given index.
     * Time complexity is O(1).
     * @param      v     { parameter_description }
     *
     * @return     The pr.
     */
    public double getPR(final int v) {
        return pr[v];
    }
    /**
     * Returns a string representation of the object.
     * Time complexity is O(V).
     *
     * @return     String representation of the object.
     */
    public String toString() {
        String str = "";
        for (int i = 0; i < pr.length; i++) {
            str += i + " - " + pr[i] + "\n";
        }
        return str;
    }
}
// /**
//  * Class for web search.
//  */
// class WebSearch {
//     /**
//      * Constructs the object.
//      */
//     private WebSearch() { }
// }

/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() { }
    /**
     * { main method to handle the input }.
     * Time complexity is O(1000(V+E)).
     * @param      args  The arguments are String array type
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        // read the first line of the input to get the number of vertices
        int vertices = Integer.parseInt(scan.nextLine());
        Digraph dg = new Digraph(vertices);
        while (scan.hasNext()) {
            String[] tokens = scan.nextLine().split(" ");
            for (int i = 1; i < tokens.length; i++) {
                dg.addEdge(Integer.parseInt(tokens[0]),
                           Integer.parseInt(tokens[i]));
            }
        }
        System.out.println(dg);
        for (int v = 0; v < vertices; v++) {
            if (dg.outdegree(v) == 0) {
                for (int w = 0; w < vertices; w++) {
                    if (v != w)  {
                        dg.addEdge(v, w);
                    }
                }
            }
        }

        // iterate count of vertices times
        // to read the adjacency list from std input
        // and build the graph


        // Create page rank object and pass the graph object to  constructor
        PageRank pr = new PageRank(dg);
        // print the page rank object
        System.out.println(pr);

        // This part is only for the final test case

        // File path to the web content
        String file = "WebContent.txt";

        // instantiate web search object
        // and pass the page rank object and the file path to the constructor

        // read the search queries from std in
        // remove the q= prefix and extract the search word
        // pass the word to iAmFeelingLucky method of web search
        // print the return value of iAmFeelingLucky

    }
}
