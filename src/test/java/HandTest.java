import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandTest {

    @Test
    public void testIsPair(){
        Card[] currentHand = {new Card("2C"),new Card("4D"),new Card("3C"),new Card("5C"),new Card("2D")};
        Arrays.sort(currentHand);
        Hand handObj = new Hand();
        assertTrue(handObj.isPair(currentHand));
    }

    @Test
    public void testIsTwoPair(){
        Hand handObj = new Hand();
        Card[] currentHand = {new Card("2C"),new Card("4D"),new Card("3C"),new Card("4C"),new Card("2D")};
        Arrays.sort(currentHand);
        assertTrue(handObj.isTwoPair(currentHand));
    }

    @Test
    public void testIsThreeOfAKind(){
        Hand handObj = new Hand();
        Card[] currentHand = {new Card("2C"),new Card("4D"),new Card("3C"),new Card("2H"),new Card("2D")};
        Arrays.sort(currentHand);
        assertTrue(handObj.isThreeOfAKind(currentHand));
    }

    @Test
    public void testIsStraight(){
        Hand handObj = new Hand();
        Card[] currentHand = {new Card("2C"),new Card("3C"),new Card("4C"),new Card("5C"),new Card("6C")};
        Arrays.sort(currentHand);
        assertTrue(handObj.isStraight(currentHand));
    }

    @Test
    public void testIsFlush(){
        Hand handObj = new Hand();
        Card[] currentHand = {new Card("2C"),new Card("3C"),new Card("4C"),new Card("5C"),new Card("6C")};
        Arrays.sort(currentHand);
        assertTrue(handObj.isFlush(currentHand));
    }

    @Test
    public void testIsFullHouse(){
        Hand handObj = new Hand();;
        Card[] currentHand = {new Card("2C"),new Card("2H"),new Card("2S"),new Card("5C"),new Card("5D")};
        Arrays.sort(currentHand);
        assertTrue(handObj.isFullHouse(currentHand));
    }


    @Test
    public void testCompareHighCard(){
        Hand handObj = new Hand();
        Hand blackHand = new Hand("4C 2C 3D 6D 2S","black");
        Hand whiteHand = new Hand("4C 8C 3D 6D 2S","white");
        assertEquals(-1, handObj.compareHighCard(blackHand,whiteHand));
    }

    @Test
    public void testCompareDecreasingHighCard(){
        Hand handObj = new Hand();
        Hand blackHand = new Hand("4C 2C 2D 6D 2S","black");
        Hand whiteHand = new Hand("4C 2C 3D 6D 2S","white");
        assertEquals(-1, handObj.compareDecreasingHighCard(blackHand,whiteHand));
    }

    @ParameterizedTest(name = "{index}) For 2 hands: \"{0}\" and \"{1}\", the outcome is: {2}")
    @CsvSource(textBlock = """
            2H 3D 5S 9C KD, 2C 3H 4S 8C AH, white wins. - with high card: A
            2H 4S 4C 2D 4H, 2S 8S AS QS 3S, black wins. - with full house: 4 over 2
            2H 3D 5S 9C KD, 2D 3H 5C 9S KH, Tie.
            """)
    void checkWinnerForTwoHands(String blackHandString, String whiteHandString, String expectedResult) {
        Hand handObj = new Hand();
        handObj.getMessageList().clear();
        Hand blackHand = new Hand(blackHandString,"black");
        Hand whiteHand = new Hand(whiteHandString,"white");
        String actual = handObj.getWinner(blackHand,whiteHand);
        assertEquals(expectedResult,actual);
    }

}
