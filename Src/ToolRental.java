package Src;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Scanner;

public class ToolRental {

    private Scanner scanner;
    private Inventory inventory;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");

    public ToolRental() {
        scanner = new Scanner(System.in);
        inventory = new Inventory();
        createTools();
    }

    public void start() throws Exception {
            System.out.println("Welcome to the Tool Rental Application!");
            System.out.print("Please enter your tool code: ");
            String codeInput = scanner.next();
            Tool tool = inventory.getByToolCode(codeInput);

            if (tool == null) {
                System.out.println("Please enter a valid tool code.");
                start();
            }

            System.out.print("Please enter the number of days you'd like to rent the tool: ");
            int rentalDays = scanner.nextInt();
            if (rentalDays < 1) {
                throw new Exception("Please enter a rental day amount of 1 or greater.");
            }

            System.out.print("Please enter your discount percentage: ");
            int discountInput = scanner.nextInt();
            if (discountInput < 0 || discountInput > 100) {
                throw new Exception("Please enter a discount percentage between 0 and 100.");
            }

            System.out.print("Please enter the tool checkout date (MM/DD/YY): ");
            LocalDate checkoutDate = LocalDate.parse(scanner.next(), formatter);

            calculateTotalCharge(tool, rentalDays, checkoutDate, discountInput);
    }

    private void calculateTotalCharge(Tool tool, int rentalDays, LocalDate checkout, int discountPercent) {

        RentalAgreement rentalAgreement = new RentalAgreement();
        rentalAgreement.setTool(tool);
        rentalAgreement.setRentalDays(rentalDays);
        rentalAgreement.setCheckoutDate(checkout.format(formatter));
        rentalAgreement.setDiscountPercent(discountPercent);
        rentalAgreement.setDueDate(checkout.plusDays(rentalDays).format(formatter));
        rentalAgreement.setDailyCharge(tool.getToolType().getDailyCharge());
        int chargeDays = calculateChargeDays(tool, rentalDays, checkout);
        rentalAgreement.setChargeDays(chargeDays);
        double subtotal = tool.getToolType().getDailyCharge() * chargeDays;
        rentalAgreement.setSubtotal(subtotal);
        // todo fix rounding, should only be 2 decimals
//        double inputDiscount = double.valueOf(discountPercent).divide(double.valueOf(100));
        double discount = (subtotal * discountPercent)/100;
        rentalAgreement.setDiscountAmount(discount);
        double totalCharge = subtotal - discount;
        rentalAgreement.setFinalCharge(totalCharge);
        rentalAgreement.printAgreement();
    }

    private int calculateChargeDays(Tool tool, int rentalDays, LocalDate checkoutDate) {
        int totalChargeDays = rentalDays;
        LocalDate rentalDate;
        boolean holidayCharge = tool.getToolType().isHolidayCharge();
        boolean weekdayCharge = tool.getToolType().isWeekdayCharge();
        boolean weekendCharge = tool.getToolType().isWeekendCharge();

        // looping through each date to check if it is chargeable
        for (int i = 1; i < rentalDays; i++) {
            rentalDate = checkoutDate.plusDays(i);

            if (!holidayCharge && isHoliday(rentalDate)) {
                totalChargeDays--;
            } else if (!weekdayCharge && !isWeekend(rentalDate)) {
                totalChargeDays--;
            } else if (!weekendCharge && isWeekend(rentalDate)) {
                totalChargeDays--;
            }
        }
        return totalChargeDays;
    }

    private boolean isHoliday(LocalDate date) {
        int checkoutYear = date.getYear();
        if (date.isEqual(getIndependenceDay(checkoutYear)) || date.isEqual(isLaborDay(checkoutYear))) {
            return true;
        }
        return false;
    }

    private LocalDate getIndependenceDay(int checkoutYear) {
        LocalDate independenceDay = LocalDate.of(checkoutYear, Month.JULY, 4);

        if (independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            independenceDay = independenceDay.minusDays(1);
        } else if (independenceDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            independenceDay = independenceDay.plusDays(1);
        }
        return independenceDay;
    }

    private LocalDate isLaborDay(int checkoutYear) {
        LocalDate laborDay = LocalDate.of(checkoutYear, Month.SEPTEMBER, 1);
        return laborDay.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
    }

    private boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return true;
        }
        return false;
    }

    private void createTools() {
        // Creating Tool types
        final ToolType LADDER = new ToolType("Ladder", 1.99, true, true, false);
        final ToolType CHAINSAW = new ToolType("Chainsaw", 1.49, true, false, true);
        final ToolType JACKHAMMER = new ToolType("Jackhammer", 2.99, true, false, false);

        // Adding current tools to inventory
        inventory.addTool((new Tool("CHNS", CHAINSAW, "Stihl")));
        inventory.addTool(new Tool("LADW", LADDER, "Werner"));
        inventory.addTool(new Tool("JAKD", JACKHAMMER, "DeWalt"));
        inventory.addTool(new Tool("JAKR", JACKHAMMER, "Ridgid"));
    }
}
