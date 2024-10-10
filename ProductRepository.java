import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private List<Product> products = new ArrayList<>();
    private static ProductRepository instance;
    private static final String FILE_NAME = "products.txt";


    private ProductRepository() {
        loadFromFile();
    }

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }


    public void addProduct(Product product) {
        products.add(product);
        saveToFile();
    }

    // da o update nos produtos
    public void updateProduct(int id, Product updatedProduct) {
        for (Product product : products) {
            if (product.getId() == id) {
                product.setName(updatedProduct.getName());
                product.setPrice(updatedProduct.getPrice());
                product.setDescription(updatedProduct.getDescription());
                break;
            }
        }
        saveToFile();
    }

    // Deleta um produto e salva no arquivo
    public void deleteProduct(int id) {
        products.removeIf(product -> product.getId() == id);
        saveToFile();
    }


    public List<Product> getAllProducts() {
        return new ArrayList<>(products); // Retorna uma cópia da lista de produtos
    }


    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Product product : products) {
                writer.write(product.getId() + ";" + product.getName() + ";" +
                        product.getPrice() + ";" + product.getDescription());
                writer.newLine();  // Nova linha para cada produto
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar produtos no arquivo: " + e.getMessage());
        }
    }

    // Carrega os produtos de um arquivo .txt
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;  // Se o arquivo não existe, não há nada para carregar
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                double price = Double.parseDouble(data[2]);
                String description = data[3];

                products.add(new Product(id, name, price, description));
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar produtos do arquivo: " + e.getMessage());
        }
    }

    // Busca um produto por ID
    public Product findProductById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
