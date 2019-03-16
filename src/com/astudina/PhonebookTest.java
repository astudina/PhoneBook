package com.astudina;

import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class PhonebookTest {

    private Phonebook book;
    private Map<String, ArrayList<String>> actual;


    @Before
    public void setUp(){
        book = new Phonebook();

        actual = new HashMap<>();

        ArrayList<String> actualList = new ArrayList<>();
        actualList.add("89606817559");
        actual.put("Anastasia", actualList);

        actualList = new ArrayList<>();
        actualList.add("3-84-60");
        actual.put("Maria", actualList);

        actualList = new ArrayList<>();
        actual.put("Pavel", actualList);
        actualList = new ArrayList<>();
        actual.put("Denis", actualList);
    }


    @Test
    public void addContact() {
        book.addContact("Anastasia", "89606817559");
        book.addContact("Maria", "3-84-60");
        ArrayList<String> actualList = new ArrayList<>();
        book.addContact("Pavel", actualList);
        book.addContact("Denis");

        assertEquals(book.phones, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addContact_ADD_THE_SAME(){
        book.addContact("Anastasia", "89606817559");
        book.addContact("Anastasia", "89606817559");
        ArrayList<String> actualList = new ArrayList<>();
        book.addContact("Anastasia", actualList);
        book.addContact("Anastasia");
    }


    @Test
    public void removeContact() {
        book.addContact("Anastasia", "89606817559");
        book.addContact("Maria", "3-84-60");
        ArrayList<String> actualList = new ArrayList<>();
        book.addContact("Pavel", actualList);
        book.addContact("Denis");
        book.addContact("Alla", "53433");
        actualList = new ArrayList<>();
        book.addContact("Andrey", actualList);
        book.addContact("Anton");

        book.removeContact("Alla");
        book.removeContact("Andrey");
        book.removeContact("Anton");

        assertEquals(book.phones, actual);

    }

    @Test
    public void addNumber() {
        book.addContact("Anastasia", "89606817559");
        book.addContact("Maria", "3-84-60");
        ArrayList<String> actualList = new ArrayList<>();
        book.addContact("Pavel", actualList);
        book.addContact("Denis");
        book.addNumber("Anastasia", "6-78-90");
        book.addNumber("Maria", "+79805647392");
        book.addNumber("Pavel", "36161");
        book.addNumber("Denis", "6793679");


        actual.get("Anastasia").add("6-78-90");
        actual.get("Maria").add("+79805647392");
        actual.get("Pavel").add("36161");
        actual.get("Denis").add("6793679");

        assertEquals(book.phones, actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addNumber_2() {
        book.addContact("Anastasia", "89606817559");
        book.addContact("Maria", "3-84-60");
        ArrayList<String> actualList = new ArrayList<>();
        book.addContact("Pavel", actualList);
        book.addContact("Denis");
        book.addNumber("Oleg", "2-16-27");
    }

    @Test
    public void removeNumber() {
        book.addContact("Anastasia", "89606817559");
        book.addNumber("Anastasia", "12345");
        book.addContact("Maria", "3-84-60");
        book.addNumber("Maria", "54321");
        ArrayList<String> actualList = new ArrayList<>();
        book.addContact("Pavel", actualList);
        book.addContact("Denis");

        book.removeNumber("Anastasia", "12345");
        book.removeNumber("Maria", "54321");

        assertEquals(book.phones, actual);

    }

    @Test
    public void searchByNumber() {
        book.addContact("Anastasia", "89606817559");
        book.addContact("Maria", "3-84-60");
        ArrayList<String> actualList = new ArrayList<>();
        book.addContact("Pavel", actualList);
        book.addContact("Denis");

        String expected = book.searchByNumber("89606817559");

        assertEquals(expected, "Anastasia");
    }

    @Test
    public void searchByNumber_NULL() {
        book.addContact("Anastasia", "89606817559");
        book.addContact("Maria", "3-84-60");
        ArrayList<String> actualList = new ArrayList<>();
        book.addContact("Pavel", actualList);
        book.addContact("Denis");

        String expected = book.searchByNumber("12345");

        assertNull(expected);
    }

    @Test
    public void searchByName() {
        book.addContact("Anastasia", "89606817559");
        book.addNumber("Anastasia", "567890");
        book.addNumber("Anastasia", "7-16-45");
        book.addContact("Maria", "3-84-60");
        ArrayList<String> actualList = new ArrayList<>();
        book.addContact("Pavel", actualList);
        book.addContact("Denis");

        actual.get("Anastasia").add("567890");
        actual.get("Anastasia").add("7-16-45");

        ArrayList expected = book.searchByName("Anastasia");

        assertEquals(expected, actual.get("Anastasia"));

    }

    @Test
    public void searchByName_NULL() {
        book.addContact("Anastasia", "89606817559");
        book.addContact("Maria", "3-84-60");
        ArrayList<String> actualList = new ArrayList<>();
        book.addContact("Pavel", actualList);
        book.addContact("Denis");

        ArrayList expected = book.searchByName("Anton");

        assertNull(expected);

    }
}