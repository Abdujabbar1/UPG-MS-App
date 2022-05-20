package uz.pdp.clients.book;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient("product-service")
public interface ProductClient {
    @GetMapping("/api/product-service/view/{productId}")
    Map<String, Object> getBookInfo(@PathVariable Long productId);

}
