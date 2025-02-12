package com.example.demo.search;

public class search {
    public static int calcularDistancia(String s1, String s2) {
        int[][] distancias = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    distancias[i][j] = j;
                } else if (j == 0) {
                    distancias[i][j] = i;
                } else {
                    int costo = s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1;
                    distancias[i][j] = Math.min(distancias[i - 1][j - 1] + costo,
                            Math.min(distancias[i - 1][j] + 1, distancias[i][j - 1] + 1));
                }
            }
        }
        return distancias[s1.length()][s2.length()];
    }
}