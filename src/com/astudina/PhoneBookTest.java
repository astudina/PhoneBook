package com.astudina;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class PhoneBookTest {

    private PhoneBook book;
    private Map<String, Set<String>> actual;


    @Before
    public void setUp(){
        book = new PhoneBook();

        actual = new HashMap<>();

        Set<String> actualSet = new HashSet<>();
        actualSet.add("89606817559");
        actual.put("Anastasia", actualSet);

        actualSet = new HashSet<>();
        actualSet.add("3-84-60");
        actual.put("Maria", actualSet);

        actualSet = new HashSet<>();
        actual.put("Pavel", actualSet);
        actualSet = new HashSet<>();
        actual.put("Denis", actualSet);
    }


//    @Test
//    public void pointerNotEquals(){
//        Assert.assertNotSame(book.getPhones(), book.phones);
//    }
//    @Test
//    public void getPhones(){
//        Assert.assertEquals(book.getPhones(), book.phones);
//    }


    @Test
    public void addContact() {
        book.addContact("Anastasia", "89606817559");
        book.addContact("Maria", "3-84-60");
        Set<String> actualSet = new HashSet<>();
        book.addContact("Pavel", actualSet);
        book.addContact("Denis");

        assertEquals(book.getPhones(), actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addContact_ADD_THE_SAME(){
        book.addContact("Anastasia", "89606817559");
        book.addContact("Anastasia", "89606817559");
        Set<String> actualSet = new HashSet<>();
        book.addContact("Anastasia", actualSet);
        book.addContact("Anastasia");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addContact_ERROR(){
        book.addContact("Anastasia", "89606817559&");
        Set<String> actualSet = new HashSet<>();
        book.addContact("Anastasia", actualSet);
        book.addContact("Anastasia");
    }


    @Test
    public void removeContact() {
        book.addContact("Anastasia", "89606817559");
        book.addContact("Maria", "3-84-60");
        Set<String> actualSet = new HashSet<>();
        book.addContact("Pavel", actualSet);
        book.addContact("Denis");
        book.addContact("Alla", "53433");
        actualSet = new HashSet<>();
        book.addContact("Andrey", actualSet);
        book.addContact("Anton");

        book.removeContact("Alla");
        book.removeContact("Andrey");
        book.removeContact("Anton");

        assertEquals(book.getPhones(), actual);

    }

    @Test
    public void addNumber() {
        book.addContact("Anastasia", "89606817559");
        book.addContact("Maria", "3-84-60");
        Set<String> actualSet = new HashSet<>();
        book.addContact("Pavel", actualSet);
        book.addContact("Denis");
        book.addNumber("Anastasia", "6-78-90");
        book.addNumber("Maria", "+79805647392");
        book.addNumber("Pavel", "36161");
        book.addNumber("Denis", "6793679");


        actual.get("Anastasia").add("6-78-90");
        actual.get("Maria").add("+79805647392");
        actual.get("Pavel").add("36161");
        actual.get("Denis").add("6793679");

        assertEquals(book.getPhones(), actual);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addNumber_2() {
        book.addContact("Anastasia", "89606817559");
        book.addContact("Maria", "3-84-60");
        Set<String> actualSet = new HashSet<>();
        book.addContact("Pavel", actualSet);
        book.addContact("Denis");
        book.addNumber("Oleg", "2-16-27");
    }

    @Test (expected = IllegalArgumentException.class)
    public void addNumber_3() {
        book.addContact("Anastasia", "89606817559");
        book.addContact("Maria", "3-84-60");
        Set<String> actualSet = new HashSet<>();
        book.addContact("Pavel", actualSet);
        book.addContact("Denis");
        book.addNumber("Anastasia", "2-16-27?");
    }

    @Test
    public void removeNumber() {
        book.addContact("Anastasia", "89606817559");
        book.addNumber("Anastasia", "12345");
        book.addContact("Maria", "3-84-60");
        book.addNumber("Maria", "54321");
        Set<String> actualSet = new HashSet<>();
        book.addContact("Pavel", actualSet);
        book.addContact("Denis");

        book.removeNumber("Anastasia", "12345");
        book.removeNumber("Maria", "54321");

        assertEquals(book.getPhones(), actual);

    }

    @Test
    public void searchByNumber() {
        book.addContact("Anastasia", "89606817559");
        book.addContact("Maria", "3-84-60");
        Set<String> actualSet = new HashSet<>();
        book.addContact("Pavel", actualSet);
        book.addContact("Denis");

        String expected = book.searchByNumber("89606817559");

        assertEquals(expected, "Anastasia");
    }

    @Test
    public void searchByNumber_NULL() {
        book.addContact("Anastasia", "89606817559");
        book.addContact("Maria", "3-84-60");
        Set<String> actualSet = new HashSet<>();
        book.addContact("Pavel", actualSet);
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
        Set<String> actualSet = new HashSet<>();
        book.addContact("Pavel", actualSet);
        book.addContact("Denis");

        actual.get("Anastasia").add("567890");
        actual.get("Anastasia").add("7-16-45");

        Set<String> expected = book.searchByName("Anastasia");

        assertEquals(expected, actual.get("Anastasia"));

    }

    @Test
    public void searchByName_NULL() {
        book.addContact("Anastasia", "89606817559");
        book.addContact("Maria", "3-84-60");
        Set<String> actualSet = new HashSet<>();
        book.addContact("Pavel", actualSet);
        book.addContact("Denis");

        assertNull(book.searchByName("Anton"));

    }

//    @Test
//    public void pointerNotEqual_2(){
//        book.addContact("Anastasia", "89606817559");
//        Assert.assertNotSame(book.searchByName("Anastasia"), book.phones.get("Anastasia"));
//    }
//
//    @Test
//    public void searchByName_2(){
//        book.addContact("Anastasia", "89606817559");
//        Assert.assertEquals(book.searchByName("Anastasia"), book.phones.get("Anastasia"));
//    }
}