package pl.britenet.consoleapp.obj.builder;

import pl.britenet.consoleapp.obj.model.Product;
import pl.britenet.consoleapp.obj.model.User;
import pl.britenet.consoleapp.obj.model.UserOrderProduct;

public class UserOrderProductBuilder {

    private final UserOrderProduct userOrderProduct;

    public UserOrderProductBuilder(int id_user, int id_product) {
        this.userOrderProduct = new UserOrderProduct(id_user, id_product);
    }

    public UserOrderProductBuilder setUser(User user) {
        this.userOrderProduct.setUser(user);
        return this;
    }

    public UserOrderProductBuilder setProduct(Product product) {
        this.userOrderProduct.setProduct(product);
        return this;
    }

    public UserOrderProduct getUserOrderProduct() {
        return this.userOrderProduct;
    }
}
