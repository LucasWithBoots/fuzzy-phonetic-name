package io.github.lucaswithboots.phoetics.utils;

import java.util.Arrays;

public class PhoneticUtils {
    private static final int MAX_DISTANCE = 2; // Ajuste este valor conforme necessário

    public static boolean isSimilar(String searchCode, String targetCode) {
        // Verifica similaridade por distância Levenshtein
        int distance = calculateLevenshteinDistance(searchCode, targetCode);
        boolean withinDistance = distance <= MAX_DISTANCE;

        // Verifica se um código é parte do outro (como LIKE)
        boolean containsMatch = targetCode.contains(searchCode) || searchCode.contains(targetCode);

        return withinDistance || containsMatch;
    }

    private static int calculateLevenshteinDistance(String x, String y) {
        int[][] dp = new int[x.length() + 1][y.length() + 1];

        for (int i = 0; i <= x.length(); i++) {
            for (int j = 0; j <= y.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = min(
                            dp[i - 1][j - 1] + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)),
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1
                    );
                }
            }
        }

        return dp[x.length()][y.length()];
    }

    private static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    private static int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }
}