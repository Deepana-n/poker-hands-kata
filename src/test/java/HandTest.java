import org.junit.jupiter.api.Test;

import java.util.Arrays;

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

}
