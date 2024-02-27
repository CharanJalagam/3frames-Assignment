import java.util.*;

// Class for adding products
class Product{
    String name;
    String description;

    String cost;
//CONSTRUCTOR
    public Product(String name, String description, String cost){
        this.name=name;
        this.description=description;
        this.cost=cost;
    }
}
//class to add products based on category and product details
class ProductDetails{
    private Map<String,Set<Product>>categoryMap;
    private Map<String,Product>productMap;

    // Constructor to initialize data structures
    public ProductDetails(){
        categoryMap =new HashMap<>();
        productMap =new HashMap<>();
    }
    // Method to add a products
    public void addProduct(String category,String productName,String productDescription,String cost) {
        Product product=new Product(productName,productDescription,cost);
        // Update product map
        productMap.put(productName,product);
        // Update category map
        if(categoryMap.containsKey(category)){
            categoryMap.get(category).add(product);
        }
        else{
            categoryMap.put(category,new HashSet<>());
            categoryMap.get(category).add(product);
        }
    }

    // Method to search for products based on a given parameter
    public Set<Product> searchProducts(String parameter){
        Set<Product>result=new HashSet<>();
        // Search in product names and descriptions and cost
        for (Product product:productMap.values()){
            if(product.name.contains(parameter)||product.description.contains(parameter)||product.cost.contains(parameter)) {
                result.add(product);
            }
        }
        return result;
    }
    // Method to get products in a specific category
    public Set<Product> getProductsFromCategory(String category){
        return categoryMap.getOrDefault(category,new HashSet<>());
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        ProductDetails details = new ProductDetails();
        // User input loop
        while(true) {
            System.out.println("1. Add Product\n2. Search Products\n3. Search Category\n4. Exit");
            System.out.print("Enter your choice: ");
            int user=sc.nextInt();
            sc.nextLine();

            switch(user) {
                case 1:
                    System.out.print("Enter category: ");
                    String category=sc.nextLine();
                    System.out.print("Enter product name: ");
                    String productName=sc.nextLine();
                    System.out.print("Enter product description: ");
                    String productDescription=sc.nextLine();
                    System.out.print("Enter product cost: ");
                    String productCost=sc.nextLine();
                    details.addProduct(category,productName,productDescription,productCost);
                    System.out.println("Product added successfully!\n");
                    break;

                case 2:
                    System.out.print("Enter search parameter: ");
                    String searchParameter=sc.nextLine();
                    Set<Product> searchResults = details.searchProducts(searchParameter);
                    if(searchResults.isEmpty()) {
                        System.out.println("No Results Found!!!");
                    }
                    else{
                        System.out.println("Search Results: ");
                        for(Product p:searchResults){
                            System.out.println("Product Name : "+p.name+" // Product Description : "+p.description);
                        }
                    }
                    System.out.println("*****************************");
                    break;


                case 3:
                    System.out.print("Enter search category: ");
                    String searchCategory = sc.nextLine();
                    Set<Product>CategoryResults = details.getProductsFromCategory(searchCategory);
                    System.out.println("Search Results: " );
                    if(CategoryResults.isEmpty()){
                        System.out.println("No products found under this category");
                    }
                    else{
                        for (Product p:CategoryResults){
                            System.out.println("Product Name : "+p.name);
                        }
                    }
                    System.out.println("*****************************");
                    break;

                case 4:
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
