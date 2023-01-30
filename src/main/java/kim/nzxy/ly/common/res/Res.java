package kim.nzxy.ly.common.res;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 全局统一响应对象
 *
 * @author xuyf
 * @since 2022/7/26 15:40
 */
@Data
@Accessors(chain = true)
public class Res<T> {
    private static final String SUCCESS_MESSAGE = "操作成功";
    /**
     * 返回提示消息, 请保障所有提示信息友好性
     */
    private String message;
    /**
     * 2000表示正常
     * 4000全局拦截到的其他异常
     * 5000业务异常
     */
    private int code;
    /**
     * 返回内容, 不允许使用String类型作为返回类型
     */
    private T data;

    /**
     * 系统时间戳
     */
    private long timestamp = System.currentTimeMillis();

    /**
     * 成功响应并返回信息
     *
     * @param message 提示信息
     * @param data    返回的data数据
     * @return 统一封装返回单条成功数据
     */
    public static <T> Res<T> ok(T data, String message) {
        Res<T> msg = new Res<T>();
        msg.setCode(2000);
        msg.setMessage(message);
        msg.setData(data);
        return msg;
    }

    /**
     * 成功响应并返回信息
     *
     * @param data 返回的data数据
     * @return 统一封装返回单条成功数据
     */
    public static <T> Res<T> ok(T data) {
        return Res.ok(data, SUCCESS_MESSAGE);
    }

    /**
     * 成功响应并返回信息
     *
     * @return 统一封装返回单条成功数据
     */
    public static <T> Res<T> ok(String message) {
        // noinspection unchecked
        return Res.ok((T) message, message);
    }

    /**
     * 成功响应并返回信息
     *
     * @return 统一封装返回单条成功数据
     */
    public static <T> Res<T> ok() {
        return Res.ok(null, SUCCESS_MESSAGE);
    }

    /**
     * 操作失败的提示
     *
     * @param message 自定义返回信息
     * @param code    自定义状态码
     * @return 统一封装操作失败的提示
     */
    public static <T> Res<T> fail(String message, int code) {
        Res<T> msg = new Res<>();
        msg.setCode(code);
        msg.setMessage(message);
        return msg;
    }

    /**
     * 操作失败的提示
     *
     * @param message 自定义返回信息
     * @return 统一封装操作失败的提示
     */
    public static <T> Res<T> fail(String message) {
        return Res.fail(message, 5000);
    }
}
