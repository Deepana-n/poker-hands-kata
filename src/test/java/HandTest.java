import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandTest {
    @Test
    public void testIsPair(){
        Hand handObj = new Hand();;
        Card[] currentHand = {new Card("2C"),new Card("4D"),new Card("3C"),new Card("5C"),new Card("2D")};
        Arrays.sort(currentHand);
        assertTrue(handObj.isPair(currentHand));
    }

    @Test
    public void testIsTwoPair(){
        Hand handObj = new Hand();;
        Card[] currentHand = {new Card("2C"),new Card("4D"),new Card("3C"),new Card("4C"),new Card("2D")};
        Arrays.sort(currentHand);
        assertTrue(handObj.isTwoPair(currentHand));
    }

    @Test
    public void testIsThreeOfAKind(){
        Hand handObj = new Hand();;
        Card[] currentHand = {new Card("2C"),new Card("4D"),new Card("3C"),new Card("2H"),new Card("2D")};
        Arrays.sort(currentHand);
        assertTrue(handObj.isThreeOfAKind(currentHand));
    }

    @Test
    public void testIsStraight(){
        Hand handObj = new Hand();;
        Card[] currentHand = {new Card("2C"),new Card("3C"),new Card("4C"),new Card("5C"),new Card("6C")};
        Arrays.sort(currentHand);
        assertTrue(handObj.isStraight(currentHand));
    }

    @Test
    public void testIsFlush(){
        Hand handObj = new Hand();;
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

    @Test
    public void testWhiteWinsHighCard(){
        Hand handObj = new Hand();
        Hand blackHand = new Hand("2H 3D 5S 9C KD","black");
        Hand whiteHand = new Hand("2C 3H 4S 8C AH","white");
        assertEquals("white:A", handObj.getWinner(blackHand,whiteHand));
    }

    @Test
    public void testBlackWinsFullHouse(){
        Hand handObj = new Hand();
        Hand blackHand = new Hand("2H 4S 4C 2D 4H","black");
        Hand whiteHand = new Hand("2S 8S AS QS 3S","white");
        assertEquals("black", handObj.getWinner(blackHand,whiteHand));
    }

    @Test
    public void testBlackWinsCompareHighCard(){
        Hand handObj = new Hand();
        Hand blackHand = new Hand("2H 3D 5S 9C KD","black");
        Hand whiteHand = new Hand("2D 3H 5C 9S KH","white");
        assertEquals("Tie.", handObj.getWinner(blackHand,whiteHand));
    }

}
