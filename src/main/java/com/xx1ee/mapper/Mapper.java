package com.xx1ee.mapper;

public interface Mapper<F, T> {
    T mapFrom(F object);
}
