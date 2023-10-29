package edu.hw3;

import java.util.Arrays;

public class Task5 {
    private static final String ASC_ORDER = "ASC";

    public Contact[] parseContacts(String[] names, String order) {
        if (!ASC_ORDER.equals(order) && !"DESC".equals(order)) {
            throw new IllegalArgumentException("Неверный параметр сортировки");
        }
        if (names == null || names.length == 0) {
            return new Contact[0];
        }
        return Arrays.stream(names).map(Contact::new)
            .sorted((Contact o1, Contact o2) -> (ASC_ORDER.equals(order) ? 1 : -1) * o1.compareTo(o2))
            .toArray(Contact[]::new);
    }

    public record Contact(String name) implements Comparable {

        @Override
        public int compareTo(Object o) {
            return getSortingParam(this.name).compareTo(getSortingParam(((Contact) o).name));
        }

        private String getSortingParam(String name) {
            String[] nameParts = name.split(" ");
            if (nameParts.length > 2) {
                throw new IllegalArgumentException("Неверно заданы имя и фамилия");
            }
            return nameParts[nameParts.length - 1];
        }
    }
}
