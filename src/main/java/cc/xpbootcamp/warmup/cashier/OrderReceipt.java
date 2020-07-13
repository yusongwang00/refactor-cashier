package cc.xpbootcamp.warmup.cashier;

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
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        // print headers
        output.append("======Printing Orders======\n");

        // print date, bill no, customer name
//        output.append("Date - " + order.getDate();
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());
//        output.append(order.getCustomerLoyaltyNumber());
        appendTax(output);

        for (LineItem lineItem : order.getLineItems()) {
            appendBasicInfo(output, lineItem);
        }
        return output.toString();
    }

    private void appendTax(StringBuilder output) {
        // prints lineItems
        double totSalesTx = 0d;
        double tot = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            // calculate sales tax @ rate of 10%
            double salesTax = lineItem.totalAmount() * TAX_RATE;
            totSalesTx += salesTax;
            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            tot += lineItem.totalAmount() + salesTax;
        }

        // prints the state tax
        output.append("Sales Tax").append('\t').append(totSalesTx);

        // print total amount
        output.append("Total Amount").append('\t').append(tot);
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