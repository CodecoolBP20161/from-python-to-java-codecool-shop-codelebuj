import com.codecool.shop.dao.implementation.ConnectionDb;
import org.junit.After;
import org.junit.Before;

import java.sql.Connection;
import java.sql.SQLException;

public class TestProductCategoryDaoJdbc extends ConnectionDb {

    @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    @Before
    public void setUp(){

    }




    @After
    public void tearDown(){

    }
}
