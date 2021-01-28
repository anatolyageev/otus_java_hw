package homework;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CustomerService {
    private final NavigableMap<Customer, String> customerStringMap;

    public CustomerService() {
        customerStringMap = new TreeMap<>(Comparator.comparingLong(Customer::getScores));
    }

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> temEntry = customerStringMap.firstEntry();
        Customer tempCustomer = new Customer(temEntry.getKey().getId(), temEntry.getKey().getName(), temEntry.getKey().getScores());
        return new AbstractMap.SimpleEntry<>(tempCustomer, temEntry.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return customerStringMap.higherEntry(customer);
    }

    public void add(Customer customer, String data) {
        customerStringMap.put(customer, data);
    }
}
