package com.codegym.thithuchanh.Controller;

import com.codegym.thithuchanh.Dto.OrderDto;
import com.codegym.thithuchanh.Model.Order;
import com.codegym.thithuchanh.Service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String listOrders(
            @RequestParam(name = "startDate", required = false) String startDateStr,
            @RequestParam(name = "endDate", required = false) String endDateStr,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(name = "top", required = false, defaultValue = "false") boolean top,
            Model model) {

        List<Order> orders;

        if (top) {
            orders = orderService.getTopOrders(limit);
        } else {
            LocalDateTime startDate = null;
            LocalDateTime endDate = null;

            if (startDateStr != null && !startDateStr.isEmpty()) {
                startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
            }

            if (endDateStr != null && !endDateStr.isEmpty()) {
                endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atTime(23, 59, 59);
            }

            orders = orderService.getOrdersByDateRange(startDate, endDate);
        }

        model.addAttribute("orders", orders);
        return "list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Order order = orderService.findById(id);
        if (order != null) {
            model.addAttribute("order", order);
            return "edit_order";
        } else {
            return "redirect:/orders";
        }
    }

    @PostMapping("/edit")
    public String updateOrder(@RequestParam("id") Long id,
                              @RequestParam("productType") String productType,
                              @RequestParam("productName") String productName,
                              @RequestParam("purchaseDate") String purchaseDateStr,
                              @RequestParam("quantity") Integer quantity) {

        Order order = orderService.findById(id);
        if (order != null) {
            order.getProduct().getProductType().setNameType(productType);
            order.getProduct().setNameProduct(productName);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime purchaseDate = LocalDate.parse(purchaseDateStr, formatter).atStartOfDay();
            order.setPurchaseDate(purchaseDate);
            order.setQuantity(quantity);

            orderService.save(order);
        }
        return "redirect:/orders";
    }
//@GetMapping("/edit/{id}")
//public String showEditForm(@PathVariable("id") Long id, Model model) {
//    Order order = orderService.findById(id);
//    if (order != null) {
//        OrderDto orderDto = new OrderDto();
//        orderDto.setOrderId(order.getOrderId());
//        orderDto.setProductType(order.getProduct().getProductType().getNameType());
//        orderDto.setProductName(order.getProduct().getNameProduct());
//        orderDto.setPurchaseDate(order.getPurchaseDate().toLocalDate());
//        orderDto.setQuantity(order.getQuantity());
//        orderDto.setProductId(order.getProduct().getProductId());
//
//        model.addAttribute("orderDto", orderDto);
//        return "edit_order";
//    } else {
//        return "redirect:/orders";
//    }
//}
//
//    @PostMapping("/edit")
//    public String updateOrder(@Valid @ModelAttribute("orderDto") OrderDto orderDto,
//                              BindingResult result) {
//        if (result.hasErrors()) {
//            return "edit_order";
//        }
//
//        Order order = orderService.findById(orderDto.getOrderId());
//        if (order != null) {
//            order.getProduct().getProductType().setNameType(orderDto.getProductType());
//            order.getProduct().setNameProduct(orderDto.getProductName());
//            order.setPurchaseDate(orderDto.getPurchaseDate().atStartOfDay());
//            order.setQuantity(orderDto.getQuantity());
//
//            orderService.save(order);
//        }
//        return "redirect:/orders";
//    }
}

