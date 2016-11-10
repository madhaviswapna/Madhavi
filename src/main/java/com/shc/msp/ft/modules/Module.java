package com.shc.msp.ft.modules;

import com.shc.automation.Action;
import com.shc.automation.utils.TestContext;
import com.shc.msp.ft.factory.SiteFactory;


public abstract class Module {

    public SiteFactory factory = new SiteFactory();

    protected Module(SiteFactory factory) {
        this.factory = factory;
    }

    public TestContext getContext() {
        return TestContext.get();
    }

    public Action getAction() {
        return getContext().getAction();
    }

}
