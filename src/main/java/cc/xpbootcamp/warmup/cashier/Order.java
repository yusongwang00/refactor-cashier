package cc.xpbootcamp.warmup.cashier;

import java.util.List;
import java.util.*;
import java.text.*;

public class Order {
    String cName;
    String addr;
    List<LineItem> lineItemList;
    Date date;

    public Order(String cName, String addr, List<LineItem> lineItemList) {
        this.cName = cName;
        this.addr = addr;
        this.lineItemList = lineItemList;
        this.date = new Date();
    }

    public String getCustomerName() {
        return cName;
    }

    public String getCustomerAddress() {
        return addr;
    }

    public List<LineItem> getLineItems() {
        return lineItemList;
    }

    public Date getDate() {
        return date;
    }

    public String getFormattedDate() {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd E");
        System.out.printf("%tFA%n",date);
        return ft.format(date);
    }
}
