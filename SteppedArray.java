import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class SteppedArray {
    private static final int NOT_DEFINED = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please, enter a rows count (N): ");
        int rows = scanner.nextInt();

        System.out.print("Please, enter a maximum count columns (M): ");
        int maxColumns = scanner.nextInt();

        int[][] steppedArray = generateMatrix(rows, maxColumns);
        System.out.println("\nThe first matrix:");
        printMatrix(steppedArray);

        sortedMatrixRows(steppedArray);
        System.out.println("\nThe matrix after sorted: ");
        printMatrix(steppedArray);

        int steppedMatrixSum = calculateSum(steppedArray);
        System.out.println("\nThe sum of all matrix's elements : " + steppedMatrixSum);

        int[] minRowMatrixValue = findMinRowValue(steppedArray);
        System.out.println("\nThe minimum values for every matrix's rows: ");
        printMinValue(minRowMatrixValue);

        int minMatrixValue = findMatrixMinimum(minRowMatrixValue);
        System.out.println("\n\nThe minimum value of matrix: " + minMatrixValue);

        divideElement(steppedArray, minMatrixValue);
        if (minMatrixValue != 0) {
            System.out.println("\nResult after division is: ");
            printMatrix(steppedArray);
        } else {
            System.out.println("You can't divide by 0!");
        }
    }

    public static int[][] generateMatrix(int rows, int maxColumns) {
        int[][] steppedArray = new int[rows][];
        for (int i = 0; i < rows; i++) {
            int columns = ThreadLocalRandom.current().nextInt(0, maxColumns);
            steppedArray[i] = new int[columns];
            for (int j = 0; j < columns; j++) {
                steppedArray[i][j] = ThreadLocalRandom.current().nextInt(50);
            }
        }
        return steppedArray;
    }

    public static void sortedMatrixRows(int[][] steppedArray) {
        for (int i = 0; i < steppedArray.length; i++) {
            if (i % 2 == 0) {
                descendSort(steppedArray[i]);
            } else {
                ascendantSort(steppedArray[i]);
            }
        }
    }

    public static void descendSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    change(array, j);
                }
            }
        }
    }

    public static void ascendantSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] < array[j + 1]) {
                    change(array, j);
                }
            }
        }
    }

    public static void change(int[] array, int i) {
        int tmp = array[i];
        array[i] = array[i + 1];
        array[i + 1] = tmp;
    }


    public static int calculateSum(int[][] steppedArray) {
        int totalSum = 0;
        for (int i = 0; i < steppedArray.length; i++) {
            for (int j = 0; j < steppedArray[i].length; j++) {
                totalSum += steppedArray[i][j];
            }
        }
        return totalSum;
    }

    public static int[] findMinRowValue(int[][] steppedArray) {
        int[] minRowValue = new int[steppedArray.length];
        for (int i = 0; i < steppedArray.length; i++) {
            if (steppedArray[i].length == 0) {
                minRowValue[i] = NOT_DEFINED;
            } else {
                minRowValue[i] = findMatrixMinimum(steppedArray[i]);
            }
        }
        return minRowValue;
    }

    public static Integer findMatrixMinimum(int[] array) {
        int minimumValue = NOT_DEFINED;

        for (int i = 0; i < array.length; i++) {
            if (minimumValue == NOT_DEFINED || (array[i] != NOT_DEFINED && array[i] < minimumValue)) {
                minimumValue = array[i];
            }
        }
        return minimumValue;
    }

    public static void divideElement(int[][] steppedArray, int divider) {
        if (divider == 0 || divider == NOT_DEFINED) {
            System.out.println("Can't divide by 0! Sorry:(");
            return;
        }
        for (int i = 0; i < steppedArray.length; i++) {
            for (int j = 0; j < steppedArray[i].length; j++) {
                steppedArray[i][j] /= divider;
            }
        }
    }

    public static void printMatrix(int[][] steppedArray) {
        for (int i = 0; i < steppedArray.length; i++) {
            for (int j = 0; j < steppedArray[i].length; j++) {
                System.out.print(steppedArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printMinValue(int[] array) {
        for (int value : array) {
            if (value == NOT_DEFINED) {
                System.out.print(" , ");
            } else {
                System.out.print(value + ", ");
            }
        }
    }
}