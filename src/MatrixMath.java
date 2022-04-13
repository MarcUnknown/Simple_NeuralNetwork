public class MatrixMath {

    public Matrix dot(Matrix matrix_1, Matrix matrix_2){
        if (matrix_1.getColumns() != matrix_2.getRows())
            throw new IllegalArgumentException("Rows does not match columns!");
        Matrix result = new Matrix(matrix_1.getRows(), matrix_2.getColumns());
        for (int i = 0; i < matrix_1.getRows(); i++){
            for (int j = 0; j < matrix_2.getColumns(); j++){
                for (int k = 0; k < matrix_1.getColumns(); k++){
                    result.getElements()[i][j] += matrix_1.getElements()[i][k] * matrix_2.getElements()[k][j];
                }
            }
        }
        return result;
    }

    public Matrix hadamard_dot(Matrix matrix_1, Matrix matrix_2){
        if (matrix_1.getRows() != matrix_2.getRows() || matrix_1.getColumns() != matrix_2.getColumns())
            throw new IllegalArgumentException("Matrices must have same dimensions!");
        Matrix result = new Matrix(matrix_1.getRows(), matrix_1.getColumns());
        for (int i = 0; i < matrix_1.getRows(); i++){
            for (int j = 0; j < matrix_1.getColumns(); j++){
                result.getElements()[i][j] = matrix_1.getElements()[i][j] * matrix_2.getElements()[i][j];
            }
        }
        return result;
    }

    public Matrix transpose(Matrix matrix){
        Matrix transposed_Matrix = new Matrix(matrix.getColumns(), matrix.getRows());
        for (int i = 0; i < matrix.getRows(); i++){
            for (int j = 0; j < matrix.getColumns(); j++){
                transposed_Matrix.getElements()[j][i] = matrix.getElements()[i][j];
            }
        }
        return transposed_Matrix;
    }

    public Matrix subtract(Matrix matrix_1, Matrix matrix_2){
        if (matrix_1.getRows() != matrix_2.getRows() || matrix_1.getColumns() != matrix_2.getColumns())
            throw new IllegalArgumentException("Matrices must have same dimensions!");
        Matrix result = new Matrix(matrix_1.getRows(), matrix_1.getColumns());
        for (int i = 0; i < matrix_1.getRows(); i++){
            for (int j = 0; j < matrix_1.getColumns(); j++){
                result.getElements()[i][j] = matrix_1.getElements()[i][j] - matrix_2.getElements()[i][j];
            }
        }
        return result;
    }

    public Matrix subtract(double constant, Matrix matrix){
        Matrix result = new Matrix(matrix.getRows(), matrix.getColumns());
        for (int i = 0; i < matrix.getRows(); i++){
            for (int j = 0; j < matrix.getColumns(); j++){
                result.getElements()[i][j] = 1 - matrix.getElements()[i][j];
            }
        }
        return result;
    }

    public Matrix add(Matrix matrix_1, Matrix matrix_2){
        if (matrix_1.getRows() != matrix_2.getRows() || matrix_1.getColumns() != matrix_2.getColumns())
            throw new IllegalArgumentException("Matrices must have same dimensions!");
        Matrix result = new Matrix(matrix_1.getRows(), matrix_1.getColumns());
        for (int i = 0; i < matrix_1.getRows(); i++){
            for (int j = 0; j < matrix_1.getColumns(); j++){
                result.getElements()[i][j] = matrix_1.getElements()[i][j] + matrix_2.getElements()[i][j];
            }
        }
        return result;
    }

    public Matrix mult(Matrix matrix, double scalar){
        Matrix result = new Matrix(matrix.getRows(), matrix.getColumns());
        for (int i = 0; i < matrix.getRows(); i++){
            for (int j = 0; j < matrix.getColumns(); j++){
                result.getElements()[i][j] = matrix.getElements()[i][j] * scalar;
            }
        }
        return result;
    }
}
