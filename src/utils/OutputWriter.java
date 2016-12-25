package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Mihai Popescu
 *
 */
public class OutputWriter {
    private BufferedWriter bufferWriter;

    /**
     * 
     * @param outputFileName
     * @throws IOException
     */
    public OutputWriter(String outputFileName) throws IOException {
	bufferWriter = new BufferedWriter(new FileWriter(outputFileName));
    }

    /**
     * 
     * @param line
     */
    public void println(String line) {
	try {
	    bufferWriter.write(line + "\n");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Closes the stream, flushing it first. Once the stream has been closed,
     * further write() invocations will cause an IOException to be thrown.
     */
    public void close() {
	try {
	    bufferWriter.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
