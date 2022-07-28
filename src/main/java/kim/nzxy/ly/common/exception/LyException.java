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

    private final LyExceptionLevelEnum level;

    private LyException(String message, Throwable cause, LyExceptionLevelEnum level) {
        super(message, cause);
        this.message = message;
        this.cause = cause;
        this.level = level;
    }

    /**
     * 寻常的异常, 如业务中常见的用户错误操作
     */
    public static class Normal extends LyException {
        public Normal(String message, Throwable cause) {
            super(message, cause, LyExceptionLevelEnum.NORMAL);
        }

        public Normal(String message) {
            this(message, null);
        }
    }

    /**
     * 严重的异常, 如连接中断等
     */
    public static class Panic extends LyException {
        public Panic(String message, Throwable cause) {
            super(message, cause, LyExceptionLevelEnum.PANIC);
        }

        public Panic(String message) {
            this(message, null);
        }
    }

    /**
     * 微小的异常, 如参数校验等
     */
    public static class Minor extends LyException {
        public Minor(String message, Throwable cause) {
            super(message, cause, LyExceptionLevelEnum.MINOR);
        }

        public Minor(String message) {
            this(message, null);
        }
    }
}
