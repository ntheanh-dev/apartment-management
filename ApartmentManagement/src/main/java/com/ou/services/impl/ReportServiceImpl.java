package com.ou.services.impl;

import com.ou.apiclient.ApiClient;
import com.ou.dto.response.ApiSentiment;
import com.ou.pojo.Report;
import com.ou.pojo.Resident;
import com.ou.pojo.User;
import com.ou.repositories.ReportRepository;
import com.ou.repositories.ResidentRepository;
import com.ou.services.ReportService;
import com.ou.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private UserService userService;
    @Autowired
    private ResidentRepository residentRepository;
    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Report addReport(Report report) throws IOException, InterruptedException {
        ApiSentiment text = ApiClient.sendPostRequest("http://127.0.0.1:5000/api/classification_text",String.format("{\"text\":\"%s\"}",report.getContent()));
        report.setStatus(text.getResult());
        report.setCreatedDate(LocalDate.now());
        User u = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Resident r = residentRepository.getResidentById(u.getId());
        report.setResidentUser(r);
        return reportRepository.addReport(report);
    }

    @Override
    public List<Report> getAllReport(Map<String, String> params) {
        return reportRepository.getAllReports(params);
    }
}
