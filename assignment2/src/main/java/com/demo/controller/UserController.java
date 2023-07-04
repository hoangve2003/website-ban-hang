package com.demo.controller;

import com.demo.model.Account;
import com.demo.model.Order;
import com.demo.model.OrderDetail;
import com.demo.model.Product;
import com.demo.repository.AccountDAO;
import com.demo.repository.OrderDAO;
import com.demo.repository.OrderDetailDAO;
import com.demo.service.CartService;
import com.demo.service.CategoryService;
import com.demo.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    HttpSession session;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    CartService cart;
    List<PriceRange> priceRangeList = Arrays.asList(
            new PriceRange(0, 0, Integer.MAX_VALUE, "Tất cả"),
            new PriceRange(1, 0, 10000000, "Dưới 10 triệu"),
            new PriceRange(2, 10000000, 20000000, "Từ 10-20 triệu"),
            new PriceRange(3, 20000000, Integer.MAX_VALUE, "Trên 20 triệu")
    );
    @Autowired
    AccountDAO accountDAO;
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    OrderDetailDAO orderDetailDAO;

    @ModelAttribute("cart")
    CartService getCart() {
        return cart;
    }

    @RequestMapping("/")
    public String index(
            @RequestParam(defaultValue = "") Optional<String> keyword,
            @RequestParam(defaultValue = "") Optional<String> categoryId,
            @RequestParam(defaultValue = "0") Integer priceRangeId,
            @RequestParam(defaultValue = "0") Optional<Integer> p,
            Model model) {

        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        model.addAttribute("priceRangeList", priceRangeList);
        model.addAttribute("categoryList", categoryService.getAll());

        int minPrice = priceRangeList.get(priceRangeId).minValue;
        int maxPrice = priceRangeList.get(priceRangeId).maxValue;
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Product> page = searchProducts(categoryId.orElse(""), keyword.orElse(""), minPrice, maxPrice, pageable);
        model.addAttribute("productList", page);


        System.out.println("keyword=" + keyword);
        System.out.println("categoryId=" + categoryId);
        System.out.println("minPrice=" + minPrice);
        System.out.println("maxPrice=" + maxPrice);
        System.out.println("page=" + p);

        // TODO: Search & paginate

        return "home/index";
    }

    private Page<Product> searchProducts(String categoryId, String keyword, int minPrice, int maxPrice, Pageable pageable) {
        if (categoryId.isEmpty() && keyword.isEmpty() && minPrice == 0 && maxPrice == 0) {
            // No search parameters provided, return all products
            return productService.getAll(pageable);
        } else {
            // Perform search with provided parameters
            return productService.searchProduct("%" + categoryId + "%", "%" + keyword + "%", minPrice, maxPrice, pageable);
        }
    }

    @GetMapping("/detail/{id}")
    public String viewProduct(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "home/detail";
    }

    @RequestMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable int id) {
        cart.add(id);
        return "redirect:/cart";
    }

    @RequestMapping("/remove-cart/{id}")
    public String removeCart(@PathVariable int id) {
        cart.remove(id);
        if (cart.getTotal() == 0) {
            return "redirect:/";
        }
        return "redirect:/cart";
    }

    @RequestMapping("/update-cart/{id}")
    public String updateCart(@PathVariable int id, int quantity) {
        cart.update(id, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cart() {
        return "home/cart";
    }

    @GetMapping("/confirm")
    public String confirm() {
        return "home/confirm";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        return "home/about";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
//    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        // TODO: Check if user/password exists in database
        Account account = accountDAO.findByUsernameAndPassword(username, password).orElse(null);
        if (account != null) {
            session.setAttribute("username", username);
            session.setAttribute("account", account);
            if (account.isAdmin()) {
                return "redirect:/admin/category/index";
            } else {
                return "redirect:/";
            }
        }
        model.addAttribute("message", "Tên đăng nhập/mật khẩu không đúng");
        return "login";
    }

    @PostMapping("/purchase")
    public String purchase(@RequestParam String address) {
        String username =(String) session.getAttribute("username");
        System.out.println("address=" + address);
        System.out.println("username=" + username);
        System.out.println("items=" + cart.getItems());
        // TODO: Save items to database

        Order order = new Order();

        Account account = accountDAO.findByUsername(username).orElse(null);
        order.setAddress(address);
        order.setAccount(account);
        orderDAO.save(order);
        for (OrderDetail item : cart.getItems()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(item.getProduct());
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setPrice(item.getProduct().getPrice());
            orderDetailDAO.save(orderDetail);
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        session.removeAttribute("username");
        session.removeAttribute("account");
        return "redirect:/login";
    }

    @Data
    @AllArgsConstructor
    public static class PriceRange {
        int id;
        int minValue;
        int maxValue;
        String display;
    }
}
