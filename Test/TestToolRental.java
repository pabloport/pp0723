package Test;

import Src.Inventory;
import Src.RentalAgreement;
import Src.Tool;
import Src.ToolRental;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestToolRental {

    ToolRental testToolRental;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
    Inventory testInventory;
    Tool testTool;
    RentalAgreement testRentalAgreement;

    @Before
    public void setUp() {
        testToolRental = new ToolRental();
        testTool = new Tool();
        testInventory = Inventory.getInventory();
    }

    @Test
    public void testScenario1() {
        Tool JAKR = testInventory.getByToolCode("JAKR");
        LocalDate testDate = LocalDate.parse("09/03/15", formatter);

        Assert.assertThrows(String.format("Please enter a discount percentage between 0 and 100."), Exception.class, () -> {
            testToolRental.calculateTotalCharge(JAKR, 5, testDate, 101);
        });
    }

    @Test
    public void testScenario2() throws Exception {
        Tool LADW = testInventory.getByToolCode("LADW");
        LocalDate testDate = LocalDate.parse("07/02/20", formatter);
        testRentalAgreement = new RentalAgreement(LADW, 3, "07/02/20", "07/05/20", 1.99, 1, "1.99", 10, "0.20", "1.80" );

        Assert.assertEquals(testRentalAgreement.toString(), testToolRental.calculateTotalCharge(LADW, 3, testDate, 10).toString());
    }

    @Test
    public void testScenario3() throws Exception {
        Tool CHNS = testInventory.getByToolCode("CHNS");
        LocalDate testDate = LocalDate.parse("07/02/15", formatter);
        testRentalAgreement = new RentalAgreement(CHNS, 5, "07/02/15", "07/07/15", 1.49, 2, "2.98", 25, "0.75", "2.24" );

        Assert.assertEquals(testRentalAgreement.toString(), testToolRental.calculateTotalCharge(CHNS, 5, testDate, 25).toString());
    }

    @Test
    public void testScenario4() throws Exception {
        Tool JAKD = testInventory.getByToolCode("JAKD");
        LocalDate testDate = LocalDate.parse("09/03/15", formatter);
        testRentalAgreement = new RentalAgreement(JAKD, 6, "09/03/15", "09/09/15", 2.99, 3, "8.97", 0, "0.00", "8.97" );

        Assert.assertEquals(testRentalAgreement.toString(), testToolRental.calculateTotalCharge(JAKD, 6, testDate, 0).toString());
    }

    @Test
    public void testScenario5() throws Exception {
        Tool JAKR = testInventory.getByToolCode("JAKR");
        LocalDate testDate = LocalDate.parse("07/02/15", formatter);
        testRentalAgreement = new RentalAgreement(JAKR, 9, "07/02/15", "07/11/15", 2.99, 6, "17.94", 0, "0.00", "17.94" );

        Assert.assertEquals(testRentalAgreement.toString(), testToolRental.calculateTotalCharge(JAKR, 9, testDate, 0).toString());
    }

    @Test
    public void testScenario6() throws Exception {
        Tool JAKR = testInventory.getByToolCode("JAKR");
        LocalDate testDate = LocalDate.parse("07/02/20", formatter);
        testRentalAgreement = new RentalAgreement(JAKR, 4, "07/02/20", "07/06/20", 2.99, 1, "2.99", 50, "1.50", "1.50" );

        Assert.assertEquals(testRentalAgreement.toString(), testToolRental.calculateTotalCharge(JAKR, 4, testDate, 50).toString());
    }


}
