package com.codegym.thithuchanh.Controller;

import com.codegym.thithuchanh.Model.ProductType;
import com.codegym.thithuchanh.Service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping
    public String listProductTypes(Model model) {
        model.addAttribute("productTypes", productTypeService.findAll());
        return "/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("productType", new ProductType());
        return "product-type/create";
    }

    @PostMapping("/save")
    public String saveProductType(@ModelAttribute("productType") ProductType productType) {
        productTypeService.save(productType);
        return "redirect:/product-types";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("productType", productTypeService.findById(id));
        return "product-type/edit";
    }

    @PostMapping("/update/{id}")
    public String updateProductType(@PathVariable Long id, @ModelAttribute("productType") ProductType productType) {
        productType.setProductTypeId(id);
        productTypeService.save(productType);
        return "redirect:/product-types";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductType(@PathVariable Long id) {
        productTypeService.deleteById(id);
        return "redirect:/product-types";
    }
}
