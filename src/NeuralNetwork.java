import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class NeuralNetwork {
    private Matrix weights_input_hidden;
    private Matrix weights_hidden_output;
    private double learning_rate = 0.1;
    private final MatrixMath matrixMath;

    public NeuralNetwork(int input_nodes, int hidden_nodes, int output_nodes){
        this.weights_input_hidden = new Matrix(hidden_nodes, input_nodes);
        this.weights_hidden_output = new Matrix(output_nodes, hidden_nodes);
        this.fillMatrixWithWeights(this.weights_input_hidden);
        this.fillMatrixWithWeights(this.weights_hidden_output);
        this.matrixMath = new MatrixMath();
    }

    private void fillMatrixWithWeights(Matrix matrix){
        Random random = new Random();
        for (int i = 0; i < matrix.getRows(); i++){
            for (int j = 0; j < matrix.getColumns(); j++){
                matrix.setWeight(i, j, random.nextGaussian());
            }
        }
    }

    public Matrix predict(Matrix inputs){
        Matrix hidden_in = matrixMath.dot(weights_input_hidden, inputs);
        Matrix hidden_out = applySigmoid(hidden_in);
        Matrix output_in = matrixMath.dot(weights_hidden_output, hidden_out);
        return applySigmoid(output_in);
    }

    public void train(Matrix inputs, Matrix targets){
        Matrix hidden_in = matrixMath.dot(weights_input_hidden, inputs);
        Matrix hidden_out = applySigmoid(hidden_in);
        Matrix output_in = matrixMath.dot(weights_hidden_output, hidden_out);
        Matrix output_out = applySigmoid(output_in);
        Matrix output_errors = matrixMath.subtract(targets, output_out);
        Matrix hidden_errors = matrixMath.dot(matrixMath.transpose(weights_hidden_output), output_errors);
        Matrix delta_weights_hidden_output = matrixMath.mult(matrixMath.dot(matrixMath.hadamard_dot(output_errors,
                matrixMath.hadamard_dot(output_out, matrixMath.subtract(1.0, output_out))),
                matrixMath.transpose(hidden_out)), learning_rate);
        Matrix delta_weights_input_hidden = matrixMath.mult(matrixMath.dot(matrixMath.hadamard_dot(hidden_errors,
                matrixMath.hadamard_dot(hidden_out, matrixMath.subtract(1.0, hidden_out))),
                matrixMath.transpose(inputs)), learning_rate);
        weights_input_hidden = matrixMath.add(weights_input_hidden, delta_weights_input_hidden);
        weights_hidden_output = matrixMath.add(weights_hidden_output, delta_weights_hidden_output);
    }

    private Matrix applySigmoid(Matrix matrix){
        Matrix result = new Matrix(matrix.getRows(), matrix.getColumns());
        IntStream.range(0, matrix.getRows())
                .forEach(rowIndex -> result.setRow(rowIndex, Arrays.stream(matrix.getRow(rowIndex))
                        .map(this::sigmoid).toArray()));

        return result;
    }

    private double sigmoid(double x){
        return 1 / (1 + Math.exp(-x));
    }

    public double getLearning_rate() {
        return learning_rate;
    }

    public void setLearning_rate(double learning_rate) {
        this.learning_rate = learning_rate;
    }
}
