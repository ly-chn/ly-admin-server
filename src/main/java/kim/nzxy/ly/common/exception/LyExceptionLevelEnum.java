package kim.nzxy.ly.common.exception;

/**
 * 异常严重级别
 *
 * @author xuyf
 * @since 2022/7/26 15:05
 */
public enum LyExceptionLevelEnum {
    /**
     * 一般异常, 常见业务异常均使用此等级
     */
    NORMAL,
    /**
     * 严重异常, 程序可能会在一段时间内无法正常运行, 此等级异常将被记录到数据库, 用于排查问题
     */
    PANIC,
    /**
     * 低等级异常, log文件中打印, 但不存表
     */
    MINOR,
}
