package com.veinhorn.rwbytickets.action;

import com.veinhorn.rwbytickets.TicketsApp;
import com.veinhorn.rwbytickets.purchase.dialog.DialogStatus;
import com.veinhorn.rwbytickets.purchase.dialog.PurchaseDialog;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by veinhorn on 3.4.16.
 */
public class SelectRouteAction extends BaseAction {

    private static final String RW_FORM_SELECT_ROUTE_ID = "viewns_7_48QFVAUK6HA180IQAQVJU80004_:form1";
    private static final String RW_INPUT_FACES_ID = "com.sun.faces.VIEW";

    private static final String RW_INPUT_TEXT_DEP_STAT = RW_FORM_SELECT_ROUTE_ID + ":textDepStat";
    private static final String RW_INPUT_TEXT_ARR_STAT = RW_FORM_SELECT_ROUTE_ID + ":textArrStat";
    private static final String RW_INPUT_TEXT_DEP_STAT_CODE = RW_FORM_SELECT_ROUTE_ID + ":textDepStatCode:";
    private static final String RW_INPUT_TEXT_ARR_STAT_CODE = RW_FORM_SELECT_ROUTE_ID + ":textArrStatCode";

    private static final String RW_INPUT_COUNT_ADULTS = RW_FORM_SELECT_ROUTE_ID + ":countAdults";
    private static final String RW_INPUT_COUNT_CHILDREN = RW_FORM_SELECT_ROUTE_ID + ":countChildren";
    private static final String RW_INPUT_COUNT_CHILDREN_NO_PLACES = RW_FORM_SELECT_ROUTE_ID + ":countChildrenNoPlaces";

    private static final String RW_INPUT_DOB = RW_FORM_SELECT_ROUTE_ID + ":dob";
    private static final String RW_INPUT_INT_START_TIME = RW_FORM_SELECT_ROUTE_ID + ":intStartTime";
    private static final String RW_INPUT_INT_END_TIME = RW_FORM_SELECT_ROUTE_ID + ":intEndTime";
    private static final String RW_INPUT_MIN_DATE = RW_FORM_SELECT_ROUTE_ID + ":minDate";
    private static final String RW_INPUT_MAX_DATE = RW_FORM_SELECT_ROUTE_ID + ":maxDate";
    private static final String RW_INPUT_MAX_P = RW_FORM_SELECT_ROUTE_ID + ":maxP";
    private static final String RW_INPUT_ONLY_SCHEDULE = RW_FORM_SELECT_ROUTE_ID + ":onlySchedule";

    private static final String RW_INPUT_KEY_ERR = RW_FORM_SELECT_ROUTE_ID + ":keyErr";
    private static final String RW_INPUT_BUTTON_SEARCH = RW_FORM_SELECT_ROUTE_ID + ":buttonSearch";
    private static final String RW_INPUT_MENU_ID = RW_FORM_SELECT_ROUTE_ID + ":menuId";
    private static final String RW_INPUT_LANGUAGE = RW_FORM_SELECT_ROUTE_ID + ":language1";

    private OkHttpClient httpClient;

    public SelectRouteAction() {
        httpClient = TicketsApp.httpClient;
    }

    @Override public Response doAction(PurchaseDialog dialog) throws Exception {
        Response confirmRulesResponse = dialog.getCurrentResponse();

        // Fetch info from input for future request creation
        Document document = Jsoup.parse(confirmRulesResponse.body().string());
        Element selectRouteForm = document.getElementById(RW_FORM_SELECT_ROUTE_ID);
        String selectRouteAction = selectRouteForm.attr(RW_ACTION_ATTR_NAME);
        Element sunFacesInput = document.getElementById(RW_INPUT_FACES_ID);
        String sunFacesIds = sunFacesInput.attr("value");
        // Create url
        String selectRouteUrl = createSelectRouteUrl(selectRouteAction);
        Request selectRouteRequest = createSelectRouteRequest(dialog, selectRouteUrl, sunFacesIds);
        // Do request
        Response selectRouteResponse = httpClient.newCall(selectRouteRequest).execute();
        // Fill purchase dialog
        // TODO: Maybe move fill dialog logic on upper level
        fillPurchaseDialog(dialog, selectRouteResponse);
        return selectRouteResponse;
    }

    private void fillPurchaseDialog(PurchaseDialog dialog, Response selectRouteResponse) {
        dialog.setCurrentResponse(selectRouteResponse);
        dialog.setDialogStatus(DialogStatus.SELECT_TRAIN);
    }

    private Request createSelectRouteRequest(PurchaseDialog dialog, String selectRouteUrl, String sunFacesIds) {
        return new Request.Builder()
                .url(selectRouteUrl)
                .header("Content-Type", "application/x-www-form-urlencoded") // be on the safe side
                .post(createSelectRouteRequestBody(dialog, sunFacesIds))
                .build();
    }

    private String createSelectRouteUrl(String selectRouteAction) {
        return BASE_URL + selectRouteAction;
    }

    private RequestBody createSelectRouteRequestBody(PurchaseDialog dialog, String sunFacesIds) {
        return new FormBody.Builder()
                .add(RW_INPUT_TEXT_DEP_STAT, dialog.getFromStation().getName())
                .add(RW_INPUT_TEXT_ARR_STAT, dialog.getToStation().getName())
                .add(RW_INPUT_TEXT_DEP_STAT_CODE, dialog.getFromStation().getCode())
                .add(RW_INPUT_TEXT_ARR_STAT_CODE, dialog.getToStation().getCode())
                .add(RW_INPUT_COUNT_ADULTS, dialog.getAdults().toString())
                .add(RW_INPUT_COUNT_CHILDREN, dialog.getChildren().toString())
                .add(RW_INPUT_COUNT_CHILDREN_NO_PLACES, dialog.getChildrenNoPlaces().toString())
                .add(RW_INPUT_DOB, dialog.getWhenDate())
                .add(RW_INPUT_INT_START_TIME, "0") // for test now
                .add(RW_INPUT_INT_END_TIME, "23") // for test now
                .add(RW_INPUT_MIN_DATE, "03.04.2016") // for test now
                .add(RW_INPUT_MAX_DATE, "01.06.2016") // for test now
                .add(RW_INPUT_MAX_P, "4") // for test now (MUST BE 4 in purchase dialog -> constant)
                .add(RW_INPUT_ONLY_SCHEDULE, "false") // it's - ER (for test now)
                .add(RW_INPUT_KEY_ERR, "Разрешен только ввод русских букв и специальных символов")
                .add(RW_INPUT_BUTTON_SEARCH, "Продолжить")
                .add(RW_INPUT_MENU_ID, "0")
                .add(RW_INPUT_LANGUAGE, "ru")
                .add(RW_FORM_SELECT_ROUTE_ID, RW_FORM_SELECT_ROUTE_ID)
                .add(RW_INPUT_FACES_ID, sunFacesIds)
                .build();
    }
}
