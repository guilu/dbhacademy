package com.dbhacademy.firefight.selenium.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.test.context.TestExecutionListeners;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@TestExecutionListeners(
        listeners = SeleniumTestExecutionListener.class,
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public @interface SeleniumTest {

    Class<? extends WebDriver> driver() default ChromeDriver.class;

    String baseUrl() default "http://localhost:8080";
}
