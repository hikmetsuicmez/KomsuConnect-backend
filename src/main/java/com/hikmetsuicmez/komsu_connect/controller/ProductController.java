package com.hikmetsuicmez.komsu_connect.controller;

import com.hikmetsuicmez.komsu_connect.controller.base.RestBaseController;
import com.hikmetsuicmez.komsu_connect.response.ApiResponse;
import com.hikmetsuicmez.komsu_connect.service.ProductService;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Validated
public class ProductController extends RestBaseController {

    private final ProductService productService;

    @PostMapping("/{productId}/rate")
    public ApiResponse<String> rateProduct(
            @PathVariable Long productId,
            @RequestParam @DecimalMin(value = "0.0", inclusive = true, message = "Rating must be at least 0.0")
            @DecimalMax(value = "5.0", inclusive = true, message = "Rating must be at most 5.0") Double rating) {
        productService.rateProduct(productId, rating);
        return ApiResponse.success("The point was awarded successfully.");
    }

}
