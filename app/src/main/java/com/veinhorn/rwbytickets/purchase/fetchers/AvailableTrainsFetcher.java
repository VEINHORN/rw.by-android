package com.veinhorn.rwbytickets.purchase.fetchers;

import com.veinhorn.rwbytickets.Fetcher;
import com.veinhorn.rwbytickets.purchase.model.AvailableTrain;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veinhorn on 5.4.16.
 */
public class AvailableTrainsFetcher implements Fetcher<List<AvailableTrain>> {
    private static final String RW_TABLE_ID = "viewns_7_48QFVAUK6HA180IQAQVJU80004_:form2:id2:tableEx1";

    @Override public List<AvailableTrain> fetch(String html) throws NullPointerException {
        return fetchAvailableTrains(html);
    }

    public static List<AvailableTrain> fetchAvailableTrains(String html) throws NullPointerException {
        Document document = Jsoup.parse(html);
        Element trainsTable = document.getElementById(RW_TABLE_ID);
        Elements trainRows = getRowsTrElements(trainsTable);
        return fetchAvailableTrains(trainRows);
    }

    private static List<AvailableTrain> fetchAvailableTrains(Elements trainRows) throws NullPointerException{
        List<AvailableTrain> trains = new ArrayList<>();
        for (Element trainRow : trainRows) {
            AvailableTrain train = fetchTrain(trainRow);
            trains.add(train);
        }
        return trains;
    }

    // TODO: Add several checking for NullPointer anti-vanomas
    private static AvailableTrain fetchTrain(Element trainRow) throws NullPointerException {
        AvailableTrain train = new AvailableTrain();
        Elements columnElms = new Elements();
        columnElms.addAll(trainRow.getElementsByClass("columnClass1"));
        columnElms.addAll(trainRow.getElementsByClass("tdLeft"));
        columnElms.addAll(trainRow.getElementsByClass("tdRight"));

        // Fetch values from columns
        String selectedRow = columnElms.get(0).getElementsByTag("span").get(0).attr("id");
        String name = columnElms.get(2).getElementsByTag("a").get(0).html();
        String type = columnElms.get(2).getElementsByTag("span").get(0).html();
        String dispatch = columnElms.get(4).html();
        String arrival = columnElms.get(5).html();
        String travelTime = columnElms.get(6).getElementsByTag("span").get(0).html();

        // Fetch passengers seats
        String obshie = fetchPassengerSeats(columnElms.get(7));
        String sidyachie = fetchPassengerSeats(columnElms.get(8));
        String plazkartnie = fetchPassengerSeats(columnElms.get(9));
        String kupe = fetchPassengerSeats(columnElms.get(10));
        String sv = fetchPassengerSeats(columnElms.get(11));
        String myagkie = fetchPassengerSeats(columnElms.get(12));

        // Fill train obj
        train.setSelectedRow(selectedRow);
        train.setName(name);
        train.setType(type);
        train.setDispatch(dispatch);
        train.setArrival(arrival);
        train.setTravelTime(travelTime);
        train.setObshie(obshie);
        train.setSidyachie(sidyachie);
        train.setPlazkartnie(plazkartnie);
        train.setKupe(kupe);
        train.setSV(sv);
        train.setMyagkie(myagkie);

        return train;
    }

    /** Fetches and return passanger seats/price */
    private static String fetchPassengerSeats(Element columnElm) {
        String seats = null;
        Elements seatSpans = columnElm.getElementsByTag("span");
        if (seatSpans.size() == 2) {// there two span elements seats/price
            seats = seatSpans.get(0).html() + "/" + seatSpans.get(1).html();
        } else {
            seats = "";
        }
        return seats;
    }

    /** Selects and combines rows with availible trains from table */
    private static Elements getRowsTrElements(Element trainsTable) {
        Elements greyRows = trainsTable.getElementsByTag("tbody").get(0).getElementsByClass("grey");
        Elements rowClass1 = trainsTable.getElementsByTag("tbody").get(0).getElementsByClass("rowClass1");
        Elements totalRows = new Elements();
        totalRows.addAll(greyRows);
        totalRows.addAll(rowClass1);
        return totalRows;
    }
}
