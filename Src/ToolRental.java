package Src;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ToolRental {

    private Scanner scanner;
    private Inventory inventory;

    public ToolRental() {
        scanner = new Scanner(System.in);
        inventory = new Inventory();
        createTools();
    }

    public void start() {
        while (true) {
            // todo need to add while loop for handling wrong inputs
            System.out.println("Welcome to the Tool Rental Application!");
            System.out.print("Please enter your tool code: ");
            String codeInput = scanner.next();
            Tool tool = inventory.getByToolCode(codeInput);

            if (tool == null) {
                System.out.println("Please enter a valid tool code: ");
                return;
            }
            System.out.print("Please enter the tool checkout date (MM-DD-YY): ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy");
            LocalDate checkoutDate = LocalDate.parse(scanner.next(), formatter);
            System.out.println("checkout date -- " + checkoutDate.format(formatter));

            System.out.print("Please enter the number of days you'd like to rent the tool: ");
            // todo add exception handler here
            int rentalDays = scanner.nextInt();

            System.out.print("Please enter your discount percentage: ");
            // todo add exception handler here
            int discountInput = scanner.nextInt();

            System.out.println("calculate charge: " + tool.getToolType().getDailyCharge().multiply(BigDecimal.valueOf(rentalDays)));
            calculateTotalCharge(tool, rentalDays, checkoutDate, discountInput);
            break;
        }
    }

    private void createTools() {
        // Creating Tool types
        final ToolType LADDER = new ToolType("Ladder", new BigDecimal("1.99"), true, true, false);
        final ToolType CHAINSAW = new ToolType("Chainsaw", new BigDecimal("1.49"), true, false, true);
        final ToolType JACKHAMMER = new ToolType("Jackhammer", new BigDecimal("2.99"), true, false, false);

        // Adding current tools to inventory
        inventory.addTool((new Tool("CHNS", CHAINSAW, "Stihl")));
        inventory.addTool(new Tool("LADW", LADDER, "Werner"));
        inventory.addTool(new Tool("JAKD", JACKHAMMER, "DeWalt"));
        inventory.addTool(new Tool("JAKR", JACKHAMMER, "Ridgid"));
    }

    private static void calculateTotalCharge(Tool tool, int rentalDays, LocalDate checkout, int discountPercent) {
        RentalAgreement rentalAgreement = new RentalAgreement();
        rentalAgreement.setTool(tool);
        rentalAgreement.setRentalDays(rentalDays);
        rentalAgreement.setCheckoutDate(checkout);
        rentalAgreement.setDiscountPercent(discountPercent);
        rentalAgreement.setDueDate(checkout.plusDays(rentalDays));

        // todo add logic for holidays and discount codes
        // todo add exception for discount not 0-100
        BigDecimal subtotal = tool.getToolType().getDailyCharge().multiply(BigDecimal.valueOf(rentalDays));
        if (discountPercent > 1 && discountPercent < 100) {
            // todo fix rounding, should only be 2 decimals
            // todo subtotal might have to be class value for agreement, along with other vars
            subtotal.subtract(subtotal.multiply(BigDecimal.valueOf(discountPercent).movePointLeft(2)));
        }
        rentalAgreement.printAgreement();
    }

    private static boolean isIndependenceDay(LocalDate date) {
        LocalDate julyFourth = LocalDate.of(date.getYear(), 7, 4);
        LocalDate dayBefore = date.minusDays(1);
        LocalDate dayAfter = date.plusDays(1);

        // Holiday is never observed on a weekend
        if (!isWeekend(date)) {
            if (date.isEqual(julyFourth)
                    || dayBefore.isEqual(julyFourth)
                    || dayAfter.isEqual(julyFourth)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return true;
        }
        return false;
    }

    private static boolean isLaborDay(LocalDate date) {
        Month monthOneWeekAgo = date.minusWeeks(1).getMonth();
        // Because labor day is the first Monday, check the previous Monday's month
        if (date.getMonth() == Month.SEPTEMBER
                && date.getDayOfWeek() == DayOfWeek.MONDAY
                && monthOneWeekAgo != Month.SEPTEMBER) {
            return true;
        }
        return false;
    }

//    private static void printAgreement(Tool tool, int rentalDays, LocalDate checkout, int discount) {
//
//
//        // todo maybe add more methods that do calculations for days like holiday
////        BigDecimal totalCharge = calculateTotalCharge(tool, rentalDays, discount);
//
////        System.out.println("Your total price is: $" + totalCharge);
//
//    }

//    private static LocalDate getClosestWeekday(LocalDate date) {
//
//        if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
//            return date.minusDays(1);
//        } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
//            return date.plusDays(1);
//        }
//        return date;
//    }
}
