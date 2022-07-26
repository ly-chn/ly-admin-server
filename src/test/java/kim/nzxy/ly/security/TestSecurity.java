package kim.nzxy.ly.security;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.junit.jupiter.api.Test;

/**
 * @author xuyf
 * @since 2022/7/26 17:52
 */
public class TestSecurity {
    @Test
    public void testArgon2() {
        Argon2 argon2 = Argon2Factory.create( Argon2Factory.Argon2Types.ARGON2id);
        // argon2.hash()
    }
}
