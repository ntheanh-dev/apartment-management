package com.ou.repositories;

import com.ou.pojo.Receipt;

import java.util.List;

public interface BillRepository {
    void createReceipt(Receipt receipt);
    List<Receipt> getAllReceipts();
    void updateBillById(int orderID);
}
