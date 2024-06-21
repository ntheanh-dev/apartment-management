package com.ou.services;

import com.ou.pojo.Receipt;

import java.util.List;
import java.util.Map;

public interface BillService {
    void createAllBill();
    void updateBill(int billID,Long price);
    List<Receipt> getAllBill(Map<String, String> params);
    List<Receipt> getAllReceiptsByResidentID(int residentID);
}
