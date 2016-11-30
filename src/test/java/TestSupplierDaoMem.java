import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class TestSupplierDaoMem {

    private Supplier supplier1;
    private Supplier supplier2;
    private Supplier supplier3;
    SupplierDao supplierDataStore = SupplierDaoMem.getInstance();


    @Before
    public void setUp() {
        supplier1 = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(supplier1);
        supplier2 = new Supplier("Acer", "Computers");
        supplierDataStore.add(supplier2);
        supplier3 = new Supplier("Canon", "Camera");
        supplierDataStore.add(supplier3);
    }

    @Test
    public void addFindTest(){
        int supplierId = supplier2.getId();
        supplierDataStore.find(supplierId);
        assertEquals(2, supplierId);
    }



    @After
    public void tearDown() {
        supplierDataStore.remove(supplier1.getId());
        supplierDataStore.remove(supplier2.getId());
        supplierDataStore.remove(supplier3.getId());
    }

}
