package graphTheory.impl;

import graphTheory.Graph;
import graphTheory.Iterator;

//稠密图 邻接矩阵
public class DenseGraph extends Graph {


    private boolean g[][];


    public DenseGraph(int n, boolean direct) {
        super(n, direct);
        this.g = new boolean[n][n];
    }

    public void addEdge(int i, int j) {
        if (hasEdge(i, j)) {
            return;
        }
        g[i][j] = true;
        m++;
        if (!direct) {
            g[j][i] = true;
        }

    }

    @Override
    public boolean hasEdge(int i, int j) {
        check(i, j);
        return g[i][j];
    }

    @Override
    public Iterator iterator(int v) {
        return new AdjIterator(this, v);
    }

    @Override
    public void show() {
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[i].length; j++) {
                System.out.printf((g[i][j] ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }

    private void check(int i, int j) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n || j < 0 || j >= n) {
            throw new IndexOutOfBoundsException("Attention ! n = " + n + ", your i = " + i + ", j = " + j);
        }
    }

    private class AdjIterator implements Iterator {
        private boolean[] list;
        private int i;

        AdjIterator(DenseGraph graph, int v) {
            i = -1;
            list = graph.g[v];
        }

        @Override
        public boolean hasNext() {
            for (; i + 1 < list.length; i++) {
                if (list[i + 1]) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int next() {
            i++;
            for (; i < list.length; i++) {
                if (list[i]) {
                    return i;
                }
            }
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
