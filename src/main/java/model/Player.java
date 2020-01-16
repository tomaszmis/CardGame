package model;

import java.io.PrintWriter;

import java.util.Scanner;


/**
 *
 * @author Tomasz Mi?
 * @version 1.1
 * Represent one player and provide methods to play in Eights.
 * Each player is thread in the game. For decide who play now  class Eights provide synchronized method next player.
 */
public class Player extends Thread {
    /**
     * Name of player
     */
    private String name;
    /**
     * Store player's card
     */
    protected Hand hand;
    /**
     * Create new player
     * @param name - name of player
     */
    protected PrintWriter output;
    private Scanner input;
    public Player(String name, PrintWriter output, Scanner input) {
        this.output = output;
        this.input = input;
        this.name = name;
        this.hand = new Hand(name,output);
        this.start();
    }

    public Player(String name,PrintWriter output) {
        this.output = output;
        this.name = name;
        this.hand = new Hand(name,output);
        this.start();
    }
    /**
     * Method which finally return card to discard pile.
     * @param eights - current game
     * @param prev - last card on discard pile
     * @return card from hand deck to discard pile
     */
    public Card play(Eights eights, Card prev) {

        Card card = searchForEight();
        if(card == null) {
            card = searchForMatch(prev);
            if(card == null) {
                card  = drawForMatch(eights, prev);
            }
        }
        return card;
    }
    public synchronized Card playOwn(Eights eights, Card prev) throws Exception {
        this.hand.display();
        int choice = 0;

        Card returned = null;
        output.println("Twoja tura, wybierz numer karty");
        choice = input.nextInt();

        Card card = null;

        output.println(choice);


        if (choice == 99) {
            output.println("Ciagniesz do skutku");
            returned = drawForMatch(eights, prev);
        } else {
            output.println("Wybrales karte numer: " + choice);
            card = hand.getCard(choice);

            if (isEight(card)) {
                return hand.popCard(choice);
            }
            if (cardMatches(card, prev)) {
                return  hand.popCard(choice);
            } else {
                return playOwn(eights, prev);
            }
        }

        return returned;
    }

    public boolean isEight(Card card){
        if(card.getRank() == 8) return true;
        return false;
    }
    /**
     * Looking for card which rank is 8, if is return it else return null.
     * @return
     */
    public Card searchForEight() {
        for(int i = 0; i < hand.size(); ++i) {
            Card card = hand.getCard(i);
            if(card.getRank() == 8) {
                return hand.popCard(i);
            }
        }
        return null;
    }
    /**
     * Looking for card which match to last card in discard pile id est this same rank or suit.
     * @param prev
     * @return
     */
    public Card searchForMatch(Card prev) {
        for(int i = 0; i < hand.size(); ++i) {
            Card card = hand.getCard(i);
            if(cardMatches(card,prev)) {
                return hand.popCard(i);
            }
        }
        return null;
    }
    /**
     * If player don't have match card in hand he must draw from draw pile while he don't get match card, each drawed card was add to hand cards.
     * @param eights - current game
     * @param prev - last card on discard pile
     * @return - matches card
     */
    public Card drawForMatch(Eights eights, Card prev) {
        while(true) {
            Card card = eights.draw();
            output.println(name + " wyci?gnal " + card);
            if(cardMatches(card, prev)) {
                return card;
            }
            hand.addCard(card);
        }
    }
    /**
     * Decided if card matches to last card in discard pile id est have this same rank or suit or in hand is card which rank is 8.
     * @param card1
     * @param card2
     * @return
     */
    public static boolean cardMatches(Card card1, Card card2) {
        if(card1.getSuit() == card2.getSuit()) {
            return true;
        }
        if(card1.getRank() == card2.getRank()) {
            return true;
        }
        if(card1.getRank() == 8) {
            return true;
        }
        return false;
    }
    /**
     * Calculate point each player.
     * @return sum of points
     */
    public int score() {
        int sum = 0;
        for(int i = 0; i < hand.size(); ++i) {
            Card card = hand.getCard(i);
            int rank = card.getRank();
            if(rank == 8) {
                sum =- 20;
            } else if(rank > 10) {
                sum -=10;
            }else {
                sum -= rank;
            }
        }
        return sum;
    }
    /**
     * Write in console score.
     */
    public void displayScore() {
        output.println("Wynik: " + name + " to " + score());
    }
    public String getScore(){
        return "Wynik: " + name + " to " + score();
    }
    /**
     * Display all information about player name, count of cards in hand and score.
     */
    public void display() {
        output.println("Gracz " + name + " Liczba kart w ręce: " + hand.size() + " Wynik to " + score());
    }
    /**
     * Getter return name of player.
     * @return - name of player.
     */

    public String getPlayerName() {
        return name;
    }
    /**
     * Getter return players cards.
     * @return - players cards.
     */
    public Hand getHand() {
        return hand;
    }

}