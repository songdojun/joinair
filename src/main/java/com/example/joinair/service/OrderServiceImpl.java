package com.example.joinair.service;

import com.example.joinair.dto.ORDERS;
import com.example.joinair.dto.ORDER_DETAIL;
import com.example.joinair.dto.PAYMENT;
import com.example.joinair.mapper.paymentMapper;
import com.example.joinair.entity.Item;
import com.example.joinair.mapper.OrderMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    @Autowired
    private paymentMapper paymentMapper;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    // 장바구니에서 주문을 처리하는 메서드
    @Override
    public void placeOrderFromCart(HttpSession session, String total) {
        // 세션에서 장바구니 정보를 가져옴
        List<Item> cart = (List<Item>) session.getAttribute("cart");


        if (cart == null) {
            // cart가 null인 경우: 세션에서 "cart" 속성이 설정되지 않았거나, 값이 null입니다.
            System.out.println("장바구니가 비어 있습니다.");
        } else if (cart.isEmpty()) {
            // cart가 비어 있는 경우: 세션에서 "cart" 속성은 설정되어 있지만 비어 있습니다.
            System.out.println("장바구니가 비어 있습니다.");
        } else {
            // cart에 항목이 있는 경우: 장바구니에 항목이 포함되어 있습니다.
            System.out.println("장바구니에 항목이 포함되어 있습니다.");

            // cart에 있는 항목을 확인하려면 반복문을 사용합니다.
            for (Item item : cart) {
                // 각 항목에 대한 처리
                System.out.println("상품명: " + item.getProduct().getPro_Name() + ", 수량: " + item.getQuantity());
            }
        }
        // 장바구니 정보를 주문 상세 정보로 변환
        List<ORDER_DETAIL> orderDetails = convertCartToOrderDetails(cart);


        // 주문 정보를 생성하여 데이터베이스에 저장
        ORDERS order = convertCartToOrder(cart);

        //toal이 마일리지가 적용된 값인데 문자열이니까 int형으로 바꿔서 order객체 안에 가격으로 넣음
        order.setOrders_Total_Price(Integer.parseInt(total));
        orderMapper.insertOrder(order);

        // orders_Num을 가져와서 order_detail에 설정
        int orderId = order.getOrders_Num();

        // 주문 상세 정보를 데이터베이스에 저장
        for (ORDER_DETAIL orderDetail : orderDetails) {

            orderDetail.setOrders_Num(orderId);
            orderMapper.insertOrderDetail(orderDetail);
        }



        // 주문 정보를 세션에 저장
        session.setAttribute("order", order);
        session.setAttribute("orderDetails", orderDetails);

        // 주문 완료 후 장바구니 비우기 (선택적)
        session.removeAttribute("cart");
    }

    // 장바구니 정보를 주문 상세 정보 리스트로 변환하는 메서드
    private List<ORDER_DETAIL> convertCartToOrderDetails(List<Item> cart) {
        List<ORDER_DETAIL> orderDetails = new ArrayList<>();
        for (Item item : cart) {
            ORDER_DETAIL orderDetail = new ORDER_DETAIL();

            // 주문 상세 정보 설정
            orderDetail.setOD_Pro_Name(item.getProduct().getPro_Name());
            orderDetail.setOD_Totalweight(item.getProduct().getPro_Weight());
            orderDetail.setOD_Qua(item.getQuantity());
            orderDetail.setOD_Total_Price((int) item.getSubtotal());
            orderDetail.setOD_Pro_Price(item.getProduct().getPro_Price());

            // 다른 필요한 정보들도 설정 가능

            // 리스트에 주문 상세 정보 추가
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }


    // 장바구니 정보를 주문으로 변환하는 메서드
    private ORDERS convertCartToOrder(List<Item> cart) {
        ORDERS order = new ORDERS();

        // 세션에서 사용자 ID 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

//        String userId = (String) session.getAttribute("User_Id");
        order.setUser_Id(userId);

        System.out.println(order.getUser_Id());

        int totalWeight = 0;
        int totalPrice = 0;

        // 각 상품을 주문에 추가
        for (Item item : cart) {
            // 주문 상세 정보를 설정
            totalWeight += item.getProduct().getPro_Weight();
            totalPrice += item.getSubtotal();
        }

        // 주문 정보 설정
        order.setOrders_Totalweight(totalWeight);
        order.setOrders_Total_Price(totalPrice);

        System.out.println(order);

        return order;
    }

    @Override
    public ORDERS getOrder(int orderId) {
        return orderMapper.getOrder(orderId);
    }

    @Override
    public List<ORDER_DETAIL> getOrderDetails(int orderId) {
        return orderMapper.getOrderDetails(orderId);
    }

    @Override
    public void savePaymentInformation(int orderId, int totalPrice) {
        PAYMENT payment = new PAYMENT();
        payment.setOrderId(orderId);
        payment.setPAY_PRICE(totalPrice);
        payment.setPAY_METHOD("카드"); // 고정 값
        payment.setPAY_STATUS("완료"); // 고정 값

        paymentMapper.insertPayment(payment);
    }
}