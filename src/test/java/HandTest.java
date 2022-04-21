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

}
