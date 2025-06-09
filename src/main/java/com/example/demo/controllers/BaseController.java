package com.example.demo.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Product;
import com.example.demo.repo.ProductRepo;


@Controller
public class BaseController {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/")
    public String index(Model model) 
    {
        List<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/clubePag")
    public String clubePag() {
        return "clubePag";
    }

    @GetMapping("/equipamentoPag")
    public String equipamentoPag() {
        return "equipamentoPag";
    }

    @GetMapping("/campeaoPag")
    public String campeaoPag() {
        return "campeaoPag";
    }
    
    @GetMapping("/acessoriosPag")
    public String acessoriosPag() {
        return "acessoriosPag";
    }

    @GetMapping("/carrinhoPag")
    public String carrinhoPag() {
        return "carrinhoPag";
    }

    @GetMapping("/admin/adminPag")
    public String adminPag(Model model) {
        model.addAttribute("product", new Product());
        return "adminPag";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute Product product, @RequestParam("imageFile") MultipartFile imageFile) throws IOException
    {
        if (!imageFile.isEmpty()) 
        {
            String filename = imageFile.getOriginalFilename();
            Path uploadPath = Paths.get(System.getProperty("user.dir"), "uploads");
            if (!Files.exists(uploadPath)) 
            {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(filename);
            
            imageFile.transferTo(filePath.toFile());

            // Save relative path or URL to product.image
            product.setImage("/uploads/" + filename);
        }
        productRepo.save(product);
        return "redirect:/admin/adminPag";
    }

    @GetMapping("/admin/products")
    public String listProducts(Model model) 
    {
        List<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "productList";
    }

    @GetMapping("/admin/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) 
    {
        Product product = productRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product ID:" + id));
        model.addAttribute("product", product);
        return "editProduct";
    }
    
    @PostMapping("/admin/editProduct")
    public String updateProduct(@ModelAttribute Product product, @RequestParam("imageFile") MultipartFile imageFile) throws IOException 
    {
        Product existingProduct = productRepo.findById(product.getId()).orElse(null);
        product.setCreated_at(existingProduct.getCreated_at());

        if (!imageFile.isEmpty()) 
        {
            String filename = imageFile.getOriginalFilename();
            Path uploadPath = Paths.get(System.getProperty("user.dir"), "uploads");
            if (!Files.exists(uploadPath)) 
            {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(filename);
            imageFile.transferTo(filePath.toFile());
            product.setImage("/uploads/" + filename);
        } 
        else 
        {
            Product existing = productRepo.findById(product.getId()).orElse(null);
            if (existing != null) {
                product.setImage(existing.getImage());
            }
        }

        productRepo.save(product);
        return "redirect:/admin/products";
    }

    @PostMapping("/admin/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepo.deleteById(id);
        return "redirect:/admin/products";
}
}
