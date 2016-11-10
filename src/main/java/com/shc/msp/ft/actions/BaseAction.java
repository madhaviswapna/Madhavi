package com.shc.msp.ft.actions;

import com.shc.automation.Logger;
import com.shc.automation.utils.TestContext;
import com.shc.automation.utils.TestUtils.Feature;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.factory.SiteFactory;

public abstract class BaseAction {
    protected SiteFactory factory;

    public BaseAction(SiteFactory factory) {
        this.factory = factory;
        setCurrentFeature();
    }

    public BaseAction() {
    }

    /**
	 * To set the current feature. Retrieve the feature in context and set it as
	 * the current feature
	 * TODO: feature().getFeatureName() should be changed to feature() once we move the Feature class to framework
	 */
	protected void setCurrentFeature() {
		TestContext.get().setCurrentFeature(feature());
	}

    protected abstract Feature feature();

    public NavigationAction _NavigationAction() {

        return this.factory.navigationAction();
    }

    public TestContext getContext() {
        return TestContext.get();
    }

    public OrderDetailsAction _OrderDetailsAction() {

        return this.factory.orderDetailsAction();
    }
    
    public LineItemDetailsAction _LineItemDetailsAction() {

        return this.factory.lineItemDetailsAction();
    }

    public SalesCheckDetailsAction _SalesCheckDetailsAction() {

        return this.factory.salesCheckDetailsAction();
    }
    
    public BaseAction demarcate(LogFormatterAction.LogType typeOfLog, String textToAppendAsTitle) {
        return this._LogFormatterAction().demarcate(typeOfLog, textToAppendAsTitle);
    }

    public LogFormatterAction _LogFormatterAction() {
        return this.factory.logFormatterAction();
    }
    
    public BaseAction addlogType(TestStepType testStepType)
    {
        Logger.log(null,testStepType);
        return this;
    }
    

}
