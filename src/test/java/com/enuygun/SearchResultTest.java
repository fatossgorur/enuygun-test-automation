package com.enuygun;

import com.enuygun.helper.ConfigHelper;
import com.enuygun.pages.HomePage;
import com.enuygun.pages.SearchResultPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Properties;

@Epic("Flight search results tests")
public class SearchResultTest extends BaseTest {
    public static Properties testDataProps = ConfigHelper.readProp("src/test/resources/properties/testData.properties");

    @Test(priority = 1)
    @Story("Check the flight list")
    @Description("Check the flight list")
    private void check_flight_list() {
        String flightOrigin = testDataProps.getProperty("flightOrigin");
        String flightDestination = testDataProps.getProperty("flightDestination");
        String departureDateMonthYear = testDataProps.getProperty("departureDateMonthYear");
        String returnDateMonthYear = testDataProps.getProperty("returnDateMonthYear");
        int targetAdultCount = Integer.parseInt(testDataProps.getProperty("targetAdultCount"));
        int targetChildCount = Integer.parseInt(testDataProps.getProperty("targetChildCount"));
        int targetInfantCount = Integer.parseInt(testDataProps.getProperty("targetInfantCount"));
        String flightClass = testDataProps.getProperty("flightClass");
        HomePage homePage = new HomePage(webDriver);
        homePage.navigateToEnUygunHomePage();
        homePage.selectRoundTripOption();
        homePage.selectFromCityToCityFlight(flightOrigin, flightDestination);
        homePage.selectDepartureAndReturnDate(departureDateMonthYear, returnDateMonthYear);
        homePage.selectPassengerAndCabin(targetAdultCount, targetChildCount, targetInfantCount, flightClass);
        SearchResultPage searchResultPage = homePage.findCheapTickets();
        boolean isDisplayed = searchResultPage.controlFlightList();
        Assert.assertTrue(isDisplayed, "Element görünür değil");
    }

    @Test(priority = 2)
    @Story("Check for flights' departure times being within the specified interval")
    @Description("Check for flights' departure times being within the specified interval")
    private void check_flight_departure_time() {
        String flightOrigin = testDataProps.getProperty("flightOrigin");
        String flightDestination = testDataProps.getProperty("flightDestination");
        String departureDateMonthYear = testDataProps.getProperty("departureDateMonthYear");
        String returnDateMonthYear = testDataProps.getProperty("returnDateMonthYear");
        int targetAdultCount = Integer.parseInt(testDataProps.getProperty("targetAdultCount"));
        int targetChildCount = Integer.parseInt(testDataProps.getProperty("targetChildCount"));
        int targetInfantCount = Integer.parseInt(testDataProps.getProperty("targetInfantCount"));
        String flightClass = testDataProps.getProperty("flightClass");
        HomePage homePage = new HomePage(webDriver);
        homePage.navigateToEnUygunHomePage();
        homePage.selectRoundTripOption();
        homePage.selectFromCityToCityFlight(flightOrigin, flightDestination);
        homePage.selectDepartureAndReturnDate(departureDateMonthYear, returnDateMonthYear);
        homePage.selectPassengerAndCabin(targetAdultCount, targetChildCount, targetInfantCount, flightClass);
        SearchResultPage searchResultPage = homePage.findCheapTickets();
        searchResultPage.setTheDepartureArrivalTimesFilter(100, -60);
        List<String> flightDepartureTimeList = searchResultPage.flightDepartureTimes();
        for (String flightDepartureTime : flightDepartureTimeList) {
            int hour = Integer.parseInt(flightDepartureTime.split(":")[0]);
            int minute = Integer.parseInt(flightDepartureTime.split(":")[1]);
            Assert.assertTrue((hour > 10 && hour < 18) ||
                            (hour == 10 && minute >= 0) ||
                            (hour == 18 && minute == 0),
                    "Uçuşun kalkış saati 10:00 ile 18:00 arasında değil: " + flightDepartureTime);
        }
    }

    @Test(priority = 3)
    @Story("Check the ascending order of Turkish Airlines flight prices")
    @Description("Check the ascending order of Turkish Airlines flight prices")
    private void check_asc_order_turkish_airlines_prices() {
        String flightOrigin = testDataProps.getProperty("flightOrigin");
        String flightDestination = testDataProps.getProperty("flightDestination");
        String departureDateMonthYear = testDataProps.getProperty("departureDateMonthYear");
        String returnDateMonthYear = testDataProps.getProperty("returnDateMonthYear");
        int targetAdultCount = Integer.parseInt(testDataProps.getProperty("targetAdultCount"));
        int targetChildCount = Integer.parseInt(testDataProps.getProperty("targetChildCount"));
        int targetInfantCount = Integer.parseInt(testDataProps.getProperty("targetInfantCount"));
        String flightClass = testDataProps.getProperty("flightClass");
        HomePage homePage = new HomePage(webDriver);
        homePage.navigateToEnUygunHomePage();
        homePage.selectRoundTripOption();
        homePage.selectFromCityToCityFlight(flightOrigin, flightDestination);
        homePage.selectDepartureAndReturnDate(departureDateMonthYear, returnDateMonthYear);
        homePage.selectPassengerAndCabin(targetAdultCount, targetChildCount, targetInfantCount, flightClass);
        SearchResultPage searchResultPage = homePage.findCheapTickets();
        searchResultPage.setTheDepartureArrivalTimesFilter(100, -60);
        searchResultPage.selectTurkishAirlines();
        List<Double> flightTHYPriceList = searchResultPage.flightPrices();
        double previousPrice = Double.MIN_VALUE;
        for (double flightTHYPrice : flightTHYPriceList) {
            Assert.assertTrue(flightTHYPrice >= previousPrice, "Fiyatlar artan sırada değil");
            previousPrice = flightTHYPrice;
        }
    }
}
