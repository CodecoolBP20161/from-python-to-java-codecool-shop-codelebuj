import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.model.ProductCategory;
import org.junit.*;


import java.util.List;

import static java.util.Arrays.*;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;

public class TestProductCategoryDaoJdbc {


    private ProductCategory productCategory1;
    private ProductCategory productCategory2;
    private ProductCategory productCategory3;
    ProductCategoryDao productCategoryDataStore = ProductCategoryDaoJdbc.getInstance();


    @Before
    public void setUp() {
        productCategory1 = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(productCategory1);
        productCategory2 = new ProductCategory("Notebook", "Hardware", "A tablet computer, commonly shortened to tablet.");
        productCategoryDataStore.add(productCategory2);
        productCategory3 = new ProductCategory("Camera", "Hardware", "A tablet computer, commonly shortened to tablet.");
        productCategoryDataStore.add(productCategory3);

    }

    @Test
    public void addFindTest(){
        int categoryId = productCategory2.getId();
        ProductCategory prodC = productCategoryDataStore.find(categoryId);
        assertEquals(categoryId, prodC.getId());
    }


    @Test
    public void getAllTest(){
        List<ProductCategory> allProductCategory;
        allProductCategory = asList(productCategory1,productCategory2,productCategory3);
        assertEquals(asList(allProductCategory), asList(productCategoryDataStore.getAll()));
    }

    @Test
    public void removeTest(){
        productCategoryDataStore.remove(productCategory3.getId());
        assertNull(productCategoryDataStore.find(3));
    }


    @After
    public void tearDown() {
        productCategoryDataStore.remove(productCategory1.getId());
        productCategoryDataStore.remove(productCategory2.getId());
        productCategoryDataStore.remove(productCategory3.getId());
    }

}
