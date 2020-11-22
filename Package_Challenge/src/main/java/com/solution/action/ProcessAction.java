package com.solution.action;

import com.solution.processor.FriendPackageProcessor;
import com.solution.processor.IProcessor;
import java.util.function.Supplier;

/**
 * Enum based factory to maintain and responsible to handle the Processor actions instantiation.
 * @author Sandeep Agrawal
 */
public enum ProcessAction {
    FRIENDPACKAGE(FriendPackageProcessor::new);

    private Supplier<IProcessor> instantiator;

    public IProcessor getInstance() {
        return instantiator.get();
    }

    ProcessAction(Supplier<IProcessor> instantiator) {
        this.instantiator = instantiator;
    }
}
