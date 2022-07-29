package kim.nzxy.ly.common.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 密码工具类
 *
 * @author xuyf
 * @since 2022/7/27 11:43
 */
class Argon2UtilTest {

    @Test
    void hash() {
        System.out.println("hash = " + Argon2Util.hash("ly"));
    }

    @Test
    void verify() {
        String plaintext = "123456";
        String hash = Argon2Util.hash(plaintext);
        assertTrue(Argon2Util.verify(plaintext, hash));
    }
}