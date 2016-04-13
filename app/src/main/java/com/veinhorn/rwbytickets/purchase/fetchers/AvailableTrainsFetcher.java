package com.veinhorn.rwbytickets.purchase.fetchers;

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
public class AvailableTrainsFetcher {
    private static final String RW_TABLE_ID = "viewns_7_48QFVAUK6HA180IQAQVJU80004_:form2:id2:tableEx1";

    public static List<AvailableTrain> fetchAvailableTrains(String html) {
        List<AvailableTrain> trains = new ArrayList<>();
        Document document = Jsoup.parse(html);
        Element trainsTable = document.getElementById(RW_TABLE_ID);
        Elements trainRows = getRowsTrElements(trainsTable);
        List<AvailableTrain> availableTrains = fetchAvailableTrains(trainRows);
        return trains;
    }

    private static List<AvailableTrain> fetchAvailableTrains(Elements trainRows) {
        List<AvailableTrain> trains = new ArrayList<>();
        for (Element trainRow : trainRows) {
            AvailableTrain train = fetchTrain(trainRow);
        }
        return trains;
    }

    // Add several checking for NullPointer anti-vanomas
    private static AvailableTrain fetchTrain(Element trainRow) {
        AvailableTrain train = new AvailableTrain();
        Elements rowElms = trainRow.getElementsByTag("td");

        String selectedRow = rowElms.get(0).getElementsByTag("span").get(0).attr("id");
        train.setSelectedRow(selectedRow);

        String name = rowElms.get(2).getElementsByTag("a").get(0).val();
        train.setName(name);

        String type = rowElms.get(2).getElementsByTag("span").get(0).val();
        train.setType(type);

        String dispatch = rowElms.get(4).val();
        train.setDispatch(dispatch);

        String arrival = rowElms.get(5).val();
        train.setArrival(arrival);

        String travelTime = rowElms.get(6).getElementsByTag("span").get(0).val();
        train.setTravelTime(travelTime);

        Integer test = 2143324;
        return train;
    }

    private static Elements getRowsTrElements(Element trainsTable) {
        Elements greyRows = trainsTable.getElementsByTag("tbody").get(0).getElementsByClass("grey");
        Elements rowClass1 = trainsTable.getElementsByTag("tbody").get(0).getElementsByClass("rowClass1");
        Elements totalRows = new Elements();
        totalRows.addAll(greyRows);
        totalRows.addAll(rowClass1);
        return totalRows;
    }
}
