import java.util.Arrays;

public class Hand {

    public static enum handRank{
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

}
