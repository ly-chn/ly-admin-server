package kim.nzxy.ly.common.exception;

import cn.dev33.satoken.exception.NotLoginException;
import kim.nzxy.ly.common.res.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.PoolException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.sql.SQLException;

/**
 * 异常拦截
 *
 * @author ly-chn
 */
@RestControllerAdvice
@Slf4j
public class LyExceptionHandler {
    /**
     * 自定义异常
     */
    @ExceptionHandler(LyException.class)
    public Res<?> handler(LyException e) {
        log.error("自定义异常", e);
        return Res.fail(e.getMessage());
    }
    /**
     * 自定义异常
     */
    @ExceptionHandler(LyException.None.class)
    public void handler(LyException.None e) {
        log.error("忽略异常", e);
    }

    /**
     * 自定义异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Res<?> handler(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return Res.fail("请求方式不支持");
    }

    /**
     * 计算异常, 如将long转为int时, 超出int范围
     */
    @ExceptionHandler(ArithmeticException.class)
    public Res<?> handler(ArithmeticException e) {
        log.error(e.getMessage(), e);
        return Res.fail("运算异常");
    }

    /**
     * 空指针
     */
    @ExceptionHandler(NullPointerException.class)
    public Res<?> handler(NullPointerException e) {
        log.error(e.getMessage(), e);
        return Res.fail("服务器发生了错误");
    }

    /**
     * 参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Res<?> handler(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String s;
        try {
            s = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        } catch (Exception ignored) {
            s = "参数校验错误";
        }
        return Res.fail(s);
    }

    @ExceptionHandler(SQLException.class)
    public Res<?> handler(SQLException e) {
        log.error(e.getMessage(), e);
        return Res.fail("数据库异常");
    }

    @ExceptionHandler(Exception.class)
    public Res<?> handler(Exception e) {
        log.error(e.getMessage(), e);
        return Res.fail("操作失败");
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Res<?> handler(MaxUploadSizeExceededException e) {
        log.error(e.getMessage(), e);
        return Res.fail("文件大小超出限制, 请压缩或降低文件质量! ");
    }

    @ExceptionHandler(PoolException.class)
    public Res<?> handler(PoolException e) {
        log.error(e.getMessage(), e);
        return Res.fail("连接异常, 请稍候重试");
    }

    @ExceptionHandler(NotLoginException.class)
    public Res<?> handler(NotLoginException e) {
        log.error("token校验失败: {}", e.getType());
        // 判断场景值，定制化异常信息
        String message = switch (e.getType()) {
            case NotLoginException.NOT_TOKEN -> "请登录";
            case NotLoginException.INVALID_TOKEN,
                    NotLoginException.KICK_OUT,
                    NotLoginException.TOKEN_TIMEOUT,
                    NotLoginException.BE_REPLACED -> "请重新登录";
            default -> "token校验失败";
        };
        // 返回给前端
        return Res.fail(message, 4001);
    }
}
