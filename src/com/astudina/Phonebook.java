package com.astudina;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Phonebook {
    public Map<String, ArrayList<String>> phones;

    public Phonebook() {
        phones = new HashMap<>();
    }


    public void addContact(String name, String number) {
        ArrayList<String> list = new ArrayList<>();
        list.add(number);
        addContact(name, list);
    }


    public void addContact(String name) {
        addContact(name, new ArrayList<>());
    }

    public void addContact(String name, ArrayList<String> numbers) throws IllegalArgumentException {
        if (phones.containsKey(name)) {
            throw new IllegalArgumentException("Такой контакт уже существует");
        }
        phones.put(name, numbers);
    }

    public void removeContact(String name) {
        phones.remove(name);
    }

    public void addNumber(String name, String number) throws IllegalArgumentException {
        ArrayList<String> list = phones.get(name);
        if (list != null) {
            list.add(number);
        } else {
            throw new IllegalArgumentException("Такого контакта нет");
        }
    }


    public void removeNumber(String name, String number) {
        ArrayList<String> list = phones.get(name);
        if (list != null) {
            list.remove(number);
        }
    }

    /**
     * @param number
     * @return Возвращает найденный контакт или Null, если такого контакта нет
     */
    public String searchByNumber(String number)  {
        for (String name :
                phones.keySet()) {
            for (String s :
                    phones.get(name)) {
                if (number.equalsIgnoreCase(s)) {
                    return name;
                }
            }
        }
        return null;
    }

    /**
     * @param name
     * @return Возвращает найденный номер или null, если такого номера нет
     */
    public ArrayList<String> searchByName(String name) {
        return phones.get(name);
    }
}
