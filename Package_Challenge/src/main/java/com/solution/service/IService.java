package com.solution.service;

/**
 * Interface is use to implement different or single responsibility services which can generate different result format.
 * @param <R> Custome response type
 * @author Sandeep Agrawal
 */
public interface IService<R> {

    R execute(String filePath);
}
