package com.giao_thong.vnpay;

import com.giao_thong.model.Car;
import com.giao_thong.model.Payment;
import com.giao_thong.model.Violate;
import com.giao_thong.repository.IPaymentRepo;
import com.giao_thong.repository.IViolateRepo;
import com.giao_thong.service.CarService;
import com.giao_thong.service.ViolateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;


@Controller
@RequestMapping("/pay/vnpay")
public class PaymentVnPayController {
    public static final String URL_PAYPAL_SUCCESS = "success";
    public static final String URL_PAYPAL_CANCEL = "cancel";
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IPaymentRepo iPaymentRepo;

    @Autowired
    CarService carService;

    @Autowired
    IViolateRepo violateRepo;


    @GetMapping()
    protected ResponseEntity<?> create(@RequestParam long total, @RequestParam int idCart, @RequestParam int violateId, HttpServletRequest req) throws ServletException, IOException {

        long totalVND = (Math.round(total *100));
        total = (Math.round(total));
        String redirectUrl = "http://localhost:8080/pay/vnpay/" + URL_PAYPAL_SUCCESS + "?total=" + total+"&&idCart="+idCart+"&&violateId="+violateId;
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        long amount = totalVND;
        String bankCode = "NCB";

        String vnp_TxnRef = Config.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1";

        String vnp_TmnCode = Config.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_BankCode", bankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Nộp phạt đơn:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", redirectUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
        Map<String,String> map = new HashMap<>();
        map.put("url",paymentUrl);
        return ResponseEntity.ok(map);
    }

    @GetMapping(URL_PAYPAL_SUCCESS)
    public ModelAndView successPay(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("success");
        long total = Long.parseLong(request.getParameter("total"));
        int idCart = Integer.parseInt(request.getParameter("idCart"));
        int violateId = Integer.parseInt(request.getParameter("violateId"));
        Car car = carService.findById(idCart);
        Violate violate = violateRepo.findById(violateId).get();
        violate.setStatus("đã đóng phạt");
        violateRepo.save(violate);
        Payment payment = new Payment();
        payment.setCar(car);
        payment.setTotal(total);
        payment.setViolate(violate);
        payment.setTime(LocalDateTime.now());
        iPaymentRepo.save(payment);
        return modelAndView;
    }
}
