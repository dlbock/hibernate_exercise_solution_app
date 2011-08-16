package plainsql;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class CarRepositoryTest {

    private CarRepository repository;

    @Before
    public void clearDB() throws Exception {
        repository = new CarRepository();
        repository.update("DELETE FROM cars");
    }

    @Test
    public void shouldSaveCarsToRepository() throws SQLException {
        insertToRepository(new Car("Ford", "JFK256"));
        insertToRepository(new Car("Toyota", "ABC999"));
        insertToRepository(new Car("Honda", "XYZ789"));
        insertToRepository(new Car("GM", "GHJ123"));

        assertEquals(4, repository.findAll("SELECT * FROM cars"));
    }

    private void insertToRepository(Car car) throws SQLException {
        String sqlStatement = "INSERT INTO cars(name,number_plate) VALUES('%s', '%s')";
        repository.update(String.format(sqlStatement, car.getName(), car.getNumberPlate()));
    }
}
