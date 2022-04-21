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

}
