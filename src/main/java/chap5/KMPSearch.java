package chap5;

import java.util.Arrays;

public class KMPSearch {
    // test: https://leetcode.com/problems/implement-strstr/description/
    public static void main(String args[]) {
        String txt = "ABABDABACDABABCABAB";
        String pat = "ABABCABABC";
        System.out.println(search(txt, pat));
        System.out.println(search("hello", "ll"));
    }

    private static int search(String s, String p) {
        if (p == null || p.length() == 0) {
            return 0;
        }
        int len = p.length();
        int[] lps = new int[len]; // the index pointing to the next most possible index for longestPrefixAndSuffix;
        Arrays.fill(lps, 0);
        for (int i = 1, j = 0; i < len; ++i) {
            while (j > 0 && p.charAt(j) != p.charAt(i)) j = lps[j-1];
            if (p.charAt(i) == p.charAt(j)) j++;
            lps[i] = j;
        }
        System.out.println(Arrays.toString(lps));

        for (int i = 0, j = 0; i < s.length(); ++i) {
            while(j > 0 && p.charAt(j) != s.charAt(i)) j = lps[j-1];
            if (p.charAt(j) == s.charAt(i)) j++;
            if (j == len) return i - j + 1;
        }
        return -1;
    }
}
