package com.ou.repositories;

import com.ou.pojo.ReceiptDetail;

import java.util.List;

public interface BillDetailRepository {
    void createBillDetail(ReceiptDetail receiptDetail);
    List<ReceiptDetail> getReceiptDetailByBillId(int billId);
}
