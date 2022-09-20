package kim.nzxy.ly.common.exception;

import lombok.Getter;

/**
 * 全局异常
 *
 * @author xuyf
 * @since 2022/7/26 14:52
 */
@Getter
public class LyException extends RuntimeException {
    /**
     * 异常具体细节
     */
    private final String message;
    /**
     * 相应异常
     */
    private final Throwable cause;

    private LyException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.cause = cause;
    }

    /**
     * 寻常的异常, 如业务中常见的用户错误操作, 用户应该变更操作方式才能解决问题
     */
    public static class Normal extends LyException {
        public Normal(String message, Throwable cause) {
            super(message, cause);
        }

        public Normal(String message) {
            this(message, null);
        }
    }

    /**
     * 严重的异常, 如连接中断等, 用户无法解决的问题, 需要联系网站维护者/开发人员, 一般由系统自动捕获
     */
    public static class Panic extends LyException {
        public Panic(String message, Throwable cause) {
            super(message, cause);
        }

        public Panic(String message) {
            this(message, null);
        }
    }

    /**
     * 微小的异常, 如参数校验等, 用户可以解决的问题, 一般由用户自己处理
     */
    public static class Minor extends LyException {
        public Minor(String message, Throwable cause) {
            super(message, cause);
        }

        public Minor(String message) {
            this(message, null);
        }
    }
}
