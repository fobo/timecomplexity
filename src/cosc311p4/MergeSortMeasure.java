package cosc311p4;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MergeSortMeasure {
    private MergeSort m;
    private String baseFileName;
    private List<Double> timesListm;

    public MergeSortMeasure(MergeSort m, String baseFileName) {
        this.m = m;
        this.baseFileName = baseFileName;
        timesListm = new ArrayList<>();
    }

    public void measureAndPrintSortingTime() {

        measureSortingTime(m.listAsc, "mSortAsc");
        measureSortingTime(m.listRand, "mSortRand");
        measureSortingTime(m.listDesc, "mSortDesc");
    }

    private void measureSortingTime(int[] list, String filename) {

        long currTime1 = System.nanoTime();
        m.mergeSort(list);
        long currTime2 = System.nanoTime();
        

        double currMili = (double) (currTime2 - currTime1) / 1_000_000;
        
        timesListm.add(currMili);

        
        
        //System.out.println("File sorted");
        printArrayToFile(list, baseFileName, filename);
        System.out.println(filename + " Time Elapsed: " + currMili + "ms");
    }

    public List<Double> getTimesListm() {
		return timesListm;
	}

	public void setTimesList(List<Double> timesList) {
		this.timesListm = timesList;
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
