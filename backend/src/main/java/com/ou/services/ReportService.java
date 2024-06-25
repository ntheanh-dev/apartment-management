package com.ou.services;

import com.ou.pojo.Report;
import com.ou.pojo.User;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ReportService {
    void addReport(Report report, User u) throws IOException, InterruptedException;
    List<Report> getAllReport(Map<String, String> params);
    Long countReport();
}
