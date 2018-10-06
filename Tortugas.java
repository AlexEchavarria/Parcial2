package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Turtle implements Comparable<Turtle> {
    public int peso;
    public int fuerza;

    public Turtle(int p, int f) {
        this.peso = p;
        this.fuerza = f;
    }

    @Override
    public int compareTo(Turtle o) {
        if (this.fuerza == o.fuerza)
            return this.peso - o.fuerza;
        return (this.fuerza) - (o.fuerza);
    }
}

    class Main {
        public List<Turtle> list;

        public static void main(String[] args) throws Exception {
            Main main = new Main();
            main.init();
            System.out.println(main.torre());
        }

        public void init() throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            list = new ArrayList<Turtle>();
            String strLine;
            while ((strLine = br.readLine()) != null) {
                String[] str = strLine.split(" ");
                int p = Integer.parseInt(str[0]);
                int f = Integer.parseInt(str[1]) - p;
                list.add(new Turtle(p, f));
            }
        }

        public int torre() {
            Collections.sort(list);
            int N = list.size();
            int[][] matriz = new int[N][N + 1];
            for (int i = 0; i < N; i++)
                for (int j = 0; j <= N; j++)
                    matriz[i][j] = (j == 0 ? 0 : Integer.MAX_VALUE);
            matriz[0][1] = list.get(0).fuerza;
            for (int i = 1; i < N; i++) {
                for (int j = 1; j <= i + 1; j++) {
                    matriz[i][j] = Math.min(matriz[i][j], matriz[i - 1][j]);
                    if (list.get(i).fuerza > matriz[i - 1][j - 1]) {
                        matriz[i][j] = Math.min(matriz[i][j],
                                matriz[i - 1][j - 1] + list.get(i).fuerza);
                    }
                }
            }
            for (int i = N; i >= 0; i--)
                if (matriz[N - 1][i] != Integer.MAX_VALUE)
                    return i;
            return 0;
        }
    }