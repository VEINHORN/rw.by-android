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

    private static AvailableTrain fetchTrain(Element trainRow) {
        AvailableTrain train = new AvailableTrain();
        String selectedRow = trainRow.getElementsByTag("td").get(0).getElementsByTag("span").get(0).attr("id");
        train.setSelectedRow(selectedRow);
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
