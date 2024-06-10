package com.example.spring_app.controller;

import com.example.spring_app.model.Product;
import com.example.spring_app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute Product book) {
        this.productService.saveOrUpdate(book);
        return "redirect:/main";
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable int id, Model model) {
        Optional<Product> productOpt = this.productService.getById(id);
        if (productOpt.isEmpty()) {
            return "redirect:/main";
        }
        model.addAttribute("product", productOpt.get());
        return "product-form";
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable int id, @ModelAttribute Product product) {
        //product.setId(id);
        this.productService.saveOrUpdate(product);
        return "redirect:/main";
    }
    @PostMapping("/delete")
    public String deleteProduct(@RequestParam int id) {
        productService.delete(id);
        return "redirect:/main";
    }

}

