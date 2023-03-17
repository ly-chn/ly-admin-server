package kim.nzxy.ly.common.util;


import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TreeUtil {
    public static <T extends TreeNode<T>> List<T> build(List<T> list) {
        return build(list, it->Objects.isNull(it.getParentId()));
    }

    public static <T extends TreeNode<T>> List<T> build(List<T> list, Function<TreeNode<T>, Boolean> parentCheck) {
        return list.stream().filter(parentCheck::apply)
                .sorted(Comparator.comparing(TreeNode::getOrderWeight, Comparator.nullsFirst(Long::compareTo)))
                .peek(item -> item.setChildren(getChildrenList(item, list)))
                .collect(Collectors.toList());
    }

    /**
     * 获取子节点列表
     * @param tree 菜单列表
     * @param list 子节点列表
     */
    private static <T extends TreeNode<T>> List<T> getChildrenList(T tree, List<T> list){
        return list.stream().filter(item -> Objects.equals(item.getParentId(), tree.getId()))
                .sorted(Comparator.comparing(TreeNode::getOrderWeight, Comparator.nullsFirst(Long::compareTo)))
                .peek(item -> item.setChildren(getChildrenList(item, list))).collect(Collectors.toList());
    }
}
