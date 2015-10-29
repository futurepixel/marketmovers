package io.marketmovers.controller;

import io.marketmovers.documents.TestStock;
import io.marketmovers.repository.TestStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;

import io.marketmovers.http.AjaxResponseFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mikechabot on 10/28/15.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private TestStockRepository testStockRepository;

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

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ResponseEntity createDocument() {
        int numberOfStocks = testStockRepository.findAll().size();
        TestStock stock = new TestStock("foo", "bar", numberOfStocks);
        testStockRepository.save(stock);
        return AjaxResponseFactory.success("Save successful", stock.getId() + " - " + stock.getNumberOfStocks());
    }

    @RequestMapping(value = "/top/{number}", method = RequestMethod.GET)
    public ResponseEntity getTop(@PathVariable int number) {
        return AjaxResponseFactory.success(null, testStockRepository.findByNumberOfStocksGreaterThan(number));
    }

}
