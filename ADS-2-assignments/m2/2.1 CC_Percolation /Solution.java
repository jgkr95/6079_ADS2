/* To import scanner class*/
import java.util.Scanner;
/**
 * Class for percolation.
 */
class Percolation {
/**
 * variable to keep count value.
 */
    private int count;
    /**
     * 2 dimentional array.
     */
    private int[][] grid;
    /**
     * { to keep size }.
     */
    private int size;
    /**
     * object for weighted addEdge class.
     */
    private Graph cd;
    /**
     * cc object.
     */
    private CC cc;
    /**
     * Constructs the object.
     *
     * @param      n     { size of the grid }
     */
    Percolation(final int n) {
        grid = new int[n][n];
        count = 0;
        cd = new Graph((n * n) + 2);
        cc = new CC(cd);
        this.size = n;
    }
    /**
     * function to check whether the element has to be connected or not.
     *
     * @param      row   The value of row
     * @param      col   The value of column
     * Time complexity is O(1)
     */
    void open(final int row, final int col) {
        grid[row][col] = 1;
        count++;
        if (row == 0) {
            cd.addEdge(0, component(row, col));
        }
        if (row == size - 1) {
            cd.addEdge((size * size) + 1, component(row, col));
        }
        if (row + 1 < size && grid[row][col] == 1) {
                cd.addEdge(
                    component(row + 1, col), component(row, col));
            }
        if (row - 1 >= 0 && grid[row - 1][col] == 1) {
                cd.addEdge(
                    component(row - 1, col), component(row, col));
        }
        if (col - 1 >= 0 && grid[row][col - 1] == 1) {
                cd.addEdge(component(row, col - 1), component(row, col));
        }
        if (col + 1 < size && grid[row][col + 1] == 1) {
                cd.addEdge(
                    component(row, col + 1), component(row, col));
        }
    }
    /**
     * to get the component at the particular row and column.
     *
     * @param      i     row index is given.
     * @param      j     column index is given
     * Time complexity is O(1)
     *
     * @return     return type is int
     */
    int component(final int i, final int j) {
        return (i) * size + j;
    }
    /**
     * Determines if open.
     *
     * @param      row   The row
     * @param      col   The col
     * Time complexity is O(1)
     * @return     True if open, False otherwise.
     */
    boolean isOpen(final int row, final int col) {
        return grid[row][col] == 1;
    }
    /**
     * Determines if full or not.
     *
     * @param      row   The row
     * @param      col   The col
     * Time complexity is O(1)
     * @return     True if full, False otherwise.
     */
    boolean isFull(final int row, final int col) {
        return grid[row][col] == 0;
    }
    /**
     * to get the number of open sites.
     * Time complexity is O(1)
     * @return     integer is returned.
     */
    int numberofopensites() {
        return count;
    }
    /**
     * function to check if the grid percolates or not.
     * Time complexity is O(1)
     * @return     True if percolates, False otherwise.
     */
    boolean percolates() {
        cc = new CC(cd);
        return cc.connected(0, (size * size) + 1);
    }
}
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
     * Main method to handle the input.
     * Time complexity is O(N)
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertices = scan.nextInt();
        Percolation prc = new Percolation(vertices);
        while (scan.hasNext()) {
        prc.open(scan.nextInt() - 1, scan.nextInt() - 1);
        }
        System.out.println(prc.percolates());
    }
}
