package com.example.joinair.controller;

        import com.example.joinair.dto.ORDERS;
        import com.example.joinair.dto.ORDER_DETAIL;
        import com.example.joinair.dto.USERS;
        import com.example.joinair.service.OrderService;
        import com.example.joinair.service.UserService;
        import jakarta.servlet.http.HttpSession;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.core.Authentication;
        import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {


    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping("/place")
    @ResponseBody                                               //fetche에서 받아온 마일리지 값(문자열 콤마가 붙어있음)
    public String placeOrder(HttpSession session, Model model, String finalAmount,
                             @RequestParam("orderPname") String orderPname,
                             @RequestParam("orderDest1") String orderDest1,
                             @RequestParam("orderPhone1") String orderPhone1,
                             int afuserMileage) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // finalAmount = 마일리지 적용 값(문자열 인데 앞에 콤마가 붙어있음 // 서브스트링으로 콤마를 제거
        String total = finalAmount.replace(",", "");

        System.out.println("replace total :" + total);

        // 세션에서 카트 정보를 가져와서 ORDER_DETAIL 객체로 변환하여 DB에 저장
        orderService.placeOrderFromCart(session, total, orderPname, orderDest1, orderPhone1);
                                                //total = db저장함수에 마일리지가 적용된 값을 같이 넘겨줌
        userService.updateUserMileage(username, afuserMileage);
        // 로그 추가
        System.out.println("Order placed successfully!");

        ORDERS order = (ORDERS) session.getAttribute("order");

        Integer orderId = null;

        if (order != null) {
            orderId = order.getOrders_Num();
            session.setAttribute("orderId", orderId);


        } else {
            // 에러 처리 또는 로깅 필요
            System.out.println("Failed to retrieve orderId from session.");
        }

        session.setAttribute("order", order);

        // 결제 정보 저장
        orderService.savePaymentInformation(orderId, order.getOrders_Total_Price());

        // 주문 완료 후 장바구니 비우기 (선택적)
        session.removeAttribute("cart");

        // 리다이렉트 없이 현재 페이지에서 유지 => 콘솔창에서는 에러 뜸 /
        return String.valueOf(orderId);
    }
    @GetMapping("/orderConfirm/{orderId}")
    public String showOrderConfirmPage(@PathVariable Integer orderId, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // 여기에서 필요한 사용자 정보를 모델에 추가합니다.
        model.addAttribute("userId", username);
        model.addAttribute("userAuthorities", authentication.getAuthorities());

        // 마일리지 정보를 얻어와 모델에 추가
        if (authentication.getPrincipal() instanceof USERS) {
            USERS user = (USERS) authentication.getPrincipal();
            model.addAttribute("userMileage", user.getUser_Mileage());
        }

        ORDERS order = orderService.getOrder(orderId);
        logger.info("Received orderId: {}", orderId);
        List<ORDER_DETAIL> orderDetails = orderService.getOrderDetails(orderId);

        model.addAttribute("order", order);
        model.addAttribute("orderDetails", orderDetails);

        return "orderConfirm";
    }





}