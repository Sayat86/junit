package junit.cookie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FortuneCookieFactoryTest {

    private FortuneCookieFactory factory;
    private List<String> goodFortunes = Arrays.asList("Вам повезет!");
    private List<String> badFortunes = Arrays.asList("Сегодня будет дождь");

    @BeforeEach
    public void setup() {
        factory = new FortuneCookieFactory(new FortuneConfig(true), goodFortunes, badFortunes);
    }


    @Test
    void shouldIncrementCountByOneAfterOneCookieBaked() {
        factory.bakeFortuneCookie();

        assertEquals(1, factory.getCookiesBaked());
    }

    @Test
    void shouldIncrementCountByTwoAfterTwoCookiesBaked() {
        factory.bakeFortuneCookie();
        factory.bakeFortuneCookie();

        assertEquals(2, factory.getCookiesBaked());
    }

    @Test
    void shouldSetCounterToZeroAfterResetCookieCreatedCall() {
        factory.bakeFortuneCookie();

        assertEquals(1, factory.getCookiesBaked());

        factory.resetCookiesCreated();

        assertEquals(0, factory.getCookiesBaked());

    }

}