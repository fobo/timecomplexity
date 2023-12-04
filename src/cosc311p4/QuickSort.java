package cosc311p4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class QuickSort {

	
	
    int[] listDesc = new int[10000];
	int[] listAsc = new int[10000];
	int[] listRand = new int[10000];
	

	

    public void readFileQsort() {
        readDataFromFile("dataDescend.txt", listDesc);
        readDataFromFile("dataAscend-1.txt", listAsc);
        readDataFromFile("dataRandom.txt", listRand);
    }

    private void readDataFromFile(String filePath, int[] targetArray) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int index = 0;
            while ((line = br.readLine()) != null && index < 10000) {
                targetArray[index++] = Integer.parseInt(line.trim());
            }
            ///System.out.println("Data read successfully from " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
    void quicksort(int[] arr, int low, int high) {

        if (low < high) {
            int pivotIndex = partition(arr, low, high);

            // Recursively sort the sub-arrays
            quicksort(arr, low, pivotIndex - 1);
            quicksort(arr, pivotIndex + 1, high);
        }
    }

    int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public void printIntArray(int[] array) {
        for (int value : array) {
            System.out.println(value);
        }
    }

	
}
