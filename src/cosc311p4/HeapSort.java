package cosc311p4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HeapSort {
	
    int[] listDesc = new int[10000];
	int[] listAsc = new int[10000];
	int[] listRand = new int[10000];
	
	
	

    public void readFileHeapSort() {
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
            //System.out.println("Data read successfully from " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void heapSort(int[] array) {
        int n = array.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // Extract elements from the heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Swap the root (maximum element) with the last element
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Heapify the reduced heap
            heapify(array, i, 0);
        }
    }

    private static void heapify(int[] array, int n, int i) {
        int largest = i; // Initialize the largest as the root
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        // If the left child is larger than the root
        if (leftChild < n && array[leftChild] > array[largest]) {
            largest = leftChild;
        }

        // If the right child is larger than the largest so far
        if (rightChild < n && array[rightChild] > array[largest]) {
            largest = rightChild;
        }

        // If the largest is not the root
        if (largest != i) {
            // Swap the root with the largest element
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;

            // Recursively heapify the affected sub-tree
            heapify(array, n, largest);
        }
    }
}
