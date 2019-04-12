public class Test {

    public static void main(String[] args) {
        Deck deck = new Deck("Talia");
        deck.shuffle();

        Hand hand = new Hand("Uk³ad");
        deck.deal(hand, 5);
        hand.display();

        Hand drawPile = new Hand("Stos ci¹gniêcia");
        deck.dealAll(drawPile);
        System.out.printf("Stos ci¹gniêcia zawiera %d kart.\n",
                          drawPile.size());
    }

}