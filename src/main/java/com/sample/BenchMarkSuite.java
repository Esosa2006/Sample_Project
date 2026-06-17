package com.sample;

import java.util.*;
import java.io.*;

/**
 * BenchmarkSuite.java
 *
 * 30 benchmark Java methods used to validate the
 * Java Code Efficiency and Energy-Aware Performance Analyzer.
 *
 * Each method represents a known algorithmic complexity class.
 * Expected EEI ranges are documented per method.
 * Actual analyzer output is compared against expected ranges
 * to validate correctness of static analysis.
 */
public class BenchmarkSuite {

    // ══════════════════════════════════════════════════════════════
    // GROUP 1: O(1) — Expected EEI: 90–100
    // ══════════════════════════════════════════════════════════════

    /** BM01 — Simple field getter. No loops, no recursion. */
    public String getName(String name) {
        return name;
    }

    /** BM02 — HashMap O(1) lookup. */
    public Object findById(Map<String, Object> map, String id) {
        return map.get(id);
    }

    /** BM03 — Array index access. Constant time. */
    public int getElement(int[] arr, int index) {
        return arr[index];
    }

    /** BM04 — Arithmetic calculation. No branching beyond one check. */
    public double calculateTax(double price, double rate) {
        return price * rate;
    }

    /** BM05 — Simple boolean evaluation. */
    public boolean isAdult(int age) {
        return age >= 18;
    }

    // ══════════════════════════════════════════════════════════════
    // GROUP 2: O(n) — Expected EEI: 75–85
    // ══════════════════════════════════════════════════════════════

    /** BM06 — Linear search through list. Single loop. */
    public int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    /** BM07 — Sum all elements. Single pass. */
    public long sumArray(int[] arr) {
        long sum = 0;
        for (int val : arr) sum += val;
        return sum;
    }

    /** BM08 — Count occurrences of value in list. */
    public int countOccurrences(List<Integer> list, int target) {
        int count = 0;
        for (int val : list) {
            if (val == target) count++;
        }
        return count;
    }

    /** BM09 — StringBuilder loop. Correct string building pattern. */
    public String buildString(List<String> items) {
        StringBuilder sb = new StringBuilder();
        for (String item : items) {
            sb.append(item).append(", ");
        }
        return sb.toString();
    }

    /** BM10 — Filter list by condition. Single pass. */
    public List<Integer> filterPositive(List<Integer> numbers) {
        List<Integer> result = new ArrayList<>();
        for (int n : numbers) {
            if (n > 0) result.add(n);
        }
        return result;
    }

    /** BM11 — Find maximum value. Single loop. */
    public int findMax(int[] arr) {
        int max = arr[0];
        for (int val : arr) {
            if (val > max) max = val;
        }
        return max;
    }

    /** BM12 — Linear recursion (factorial). Single recursive call. */
    public long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    // ══════════════════════════════════════════════════════════════
    // GROUP 3: O(n²) — Expected EEI: 40–55
    // ══════════════════════════════════════════════════════════════

