package uz.pdp.productreviewserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableEurekaClient
@EnableFeignClients(basePackages = "uz.pdp.clients")
public class ProductReviewServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductReviewServerApplication.class, args);
    }

}
