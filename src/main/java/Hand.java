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

    private handRank getHandRank(){
        Card[] tempHandRank = this.getPlayerHand();
        if (isStraightFlush(tempHandRank)) return handRank.STRAIGHTFLUSH;
        else if (isFourOfAKind(tempHandRank)) return handRank.FOUROFAKIND;
        else if (isFullHouse(tempHandRank)) return handRank.FULLHOUSE;
        else if (isFlush(tempHandRank)) return handRank.FLUSH;
        else if (isStraight(tempHandRank)) return handRank.STRAIGHT;
        else if (isThreeOfAKind(tempHandRank)) return handRank.THREEOFAKIND;
        else if (isTwoPair(tempHandRank)) return handRank.TWOPAIR;
        else if (isPair(tempHandRank)) return handRank.PAIR;
        else return handRank.HIGHCARD;
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

    public boolean isFourOfAKind(Card[] currentHand){
        return (currentHand[0].getValue() == currentHand[3].getValue() ||
                currentHand[1].getValue() == currentHand[4].getValue());
    }

    public boolean isStraightFlush(Card[] currentHand){
        return isStraight(currentHand) && isFlush(currentHand);
    }

    public int compareHighCard(Hand hand1, Hand hand2){
        Card[] hand1Cards = hand1.getPlayerHand();
        Card[] hand2Cards = hand2.getPlayerHand();
        return hand1Cards[hand1Cards.length-1].compareTo(hand2Cards[hand2Cards.length-1]);
    }


    public int compareDecreasingHighCard(Hand hand1, Hand hand2) {
        Card[] hand1Cards = hand1.getPlayerHand();
        Card[] hand2Cards = hand2.getPlayerHand();
        int current = hand1Cards.length-1;
        int compare = 0;
        while (current>=0){
            compare = hand1Cards[current].compareTo(hand2Cards[current]);
            if (compare!=0) break;
            current--;
        }
        return compare;

    }





}
