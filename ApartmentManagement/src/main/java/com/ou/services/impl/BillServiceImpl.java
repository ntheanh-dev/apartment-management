package com.ou.services.impl;

import com.ou.pojo.Contract;
import com.ou.pojo.Receipt;
import com.ou.pojo.ReceiptDetail;
import com.ou.repositories.BillDetailRepository;
import com.ou.repositories.BillRepository;
import com.ou.repositories.ContractRepository;
import com.ou.repositories.ServiceReposity;
import com.ou.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ServiceReposity serviceReposity;

    @Autowired
    private BillDetailRepository billDetailRepository;

    @Override
    public void createAllBill() {
        for (Contract c : contractRepository.getAllContractsActive()){
            Receipt r = new Receipt();
            r.setContract(c);
            r.setTitle("Hóa đơn tiền nhà tháng " + LocalDate.now().minusMonths(1).getMonthValue() );
            r.setStartedDate(LocalDate.now().minusMonths(1));
            r.setEndDate(LocalDate.now().minusDays(1));
            billRepository.createReceipt(r);
            for(com.ou.pojo.Service s : serviceReposity.getServices()){
                ReceiptDetail rd = new ReceiptDetail();
                rd.setReceipt(r);
                rd.setServices(s);
                switch (s.getName()){
                    case "Điện":
                        rd.setAmount((int) ((Math.random() * (2000 - 500 + 1)) + 500));
                        break;
                    case "Nước":
                        rd.setAmount((int) ((Math.random() * (15 - 8 + 1)) + 8));
                        break;
                    case "An ninh":
                        rd.setAmount(1);
                        break;
                    case "Xe":
                        rd.setAmount(5);//Ví dụ
                        break;
                }
                billDetailRepository.createBillDetail(rd);
            }

        }
    }

    @Override
    public void updateBill(int billID,Long price) {
        billRepository.updateBillById(billID,price);
    }
}
