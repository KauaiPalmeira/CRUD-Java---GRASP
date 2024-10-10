import java.util.Scanner;

public class Main {
    private static ProductController controller = ProductController.getInstance();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("--------------------");
            System.out.println("1. List Products");
            System.out.println("2. Add Product");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.println("--------------------");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("List of Products:");
                    controller.getProducts().forEach(System.out::println);
                    break;
                case 2:
                    addProductFlow(scanner);
                    break;
                case 3:
                    updateProductFlow(scanner);
                    break;
                case 4:
                    deleteProductFlow(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Option! Try Again.");
            }
        }
    }

    private static void addProductFlow(Scanner scanner) {
        System.out.println("Add a New Product (type 'Finalizar' to finish):");

        System.out.print("Enter Product Name: ");
        String name = scanner.next();
        if (name.equalsIgnoreCase("Finalizar")) return;

        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter Product Description: ");
        scanner.nextLine(); // Consume newline left-over
        String description = scanner.nextLine();
        if (description.equalsIgnoreCase("Finalizar")) return;

        controller.createProduct(name, price, description);
        System.out.println("Product Added!");
    }

    private static void updateProductFlow(Scanner scanner) {
        System.out.print("Enter Product ID to update: ");
        int idToUpdate = scanner.nextInt();

        Product product = controller.findProductById(idToUpdate);
        if (product == null) {
            System.out.println("Product not found!");
            return;
        }

        System.out.println("Updating Product: " + product.getName());
        System.out.println("Type 'Finalizar' to finish or 'skip' to keep the current value.");

        // Update Name
        System.out.print("Enter New Name (ou 'skip'): ");
        String newName = scanner.next();
        if (newName.equalsIgnoreCase("Finalizar")) return;
        if (newName.equalsIgnoreCase("skip")) newName = product.getName();

        // Update Price
        System.out.print("Enter New Price (or 'skip'): ");
        String priceInput = scanner.next();
        double newPrice = product.getPrice(); // Default value is the current price
        if (!priceInput.equalsIgnoreCase("skip")) {
            if (priceInput.equalsIgnoreCase("Finalizar")) return;
            newPrice = Double.parseDouble(priceInput);
        }

        // Update Description
        System.out.print("Enter New Description (or 'skip'): ");
        scanner.nextLine(); // Consume newline left-over
        String newDescription = scanner.nextLine();
        if (newDescription.equalsIgnoreCase("Finalizar")) return;
        if (newDescription.equalsIgnoreCase("skip")) newDescription = product.getDescription();

        controller.updateProduct(idToUpdate, newName, newPrice, newDescription);
        System.out.println("Product Updated!");
    }

    private static void deleteProductFlow(Scanner scanner) {
        System.out.print("Enter Product ID to delete: ");
        int idToDelete = scanner.nextInt();
        controller.deleteProduct(idToDelete);
        System.out.println("Product Deleted!");
    }
}
