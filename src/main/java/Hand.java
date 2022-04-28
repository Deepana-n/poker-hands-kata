import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hand {

    public enum handRank{
        HIGHCARD, PAIR, TWOPAIR, THREEOFAKIND, STRAIGHT, FLUSH,
        FULLHOUSE, FOUROFAKIND, STRAIGHTFLUSH
    }

    private final Card[] playerHand;
    private final String playerName;
    private static List<String> messageList = new ArrayList<>();


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

    public void setMessage(String message) {
        messageList.add(message);
    }

    public List<String> getMessageList() {
        return messageList;
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
        boolean fullHouse = false;

        if(card1.equals(card2) && !card2.equals(card3) && card3.equals(card5)){
            fullHouse  = true;
            setMessage("- with full house: " + card3 + " over " + card2);
        }else if(card1.equals(card3) && !card3.equals(card4) && card4.equals(card5)){
            fullHouse = true;
            setMessage("- with full house: " + card1 + " over " + card4);
        }
        return fullHouse;
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
        int compare = 0;
        compare = hand1Cards[hand1Cards.length-1].compareTo(hand2Cards[hand2Cards.length-1]);
        if(compare>0) setMessage(hand1Cards[hand1Cards.length-1].toString());
        else if(compare<0) setMessage(hand2Cards[hand2Cards.length-1].toString());
        return compare;
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
        if(compare>0) setMessage("- with high card: " + hand1Cards[hand1Cards.length-1].toString());
        else if(compare<0) setMessage("- with high card: " +hand2Cards[hand2Cards.length-1].toString());
        return compare;

    }

    public Card compareFourOfAKind(Hand hand){
        Card[] cards = hand.getPlayerHand();
        if (cards[0] == cards[3]) return cards[0];
        else if (cards[1] == cards[4]) return cards[1];
        return null;
    }

    public Card compareThreeOfAKind(Hand hand){
        Card[] cards = hand.getPlayerHand();
        if (cards[0] == cards[2]) return cards[0];
        else if (cards[1] == cards[3]) return cards[1];
        else if (cards[2] == cards[4]) return cards[2];
        return null;
    }

    public String getWinner(Hand hand1, Hand hand2){
        handRank hand1Rank = hand1.getHandRank();
        handRank hand2Rank = hand2.getHandRank();
        int compare = hand1Rank.compareTo(hand2Rank);
        if (compare>0) return hand1.getPlayerName() + " wins. " + messageList.get(0);
        else if (compare<0) return hand2.getPlayerName() + " wins. " + messageList.get(1);
        else{
            switch (hand1Rank){
                case STRAIGHTFLUSH :
                    compare = compareHighCard(hand1,hand2);
                    break;
                case FOUROFAKIND :
                    compare = compareFourOfAKind(hand1).compareTo(compareFourOfAKind(hand2));
                    break;
                case FULLHOUSE :
                    compare = compareThreeOfAKind(hand1).compareTo(compareThreeOfAKind(hand2));
                    break;
                case FLUSH :
                    compare = compareDecreasingHighCard(hand1,hand2);
                    break;
                case STRAIGHT :
                    compare = compareHighCard(hand1,hand2);
                    break;
                case THREEOFAKIND :
                    compare = compareThreeOfAKind(hand1).compareTo(compareThreeOfAKind(hand2));
                    break;
                case TWOPAIR :
                    break;
                case PAIR :
                    break;
                case HIGHCARD :
                    compare = compareDecreasingHighCard(hand1,hand2);
                    break;
            }
        }
        return compare > 0  ? hand1.getPlayerName() + " wins. " + messageList.get(0)  : compare < 0 ? hand2.getPlayerName() + " wins. " +  messageList.get(0)  :"Tie.";
    }
}
