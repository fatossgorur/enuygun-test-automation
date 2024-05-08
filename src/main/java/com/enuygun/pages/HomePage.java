package com.enuygun.pages;

import com.enuygun.helper.ConfigHelper;
import com.enuygun.helper.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;


public class HomePage extends DriverHelper {
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public static Properties configProps = ConfigHelper.readProp("src/test/resources/properties/config.properties");
    public static Properties testDataProps = ConfigHelper.readProp("src/test/resources/properties/testData.properties");
    public static String departureDateDay = testDataProps.getProperty("departureDay");
    public static String returnDateDay = testDataProps.getProperty("returnDay");
    private final By roundTripOptionBy = By.cssSelector("div[data-testid='search-round-trip-text']");
    private final By flightOriginBy = By.cssSelector("input[data-testid='endesign-flight-origin-autosuggestion-input']");
    private final By flightDestinationBy = By.cssSelector("input[data-testid='endesign-flight-destination-autosuggestion-input']");
    private final By departureDatePickerBy = By.cssSelector("input[data-testid='enuygun-homepage-flight-departureDate-datepicker-input']");
    private final By departureDatePickerPopOverPanelBy = By.cssSelector("div[data-testid='enuygun-homepage-flight-departureDate-datepicker-popover-panel-inner']");
    private final By departureDateMonthYearBy = By.cssSelector("h3[data-testid='enuygun-homepage-flight-departureDate-month-name-and-year']");
    private final By departureDateMonthforwardButtonBy = By.cssSelector("button[data-testid='enuygun-homepage-flight-departureDate-month-forward-button']");
    private final By departureDateDayBy = By.cssSelector("div.sc-gdfaqJ div.dlKEMh div.hWjVug button[data-day='" + departureDateDay + "']");
    private final By returnDatePickerBy = By.cssSelector("input[data-testid='enuygun-homepage-flight-returnDate-datepicker-input']");
    private final By returnDatePickerPopOverPanelBy = By.cssSelector("div[data-testid='enuygun-homepage-flight-returnDate-datepicker-popover-panel-inner']");
    private final By returnDateMonthYearBy = By.cssSelector("h3[data-testid='enuygun-homepage-flight-returnDate-month-name-and-year']");
    private final By returnDateMonthforwardButtonBy = By.cssSelector("button[enuygun-homepage-flight-returnDate-month-forward-button']");
    private final By returnDateDayBy = By.cssSelector("div.dlKEMh div.hWjVug button[data-day='" + returnDateDay + "']");
    private final By passengersBy = By.cssSelector("input[data-testid='undefined-popover-button']");
    private final By passengersPopOverPanelBy = By.cssSelector("div[data-testid='undefined-popover-panel-inner']");
    private final By adultCounterButtonBy = By.cssSelector("button[data-testid='flight-adult-counter-plus-button']");
    private final By childCounterButtonBy = By.cssSelector("button[data-testid='flight-child-counter-plus-button']");
    private final By infantCounterButtonBy = By.cssSelector("button[data-testid='flight-infant-counter-plus-button']");
    private final By economyCabinButtonBy = By.cssSelector("button[data-testid='enuygun-homepage-flight-ekonomiCabin']");
    private final By businessCabinButtonBy = By.cssSelector("button[data-testid='enuygun-homepage-flight-businessCabin']");
    private final By passengerAndCabinDoneButtonBy = By.cssSelector("button[data-testid='enuygun-homepage-flight-doneBtn']");
    private final By flightsubmitButtonBy = By.cssSelector("button[data-testid='enuygun-homepage-flight-submitButton']");

    public void navigateToEnUygunHomePage() {
        String homePageUrl = configProps.getProperty("enUygunHomePageUrl");
        getUrl(homePageUrl);
    }

    public void selectRoundTripOption() {
        clickElement(roundTripOptionBy);
    }

    public void selectFromCityFlight(String fromCity) {
        sendKeysElementWithEnter(flightOriginBy, fromCity);
    }

    public void selectToCityFlight(String toCity) {
        sendKeysElementWithEnter(flightDestinationBy, toCity);
    }

    public void selectFromCityToCityFlight(String fromCity, String toCity) {
        selectFromCityFlight(fromCity);
        selectToCityFlight(toCity);
    }

    public void selectDepartureDate(String departureDate) {
        clickElement(departureDatePickerBy);
        waitToElement(departureDatePickerPopOverPanelBy);
        while (!(getTextAtIndex(departureDateMonthYearBy, 0).equals(departureDate) || getTextAtIndex(departureDateMonthYearBy, 1).equals(departureDate))) {
            clickElement(departureDateMonthforwardButtonBy);
        }
        if (getTextAtIndex(departureDateMonthYearBy, 0).equals(departureDate)) {
            clickElementAtIndex(departureDateDayBy, 0);
        } else {
            clickElementAtIndex(departureDateDayBy, 1);
        }
    }

    public void selectReturnDate(String returnDate) {
        clickElement(returnDatePickerBy);
        waitToElement(returnDatePickerPopOverPanelBy);
        while (!(getTextAtIndex(returnDateMonthYearBy, 0).equals(returnDate) || getTextAtIndex(returnDateMonthYearBy, 1).equals(returnDate))) {
            clickElement(returnDateMonthforwardButtonBy);
        }
        if (getTextAtIndex(returnDateMonthYearBy, 0).equals(returnDate)) {
            clickElementAtIndex(returnDateDayBy, 0);
        } else {
            clickElementAtIndex(returnDateDayBy, 1);
        }
    }

    public void selectDepartureAndReturnDate(String departureDateMonthYear, String returnDateMonthYear) {
        selectDepartureDate(departureDateMonthYear);
        selectReturnDate(returnDateMonthYear);
    }

    public void selectPassengerCount(int adult, int child, int infant) {
        int defaultAdultCount = Integer.parseInt(configProps.getProperty("defaultAdultCount"));
        int defaultChildCount = Integer.parseInt(configProps.getProperty("defaultChildCount"));
        int defaultInfantCount = Integer.parseInt(configProps.getProperty("defaultInfantCount"));

        clickElement(passengersBy);
        waitToElement(passengersPopOverPanelBy);

        while (defaultAdultCount < adult) {
            clickElement(adultCounterButtonBy);
            defaultAdultCount++;
        }

        while (defaultChildCount < child) {
            clickElement(childCounterButtonBy);
            defaultChildCount++;
        }

        while (defaultInfantCount < infant) {
            clickElement(infantCounterButtonBy);
            defaultInfantCount++;
        }

    }

    public void selectCabinClass(String cabinClass){
        if (cabinClass.equalsIgnoreCase("Business")) {
            clickElement(businessCabinButtonBy);
        } else {
            clickElement(economyCabinButtonBy);
        }
    }

    public void selectPassengerAndCabinDone(){
            clickElement(passengerAndCabinDoneButtonBy);
    }

    public void selectPassengerAndCabin(int adultC, int childC, int infantC,String cabinClass){
        selectPassengerCount(adultC,childC,infantC);
        selectCabinClass(cabinClass);
        selectPassengerAndCabinDone();
    }

    public SearchResultPage findCheapTickets(){
        clickElement(flightsubmitButtonBy);
        return new SearchResultPage(webDriver);
    }

}
