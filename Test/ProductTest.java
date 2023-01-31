import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ProductTest {
    Product p1, p2, p3, p4, p5, p6;

    @BeforeEach
    void setUp()
    {

       p1 = new Product("00000A", "Wood", "Made out of tree", 34.45);
       p2 = new Product("00000B", "Glucose", "Made from photosynthesis", 9.99);
       Product.setIDSeed(0);
       p3 = new Product("Apple", "Bought at the store", 3.45);
       p4 = new Product("Car", "Bought from dealership", 23450.00);


    }

    @Test
    void getIDSeed() {
        assertEquals(2, Product.getIDSeed());
        }

    @Test
    void getName() {
        assertEquals("Wood", p1.getName());
    }

    @Test
    void setName() {
        p1.setName("Glucose");
        assertEquals("Glucose", p1.getName());
    }

    @Test
    void getDescription() {
        assertEquals("Made out of tree", p1.getDescription());
    }

    @Test
    void setDescription() {
        p1.setDescription("Made from photosynthesis");
        assertEquals("Made from photosynthesis", p1.getDescription());
    }

    @Test
    void getID() {
        assertEquals("00000A", p1.getID());
    }

    @Test
    void setID() {
        p1.setID("00000B");
        assertEquals("00000B", p1.getID());
    }

    @Test
    void getCost() {
        assertEquals(34.45, p1.getCost());
    }

    @Test
    void setCost() {
        p1.setCost(9.99);
        assertEquals(9.99, p1.getCost());
    }

    @Test
    void Equals() {
        p1.setID("00000B");
        p1.setName("Glucose");
        p1.setDescription("Made from photosynthesis");
        p1.setCost(9.99);
        assertEquals(true, p1.equals(p2));
    }
}
