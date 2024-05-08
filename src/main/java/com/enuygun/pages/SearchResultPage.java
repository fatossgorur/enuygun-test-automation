package com.enuygun.pages;

import com.enuygun.helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends DriverHelper {
    public SearchResultPage(WebDriver webDriver) {
        super(webDriver);
    }

    private final By flightListBy = By.cssSelector("div.flight-list-body");
    private final By filterDepartureReturnTimeBy = By.cssSelector("i.ctx-filter-departure-return-time");
    private final By departureTimeLeftBy = By.cssSelector("div[data-testid='departureDepartureTimeSlider'] div.rc-slider-handle-1");
    private final By departureTimeRightBy = By.cssSelector("div[data-testid='departureDepartureTimeSlider'] div.rc-slider-handle-2");
    private final By flightDepartureTimeListBy = By.cssSelector("div.flight-departure-time");
    private final By filterAirlineButtonBy = By.cssSelector("i.ctx-filter-airline");
    private final By thyAirlineBy = By.xpath("//span[text()='Türk Hava Yolları']");
    private final By flightPriceListBy = By.cssSelector("data-price");

    public void setTheDepartureArrivalTimesFilter(int leftX,int rightX) {
        clickElement(filterDepartureReturnTimeBy);
        dragAndDropAction(departureTimeLeftBy,leftX,0);
        dragAndDropAction(departureTimeRightBy,rightX,0);
    }

    public List<String> flightDepartureTimes() {
        waitToElement(flightDepartureTimeListBy);
        List<WebElement> flightElements = findElements(flightDepartureTimeListBy);
        List<String> departureTime = new ArrayList<>();
        for (WebElement flightElement : flightElements) {
            String departureTimeText = getText(flightElement);
            departureTime.add(departureTimeText);
        }
        return departureTime;
    }

    public List<Double> flightPrices() {
        List<WebElement> flightElements = findElements(flightPriceListBy);
        List<Double> flightPrice = new ArrayList<>();
        for (WebElement flightElement : flightElements) {
            double price = Double.parseDouble(getText(flightElement).replaceAll("[^0-9.]", ""));
            flightPrice.add(price);
        }
        return flightPrice;
    }

    public void selectTurkishAirlines() {
        scrollAndClickElement(filterAirlineButtonBy);
        clickElement(thyAirlineBy);
    }

    public boolean controlFlightList() {
        return isElementDisplayed(flightListBy);
    }
}
