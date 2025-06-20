package Testcase.Raiway;

import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.Railway.*;

import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.Constant;


public class LoginTest {

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");

        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");

        Constant.WEBDRIVER.quit();
    }


    @Test
    public void TC01_UserCanLoginWithValidCredentials() {
        System.out.println("TC01 - User can log in to Railway with valid username and password");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        String actualMsg = loginPage.login(Constant.USERNAME, Constant.PASSWORD).getWelcomeMessage();

        String expectedMsg = "Welcome " + Constant.USERNAME;

        System.out.println("Expected message: '" + expectedMsg + "'");
        System.out.println("Actual message: '" + actualMsg + "'");

        Assert.assertEquals(actualMsg.trim(), expectedMsg, "Error message is not displayed as expected");

        System.out.println("TC01 - Login successful and welcome message displayed correctly");
    }

    @Test
    public void TC02() {
        System.out.println("TC02 - User can't login with blank Username textbox");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        loginPage.login("", Constant.PASSWORD);

        String actualErrorMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualErrorMsg, expectedMsg, "Error message is not displayed as expected");
    }

    @Test
    public void TC03() {
        System.out.println("TC03 - User cannot log into Railway with invalid password");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        loginPage.login(Constant.USERNAME, "invalid");

        String actualErrorMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";

        Assert.assertEquals(actualErrorMsg, expectedMsg, "Error message is not displayed as expected");
    }
    @Test
    public void TC04() {
        System.out.println("Login page displays when un-logged User clicks on 'Book ticket' tab");
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickBookTicket();
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.getBtnLogin().isDisplayed(), "Login page did not display when un-logged User clicked on 'Book ticket' tab");
    }

    @Test
    public void TC05() {
        System.out.println("TC05 - System shows message when user enters wrong password several times");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        for (int i = 1; i <= 4; i++) {
            loginPage.login(Constant.USERNAME, "wrongPassword");
            loginPage.gotoLoginPage();
        }

        loginPage.login(Constant.USERNAME, "wrongPassword");

        String actualErrorMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";

        Assert.assertEquals(actualErrorMsg, expectedMsg, "Error message for multiple failed login attempts is not displayed as expected");
    }

    @Test
    public void TC06() {
        System.out.println("TC06 - Additional pages display once user logged in");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        MyTicketPage myTicketPage = homePage.gotoMyTicketPage();

        Assert.assertTrue(myTicketPage.isSomeElementDisplayed(), "My Ticket page is not displayed as expected");
    }
    @Test
    public void TC07() {
        System.out.println("TC7 - User can create new account");
        HomePage HomePage = new HomePage();
        HomePage.open();

        RegisterPage registerPage = HomePage.gotoRegisterPage();

        String email = "hahahaha@gmail.com";
        String password = "Password123";
        String confirmPassword = "Password123";
        String pidPassport = "M98765432";

        registerPage.register(email, password, confirmPassword, pidPassport);
        String actualMessage = registerPage.getRegisterSuccessMessage();
        String expectedSuccessMessage = "Thank you for registering your account";

        Assert.assertEquals(actualMessage, expectedSuccessMessage);
    }

@Test
public void TC08() {
    System.out.println("TC08 - User can't login with an account that hasn't been activated");
    HomePage homePage = new HomePage();
    homePage.open();

    LoginPage loginPage = homePage.gotoLoginPage();
    String actualMsg = loginPage.login("inactiveUsername", "inactivePassword").getErrorMessage();
    String expectedMsg = "Invalid username or password. Please try again.";

    Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected.");
}

