package me.markminisce.app.basicalgorithms;

public class LongestCommonSubsequence {

    private String s;
    private String t;
    // longest possible string to analyze
    private final int N = 1000;

    //matrix to hold intermediate results.
    private int[][] m;

    LongestCommonSubsequence(String s, String t) {
        this.s = s.toLowerCase();
        this.t = t.toLowerCase();

        this.m = new int[N][N];
    }

    public int getLengthLongestCommonSubsequence() {
        for (int i = 0; i < s.length() + 1; i++) {
            for (int j = 0; j < t.length() + 1; j++) {
                if (i == 0 || j == 0) {
                    this.m[i][j] = 0;
                } else if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    this.m[i][j] = Math.max(this.m[i - 1][j - 1] + 1, Math.max(this.m[i - 1][j], this.m[i][j - 1]));
                } else {
                    this.m[i][j] = Math.max(this.m[i - 1][j], this.m[i][j - 1]);
                }
            }
        }

        return this.m[s.length()][t.length()];
    }

    // print the state of the intermediate subproblems for debugging
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length() + 1; i++) {
            for (int j = 0; j < t.length() + 1; j++) {
                sb.append(this.m[i][j] + " ");
            }
            sb.append("\n");
        }

        return sb.toString().trim();
    }
}
