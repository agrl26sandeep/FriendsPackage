package com.solution.reader;

import com.solution.exception.FileReaderException;
import com.solution.parser.IDataParser;
import com.solution.utility.FileUtility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;


public class TextFileReader extends AbstractFileReader{
    private String filePath;

    public TextFileReader() {}

    /**
     * This method is used to read the Text file
     * @return
     * @throws FileReaderException
     */
    @Override
    protected Object read(IDataParser parser, String filePath) throws FileReaderException {
        System.out.println("Start reading text file.....");
        List<String> result = null;
        BufferedReader reader = null;
        try {
            this.filePath = FileUtility.getAbsolutePath(filePath);
            reader = new BufferedReader(new FileReader(this.filePath));
            result = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line == null || line.isEmpty())
                    continue;
                //System.out.println(line);
                result.add(line);
            }

            System.out.println("End reading text file.....");
            if(result.isEmpty())
                throw new FileReaderException("Input data not found:- '%s'", filePath);

            if(null != parser)
                return parser.parse(result);

        } catch (AccessDeniedException ex) {
            throw new FileReaderException("Please provide the valid file name:- '%s'", filePath);
        } catch (NoSuchFileException ex) {
            throw new FileReaderException("File not found in given location:- '%s'", filePath);
        }  catch (IOException ex) {
            ex.printStackTrace();
            throw new FileReaderException(ex.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }

    /*public static void main(String[] args) throws FileReaderException {
            new TextFileReader("input/sampleInput.txt").read();
    }*/
}
