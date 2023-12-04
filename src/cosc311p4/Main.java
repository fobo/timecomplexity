package cosc311p4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		List<Double> timesListq; // quicksort times
		List<Double> timesListm; // merge sort times
		List<Double> timesListh; // heap sort times
		Scanner scanner = new Scanner(System.in);

		System.out.print("Please enter base file name: ");
		String baseFileName = scanner.nextLine();

		String[] suffixes = { "qSortAsc.txt", "qSortDesc.txt", "qSortRand.txt", "hSortAsc.txt", "hSortDesc.txt",
				"hSortRand.txt", "mSortAsc.txt", "mSortDesc.txt", "mSortRand.txt", "timetotals.txt" };

		try {
			for (String suffix : suffixes) {
				String filename = baseFileName + suffix;
				createFile(filename);
			}

			// System.out.println("Files created successfully.");
		} catch (IOException e) {
			System.err.println("Error creating files: " + e.getMessage());
		} finally {
			scanner.close();
		}

		QuickSort q = new QuickSort();
		q.readFileQsort();

		// Create an instance of SortingTimeMeasurement and pass q to it
		QuickSortMeasure quickSortMeasure = new QuickSortMeasure(q, baseFileName);

		// Measure and print sorting times
		quickSortMeasure.measureAndPrintSortingTime();
		timesListq = quickSortMeasure.getTimesListq(); // time values for quicksort

		HeapSort h = new HeapSort();
		h.readFileHeapSort();

		HeapSortMeasure heapSortMeasure = new HeapSortMeasure(h, baseFileName);
		heapSortMeasure.measureAndPrintSortingTime();
		timesListh = heapSortMeasure.getTimesListh();

		MergeSort m = new MergeSort();
		m.readFileMergeSort();
		MergeSortMeasure mergeSortMeasure = new MergeSortMeasure(m, baseFileName);
		mergeSortMeasure.measureAndPrintSortingTime();
		timesListm = mergeSortMeasure.getTimesListm();

		// insert relevant data into time table


		// Specify the file path
		String filePath = baseFileName + "timetotals.txt";

        try {
            // Create a PrintWriter object with FileWriter for writing to the file
            PrintWriter writer = new PrintWriter(new FileWriter(filePath));

            // Write the header
            writer.printf("%-17s%20s%22s%23s%n", "", "QuickSort", "HeapSort", "MergeSort");

            // Write the data
            writeRow(writer, "Ascending", timesListq.get(0), timesListh.get(0), timesListm.get(0));
            writeRow(writer, "Random", timesListq.get(1), timesListh.get(1), timesListm.get(1));
            writeRow(writer, "Descending", timesListq.get(2), timesListh.get(2), timesListm.get(2));

            // Close the writer
            writer.close();

            System.out.println("Data has been written to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeRow(PrintWriter writer, String label, double valueq, double valueh, double valuem) {
    	writer.printf("%-15s%20.4fms%20.4fms%20.4fms%n",
                label,
                valueq,
                valueh,
                valuem
        );
    }


	private static void createFile(String filename) throws IOException {
		File file = new File(filename);
		if (file.createNewFile()) {
			// System.out.println("File created successfully: " + file.getAbsolutePath());
		} else {
			System.out.println("File already exists or couldn't be created: " + filename);
		}
	}

}
