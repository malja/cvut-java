package solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class accepts file path. File is loaded, read line by line and then
 * each line is splin into patentID (numerical value) and patentName (text)
 * divided by \t.
 */
public class FileParser {
    private File inputFile = null;
    private Scanner scanner = null;
    private int line = 0; // Current line in file

    /**
     * Load new file from path
     * @param path Path to the file. May be absolute or relative path.
     * @return If the file was loaded properly.
     */
    public boolean load(String path) {
        inputFile = new File(path);

        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            return false;
        }

        return true;
    }

    /**
     * Read next line from file. If there are no more lines returns null.
     * @return Pair with patentID and patentName.
     * @throws InvalidLineFormattingException when there is invalid line.
     */
    public Pair<Integer, String> getLine() {
        // No more lines
        if (!scanner.hasNextLine()) {
            return null;
        }

        // Split the line by \t
        String[] parts = scanner.nextLine().split("\t");
        // Expecting two parts
        if (parts.length != 2) {
            throw new InvalidLineFormattingException("There are multiple tabs on the line: " + line);
        }

        Integer index = 0;
        String value = parts[1];

        // patentId has to be a number
        try {
            index = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            throw new InvalidLineFormattingException("Patent number is invalid. See line: " + line, e);
        }

        line++;

        // Return line as pair
        return new Pair<Integer, String>(index, value);
       
    }

    /**
     * Does loaded file exists?
     * @return True if file exists, false when no file is loaded or it does not exist.
     */
    public boolean fileExists() {
        if (inputFile == null) return false;

        return inputFile.exists() && inputFile.isFile();
    }
}
