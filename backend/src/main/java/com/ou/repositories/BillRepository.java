package com.ou.repositories;

import com.ou.pojo.Receipt;

import java.util.List;
import java.util.Map;

public interface BillRepository {
    void createReceipt(Receipt receipt);
    List<Receipt> getAllReceipts(Map<String, String> params);
    void updateBillById(int orderID,Long price);
    List<Receipt> getAllReceiptsByResidentID(int residentID);
}
