package com.ou.controllers;

import com.google.firebase.FirebaseException;
import com.ou.services.BillService;
import com.ou.services.FirebaseService;
import com.ou.services.VnpayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/payment")
public class VnpayController {
    @Autowired
    private VnpayService vnPayService;

    @Autowired
    private FirebaseService firebaseService;

    @Autowired
    private BillService billService;

    @GetMapping("/submitOrder")
    public String submidOrder(@RequestParam("amount") int orderTotal,
                              @RequestParam("orderInfo") String orderInfo,
                              HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(request, orderTotal, orderInfo, baseUrl);
        return "redirect:" + vnpayUrl;
    }

    // Sau khi hoàn tất thanh toán, VNPAY sẽ chuyển hướng trình duyệt về URL này
    @GetMapping("/vnpay-payment-return")
    public String paymentCompleted(HttpServletRequest request, Model model) {
        int paymentStatus = vnPayService.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);
        if(paymentStatus == 1){
            int receiptID = Integer.parseInt(orderInfo);
            billService.updateBill(receiptID,Long.parseLong(totalPrice));
            try {
                firebaseService.deleteNotifyDocuments("notifications","receiptId",receiptID);
            }catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println("Something went wrong with firebase");
                System.out.println("Cannot delete monthly bill notification on firebase");
            }
            return "orderSuccess";
        }else{
            return "orderFail";
        }
    }
}
