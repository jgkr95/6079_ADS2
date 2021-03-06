import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;


public class Solution {

    // Don't modify this method.
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String cases = scan.nextLine();

        switch (cases) {
        case "loadDictionary":
            // input000.txt and output000.txt
            BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
            while (scan.hasNextLine()) {
                String key = scan.nextLine();
                System.out.println(hash.get(key));
            }
            break;

        case "getAllPrefixes":
            // input001.txt and output001.txt
            T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
            while (scan.hasNextLine()) {
                String prefix = scan.nextLine();
                for (String each : t9.getAllWords(prefix)) {
                    System.out.println(each);
                }
            }
            break;

        case "potentialWords":
            // input002.txt and output002.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            int count = 0;
            while (scan.hasNextLine()) {
                String t9Signature = scan.nextLine();
                for (String each : t9.potentialWords(t9Signature)) {
                    count++;
                    System.out.println(each);
                }
            }
            if (count == 0) {
                System.out.println("No valid words found.");
            }
            break;

        case "topK":
            // input003.txt and output003.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            Bag<String> bag = new Bag<String>();
            int k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                bag.add(line);
            }
            for (String each : t9.getSuggestions(bag, k)) {
                System.out.println(each);
            }

            break;

        case "t9Signature":
            // input004.txt and output004.txt
            t9 = new T9(loadDictionary("/Files/t9.csv"));
            bag = new Bag<String>();
            k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                for (String each : t9.t9(line, k)) {
                    System.out.println(each);
                }
            }
            break;

        default:
            break;

        }
    }

    // Don't modify this method.
    public static String[] toReadFile(String file) {
        In in = new In(file);
        return in.readAllStrings();
    }

    public static BinarySearchST<String, Integer> loadDictionary(String file) {
        BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
        // your code goes here
        String[] str = toReadFile(file);
        for (String line : str) {
            for (String word : line.split(" ")) {
                if (!st.contains(word.toLowerCase())) {
                    st.put(word.toLowerCase(), 1);
                } else {
                    st.put(word.toLowerCase(), st.get(word.toLowerCase()) + 1);
                }
            }
        }
        return st;
    }

}

class T9 {
    TST<Integer> tst = new TST<Integer>();
    public T9(BinarySearchST<String, Integer> st) {
        // your code goes here

        for (String word : st.keys()) {
            tst.put(word, st.get(word));
        }
    }

    // get all the prefixes that match with given prefix.
    public Iterable<String> getAllWords(String prefix) {
        // your code goes here
        return tst.keysWithPrefix(prefix);
    }

    public Iterable<String> potentialWords(String t9Signature) {
        // your code goes here

        Queue<String> potentials = new Queue<>();
        for (String pattern : possibleCombo(t9Signature)) {
            if (tst.contains(pattern)) {
                potentials.enqueue(pattern);
            }
        }
        return potentials;

    }
    public static ArrayList<String> possibleCombo(String pattern) {
        HashMap<Character, String> map = new HashMap<Character, String>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        ArrayList<String> res = new ArrayList<String>();
        ArrayList<String> preres = new ArrayList<String>();
        res.add("");

        for (int i = 0; i < pattern.length(); i++) {
            for (String str : res) {
                String letters = map.get(pattern.charAt(i));
                for (int j = 0; j < letters.length(); j++)
                    preres.add(str + letters.charAt(j));
            }
            res = preres;
            preres = new ArrayList<String>();
        }
        return res;
    }

    // return all possibilities(words), find top k with highest frequency.
    public Iterable<String> getSuggestions(Iterable<String> words, int k) {
        // your code goes here
        BinarySearchST<Integer, String> st = new BinarySearchST<Integer, String>();
        for (String s : words) {
            Integer frequency = tst.get(s);
            st.put(frequency, s);
        }
        Bag<String> bag = new Bag<String>();
        String[] arr = new String[k];
        for (int j = 0; j < k; j++) {
            Integer i = st.max();
            arr[j] = st.get(i);
            // bag.add(st.get(i));
            st.deleteMax();
        }
        Arrays.sort(arr);
        for (int i = k; i > 0; i--) {
            bag.add(arr[i - 1]);
        }
        return bag;
    }

    // final output
    // Don't modify this method.
    public Iterable<String> t9(String t9Signature, int k) {
        return getSuggestions(potentialWords(t9Signature), k);
    }
}