@Test
public void TC09() {
    System.out.println("TC09 - User can change password");

    HomePage homePage = new HomePage();
    homePage.open();

    LoginPage loginPage = homePage.gotoLoginPage();
    loginPage.login("kiemthu12345@gmail.com", "kiemthu12345");

    ChangePasswordPage changePasswordPage = homePage.gotoChangePasswordPage();

    String currentPassword = "kiemthu12345";
    String newPassword = "newPassword123";
    String confirmPassword = "newPassword123";
    changePasswordPage.changePassword(currentPassword, newPassword, confirmPassword);

    String actualMsg = changePasswordPage.successMessage();
    String expectedMsg = "Your password has been updated";

    Assert.assertEquals(actualMsg, expectedMsg, "Success message is not displayed as expected.");
    System.out.println("TC09 - Password changed successfully");
}
    @Test
    public void TC10() {
        System.out.println("TC10 - User can't create account with 'Confirm password' is not the same with 'Password'");

        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();

        String email = "testuser@example.com";
        String password = "validPassword123";
        String confirmPassword = "differentPassword";
        String pidPassport = "123456789";

        registerPage.register(email, password, confirmPassword, pidPassport);

        String actualErrorMsg = registerPage.getRegisterErrorMessage();
        String expectedErrorMsg = "There're errors in the form. Please correct the errors and try again.";

        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected.");
    }
    @Test
    public void TC11() {
        System.out.println("TC11 - User can't create account while password and PID fields are empty");

        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();

        String email = "testuser123@gmail.com";
        String password = "";
        String confirmPassword = "";
        String pidPassport = "";

        registerPage.register(email, password, confirmPassword, pidPassport);

        String actualErrorMessage = registerPage.getRegisterErrorMessage();
        String expectedErrorMessage = "There're errors in the form. Please correct the errors and try again.";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is not displayed as expected");

        System.out.println("TC11 - Error message displayed correctly when password and PID are empty");
    }

    @Test
    public void TC12() {
        System.out.println("TC12 - Errors display when password reset token is blank");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        ForgotPasswordPage forgotPasswordPage = loginPage.gotoForgotPasswordPage();
        forgotPasswordPage.enterEmailAddress("kiemthu12345@gmail.com");
        forgotPasswordPage.clickSendInstructionsButton();
    }
    @Test
    public void TC13() {
        System.out.println("TC13 - Errors display if password and confirm password don't match when resetting password");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        ForgotPasswordPage forgotPasswordPage = loginPage.gotoForgotPasswordPage();
        forgotPasswordPage.enterEmailAddress("kiemthu12345@gmail.com");
        forgotPasswordPage.clickSendInstructionsButton();
    }
    @Test
    public void TC14() {
        System.out.println("TC14 - User can book 1 ticket at a time");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        BookTicketPage ticketBookingPage = homePage.gotoBookTicketPage();

        ticketBookingPage.bookTicket("11/18/2024","Huế","Sài Gòn","Soft bed with air conditioner",1);

        String actualMsg = ticketBookingPage.getSuccessMessage();
        String expectedMsg = "Ticket booked successfully!";
        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Success message is not displayed as expected.");

        TicketInfor ticketInfor = ticketBookingPage.getTicketInfo();
        Assert.assertEquals(ticketInfor.getDepartDate(), "11/18/2024", "Depart Date is not correct.");
        Assert.assertEquals(ticketInfor.getDepartStation(), "Huế", "Depart Station is not correct.");
        Assert.assertEquals(ticketInfor.getArriveStation(), "Sài Gòn", "Arrive Station is not correct.");
        Assert.assertEquals(ticketInfor.getSeatType(), "Soft bed with air conditioner", "Seat Type is not correct.");
        Assert.assertEquals(ticketInfor.getAmount(), 1, "Ticket Amount is not correct.");
    }

    @Test
    public void TC15() {
        System.out.println("User can open 'Book ticket' page by clicking on 'Book ticket' link in 'Train timetable' page");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        loginPage.getTxtUsername().sendKeys("kiemthu12345@gmail.com");
        loginPage.getTxtPassword().sendKeys("kiemthu12345");
        loginPage.getBtnLogin().submit();

        TimetablePage trainTimetablePage = homePage.gotoTimetablePage();

        trainTimetablePage.selectDepartureAndArrival("Huế", "Sài Gòn");

        String[] selectedStations = trainTimetablePage.getSelectedStations();
        List<String> compare = Arrays.asList(selectedStations);

        List<String> expectedInfo = Arrays.asList("Huế", "Sài Gòn");

        trainTimetablePage.clickBookTicketButton();

        Assert.assertEquals(compare, expectedInfo, "Booking information doesn't match");
    }

    @Test
    public void TC16() {
        System.out.println("TC16 - User can login, book and cancel a ticket");

        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        BookTicketPage bookTicketPage = homePage.gotoBookTicketPage();
        String ticketId = bookTicketPage.bookTicket("11/18/2024", "Huế", "Sài Gòn", "Soft bed with air conditioner", 1);


        MyTicketPage myTicketPage = homePage.gotoMyTicketPage();
        myTicketPage.cancelTicket(ticketId);


        Assert.assertTrue(myTicketPage.isTicketCancelled(ticketId), "Ticket cancellation failed!");

        System.out.println("TC16 - Ticket booked and cancelled successfully");
    }
}
