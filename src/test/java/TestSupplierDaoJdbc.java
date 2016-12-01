import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.SupplierDaoJdbc;
import com.codecool.shop.model.Supplier;
import org.junit.*;


import java.util.List;

import static java.util.Arrays.*;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;

public class TestSupplierDaoJdbc {


    private Supplier supplier1;
    private Supplier supplier2;
    SupplierDao supplierDataStore = SupplierDaoJdbc.getInstance();


    @Before
    public void setUp() {
        supplier1 = new Supplier("Supplier1", "supplies stuff");
        supplierDataStore.add(supplier1);
        supplier2 = new Supplier("Supplier2", "Supplies stuff too");
        supplierDataStore.add(supplier2);

    }

    @Test
    public void addFindTest(){
        int categoryId = supplier2.getId();
        supplierDataStore.find(categoryId);
        assertEquals(2, categoryId);
    }


    @Test
    public void getAllTest(){
        List<Supplier> allSuppliers;
        allSuppliers = asList(supplier1,supplier2);
        assertEquals(asList(allSuppliers), asList(supplierDataStore.getAll()));
    }

    @Test
    public void removeTest(){
        supplierDataStore.remove(supplier2.getId());
        assertNull(supplierDataStore.find(2));
    }


    @After
    public void tearDown() {
        supplierDataStore.remove(supplier1.getId());
        supplierDataStore.remove(supplier2.getId());
    }

}
