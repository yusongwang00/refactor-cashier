package cc.xpbootcamp.warmup.cashier;

import java.util.Calendar;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 *
 * 思路：
 * - 首先这个方法有很多的常量可以抽出来便于维护
 * - 其中计算税的部分可以单独抽一个方法
 * - 打印每个LineItem也可以抽出来
 * - 变量名字有些迷惑
 */
public class OrderReceipt {

    public static final double TAX_RATE = .10;
    public static final int DISCOUNT_DAY = 4;
    public static final double DISCOUNT_RATE = .98;

    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public boolean shouldDiscount() {
        Calendar c = Calendar.getInstance();
        c.setTime(order.getDate());
        if(c.get(Calendar.DAY_OF_WEEK) == DISCOUNT_DAY)
            return true;
        return false;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        // print headers
        output.append("======老王超市 值得信赖======\n");
        output.append("Date - " + order.getFormattedDate() + "\n");
//        output.append(order.getCustomerName());
 //       output.append(order.getCustomerAddress());
//        output.append(order.getCustomerLoyaltyNumber());


        for (LineItem lineItem : order.getLineItems()) {
            appendBasicInfo(output, lineItem);
        }
        output.append("--------------------------\n");
        appendPrice(output);
        return output.toString();
    }

    private void appendPrice(StringBuilder output) {
        // prints lineItems
        double totSalesTax = 0d;
        double totalAmount = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            double salesTax = lineItem.totalAmount() * TAX_RATE;
            totSalesTax += salesTax;
            totalAmount += lineItem.totalAmount() + salesTax;
        }

        output.append("Sales Tax").append('\t').append(totSalesTax).append('\n');
        if (shouldDiscount()) {
            double actualAmount = totalAmount * DISCOUNT_RATE;
            output.append("Discount").append('\t').append(totalAmount - actualAmount).append('\n');
            output.append("Total Amount").append('\t').append(actualAmount);
        } else {
            output.append("Total Amount").append('\t').append(totalAmount);
        }
    }

    private void appendBasicInfo(StringBuilder output, LineItem lineItem) {
        output.append(lineItem.getDescription());
        output.append('\t');
        output.append(lineItem.getPrice());
        output.append('\t');
        output.append(lineItem.getQuantity());
        output.append('\t');
        output.append(lineItem.totalAmount());
        output.append('\n');
    }
}