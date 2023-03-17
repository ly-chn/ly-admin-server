package kim.nzxy.ly.common.util;

import java.util.List;

public interface TreeNode<T extends TreeNode<T>> {
    /**
     * 获取当前节点id
     *
     * @return 当前节点id
     */
    Object getId();

    /**
     * 获取父级节点id
     *
     * @return 父级节点id
     */
    Object getParentId();

    /**
     * 获取子节点
     *
     * @return 子节点
     */
    List<T> getChildren();

    /**
     * 设置子节点
     *
     * @param children 子节点
     */
    void setChildren(List<T> children);

    /**
     * 获取排序权重, 使用时以ASC方式排序
     * @return 排序权重
     */
    Long getOrderWeight();
}
