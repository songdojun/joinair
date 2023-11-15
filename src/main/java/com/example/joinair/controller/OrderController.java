package com.example.joinair.controller;

        import com.example.joinair.dto.ORDERS;
        import com.example.joinair.dto.ORDER_DETAIL;
        import com.example.joinair.service.OrderService;
        import jakarta.servlet.http.HttpSession;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestMapping;

        import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {


    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public String placeOrder(HttpSession session, Model model) {
        // 세션에서 카트 정보를 가져와서 ORDER_DETAIL 객체로 변환하여 DB에 저장
        orderService.placeOrderFromCart(session);

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
        // 리다이렉트 없이 현재 페이지에서 유지 => 콘솔창에서는 에러 뜸 /
        return "redirect:/order/orderConfirm/" + orderId;
    }
    @GetMapping("/orderConfirm/{orderId}")
    public String showOrderConfirmPage(@PathVariable Integer orderId, Model model) {
        // orderId를 이용하여 주문 정보를 가져오는 메서드 호출
        ORDERS order = orderService.getOrder(orderId);
        logger.info("Received orderId: {}", orderId);
        List<ORDER_DETAIL> orderDetails = orderService.getOrderDetails(orderId);

        model.addAttribute("order", order);
        model.addAttribute("orderDetails", orderDetails);

        return "orderConfirm";
    }


}