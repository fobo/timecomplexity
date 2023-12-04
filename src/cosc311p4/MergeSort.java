package cosc311p4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MergeSort {

    int[] listDesc = new int[10000];
	int[] listAsc = new int[10000];
	int[] listRand = new int[10000];
	
	
	

    public void readFileMergeSort() {
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
	
    public void mergeSort(int[] array) {
        int n = array.length;
        if (n > 1) {
            int mid = n / 2;

            // Split the array into two halves
            int[] leftArray = new int[mid];
            int[] rightArray = new int[n - mid];

            System.arraycopy(array, 0, leftArray, 0, mid);
            System.arraycopy(array, mid, rightArray, 0, n - mid);

            // Recursive calls to sort each half
            mergeSort(leftArray);
            mergeSort(rightArray);

            // Merge the sorted halves
            merge(array, leftArray, rightArray);
        }
    }

    private static void merge(int[] array, int[] leftArray, int[] rightArray) {
        int i = 0; // Index for leftArray
        int j = 0; // Index for rightArray
        int k = 0; // Index for merged array

        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }

        // Copy the remaining elements of leftArray, if any
        while (i < leftArray.length) {
            array[k++] = leftArray[i++];
        }

        // Copy the remaining elements of rightArray, if any
        while (j < rightArray.length) {
            array[k++] = rightArray[j++];
        }
    }
}
