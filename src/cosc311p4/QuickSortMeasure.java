package cosc311p4;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class QuickSortMeasure {

    private QuickSort q;  // Assuming QuickSort is the class containing q
    private String baseFileName;
    private List<Double> timesListq;

    public QuickSortMeasure(QuickSort q, String baseFileName) {
        this.q = q;
        this.baseFileName = baseFileName;
        timesListq = new ArrayList<>();
    }

    public void measureAndPrintSortingTime() {
        measureSortingTime(q.listAsc, "qSortAsc");
        measureSortingTime(q.listRand, "qSortRand");
        measureSortingTime(q.listDesc, "qSortDesc");
    }

    private void measureSortingTime(int[] list, String filename) {
        long currTime1 = System.nanoTime();
        q.quicksort(list, 0, 9999);
        long currTime2 = System.nanoTime();
        

        double currMili = (double) (currTime2 - currTime1) / 1_000_000;
        
        timesListq.add(currMili);

        
        
        //System.out.println("File sorted");
        printArrayToFile(list, baseFileName, filename);
        System.out.println(filename + " Time Elapsed: " + currMili + "ms");
    }

    public List<Double> getTimesListq() {
		return timesListq;
	}

	public void setTimesList(List<Double> timesList) {
		this.timesListq = timesList;
	}

	private void printArrayToFile(int[] array, String baseFileName, String filename) {
        // Combine baseFileName and filename to create the full file name
        String fullFileName = baseFileName + filename + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(fullFileName))) {
            // Write each element of the array to the file
            for (int value : array) {
                writer.println(value);
            }
            //System.out.println("Array printed to file: " + fullFileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + fullFileName);
            e.printStackTrace();
        }
    }
    
}
