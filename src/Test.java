public class Test {

    public static void main(String[] args) {
        Deck deck = new Deck("Talia");
        deck.shuffle();

        Hand hand = new Hand("Uk�ad");
        deck.deal(hand, 5);
        hand.display();

        Hand drawPile = new Hand("Stos ci�gni�cia");
        deck.dealAll(drawPile);
        System.out.printf("Stos ci�gni�cia zawiera %d kart.\n",
                          drawPile.size());
    }

}