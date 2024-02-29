package com.sqli.isc.iut.courses.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class tpSteps {
    Bar bar;
    People pignon;
    People leblanc;
    /** J'utilise un array list pour faire rentrer des personnes étant donné qu'un groupe de personne
     * peut se faire refuser d'après les histoires (donc pour éviter de faire rentrer un groupe 1 par 1)*/
    ArrayList<People> peoplesEnteringTheBar;

    @Given("Mr Pignon and Mr Leblanc arrive to the bar named le Juste \\(A cocktail bar)")
    public void mrPignonAndMrLeblancArriveToTheBarNamedLeJusteACocktailBar() {
        bar = new Bar("le Juste", "cocktail bar", 0);
        pignon = new People("Mr Pignon", 100, true, false);
        leblanc = new People("Mr Leblanc", 100, false, false);
    }

    @And("there is only {int} seats in the bar")
    public void thereIsOnlySeatsInTheBar(int seatNumber) {
        bar.setMaxSeats(seatNumber);
    }

    @And("there is only {int} peoples in the bar")
    public void thereIsOnlyPeoplesInTheBar(int numberOfPeople) {
        bar.setRandomPeopleInBar(numberOfPeople);
    }

    @When("they try to enter")
    public void theyTryToEnter() {
        peoplesEnteringTheBar = new ArrayList<>();
        peoplesEnteringTheBar.add(pignon);
        peoplesEnteringTheBar.add(leblanc);
        bar.makePeoplesEnterTheBar(peoplesEnteringTheBar);
    }

    @Then("they get refused at the entrance")
    public void theyGetRefusedAtTheEntrance() {
        assertFalse(bar.isPeopleInTheBar(pignon));
        assertFalse(bar.isPeopleInTheBar(leblanc));
    }


    @And("there are {int} people in the bar")
    public void thereArePeopleInTheBar(int numberOfPeople) {
        bar.setRandomPeopleInBar(numberOfPeople);
    }

    @Then("they get accepted")
    public void theyGetAccepted() {
        assertTrue(bar.isPeopleInTheBar(pignon));
        assertTrue(bar.isPeopleInTheBar(leblanc));
    }

    @And("the guy behind them can't enter because the bar is full")
    public void theGuyBehindThemCanTEnterBecauseTheBarIsFull() {
        peoplesEnteringTheBar = new ArrayList<>();
        People nextPeople = new People("nextPeople", 100, false, false);
        peoplesEnteringTheBar.add(nextPeople);
        bar.makePeoplesEnterTheBar(peoplesEnteringTheBar);
        assertFalse(bar.isPeopleInTheBar(nextPeople));
    }

    @And("they each order a cocktail of the month at {int} euros")
    public void theyEachOrderACocktailOfTheMonthAtEuros(int euros) {
        pignon.orderADrink(euros);
        leblanc.orderADrink(euros);
    }

    @And("Mr Leblanc pays for both drinks")
    public void mrLeblancPaysForBothDrinks() {
        leblanc.getThePartOfOtherPeople(pignon);
    }

    @When("they have ended their drink")
    public void theyHaveEndedTheirDrink() {
        leblanc.consumeADrink();
        pignon.consumeADrink();
    }

    @Then("the bill is checked, and Mr Leblanc pays")
    public void theBillIsCheckedAndMrLeblancPays() {
        leblanc.paidHisPart();
        /** Tout les tests sont là pour vérifier que les attributs des personnes après achats... sont correct.
         * Dans notre cas on vérifira à chaque fois :
         * - Combien d'euros ils ont
         * - Qu'ils n'ont rien plus rien à payer après avoir régler leurs notes
         * - Qu'ils n'ont bien plus de verre à boire après avoir consommé
         * - Que le nombre de verre consommé est bien correct.*/
        assertEquals(80, leblanc.getEuros());
        assertEquals(100, pignon.getEuros());
        assertEquals(0, pignon.getHasToPaid());
        assertEquals(0, leblanc.getHasToPaid());
        assertEquals(0, pignon.getNumberOfDrink());
        assertEquals(0, leblanc.getNumberOfDrink());
        assertEquals(1, pignon.getNumberOfDrinkConsumed());
        assertEquals(1, leblanc.getNumberOfDrinkConsumed());
    }

    @And("Mr Pignon is happy because he only consumed one drink \\(he has liver problems)")
    public void heIsHappyBecauseHeOnlyConsumedOneDrinkHeHasLiverProblems() {
        assertTrue(pignon.isHappy());
    }

    @Then("the bill is checked, and they both paid")
    public void theBillIsCheckedAndTheyBothPaid() {
        leblanc.paidHisPart();
        pignon.paidHisPart();
        assertEquals(90, leblanc.getEuros());
        assertEquals(90, pignon.getEuros());
        assertEquals(0, pignon.getHasToPaid());
        assertEquals(0, leblanc.getHasToPaid());
        assertEquals(0, pignon.getNumberOfDrink());
        assertEquals(0, leblanc.getNumberOfDrink());
        assertEquals(1, pignon.getNumberOfDrinkConsumed());
        assertEquals(1, leblanc.getNumberOfDrinkConsumed());
    }

    @And("Mr Leblanc orders {int} other cocktail of the month for him and Mr Pignon at {int} euros")
    public void mrLeblancOrdersOtherDrinksForHimAndMrPignonAtEuros(int numberOfDrink, int euros) {
        for(int i = 0; i < numberOfDrink; i+=2) {
            leblanc.orderADrink(euros);
            leblanc.orderADrinkForOtherPeople(pignon, euros);
        }
    }

    @Then("the bill is checked, and Mr Leblanc pays for both drinks")
    public void mrLeBlancPaysForBothDrinks() {
        this.leblanc.paidHisPart();
        assertEquals(70, leblanc.getEuros());
        assertEquals(90, pignon.getEuros());
        assertEquals(0, pignon.getHasToPaid());
        assertEquals(0, leblanc.getHasToPaid());
        assertEquals(0, pignon.getNumberOfDrink());
        assertEquals(0, leblanc.getNumberOfDrink());
        assertEquals(2, pignon.getNumberOfDrinkConsumed());
        assertEquals(2, leblanc.getNumberOfDrinkConsumed());
    }

    @And("Mr Pignon is not happy because he consumed more than one drink \\(he has liver problems)")
    public void mrPignonIsNotHappyBecauseHeConsumedMoreThanOneDrinkHeHasLiverProblems() {
        assertFalse(pignon.isHappy());
    }
}
