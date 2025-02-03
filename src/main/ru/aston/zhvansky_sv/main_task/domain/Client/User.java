package domain.Client;

import domain.Medicine.Medicine;
import domain.Utils.DiscountUtils;
import domain.Utils.OrdersHistory;

import java.util.ArrayList;
import java.util.Objects;

public class User {

    private ArrayList<Recipe> recipes;
    private Order order;
    private String name;
    private String surName;
    private int age;
    private PersonType personType;
    private Gender gender;
    private double money;


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
    }

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

    public Order createOrder(ArrayList<Medicine> medicines){
        ArrayList<Medicine> legalMedicines = new ArrayList<>();
        for (Medicine medicine : medicines) {
            if (!medicine.isNeedRecipe() || this.recipes.contains(medicine)){
                legalMedicines.add(medicine);
            }
        }
        Order order = new Order(legalMedicines, this);
        OrdersHistory.allOrders.add(order);
        return order;
    }

    public void payOrder(Order order){
        money -= DiscountUtils.getPriceWithDiscount(order, this);
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return age == user.age && Double.compare(money, user.money) == 0 && Objects.equals(recipes, user.recipes) && Objects.equals(order, user.order) && Objects.equals(name, user.name) && Objects.equals(surName, user.surName) && personType == user.personType && gender == user.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipes, order, name, surName, age, personType, gender, money);
    }
}
