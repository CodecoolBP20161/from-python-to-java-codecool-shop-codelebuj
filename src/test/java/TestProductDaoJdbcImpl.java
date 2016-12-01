import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class TestProductDaoJdbcImpl {

    private Product product1;
    private Product product2;
    private Product product3;

    SupplierDao supplierDataStore = SupplierDaoJdbc.getInstance();
    private Supplier lenovo;
    private Supplier amazon;
    ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJdbc.getInstance();
    private ProductCategory tablet;
    private ProductCategory notebook;
    ProductDao productDataStore = ProductDaoJdbcImpl.getInstance();



    @Before
    public void setUp() {


        lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        amazon = new Supplier("Amazon", "Computers");
        supplierDataStore.add(amazon);

        tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        notebook = new ProductCategory("Notebook", "Hardware", "A tablet computer, commonly shortened to tablet.");
        productCategoryDataStore.add(notebook);


        product1 = new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon);
        productDataStore.add(product1);
        product2 = new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo);
        productDataStore.add(product2);
        product3 = new Product("Amazon Fire HD 8", 89, "USD", "Amazons latest Fire HD 8 tablet is a great value for media consumption.", notebook, amazon);
        productDataStore.add(product3);

    }

    @Test
    public void addFindTest(){
        int productId = product2.getId();
        productDataStore.find(productId);
        System.out.println(product2.getId());

        assertEquals(15, productId);
    }


    @Test
    public void removeTest(){
        productDataStore.remove(product3.getId());
        assertNull(productDataStore.find(3));
    }


    @Test
    public void getAllTest(){
        List<Product> allProduct;
        allProduct = asList(product1, product2, product3);
        assertEquals(allProduct, productDataStore.getAll());

    }

    @Test
    public void getBySupplierTest(){
        List<Product> allProductBySupplier;
        allProductBySupplier = asList(product2);
        assertEquals(allProductBySupplier, productDataStore.getBy(lenovo));

    }

    @Test
    public void getByProductCategoryTest(){
        List<Product> allProductByProductCategory;
        allProductByProductCategory = asList(product3);
        assertEquals(allProductByProductCategory, productDataStore.getBy(notebook));

    }


    @After
    public void tearDown() {
        supplierDataStore.remove(lenovo.getId());
        supplierDataStore.remove(amazon.getId());
        productCategoryDataStore.remove(notebook.getId());
        productCategoryDataStore.remove(tablet.getId());
        productDataStore.remove(product1.getId());
        productDataStore.remove(product2.getId());
        productDataStore.remove(product3.getId());

    }


}
