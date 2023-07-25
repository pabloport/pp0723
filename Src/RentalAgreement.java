package Src;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalAgreement {

    private Tool tool;
    private int rentalDays;
    private String checkoutDate;
    private String dueDate;
    private BigDecimal dailyCharge;
    private int chargeDays;
    private BigDecimal subtotal;
    private int discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;

    public void printAgreement() {
        System.out.println("----- Here is your rental agreement: -----");
        System.out.println("Tool code: " + tool.getToolCode());
        System.out.println("Tool type: " + tool.getToolType().getType());
        System.out.println("Tool brand: " + tool.getToolBrand());
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Checkout date: " + checkoutDate);
        System.out.println("Due date: " + dueDate);
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

    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    public void setDailyCharge(BigDecimal dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public void setChargeDays(int chargeDays) {
        this.chargeDays = chargeDays;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(BigDecimal finalCharge) {
        this.finalCharge = finalCharge;
    }
}
