package io.marketmovers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;

import io.marketmovers.http.AjaxResponseFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikechabot on 10/28/15.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity test() {
        return AjaxResponseFactory.success("Hello there.");
    }

    @RequestMapping(value = "/string", method = RequestMethod.GET)
    public ResponseEntity testString() {
        return AjaxResponseFactory.success("Hello there.", "String as object");
    }

    @RequestMapping(value = "/number", method = RequestMethod.GET)
    public ResponseEntity testNumber() {
        return AjaxResponseFactory.success("Hello there.", 1);
    }

    @RequestMapping(value = "/boolean", method = RequestMethod.GET)
    public ResponseEntity testBoolean() {
        return AjaxResponseFactory.success("Hello there.", true);
    }

    @RequestMapping(value = "/object", method = RequestMethod.GET)
    public ResponseEntity testObject() {
        return AjaxResponseFactory.success("Hello there.", new TestObject("Some message", 34232, "ListEntry1", "ListEntry2", "ListEntry3", "ListEntry4"));
    }

    private class TestObject {
        private String testStr;
        private int testInt;
        private List<String> testList;

        public TestObject(String testStr, int testInt, String... testListEntry) {
            this.testStr = testStr;
            this.testInt = testInt;

            if (testListEntry != null && testListEntry.length != 0) {
                testList = new ArrayList<>(testListEntry.length);
                for (String each : testListEntry) {
                    testList.add(each);
                }
            }

        }

        public String getTestStr() {
            return testStr;
        }

        public void setTestStr(String testStr) {
            this.testStr = testStr;
        }

        public int getTestInt() {
            return testInt;
        }

        public void setTestInt(int testInt) {
            this.testInt = testInt;
        }

        public List<String> getTestList() {
            return testList;
        }

        public void setTestList(List<String> testList) {
            this.testList = testList;
        }

    }



}
