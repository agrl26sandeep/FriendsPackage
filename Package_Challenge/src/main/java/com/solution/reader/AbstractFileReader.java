package com.solution.reader;

import com.solution.exception.FileReaderException;
import com.solution.parser.IDataParser;

/**
 *  Abstract class which can be used to implement different file reading approach.
 * @author Sandeep Agrawal
 */
public abstract class AbstractFileReader<T> {

    protected abstract T read(IDataParser parser, String filePath) throws FileReaderException;

}
