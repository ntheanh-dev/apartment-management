package com.ou.repositories;

import com.ou.pojo.Report;

import java.util.List;
import java.util.Map;

public interface ReportRepository {
    void addReport(Report report);
    List<Report> getAllReports(Map<String, String> params);
    Long countReports();
}
