package pl.britenet.campusspringjune.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.britenet.consoleapp.service.*;

@Configuration
public class APIConfig {

    private final DatabaseService databaseService;

    @Autowired
    public APIConfig(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }
    @Bean
    public ProductService getProductService() {
        return new ProductService(this.databaseService);
    }
    @Bean
    public UserService getUserService() {
        return new UserService(this.databaseService);
    }
    @Bean
    public OrderService getOrderService() {
        return new OrderService(this.databaseService);
    }
    @Bean
    public CartService getCartService() {
        return new CartService(this.databaseService);
    }
    @Bean
    public OrderProductService getOrderProductService() {
        return new OrderProductService(this.databaseService);
    }
    @Bean
    public CartProductService getCartProductService() {
        return new CartProductService(this.databaseService);
    }
    @Bean
    public UserCartProductService getUserCartProductService() {return new UserCartProductService(this.databaseService);}

    @Bean
    ProductInOrderService getProductInOrderService() {return new ProductInOrderService(this.databaseService);}

}
