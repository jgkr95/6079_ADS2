import java.util.Scanner;
class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int ver = scan.nextInt(), pcount = 0, maxcount = 0, count = 0;
        int edges = scan.nextInt();
        if (edges == 0) {
            System.out.println(edges);
            return;
        }
        Graph gh = new Graph(ver+1);
        for (int i = 0; i < edges; i++) {
            gh.addEdge(scan.nextInt(), scan.nextInt());
        }
        CC com = new CC(gh);
        int[] idArr = com.idArr();
        for (int i = 0; i < gh.ve(); i++) {
            if (gh.hasParallelEdges(i)) {
                pcount++;
            }
            int id = idArr[i];
            for (int j = 0; i < idArr.length; i++) {
                if (id == idArr[j]) {
                    count++;
                }
            }
            if (maxcount < count) {
                maxcount = count;
            }

        }
        System.out.println(pcount + maxcount);
    }
}