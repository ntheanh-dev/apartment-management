package com.ou.services.impl;

import com.ou.repositories.StatRepository;
import com.ou.services.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StatServiceImpl implements StatService {
    @Autowired
    private StatRepository statRepository;

    @Override
    @Cacheable(value = "statsRevenueByMonth")
    public List<Object[]> statsRevenueByMonth() {
        int year = LocalDate.now().getYear();
        return this.statRepository.statsRevenueByMonth(year);
    }

    @Override
    @Cacheable(value = "statReport")
    public List<Object[]> statReport() {
        return this.statRepository.statReport();
    }
}
