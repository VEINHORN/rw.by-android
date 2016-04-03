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
 * Created by veinhorn on 1.4.16.
 */
public class ConfirmRulesAction extends BaseAction {

    private static final String RW_FORM_ACCEPT_RULES_ID = "viewns_7_48QFVAUK6HA180IQAQVJU80004_:form1";
    private static final String RW_INPUT_CHECKBOX_ID = RW_FORM_ACCEPT_RULES_ID + ":conf";
    private static final String RW_INPUT_CONTINUE_BUTTON_ID = RW_FORM_ACCEPT_RULES_ID + ":nextBtn";
    private static final String RW_INPUT_FACES_ID = "com.sun.faces.VIEW";

    private OkHttpClient httpClient;

    public ConfirmRulesAction() {
        httpClient = TicketsApp.httpClient;
    }

    @Override public Response doAction(PurchaseDialog dialog) throws Exception {
        Response signInResponse = dialog.getCurrentResponse();

        // Fetch information from input for future request creation
        Document document = Jsoup.parse(signInResponse.body().string());
        Element acceptRulesForm = document.getElementById(RW_FORM_ACCEPT_RULES_ID);
        String acceptRulesAction = acceptRulesForm.attr(RW_ACTION_ATTR_NAME);
        Element sunFacesInput = document.getElementById(RW_INPUT_FACES_ID);
        String sunFacesIds = sunFacesInput.attr("value");
        // Accept rules request creation
        String acceptRulesUrl = createAcceptRulesUrl(acceptRulesAction);
        Request acceptRulesRequest = createAcceptRulesRequest(acceptRulesUrl, sunFacesIds);
        // Do accept rules request
        Response acceptRulesResponse = httpClient.newCall(acceptRulesRequest).execute();
        // Fill purchase dialog
        fillPurchaseDialog(dialog, acceptRulesResponse);

        return acceptRulesResponse;
    }

    private void fillPurchaseDialog(PurchaseDialog dialog, Response acceptRulesResponse) {
        dialog.setCurrentResponse(acceptRulesResponse);
        dialog.setDialogStatus(DialogStatus.SELECT_ROUTE);
    }

    private Request createAcceptRulesRequest(String acceptRulesUrl, String sunFacesIds) {
        return new Request.Builder()
                .url(acceptRulesUrl)
                .header("Content-Type", "application/x-www-form-urlencoded") // be on the safe side
                .post(createAcceptRulesRequestBody(sunFacesIds))
                .build();
    }

    private RequestBody createAcceptRulesRequestBody(String sunFacesIds) {
        return new FormBody.Builder()
                .add(RW_INPUT_CHECKBOX_ID, "on")
                .add(RW_INPUT_CONTINUE_BUTTON_ID, "Продолжить")
                .add(RW_INPUT_FACES_ID, sunFacesIds)
                .add(RW_FORM_ACCEPT_RULES_ID, RW_FORM_ACCEPT_RULES_ID)
                .build();
    }

    private String createAcceptRulesUrl(String acceptRulesAction) {
        return BASE_URL + acceptRulesAction;
    }
}
