public class Matrix {
    private final int rows, columns;
    public double[][] elements;

    public Matrix(int rows, int columns){
        if (rows < 1 || columns < 1)
            throw new IllegalArgumentException("Rows and columns have to be greater than 0!");
        this.rows = rows;
        this.columns = columns;
        elements = new double[rows][columns];
    }

    public void setElements(double[][] elements) {
        this.elements = elements;
    }

    public double[][] getElements() {
        return elements;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.rows; i++){
            for (int j = 0; j < this.columns; j++){
                result.append(this.elements[i][j]).append("\t");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
