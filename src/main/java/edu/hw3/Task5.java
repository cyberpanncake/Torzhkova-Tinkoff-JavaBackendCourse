package edu.hw3;

import java.util.Arrays;

public class Task5 {

    private enum Order {
        ASC,
        DESC
    }

    public Contact[] parseContacts(String[] names, String order) {
        if (!Order.ASC.name().equals(order) && !Order.DESC.name().equals(order)) {
            throw new IllegalArgumentException("Неверный параметр сортировки");
        }
        Order ord = Order.valueOf(order);
        if (names == null || names.length == 0) {
            return new Contact[0];
        }
        return Arrays.stream(names).map(Contact::of)
            .sorted((Contact o1, Contact o2) -> (ord == Order.ASC ? 1 : -1) * o1.compareTo(o2))
            .toArray(Contact[]::new);
    }

    public record Contact(String name, String surname) implements Comparable<Contact> {

        public static Contact of(String fullname) {
            String[] nameParts = fullname.split(" ");
            if (nameParts.length > 2) {
                throw new IllegalArgumentException("Неверно заданы имя и фамилия");
            }
            return new Contact(nameParts[0], nameParts.length > 1 ? nameParts[1] : "");
        }

        @Override
        public int compareTo(Contact o) {
            String c1 = surname.isEmpty() ? name : surname;
            String c2 = o.surname.isEmpty() ? o.name : o.surname;
            return c1.compareTo(c2);
        }
    }
}