    /** BM13 — Bubble sort. Classic O(n²) nested loop. */
    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /** BM14 — Naive duplicate detection. Nested loop comparison. */
    public boolean hasDuplicates(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) return true;
            }
        }
        return false;
    }

    /** BM15 — Matrix addition. Two nested loops over n×n matrix. */
    public int[][] addMatrices(int[][] a, int[][] b, int n) {
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    /** BM16 — Selection sort. O(n²) comparison-based sort. */
    public void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    /** BM17 — All pairs comparison. Nested loop over list. */
    public List<String> findAllPairs(List<Integer> nums) {
        List<String> pairs = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                pairs.add(nums.get(i) + "+" + nums.get(j));
            }
        }
        return pairs;
    }

    /** BM18 — Insertion sort. O(n²) in worst case. */
    public void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // ══════════════════════════════════════════════════════════════
    // GROUP 4: O(n³) — Expected EEI: 15–25
    // ══════════════════════════════════════════════════════════════

    /** BM19 — Matrix multiplication. Classic O(n³). */
    public int[][] multiplyMatrices(int[][] a, int[][] b, int n) {
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    /** BM20 — Floyd-Warshall all-pairs shortest path. O(n³). */
    public void floydWarshall(int[][] dist, int n) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }

    /** BM21 — Three sum brute force. O(n³) triple nested search. */
    public List<List<Integer>> threeSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        return result;
    }

    /** BM22 — Triple nested string search. O(n³). */
    public int countTripleMatches(List<String> a, List<String> b, List<String> c) {
        int count = 0;
        for (String x : a) {
            for (String y : b) {
                for (String z : c) {
                    if (x.equals(y) && y.equals(z)) count++;
                }
            }
        }
        return count;
    }

    // ══════════════════════════════════════════════════════════════
    // GROUP 5: O(2^n) — Expected EEI: 3–10
    // ══════════════════════════════════════════════════════════════

    /** BM23 — Naive Fibonacci. Two recursive calls = O(2^n). */
    public long fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /** BM24 — Tower of Hanoi. Exponential recursive branching. */
    public void hanoi(int n, String from, String to, String aux) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + from + " to " + to);
            return;
        }
        hanoi(n - 1, from, aux, to);
        System.out.println("Move disk " + n + " from " + from + " to " + to);
        hanoi(n - 1, aux, to, from);
    }

    /** BM25 — Power set generation. Recursive subset enumeration. */
    public List<List<Integer>> powerSet(List<Integer> set) {
        if (set.isEmpty()) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        int first = set.get(0);
        List<Integer> rest = set.subList(1, set.size());
        List<List<Integer>> subsetsWithoutFirst = powerSet(rest);
        List<List<Integer>> subsetsWithFirst = new ArrayList<>();
        for (List<Integer> subset : subsetsWithoutFirst) {
            List<Integer> newSubset = new ArrayList<>();
            newSubset.add(first);
            newSubset.addAll(subset);
            subsetsWithFirst.add(newSubset);
        }
        subsetsWithoutFirst.addAll(subsetsWithFirst);
        return subsetsWithoutFirst;
    }

    /** BM26 — Recursive permutation generation. O(n!) ≈ O(2^n) for analysis. */
    public void generatePermutations(String str, String current) {
        if (str.isEmpty()) {
            System.out.println(current);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            generatePermutations(
                    str.substring(0, i) + str.substring(i + 1),
                    current + str.charAt(i)
            );
        }
    }

    // ══════════════════════════════════════════════════════════════
    // GROUP 6: Anti-pattern detection — Mixed expected EEI
    // ══════════════════════════════════════════════════════════════

    /**
     * BM27 — String concatenation in loop.
     * Should trigger string concat penalty (-5).
     * Expected EEI: ~60–70 (O(n) base, penalised)
     */
    public String buildReportBad(List<String> items) {
        String result = "";
        for (String item : items) {
            result = result + item + ", ";
        }
        return result;
    }

    /**
     * BM28 — Excessive object creation.
     * Creates many new objects inside a loop.
     * Should trigger object creation penalty.
     * Expected EEI: ~55–70
     */
    public List<Object> createManyObjects(int n) {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Object obj = new Object();
            Map<String, Object> map = new HashMap<>();
            List<String> inner = new ArrayList<>();
            map.put("key", obj);
            map.put("list", inner);
            list.add(map);
        }
        return list;
    }

    /**
     * BM29 — High cyclomatic complexity.
     * Many decision points (CC > 10).
     * Should trigger CC penalty (-5 per unit above 10).
     * Expected EEI: ~50–65
     */
    public String classifyInput(int value, String type, boolean flag,
                                String region, int priority) {
        if (value < 0) return "negative";
        if (value == 0) return "zero";
        if (type == null) return "unknown";
        if (type.equals("A") && value > 100) return "high-A";
        if (type.equals("A") && value > 50) return "mid-A";
        if (type.equals("A")) return "low-A";
        if (type.equals("B") && flag) return "flagged-B";
        if (type.equals("B") && region != null && region.equals("EU")) return "eu-B";
        if (type.equals("B")) return "standard-B";
        if (priority > 10 && flag) return "urgent";
        if (priority > 5) return "normal";
        if (region != null && region.equals("US")) return "us-default";
        return "default";
    }

    /**
     * BM30 — Heavy I/O operations.
     * Multiple read/write calls detected.
     * Should trigger I/O penalty (-8 per excess operation).
     * Expected EEI: ~40–55
     */
    public void processFiles(List<String> filePaths) throws IOException {
        for (String path : filePaths) {
            File file = new File(path);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        }
    }
}