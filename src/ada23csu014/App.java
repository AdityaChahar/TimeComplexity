package ada23csu014;


import java.util.Arrays;
import java.util.Random;

public class App {

    
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

   
    public static long measureExecutionTime(int[] arr) {
        long startTime = System.nanoTime();
        bubbleSort(arr); 
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    
    public static String estimateTimeComplexity(long[] times, int[] inputSizes) {
        if (times.length < 2) {
            return "Not enough data to estimate complexity.";
        }

        
        double[] ratios = new double[times.length - 1];
        for (int i = 1; i < times.length; i++) {
            ratios[i - 1] = (double) times[i] / times[i - 1];
        }

        
        double averageRatio = Arrays.stream(ratios).average().orElse(0);
        int inputSizeRatio = inputSizes[1] / inputSizes[0];

        if (averageRatio < 1.5) {
            return "O(1) - Constant Time Complexity";
        } else if (averageRatio < inputSizeRatio * 1.5) {
            return "O(n) - Linear Time Complexity";
        } else if (averageRatio < Math.pow(inputSizeRatio, 2) * 1.5) {
            return "O(n^2) - Quadratic Time Complexity";
        } else if (averageRatio < Math.pow(inputSizeRatio, 3) * 1.5) {
            return "O(n^3) - Cubic Time Complexity";
        } else {
            return "O(log n) or O(n log n) - Logarithmic or Linearithmic Time Complexity";
        }
    }

    public static void main(String[] args) {
        int[] inputSizes = {1000, 2000, 4000, 8000}; 
        long[] executionTimes = new long[inputSizes.length];

        
        for (int i = 0; i < inputSizes.length; i++) {
            int[] arr = generateRandomArray(inputSizes[i]);
            executionTimes[i] = measureExecutionTime(arr);
            System.out.println("Input size: " + inputSizes[i] + ", Time: " + executionTimes[i] + " ns");
        }

        
        String complexity = estimateTimeComplexity(executionTimes, inputSizes);
        System.out.println("Estimated Time Complexity: " + complexity);
    }

    
    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000);
        }
        return arr;
    }
}