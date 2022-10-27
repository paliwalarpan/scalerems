package com.scaler.ems;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Web layer test suite")
@SelectPackages({"com.scaler.ems.controller"})
class ControllerTestSuite {
}
