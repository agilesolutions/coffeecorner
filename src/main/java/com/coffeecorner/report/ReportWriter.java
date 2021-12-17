package com.coffeecorner.report;

public interface ReportWriter<T, R> {

    void report(T entity);

}
