import java.util.List;

public class ProductController {
    private static ProductController instance;
    private ProductRepository productRepository = ProductRepository.getInstance();

    private ProductController() {}

    public static synchronized ProductController getInstance() {
        if (instance == null) {
            instance = new ProductController();
        }
        return instance;
    }

    public List<Product> getProducts() {
        return productRepository.getAllProducts(); // Pega os produtos do repositório
    }

    public void createProduct(String name, double price, String description) {
        int id = productRepository.getAllProducts().size() + 1;
        Product newProduct = new Product(id, name, price, description);
        productRepository.addProduct(newProduct);
        System.out.println("Product created: " + newProduct);
    }

    public void updateProduct(int id, String name, double price, String description) {
        Product updatedProduct = new Product(id, name, price, description);
        productRepository.updateProduct(id, updatedProduct);
        System.out.println("Product updated: " + updatedProduct);
    }

    public void deleteProduct(int id) {
        productRepository.deleteProduct(id);
        System.out.println("Product deleted: ID = " + id);
    }

    public Product findProductById(int id) {
        return productRepository.findProductById(id);
    }
}
