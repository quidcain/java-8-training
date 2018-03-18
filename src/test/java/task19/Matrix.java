package task19;

public class Matrix {
    int n;
    int[][] data;

    Matrix(int[][] data) {
        this.data = data;
    }

    public Matrix multiply(Matrix B) {
        int x1 = data[0][0] * B.data[0][0] + data[0][1] * B.data[1][0];
        int y1 = data[0][0] * B.data[0][1] + data[0][1] * B.data[1][1];
        int x2 = data[1][0] * B.data[0][0] + data[1][1] * B.data[1][0];
        int y2 = data[1][0] * B.data[0][1] + data[1][1] * B.data[1][1];
        return new Matrix(new int[][]{{x1, y1}, {x2, y2}});
    }
}
