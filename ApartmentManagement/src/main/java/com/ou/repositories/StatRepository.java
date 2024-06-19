package com.ou.repositories;

import java.util.List;

public interface StatRepository {
    List<Object[]> statsRevenueByMonth(int year);
    List<Object[]> statReport();
}
