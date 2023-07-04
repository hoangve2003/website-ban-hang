package com.demo.controller;

import com.demo.model.Account;
import com.demo.model.Category;
import com.demo.model.Product;
import com.demo.service.AccountService;
import com.demo.service.CategoryService;
import com.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    AccountService accountService;
    @Autowired
    ProductService productService;

    //  Category
    @GetMapping("/admin/category/index")
    public String listCategory(Model model) {
        model.addAttribute("category", new Category());
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        return "admin/category/list";
    }

    @GetMapping("/admin/category/create")
    public String createCategory(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category/form";
    }

    @PostMapping("/admin/category/add")
    public String addCategory(@Valid @ModelAttribute("category") Category category,BindingResult result) {
       if (result.hasErrors()){
           return "admin/category/form";
       }else {
           categoryService.addCategory(category);
       }
        return "redirect:/admin/category/index";
    }

    @GetMapping("/admin/category/edit/{id}")
    public String editCategory(@PathVariable String id, Model model) {
        Category category = categoryService.getById(id).get();
        model.addAttribute("category", category);

        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        return "admin/category/form";
    }

    //  Product
    @GetMapping("/admin/product/index")
    public String listProduct(Model model) {
        model.addAttribute("product", new Product());
        List<Product> products = productService.getAllProduct();
        model.addAttribute("productList", products);
        return "admin/product/list";
    }

    @GetMapping("/admin/product/create")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "admin/product/form";
    }

    @PostMapping("/admin/product/add")
    public String addProduct(@Valid @ModelAttribute("product") @DateTimeFormat(pattern = "yyyy-MM-dd") Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/product/form";
        }else{
            productService.savePrd(product);
        }
        return "redirect:/admin/product/index";
    }

    @ModelAttribute("categoryList")
    public List<Category> getAllCategory() {
        return categoryService.getAll();
    }

    @GetMapping("/admin/product/edit/{id}")
    public String editProduct(@PathVariable Integer id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);

        List<Product> productList = productService.getAllProduct();
        model.addAttribute("productList", productList);
        return "admin/product/form";
    }

    //  Account
    @GetMapping("/admin/account/index")
    public String listAccount(Model model) {
        model.addAttribute("account", new Account());

        List<Account> accounts = accountService.getAll();
        model.addAttribute("accounts", accounts);
        return "admin/account/list";
    }

    @GetMapping("/admin/account/create")
    public String createAccount(Model model) {
        model.addAttribute("account", new Account());
        return "admin/account/form";
    }

    @PostMapping("/admin/account/add")
    public String adminAccount(@Valid @ModelAttribute("account") Account account,BindingResult result) {
        if (result.hasErrors()){
            return "admin/account/form";
        }else {
            accountService.addAccount(account);
        }
        return "redirect:/admin/account/index";
    }

    @GetMapping("/admin/account/edit/{username}")
    public String editAccount(@PathVariable String username, Model model) {
        Account account = accountService.getById(username).get();
        model.addAttribute("account", account);

        List<Account> accounts = accountService.getAll();
        model.addAttribute("accounts", accounts);
        return "admin/account/form";
    }
}
