package com.astudina;

import java.util.*;

public class PhoneBook {


    private Map<String, Set<String>> phones;


    public PhoneBook() {
        phones = new HashMap<>();
    }

    public Map<String, Set<String>> getPhones() {
        return new HashMap<>(phones);
    }

    public void addContact(String name, String number) throws IllegalArgumentException {
        if (!number.matches("[\\d+\\+*#-]+")) {
            throw new IllegalArgumentException("Недопустимый формат номера");
        }
        Set<String> set = new HashSet<>();
        set.add(number);
        addContact(name, set);
    }

    public void addContact(String name) {
        addContact(name, new HashSet<>());
    }

    public void addContact(String name, Set<String> numbers) throws IllegalArgumentException {
        if (phones.containsKey(name)) {
            throw new IllegalArgumentException("Такой контакт уже существует");
        }
        Set<String> numbers2 = new HashSet<>(numbers);
        phones.put(name, numbers2);
    }

    public void removeContact(String name) {
        phones.remove(name);
    }

    public void addNumber(String name, String number) throws IllegalArgumentException {
        if (!number.matches("[\\d+\\+*#-]+")) {
            throw new IllegalArgumentException("Недопустимый формат номера");
        }
        Set<String> set = phones.get(name);
        if (set != null) {
            set.add(number);
        } else {
            throw new IllegalArgumentException("Такого контакта нет");
        }
    }


    public void removeNumber(String name, String number) {
        Set<String> set = phones.get(name);
        if (set != null) {
            set.remove(number);
        }
    }

    /**
     * @param number
     * @return Возвращает найденный контакт или Null, если такого контакта нет
     */
    public String searchByNumber(String number) {
        for (Map.Entry<String, Set<String>> entry : phones.entrySet())
            if (entry.getValue().contains(number)) return entry.getKey();
        return null;
    }

    /**
     * @param name
     * @return Возвращает найденный номер или null, если такого номера нет
     */
    public Set<String> searchByName(String name) {
        if (phones.get(name) != null) {
            return new HashSet<>(phones.get(name));
        }
        return null;
    }
}
