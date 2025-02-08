package domain.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Medicine.Medicine;
import domain.Utils.DiscountUtils;
import domain.Utils.OrdersHistory;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a user in the system.
 * Contains personal information, financial details, and methods for managing orders and prescriptions.
 */
public class User {

    private static final Logger log = LoggerFactory.getLogger(User.class);
    private ArrayList<Recipe> recipes = new ArrayList<>();
    private Order order;
    private String name;
    private String surName;
    private int age;
    private PersonType personType;
    private Gender gender;
    private double money;

    /**
     * Constructs a new User instance.
     *
     * @param recipes       The list of prescriptions issued to the user.
     * @param order         The current order of the user.
     * @param name          The name of the user.
     * @param surName       The surname of the user.
     * @param age           The age of the user.
     * @param personType    The type of the user (e.g., DEFAULT, RETIREE).
     * @param gender        The gender of the user.
     * @param money         The amount of money the user has.
     */
    public User(ArrayList<Recipe> recipes, Order order, String name, String surName, int age, PersonType personType, Gender gender, double money) {
        this.recipes = recipes;
        this.order = order;
        this.name = name;
        this.surName = surName;
        this.age = age;
        this.gender = gender;
        this.money = money;
        if (personType != null) {
            this.personType = personType;
        } else {
            this.personType = calculatePersonType(gender, age);
        }
        log.debug("Created new User: name={}, surName={}, age={}, personType={}, gender={}, money={}",
                name, surName, age, personType, gender, money);
    }

    /**
     * Calculates the person type based on gender and age.
     *
     * @param gender    The gender of the user.
     * @param age       The age of the user.
     * @return          The calculated person type (e.g., DEFAULT, RETIREE).
     */
    private PersonType calculatePersonType(Gender gender, int age){
        switch (gender){
            case MALE -> {
                return age > 65 ? PersonType.RETIREE : PersonType.DEFAULT;
            }
            case FEMALE -> {
                return age > 60 ? PersonType.RETIREE : PersonType.DEFAULT;
            }
            default -> {
                return PersonType.DEFAULT;
            }
        }

    }

    /**
     * Creates a new order for the user only with legal medicine.    !!!!!!!!!
     *
     * @param medicines The list of medicines to be included in the order.
     * @return          The created order.
     */
    public Order createOrder(ArrayList<Medicine> medicines){
        if(Objects.nonNull(medicines)) {
            ArrayList<Medicine> legalMedicines = new ArrayList<>();
            for (Medicine medicine : medicines) {
                if (!medicine.isNeedRecipe() || this.recipes.contains(new Recipe(this, medicine))) {
                    legalMedicines.add(medicine);
                }
            }
            Order order = new Order(legalMedicines, this);
            OrdersHistory.addOrder(order);
            log.debug("Created new Order for User: name={}, surName={}, number of medicines={}",
                    name, surName, legalMedicines.size());
            return order;
        } else {
            Order order = new Order(new ArrayList<>(), this);
            OrdersHistory.addOrder(order);
            log.debug("Created empty Order for User: name={}, surName={}", name, surName);
            return order;
        }
    }

    /**
     * Pays for the specified order using the user's money.
     *
     * @param order The order to be paid.
     */
    public void payOrder(Order order) {
        if (Objects.nonNull(order)) {
            double priceWithDiscount = DiscountUtils.getPriceWithDiscount(order, this);
            money -= priceWithDiscount;
            log.debug("User paid for Order: name={}, surName={}, amount paid={}, remaining money={}",
                    name, surName, priceWithDiscount, money);
        }
    }

    public double getMoney() {
        log.debug("Getting money for User: name={}, surName={}, money={}", name, surName, money);
        return money;
    }

    public void setMoney(double money) {
        log.debug("Setting money for User: name={}, surName={}, old money={}, new money={}",
                name, surName, this.money, money);
        this.money = money;
    }

    public ArrayList<Recipe> getRecipes() {
        log.debug("Getting recipes for User: name={}, surName={}, number of recipes={}",
                name, surName, recipes.size());
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        log.debug("Setting recipes for User: name={}, surName={}, old number of recipes={}, new number of recipes={}",
                name, surName, this.recipes.size(), recipes.size());
        this.recipes = recipes;
    }

    public Order getOrder() {
        log.debug("Getting order for User: name={}, surName={}", name, surName);
        return order;
    }

    public void setOrder(Order order) {
        log.debug("Setting order for User: name={}, surName={}", name, surName);
        this.order = order;
    }

    public String getName() {
        log.debug("Getting name for User: name={}, surName={}", name, surName);
        return name;
    }

    public void setName(String name) {
        log.debug("Setting name for User: old name={}, new name={}", this.name, name);
        this.name = name;
    }

    public String getSurName() {
        log.debug("Getting surname for User: name={}, surName={}", name, surName);
        return surName;
    }

    public void setSurName(String surName) {
        log.debug("Setting surname for User: old surname={}, new surname={}", this.surName, surName);
        this.surName = surName;
    }

    public int getAge() {
        log.debug("Getting age for User: name={}, surName={}, age={}", name, surName, age);
        return age;
    }

    public void setAge(int age) {
        log.debug("Setting age for User: name={}, surName={}, old age={}, new age={}",
                name, surName, this.age, age);
        this.age = age;
    }

    public PersonType getPersonType() {
        log.debug("Getting person type for User: name={}, surName={}, personType={}", name, surName, personType);
        return personType;
    }

    public void setPersonType(PersonType personType) {
        log.debug("Setting person type for User: name={}, surName={}, old personType={}, new personType={}",
                name, surName, this.personType, personType);
        this.personType = personType;
    }

    public Gender getGender() {
        log.debug("Getting gender for User: name={}, surName={}, gender={}", name, surName, gender);
        return gender;
    }

    public void setGender(Gender gender) {
        log.debug("Setting gender for User: name={}, surName={}, old gender={}, new gender={}",
                name, surName, this.gender, gender);
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            log.debug("Comparing User with itself: true");
            return true;
        }
        if (!(o instanceof User user)) {
            log.debug("Comparing User with a different type: false");
            return false;
        }
        boolean isEqual = age == user.age && Double.compare(money, user.money) == 0 &&
                Objects.equals(recipes, user.recipes) && Objects.equals(order, user.order) &&
                Objects.equals(name, user.name) && Objects.equals(surName, user.surName) &&
                personType == user.personType && gender == user.gender;
        log.debug("Comparing User with another User. Result: {}", isEqual);
        return isEqual;
    }

    @Override
    public int hashCode() {
        int hashCode = Objects.hash(recipes, order, name, surName, age, personType, gender, money);
        log.debug("Calculating hash code for User: {}", hashCode);
        return hashCode;
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", age=" + age +
                ", personType=" + personType +
                ", gender=" + gender +
                ", money=" + money +
                '}';
    }
}
