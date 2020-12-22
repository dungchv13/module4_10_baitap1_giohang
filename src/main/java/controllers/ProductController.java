package controllers;

import entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.product.ProductService;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("cart")
@RequestMapping("/products")
public class ProductController {
    @ModelAttribute("cart")
    public List<Product> cart(){
        return new ArrayList<>();
    }

    @Autowired
    private ProductService productService;

    @GetMapping("/cartasd")
    public String cart(@ModelAttribute("cart") List<Product> cart, Model model){
        model.addAttribute("cart",cart);
        model.addAttribute("total",getTotal(cart));
        return "cart";
    }

    @GetMapping("buy")
    public String buy(@ModelAttribute("cart") List<Product> cart, Model model){
        for (Product p:cart
             ) {
            Product product = productService.findOne(p.getId());
            product.setQuantity(product.getQuantity()-p.getQuantity());
            productService.save(product);
        }

        cart = new ArrayList<>();
        model.addAttribute("cart",cart);

        List<Product> products = productService.findAll();
        model.addAttribute("products",products);
        return "index";
    }
    @GetMapping("home")
    public String listProduct(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products",products);
        return "index";
    }

    @GetMapping("/{id}")
    public String info(@PathVariable("id")Long id,Model model){
        Product product = productService.findOne(id);
        model.addAttribute("product",product);
        return "info";
    }
    @GetMapping("/cart/{id}")
    public String addToCart(@ModelAttribute("cart") List<Product> cart,@PathVariable("id")Long id,Model model){
        Product product = productService.findOne(id);
        if(isContain(id,cart)){
            Product p1 = null;
            for (Product p :cart
                 ) {
                if(p.getId().equals(id)){
                    p1 = p;
                    break;
                }
            }
            p1.setQuantity(p1.getQuantity()+1);
        }else{
            product.setQuantity(1);
            cart.add(product);
        }

        model.addAttribute("cart",cart);
        model.addAttribute("total",getTotal(cart));
        return "cart";
    }
    @GetMapping("/removeFromCart/{id}")
    public String removeFromCart(@ModelAttribute("cart") List<Product> cart,@PathVariable("id")Long id,Model model){
        Product product = productService.findOne(id);

            Product p1 = null;
            for (Product p :cart
            ) {
                if(p.getId().equals(id)){
                    p1 = p;
                    break;
                }
            }
            if(p1.getQuantity()<=1){
                cart.remove(p1);
            }

        p1.setQuantity(p1.getQuantity()-1);


        model.addAttribute("cart",cart);
        model.addAttribute("total",getTotal(cart));
        return "cart";
    }

    private boolean isContain(Long id,List<Product> cart){

        for (Product p : cart
        ) {
            if(p.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    private double getTotal(List<Product> cart){
        double total = 0;
        for (Product p: cart
             ) {
            total += p.getPrice()*p.getQuantity();
        }
        return total;
    }
}
