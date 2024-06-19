package com.ou.services;

import com.ou.pojo.Report;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ReportService {
    Report addReport(Report report) throws IOException, InterruptedException;
    List<Report> getAllReport(Map<String, String> params);
    Long countReport();
}
