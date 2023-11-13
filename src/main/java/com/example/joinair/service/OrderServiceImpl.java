package com.example.joinair.service;

import com.example.joinair.dto.ORDERS;
import com.example.joinair.dto.ORDER_DETAIL;
import com.example.joinair.entity.Item;
import com.example.joinair.mapper.OrderMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    // 장바구니에서 주문을 처리하는 메서드
    @Override
    public void placeOrderFromCart(HttpSession session) {
        // 세션에서 장바구니 정보를 가져옴
        List<Item> cart = (List<Item>) session.getAttribute("cart");

        // 장바구니 정보를 주문 상세 정보로 변환
        List<ORDER_DETAIL> orderDetails = convertCartToOrderDetails(cart);

        // 주문 상세 정보를 데이터베이스에 저장
        for (ORDER_DETAIL orderDetail : orderDetails) {
            orderMapper.insertOrderDetail(orderDetail);
        }

        // 주문 정보를 생성하여 데이터베이스에 저장
        ORDERS order = convertCartToOrder(cart, session);
        orderMapper.insertOrder(order);

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
    private ORDERS convertCartToOrder(List<Item> cart, HttpSession session) {
        ORDERS order = new ORDERS();

        // 세션에서 사용자 ID 가져오기
        String userId = (String) session.getAttribute("User_Id");
        order.setUser_Id(userId);

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

        return order;
    }

}
