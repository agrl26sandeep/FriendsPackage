package com.solution.action;

import com.solution.service.FriendPackageService;
import com.solution.service.IService;

import java.util.function.Supplier;

/**
 * Enum based factory to maintain and responsible to handle the service instantiation.
 * @author Sandeep Agrawal
 */
public enum ServiceAction {
    FRIENDPACKAGE(FriendPackageService::new);

    private Supplier<IService> instantiator;

    public IService getInstance() {
        return instantiator.get();
    }

    ServiceAction(Supplier<IService> instantiator) {
        this.instantiator = instantiator;
    }
}
