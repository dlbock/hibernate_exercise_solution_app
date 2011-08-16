package plainsql;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class CarRepositoryIntegrationTest {

    private CarRepository repository;

    @Before
    public void cleanRepository() throws Exception {
        repository = new CarRepository();
        repository.update("DELETE FROM cars");
    }

    @Test
    public void shouldSaveCarsToRepository() throws SQLException {
        insertToRepository(createCar("Ford", "JFK256"));
        insertToRepository(createCar("Toyota", "ABC999"));
        insertToRepository(createCar("Honda", "XYZ789"));
        insertToRepository(createCar("GM", "GHJ123"));

        assertEquals(4, repository.findAll("SELECT * FROM cars"));
    }

    private void insertToRepository(Car car) throws SQLException {
        String sqlStatement = "INSERT INTO cars(name,numberPlate) VALUES('%s', '%s')";
        repository.update(String.format(sqlStatement, car.getName(), car.getNumberPlate()));
    }

    private Car createCar(String name, String numberPlate) {
        return new Car(name, numberPlate);
    }
}
