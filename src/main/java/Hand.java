import java.util.Arrays;

public class Hand {

    public enum handRank{
        HIGHCARD, PAIR, TWOPAIR, THREEOFAKIND, STRAIGHT, FLUSH,
        FULLHOUSE, FOUROFAKIND, STRAIGHTFLUSH
    }

    private final Card[] playerHand;
    private final String playerName;

    public Hand(){
        playerHand = null;
        playerName = "";
    }

    public Hand(String currentHandString, String playerName){
        String[] cards = currentHandString.split(" ");
        this.playerHand = new Card[cards.length];
        for (int i=0;i<playerHand.length;i++){
            playerHand[i] = new Card(cards[i]);
        }
        Arrays.sort(playerHand);
        this.playerName = playerName;
    }

    public Card[] getPlayerHand() {
        return playerHand;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isPair(Card[] currentHand){
        //Pair: 2 of the 5 cards in the hand have the same value.
        Card card1 = currentHand[0];
        Card card2 = currentHand[1];
        Card card3 = currentHand[2];
        Card card4 = currentHand[3];
        Card card5 = currentHand[4];
        return card1.equals(card2) && !card2.equals(card3) ||
                (card2.equals(card3) && !card3.equals(card4) && !card2.equals(card1)) ||
                (card3.equals(card4) && !card4.equals(card5) && !card3.equals(card1)) ||
                card4.equals(card5) && !card4.equals(card3);
    }

    public boolean isTwoPair(Card[] currentHand){
        //Two Pairs: The hand contains 2 different pairs.
        Card card1 = currentHand[0];
        Card card2 = currentHand[1];
        Card card3 = currentHand[2];
        Card card4 = currentHand[3];
        Card card5 = currentHand[4];

        return ((card1.equals(card2) && card3.equals(card4) && !card2.equals(card3) && !card4.equals(card5)) ||
                (!card1.equals(card2) && card2.equals(card3) && !card3.equals(card4) && card4.equals(card5)) ||
                (card1.equals(card2) && card4.equals(card5) && !card2.equals(card3) && !card3.equals(card4))
        );
    }

    public boolean isThreeOfAKind(Card[] currentHand){
        //Three of a Kind:Three of the cards in the hand have the same value
        Card card1 = currentHand[0];
        Card card2 = currentHand[1];
        Card card3 = currentHand[2];
        Card card4 = currentHand[3];
        Card card5 = currentHand[4];

        return (card1.equals(card3) || card2.equals(card4) || card3.equals(card5));
    }

    public boolean isStraight(Card[] currentHand){
        //Straight: Hand contains 5 cards with consecutive values.
        return currentHand[4].getValue() - currentHand[0].getValue() == 4;
    }

    public boolean isFlush(Card[] currentHand){
       // Flush: Hand contains 5 cards of the same suit.
        for (int i=0;i<currentHand.length-1;i++){
            if (currentHand[i+1].getSuit() != currentHand[i].getSuit()){
                return false;
            }
        }
        return true;
    }

    public boolean isFullHouse(Card[] currentHand){
        // Full House: 3 cards of the same value, with the remaining 2 cards forming a pair.
        Card card1 = currentHand[0];
        Card card2 = currentHand[1];
        Card card3 = currentHand[2];
        Card card4 = currentHand[3];
        Card card5 = currentHand[4];

        return ((card1.equals(card2) && !card2.equals(card3) && card3.equals(card5)) ||
                (card1.equals(card3) && !card3.equals(card4) && card4.equals(card5)));
    }

}
