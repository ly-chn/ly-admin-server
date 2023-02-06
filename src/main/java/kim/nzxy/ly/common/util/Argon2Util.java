package kim.nzxy.ly.common.util;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 * 用于密码加密/验证
 *
 * @author ly-chn
 */
public class Argon2Util {
    private static final Argon2 ARGON2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
    /**
     * 迭代次数
     */
    private static final int ITERATIONS = 10;
    /**
     * 内存占用(kb)
     */
    private static final int MEMORY = 1024;
    /**
     * 并行线程/通道数量
     */
    private static final int PARALLELISM = 1;

    /**
     * @param plaintext 明文密码
     * @return 密码密文
     */
    public static String hash(String plaintext) {
        char[] chars = plaintext.toCharArray();
        try {
            return ARGON2.hash(ITERATIONS, MEMORY, PARALLELISM, chars);
        } finally {
            // 清理数据
            ARGON2.wipeArray(chars);
        }
    }

    /**
     * @param plaintext 明文密码
     * @param password  密码密文
     * @return 为true表示校验通过
     */
    public static boolean verify(String plaintext, String password) {
        return ARGON2.verify(password, plaintext.toCharArray());
    }
}
