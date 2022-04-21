import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void ranking() {
        Card cardObj = new Card("2C");
        assertEquals(8,cardObj.getRank());
    }

    @Test
    void compareTo() {
        Card cardObj1 = new Card("2C");
        Card cardObj2 = new Card("3D");
        assertEquals(-1,cardObj1.compareTo(cardObj2));
    }

    @Test
    void testEquals() {
        Card cardObj1 = new Card("2C");
        Card cardObj2 = new Card("2D");
        assertEquals(cardObj1, cardObj2);
    }

    @Test
    void testToString() {
        Card cardObj1 = new Card("2C");
        assertEquals("[2C]",cardObj1.toString());
    }
}
