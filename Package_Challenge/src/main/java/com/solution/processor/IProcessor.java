package com.solution.processor;

/**
 * Interface is use to implement different or single responsibility calculation or computations
 * which can generate different result format.
 * @param <R> Custom response type
 * @param <T> Custom input type
 * @author Sandeep Agrawal
 */
public interface IProcessor<R, T> {
    R process(T t);
}
