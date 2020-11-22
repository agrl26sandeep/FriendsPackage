package com.solution.reader;

import com.solution.action.ReaderAction;
import com.solution.exception.FileReaderException;
import com.solution.parser.IDataParser;

/**
 * Reader Factory class, single approach to read the provided file.
 * @author Sandeep Agrawal
 */
public class FileReaderFactory {

    private static FileReaderFactory fileReaderFactory = new FileReaderFactory();
    private IDataParser parser;
    /**
     *  Static method makes sure that single object will be created through out the JVM for FileReaderFactory class.
     * @return FileReaderFactory
     */
    //TODO Fix Me for Concurrent process or parallel execution
    public static FileReaderFactory getInstance() {
        if(null == fileReaderFactory)
            fileReaderFactory = new FileReaderFactory();
        return  fileReaderFactory;
    }

    /**
     * Method provides different file reading approach.
     * @param action Custom reader actions
     * @param filePath input file
     * @return Object Custom response
     * @throws FileReaderException
     */
    public Object readFile(ReaderAction action, String filePath) throws FileReaderException {
        return action.getInstance().read(this.parser, filePath);
    }

    public void setParser(IDataParser parser) {
        this.parser = parser;
    }
}
