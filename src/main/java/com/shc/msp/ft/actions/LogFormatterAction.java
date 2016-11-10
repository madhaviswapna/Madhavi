package com.shc.msp.ft.actions;


import com.shc.automation.utils.TestUtils.Feature;
import com.shc.msp.ft.factory.SiteFactory;
import org.testng.Reporter;

public class LogFormatterAction extends BaseAction {


    public LogFormatterAction(SiteFactory siteFactory) {
        super(siteFactory);
    }

    @Override
    protected Feature feature() {
        return Feature.UNDEFINED;
    }

    public static enum LogType {
        BEGINCLEANUP, BEGINTESTFLOW, ENDTESTFLOW
    }

    public BaseAction demarcate(LogType typeOfLog, String textToAppendAsTitle) {

        switch (typeOfLog) {
            case BEGINCLEANUP:
                Reporter.log("<div id=\"cleanup\" style=\"background-color:#E3CEF6;\"><h4 style=\"margin-bottom:0;\">Cleanup</h4></div>");
                break;
            case BEGINTESTFLOW:
                if (textToAppendAsTitle.isEmpty()) {
                    Reporter.log("<div id=\"testflow\" style=\"background-color:#A9E2F3;\"><h4 style=\"margin-bottom:0;\">Test Scenario</h4></div>");
                } else {
                    Reporter.log("<div id=\"testflow\" style=\"background-color:#A9E2F3;\"><h4 style=\"margin-bottom:0;\">Test Scenario - " + textToAppendAsTitle + "</h4></div>");
                }
                break;
            case ENDTESTFLOW:
                Reporter.log("<div id=\"testcomplete\" style=\"background-color:#A9E2F3;\"><h4 style=\"margin-bottom:0;\">Test Completed</h4></div>");
                break;
            default:
                break;
        }
        return this._NavigationAction();
    }

    public static void beginSetup() {
        Reporter.log("<div id=\"setUp\" style=\"background-color:#D8D8D8;\"><h4 style=\"margin-bottom:0;\">Setup</h4></div>");
    }

}

