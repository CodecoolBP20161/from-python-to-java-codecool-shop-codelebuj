import com.codecool.shop.controller.*;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {

    GetTop5Controller controller;

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        Main application = new Main();
        application.controller = new GetTop5Controller();
        port(8888);

        // populate some data for the memory storage
        //populateData();

        // Always start with more specific routes
        get("/hello", (req, res) -> "Hello World");

        get("/category/:category_id", ProductController::renderProductsByCategory, new ThymeleafTemplateEngine());

        get("/cart/:product_id", CartController::addToCart, new ThymeleafTemplateEngine());

        post("/add/:product_id", CartController::increaseCartItem, new ThymeleafTemplateEngine());

        post("/remove/:product_id", CartController::decreaseCartItem, new ThymeleafTemplateEngine());

        post("/delete/:product_id", CartController::deleteCartItem, new ThymeleafTemplateEngine());

        get("/supplier/:supplier_id", ProductController::renderProductsBySupplier, new ThymeleafTemplateEngine());

        get("/shoppingcart", ProductController::renderCart, new ThymeleafTemplateEngine());

        get("/checkout", CheckoutController::renderCheckout, new ThymeleafTemplateEngine());

        post("/checkout", CheckoutController::constructorOrder);

        get("/payment", PaymentController::renderPayment, new ThymeleafTemplateEngine());
        get("/thankyou", CompletePaymentController::renderCompletePayment, new ThymeleafTemplateEngine());

        // Always add generic routes to the end
        get("/", ProductController::renderAllProducts, new ThymeleafTemplateEngine());



        get("/gettop5", GetTop5Controller::getTop5);
        post("/addproduct", GetTop5Controller::sendProduct);

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

        productDataStore.add(new Product("Lenovo Ideapad", 200f, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand", notebook, lenovo));
        productDataStore.add(new Product("Hp Probook", 220f, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", notebook, lenovo));
        productDataStore.add(new Product("Aspire R 14", 800f, "USD", "Cutting-edge graphics and top performance for the ultimate entertainment experience.", notebook, acer));

        productDataStore.add(new Product("Canon 5D Mark IV", 500f, "USD", "The EOS 5D Mark IV camera builds on the powerful legacy of the 5D series, offering amazing refinements in image quality, performance and versatility.", camera, canon));
    }
}
