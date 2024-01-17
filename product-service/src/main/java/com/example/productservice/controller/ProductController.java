package com.example.productservice.controller;

import com.example.productservice.entity.Product;
import com.example.productservice.pojo.RequestProduct;
import com.example.productservice.pojo.RequestUpdateProduct;
import com.example.productservice.pojo.ResponseProduct;
import com.example.productservice.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@RequestMapping("/product-service")
@Controller
public class ProductController {

    private final ProductService productService;
    private final String greetingMessage;

    public ProductController(ProductService productService, @Value("${greeting.welcome}") String greetingMessage) {
        this.productService = productService;
        this.greetingMessage = greetingMessage;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @GetMapping("/health")
    @ResponseBody
    public String index(){

        return "OK";
    }

    @PostMapping("/products")
    @ResponseBody
    public ResponseEntity<ResponseProduct> createUser(@Valid @RequestBody RequestProduct requestProduct) {

        var product = productService.createProduct(requestProduct);

        ResponseProduct responseProduct = new ResponseProduct(product);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseProduct);
    }

    @GetMapping("/welcome")
    @ResponseBody
    public String welcome(){
        return greetingMessage;
    }

    @GetMapping("/products")
    @ResponseBody
    public ResponseEntity<List<ResponseProduct>> getProducts(){

        Iterable<Product> iterable = productService.findAll();

        List<ResponseProduct> responseProducts = StreamSupport
                .stream(iterable.spliterator(), false)
                .map(ResponseProduct::new)
                .toList();

        return ResponseEntity.ok(responseProducts);
    }

    @GetMapping("/products/{id}")
    @ResponseBody
    public ResponseEntity<ResponseProduct> getProduct(@PathVariable Long id) {

        var product = productService.findById(id);

        ResponseProduct responseProduct = product == null ? null : new ResponseProduct(product);

        return ResponseEntity.ok(responseProduct);
    }

    @PutMapping("/products/{id}")
    @ResponseBody
    public ResponseEntity<ResponseProduct> updateProduct(@PathVariable Long id, @RequestBody RequestUpdateProduct requestUpdateProduct) {

        if(requestUpdateProduct.getPlusQty() != null || requestUpdateProduct.getName() != null) {
            Product product = productService.updateProductById(requestUpdateProduct, id);

            return ResponseEntity.ok(new ResponseProduct(product));
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

}
