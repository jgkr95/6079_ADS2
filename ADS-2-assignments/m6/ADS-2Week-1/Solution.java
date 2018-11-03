
import java.util.Scanner;
import java.io.File;
class PageRank {
	double[] pr;
	Iterable<Integer> incoming;

	PageRank(Digraph dg) {
		Digraph dgh = dg;
		Digraph dgRev = new Digraph(dg.V());
		dgRev = dg.reverse();

		pr = new double[dg.V()];
		for (int i = 0; i < dg.V(); i++) {
			pr[i] = (double) 1 / dg.V();
			// incoming=dgRev.adj(i);
			// for(int j=0;j<1000;j++) {
			// 	for(Integer w:incoming) {
			// 		pr[i]+=pr[w]+dgh.outdegree(w);
			// 	}
			// }
		}
		for (int i = 0; i < dg.V(); i++) {
			// pr[i]=1/dg.V();
			incoming = dgRev.adj(i);
			for (int j = 0; j < 1000; j++) {
				for (Integer w : incoming) {
					// pr[i]+=pr[w]+dgh.outdegree(w);
				}
			}
		}
	}
	public double getPR(int v) {
		return pr[v];
	}
	public String toString() {
		String str = "";
		for (int i = 0; i < pr.length; i++) {
			str += i + " - " + pr[i] + "\n";
		}
		return str;
	}
}

class WebSearch {

}


public class Solution {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// read the first line of the input to get the number of vertices
		int vertices = Integer.parseInt(scan.nextLine());
		Digraph dg = new Digraph(vertices);
		while (scan.hasNext()) {
			String[] tokens = scan.nextLine().split(" ");
			for (int i = 1; i < tokens.length; i++) {
				dg.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
			}
		}
		System.out.println(dg);


		// iterate count of vertices times
		// to read the adjacency list from std input
		// and build the graph


		// Create page rank object and pass the graph object to the constructor
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
