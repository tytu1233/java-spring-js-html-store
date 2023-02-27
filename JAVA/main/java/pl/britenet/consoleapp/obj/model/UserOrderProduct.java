package pl.britenet.consoleapp.obj.model;

public class UserOrderProduct {

    private final int id_user;
    private final int id_product;

    private Product product;

    private User user;

    public UserOrderProduct(int id_user, int id_product) {
        this.id_user = id_user;
        this.id_product = id_product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId_product() {
        return id_product;
    }

    public int getId_user() {
        return id_user;
    }
}
