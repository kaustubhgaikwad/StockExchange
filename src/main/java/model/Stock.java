package model;

/**
 * Stock Entity class to represent a particular
 * hasCode and equal method to make it distinctive only based the name of the stock
 */

public class Stock {
    private final String name;

    public Stock(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Stock other = (Stock) obj;
        return this.name.equals(other.getName());
    }

}
