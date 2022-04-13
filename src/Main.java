public class Main {
    final static int training_size = 1000000;

    public static void main(String[] args){
        NeuralNetwork neuralNetwork = new NeuralNetwork(2,4,1);
        double[][] training_set = new double[][]{{0.0, 0.0}, {0.0, 1.0}, {1.0, 0.0}, {1.0, 1.0}};
        double[][] target_set = new double[][]{{0.0}, {1.0}, {1.0}, {0.0}};
        Matrix inputs = new Matrix(2,1);
        Matrix targets = new Matrix(1,1);
        for (int i = 0; i < training_size; i++) {
            inputs.setElements(new double[][]{{training_set[i%4][0]},{training_set[i%4][1]}});
            targets.setElements(new double[][]{target_set[i%4]});
            neuralNetwork.train(inputs, targets);
        }
        for (double[] training_point : training_set) {
            inputs.setElements(new double[][]{{training_point[0]}, {training_point[1]}});
            System.out.println(neuralNetwork.predict(inputs));
        }
    }
}
