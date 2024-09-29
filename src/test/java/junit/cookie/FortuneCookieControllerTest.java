package junit.cookie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FortuneCookieControllerTest {

    private FortuneCookieFactory factory;
    private FortuneCookieController controller;

    private List<String> goodFortunes = Collections.singletonList("Вам повезет!");
    private List<String> badFortunes = Arrays.asList("Сегодня будет дождь");

    @BeforeEach
    public void setup() {
        factory = new FortuneCookieFactory(new FortuneConfig(true), goodFortunes, badFortunes);
        controller = new FortuneCookieController(factory);
    }

    @Test
    void shouldReturnPositiveFortune() {
        FortuneCookie cookie = controller.tellFortune();
        assertTrue(goodFortunes.contains(cookie.getFortuneText()));

    }

    @Test
    void shouldReturnNegativeFortune() {
        factory = new FortuneCookieFactory(new FortuneConfig(false), goodFortunes, badFortunes);
        controller = new FortuneCookieController(factory);
        FortuneCookie cookie = controller.tellFortune();
        assertTrue(badFortunes.contains(cookie.getFortuneText()));

    }

}