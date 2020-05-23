package com.example.springmvc.krisspringmvc.controller;


import com.example.springmvc.krisspringmvc.model.Product;
import com.example.springmvc.krisspringmvc.repository.ProductRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Api(value = "ProductControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(path = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(path = "/products/add", method = RequestMethod.GET)
    @ApiOperation("Creates a new product")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Product.class)})
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "edit";
    }

    @RequestMapping(path = "products", method = RequestMethod.POST)
    public String saveProduct(Product product) {
        productRepository.save(product);
        return "redirect:/";
    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public String getAllProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @RequestMapping(path = "/products/edit/{id}", method = RequestMethod.GET)
    public String editProduct(Model model, @PathVariable(value = "id") String id) {
        model.addAttribute("product", productRepository.findById(id));
        return "edit";
    }

    @RequestMapping(path = "/products/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable(name = "id") String id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }
}

