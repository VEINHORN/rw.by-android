package com.veinhorn.rwbytickets.tickets.fetchers;

import com.veinhorn.rwbytickets.Fetcher;
import com.veinhorn.rwbytickets.tickets.model.Order;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veinhorn on 21.4.16.
 */
public class OrdersFetcher implements Fetcher<List<Order>> {
    private static final String ORDERS_TABLE_ID = "viewns_7_48QFVAUK6PT510AGU3KRAG1004_:form2:cabOrderList1";

    @Override public List<Order> fetch(String html) throws NullPointerException {
        Document document = Jsoup.parse(html);
        // TODO: Fix NullPointer exception here
        Element tableBodyElm = document.getElementById(ORDERS_TABLE_ID)
                .getElementsByTag("tbody").get(0);
        Elements tableRows = new Elements();
        tableRows.addAll(tableBodyElm.getElementsByClass("rowClass1"));
        tableRows.addAll(tableBodyElm.getElementsByClass("grey"));
        return fetchOrders(tableRows);
    }

    private List<Order> fetchOrders(Elements tableRows) throws NullPointerException {
        List<Order> orders = new ArrayList<>();
        for (Element tableRow : tableRows) {
            orders.add(fetchOrder(tableRow));
        }
        return orders;
    }

    private Order fetchOrder(Element tableRow) throws NullPointerException {
        Elements columnElms = new Elements();
        columnElms.addAll(tableRow.getElementsByClass("tdLeft"));
        columnElms.addAll(tableRow.getElementsByClass("tdRight"));

        Order order = new Order();
        order.setOrderNumber(columnElms.get(0).text());
        order.setOrderDate(columnElms.get(1).text());
        order.setDispatch(columnElms.get(2).text());
        order.setFromStation(columnElms.get(3).text());
        order.setToStation(columnElms.get(4).text());
        order.setTrainNumber(columnElms.get(5).text());
        order.setNumberOfSeats(columnElms.get(6).text());
        order.setPrice(columnElms.get(7).text());
        order.setEr(columnElms.get(8).text());

        return order;
    }
}
