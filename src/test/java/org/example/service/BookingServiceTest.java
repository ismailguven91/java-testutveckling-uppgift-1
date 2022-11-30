package org.example.service;

import org.example.BookingRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {


    private PayService payService;
    private BookingService bookingService;
    private ArgumentCaptor<Integer> payArgumentCaptor;

    @BeforeEach
    void setUp() {
        payService = mock(PayService.class);
        bookingService = new BookingService(payService);
        payArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
    }

    @Test
    public void Loan_Book() {
        BookingRequest bookingRequestLoan = new BookingRequest("1", "Bamse", 50, true);
        boolean requestLoan = bookingService.loanBook(bookingRequestLoan);

        assertEquals(true, requestLoan);
    }

    @Test
    public void Loan_Book_And_Return_Book() {
        BookingRequest bookingRequestLoan = new BookingRequest("1", "Bamse", 50, true);
        BookingRequest bookingRequestReturn = new BookingRequest("Bamse", true);
        bookingService.loanBook(bookingRequestLoan);
        boolean requestLoan = bookingService.returnBook(bookingRequestReturn);

        assertEquals(true, requestLoan);
    }

    @Test
    public void Try_To_Loan_An_Already_Loaned_Book() {

        BookingRequest bookingRequest1 = new BookingRequest("1", "Mamma Mu", 100, true);
        BookingRequest bookingRequest2 = new BookingRequest("2", "Mamma Mu", 100, true);

        bookingService.loanBook(bookingRequest1);

        boolean requestLoan = bookingService.loanBook(bookingRequest2);

        assertEquals(false, requestLoan);
    }

    @Test
    public void loan_Book_When_Paying_Right_Amount_Of_Money() {
        BookingRequest bookingRequestLoan = new BookingRequest("1", "Bamse", 50, true);
        bookingService.loanBook(bookingRequestLoan);

        verify(payService, times(1)).pay(payArgumentCaptor.capture());
        int amount = payArgumentCaptor.getValue();

        assertEquals(50, amount);
    }

    @Test
    public void return_False_When_Not_Paid() {
        BookingRequest bookingRequestLoan1 = new BookingRequest("1", "Bamse", 50, false);
        boolean isLoaned = bookingService.loanBook(bookingRequestLoan1);

        assertEquals(false, isLoaned);
    }

    @Test
    public void get_All_UnLoaned_Books() {
        BookingRequest bookingRequestLoan = new BookingRequest("1", "Bamse", 50, true);

        bookingService.loanBook(bookingRequestLoan);

        ArrayList<String> actualList = bookingService.getAvailableBooks();

        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("Mamma Mu");
        expectedList.add("Pippi LångStrump");

        assertEquals(expectedList, actualList);
    }

    @Test
    public void search_By_Rating() {
        BookingRequest bookingRequestLoan1 = new BookingRequest("1", "Bamse", 50, true);
        BookingRequest bookingRequestReturn1 = new BookingRequest("Bamse", true);
        BookingRequest bookingRequestLoan2 = new BookingRequest("1", "Mamma Mu", 100, true);
        BookingRequest bookingRequestReturn2 = new BookingRequest("Mamma Mu", true);
        bookingService.loanBook(bookingRequestLoan1);
        bookingService.loanBook(bookingRequestLoan2);
        bookingService.returnBook(bookingRequestReturn1);
        bookingService.returnBook(bookingRequestReturn2);

        ArrayList<String> actualList = bookingService.searchByRate(1);
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("Bamse");
        expectedList.add("Mamma Mu");

        assertEquals(expectedList, actualList);
    }

    @Test
    public void search_By_Writer() {

        ArrayList<String> expected = bookingService.searchBook("Rune Andréasson");

        ArrayList<String> actual = new ArrayList<>();
        actual.add("Bamse");

        assertEquals(expected, actual);
    }

    @Test
    public void search_By_Title() {

        ArrayList<String> expected = bookingService.searchBook("Bamse");

        ArrayList<String> actual = new ArrayList<>();
        actual.add("Bamse");

        assertEquals(expected, actual);
    }

    @Test
    public void search_By_Category() {

        ArrayList<String> expected = bookingService.searchBook("Comedy");

        ArrayList<String> actual = new ArrayList<>();
        actual.add("Mamma Mu");
        actual.add("Pippi LångStrump");

        assertEquals(expected, actual);
    }
}