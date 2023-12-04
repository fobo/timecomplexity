package cosc311p4;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class HeapSortMeasure {
    private HeapSort h;
    private String baseFileName;
    private List<Double> timesListh;

    public HeapSortMeasure(HeapSort h, String baseFileName) {
        this.h = h;
        this.baseFileName = baseFileName;
        timesListh = new ArrayList<>();
    }

    public void measureAndPrintSortingTime() {
        measureSortingTime(h.listAsc, "hSortAsc");
        measureSortingTime(h.listRand, "hSortRand");
        measureSortingTime(h.listDesc, "hSortDesc");
    }

    private void measureSortingTime(int[] list, String filename) {
        long currTime1 = System.nanoTime();
        h.heapSort(list);
        long currTime2 = System.nanoTime();
        

        double currMili = (double) (currTime2 - currTime1) / 1_000_000;
        
        timesListh.add(currMili);

        
        
        //System.out.println("File sorted");
        printArrayToFile(list, baseFileName, filename);
        System.out.println(filename + " Time Elapsed: " + currMili + "ms");
    }

    public List<Double> getTimesListh() {
		return timesListh;
	}

	public void setTimesList(List<Double> timesList) {
		this.timesListh = timesList;
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
