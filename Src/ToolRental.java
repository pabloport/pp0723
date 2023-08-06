package Src;

import java.math.RoundingMode;
import java.text.DecimalFormat;
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
        inventory = Inventory.getInventory();
    }

    public void start() throws Exception {
            System.out.println("Welcome to the Tool Rental Application!");

            System.out.print("Please enter your tool code: ");
            String codeInput = scanner.next();
            Tool tool = inventory.getByToolCode(codeInput);
            while (tool == null) {
                System.out.println("Please enter a valid tool code.");
                codeInput = scanner.next();
                tool = inventory.getByToolCode(codeInput);
            }

            System.out.print("Please enter the number of days you'd like to rent the tool: ");
            int rentalDays = scanner.nextInt();

            System.out.print("Please enter your discount percentage: ");
            int discountInput = scanner.nextInt();

            System.out.print("Please enter the tool checkout date (MM/DD/YY): ");
            String inputDate =  scanner.next();
            LocalDate checkoutDate = LocalDate.parse(inputDate, formatter);

            RentalAgreement rentalAgreement = calculateTotalCharge(tool, rentalDays, checkoutDate, discountInput);
            rentalAgreement.printAgreement();
    }

    public RentalAgreement calculateTotalCharge(Tool tool, int rentalDays, LocalDate checkout, int discountPercent) throws Exception {

        // Using DecimalFormat for currency formatting and rounding half up
        DecimalFormat decFormat = new DecimalFormat("0.00");
        decFormat.setRoundingMode(RoundingMode.UP);

        RentalAgreement rentalAgreement = new RentalAgreement();
        rentalAgreement.setTool(tool);
        if (rentalDays < 1) {
            throw new Exception("Please enter a rental day amount of 1 or greater.");
        }
        rentalAgreement.setRentalDays(rentalDays);
        rentalAgreement.setCheckoutDate(checkout.format(formatter));
        if (discountPercent < 0 || discountPercent > 100) {
            throw new Exception("Please enter a discount percentage between 0 and 100.");
        }
        rentalAgreement.setDiscountPercent(discountPercent);
        rentalAgreement.setDueDate(checkout.plusDays(rentalDays).format(formatter));
        rentalAgreement.setDailyCharge(tool.getToolType().getDailyCharge());
        int chargeDays = calculateChargeDays(tool, rentalDays, checkout);
        rentalAgreement.setChargeDays(chargeDays);
        double subtotal = tool.getToolType().getDailyCharge() * chargeDays;
        rentalAgreement.setSubtotal(decFormat.format(subtotal));
        double discount = (subtotal * discountPercent)/100;
        rentalAgreement.setDiscountAmount(decFormat.format(discount));
        double totalCharge = subtotal - discount;
        rentalAgreement.setFinalCharge(decFormat.format(totalCharge));

        return rentalAgreement;
    }

    private int calculateChargeDays(Tool tool, int rentalDays, LocalDate checkoutDate) {
        int totalChargeDays = rentalDays;
        LocalDate rentalDate;
        boolean holidayCharge = tool.getToolType().isHolidayCharge();
        boolean weekendCharge = tool.getToolType().isWeekendCharge();

        if (!holidayCharge || !weekendCharge) {
            // looping through each date to check if it is chargeable
            for (int i = 1; i < rentalDays; i++) {
                rentalDate = checkoutDate.plusDays(i);
                if (isHoliday(rentalDate)) {
                    totalChargeDays--;
                } else if (isWeekend(rentalDate)) {
                    totalChargeDays--;
                }
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
}
