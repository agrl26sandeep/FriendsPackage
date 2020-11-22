package com.solution.action;

import com.solution.reader.AbstractFileReader;
import com.solution.reader.TextFileReader;

import java.util.function.Supplier;

/**
 * Enum based factory to maintain and responsible to handle the file reader service instantiation.
 * @author Sandeep Agrawal
 */
public enum ReaderAction {
    READ_TXT(TextFileReader::new);

    private Supplier<AbstractFileReader> instantiator;

    public AbstractFileReader getInstance() {
        return instantiator.get();
    }

    ReaderAction(Supplier<AbstractFileReader> instantiator) {
        this.instantiator = instantiator;
    }
}
