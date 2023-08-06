package Src;

import java.time.format.DateTimeFormatter;

public class RentalAgreement {

    private Tool tool;
    private int rentalDays;
    private String checkoutDate;
    private String dueDate;
    private double dailyCharge;
    private int chargeDays;
    private String subtotal;
    private int discountPercent;
    private String discountAmount;
    private String finalCharge;

    public RentalAgreement() {
    }

    @Override
    public String toString() {
        return "RentalAgreement{" +
                "tool=" + tool +
                ", rentalDays=" + rentalDays +
                ", checkoutDate='" + checkoutDate + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", dailyCharge=" + dailyCharge +
                ", chargeDays=" + chargeDays +
                ", subtotal=" + subtotal +
                ", discountPercent=" + discountPercent +
                ", discountAmount=" + discountAmount +
                ", finalCharge=" + finalCharge +
                '}';
    }

    public RentalAgreement(Tool tool, int rentalDays, String checkoutDate, String dueDate, double dailyCharge, int chargeDays, String subtotal, int discountPercent, String discountAmount, String finalCharge) {
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dailyCharge = dailyCharge;
        this.chargeDays = chargeDays;
        this.subtotal = subtotal;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    public void printAgreement() {
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
        System.out.println("Pre-discount charge: $" + subtotal);
        System.out.println("Discount percent: " + discountPercent + "%");
        System.out.println("Discount amount: $" + discountAmount);
        System.out.println("Final charge: $" + finalCharge);
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

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(String finalCharge) {
        this.finalCharge = finalCharge;
    }
}
