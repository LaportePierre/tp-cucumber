package com.sqli.isc.iut.courses.cucumber;

public class People {
    private String name;
    private int euros;
    private boolean liverProblem;
    private int numberOfDrinkConsumed;
    private int hasToPaid;
    private int numberOfDrink;
    private boolean isInBar;

    People(String name, int euros, boolean liverProblem, boolean isInBar){
        this.name = name;
        this.euros = euros;
        this.liverProblem = liverProblem;
        this.numberOfDrinkConsumed = 0;
        this.hasToPaid = 0;
        this.numberOfDrink = 0;
        this.isInBar = isInBar;
    }

    public int getEuros() {
        return this.euros;
    }

    public boolean hasLiverProblem() {
        return this.liverProblem;
    }

    public int getNumberOfDrinkConsumed() {
        return numberOfDrinkConsumed;
    }

    public int getHasToPaid() {
        return hasToPaid;
    }

    public int getNumberOfDrink() {
        return numberOfDrink;
    }

    public boolean getIsInBar() {
        return isInBar;
    }

    public void setEuros(int euros) {
        this.euros = euros;
    }

    public void setNumberOfDrinkConsumed(int numberOfDrinkConsumed) {
        this.numberOfDrinkConsumed = numberOfDrinkConsumed;
    }

    public void setHasToPaid(int hasToPaid) {
        this.hasToPaid = hasToPaid;
    }

    public void setNumberOfDrink(int numberOfDrink) {
        this.numberOfDrink = numberOfDrink;
    }

    public void setInBar(boolean inBar) {
        isInBar = inBar;
    }

    public void orderADrink(int euros){
        if(this.getIsInBar()){
            this.setHasToPaid(this.getHasToPaid() + euros);
            this.setNumberOfDrink(this.getNumberOfDrink() + 1);
        }else{
            System.out.println("This person is not in the bar.");
        }
    }

    /** Cette fonction permet à la personne de commander un verre à une autre personne. Est utilisé pour
     * la partie de scénario : "Il commande donc 2 autres cocktails du mois pour sa note".
     *
     * @param people : La personne à qui il va être commandé un verre.
     * @param euros : le prix en euros du verre commandé.
     * */
    public void orderADrinkForOtherPeople(People people, int euros){
        if(this.getIsInBar() && people.getIsInBar()) {
            this.setHasToPaid(this.getHasToPaid() + euros);
            people.setNumberOfDrink(people.getNumberOfDrink() + 1);
        }else{
            System.out.println("One of the person is not in the bar.");
        }
    }

    public void paidHisPart(){
        if(this.getIsInBar()) {
            this.setEuros(this.getEuros() - this.getHasToPaid());
            this.setHasToPaid(0);
        }else{
            System.out.println("This person is not in the bar.");
        }
    }

    /**
     * Permet à une personne de prendre la part à payer d'une autre personne afin de la payer plus tard.
     * est utilisé pour : "C’est Mr Leblanc qui paie l’ensemble." (Cette fonction est bien différente de
     * orderADrinkForOtherPeople étant donné que cette dernière permet de directement commandé un verre à
     * une autre personne tandis que celle-ci permet de prendre la part d'une personne qui à déjà commandé
     * un verre
     *
     * @param people : La personne dont la part va être prise
     */
    public void getThePartOfOtherPeople(People people){
        if(this.getIsInBar() && people.getIsInBar()) {
            this.setHasToPaid(this.getHasToPaid() + people.getHasToPaid());
            people.setHasToPaid(0);
        }else{
            System.out.println("One of the person is not in the bar.");
        }
    }

    public void consumeADrink(){
        if(this.getIsInBar()) {
            if(this.getNumberOfDrink() > 0){
                this.setNumberOfDrinkConsumed(this.getNumberOfDrinkConsumed() + 1);
                this.setNumberOfDrink(this.getNumberOfDrink() - 1);
            }
        }else{
            System.out.println("This person is not in the bar.");
        }
    }

    public boolean isHappy() {
        return !this.hasLiverProblem() || this.getNumberOfDrinkConsumed() <= 1;
    }
}
