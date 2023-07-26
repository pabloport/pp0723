package Src;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public class RentalAgreement {

    private Tool tool;
    private int rentalDays;
    private String checkoutDate;
    private String dueDate;
    private double dailyCharge;
    private int chargeDays;
    private double subtotal;
    private int discountPercent;
    private double discountAmount;
    private double finalCharge;

    public void printAgreement() {
        DecimalFormat decFormat = new DecimalFormat("0.00");
        decFormat.setRoundingMode(RoundingMode.UP);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");

        System.out.println("----- Here is your rental agreement: -----");
        System.out.println("Tool code: " + tool.getToolCode());
        System.out.println("Tool type: " + tool.getToolType().getType());
        System.out.println("Tool brand: " + tool.getToolBrand());
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Checkout date: " + checkoutDate.formatted(formatter));
        System.out.println("Due date: " + dueDate.formatted(formatter));
        System.out.println("Daily rental charge: $" + dailyCharge);
        System.out.println("Charge days: " + chargeDays);
        System.out.println("Pre-discount charge: $" + decFormat.format(subtotal));
        System.out.println("Discount percent: " + discountPercent + "%");
        System.out.println("Discount amount: $" + decFormat.format(discountAmount));
        System.out.println("Final charge: $" + decFormat.format(finalCharge));
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public double getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(double dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public void setChargeDays(int chargeDays) {
        this.chargeDays = chargeDays;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(double finalCharge) {
        this.finalCharge = finalCharge;
    }
}
