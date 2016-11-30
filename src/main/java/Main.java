import static spark.Spark.*;
import static spark.Spark.get;
import static spark.debug.DebugScreen.enableDebugScreen;

import com.codecool.shop.controller.CartController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // populate some data for the memory storage
        populateData();

        // Always start with more specific routes
        get("/hello", (req, res) -> "Hello World");

        get("/category/:category_id", ProductController::renderProductsByCategory, new ThymeleafTemplateEngine());

        get("/cart/:product_id", CartController::addToCart, new ThymeleafTemplateEngine());

        get("/supplier/:supplier_id", ProductController::renderProductsBySupplier, new ThymeleafTemplateEngine());

        get("/shoppingcart", ProductController::renderCart, new ThymeleafTemplateEngine());


        // Always add generic routes to the end
        get("/", ProductController::renderAllProducts, new ThymeleafTemplateEngine());

//        get("/cart/show/", ProductController::renderCart, new ThymeleafTemplateEngine());

        // Add this line to your project to enable the debug screen
        enableDebugScreen();
    }



    public static void populateData() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier acer = new Supplier("Acer", "Computers");
        supplierDataStore.add(acer);
        Supplier canon = new Supplier("Canon", "Camera");
        supplierDataStore.add(canon);
        Supplier apple = new Supplier("Apple corp.", "Overpriced shit");


        //setting up a new product category

        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory notebook = new ProductCategory("Notebook", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(notebook);
        ProductCategory camera = new ProductCategory("Camera", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(camera);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));

        productDataStore.add(new Product("Lenovo Ideapad", 200f, "USD", "hahi", notebook, lenovo));
        productDataStore.add(new Product("Hp Probook", 220f, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", notebook, lenovo));
        productDataStore.add(new Product("Aspire R 14", 800f, "USD", "Cutting-edge graphics and top performance for the ultimate entertainment experience.", notebook, acer));

        productDataStore.add(new Product("Canon 5D Mark IV", 500f, "USD", "The EOS 5D Mark IV camera builds on the powerful legacy of the 5D series, offering amazing refinements in image quality, performance and versatility.", camera, canon));
        // Category Tests
//        ProductCategoryDaoJdbc x = new ProductCategoryDaoJdbc();
//        System.out.println(x.getAll());
//        System.out.println(x.find(3));
//        x.remove(2);
//        System.out.println(x.getAll());
//        // Supplier tests
//        SupplierDao supp = new SupplierDaoJdbc();
//        System.out.println(supp.getAll());
//        supp.remove(1);
//        System.out.println("after removed id 1");
//        System.out.println(supp.getAll());
//
//        System.out.println("Finding an element:");
//        System.out.println(supp.find(2));
//        System.out.println("\nCheck if inserts new E:");
//        supp.add(apple);
//        System.out.println(supp.getAll());
//
//        System.out.println("\n-------------\nProduct test");
//        ProductDao c = new ProductDaoJdbcImpl();
//        System.out.println(c.find(1));
    }
}
