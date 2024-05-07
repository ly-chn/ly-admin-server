package kim.nzxy.ly.common.strategy.jackson;

public interface LytHandler<F, T> {
    /**
     * 翻译
     *
     * @param key 原文
     * @return 译文
     */
    T t(F key);
}
