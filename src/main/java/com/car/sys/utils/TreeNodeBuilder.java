package com.car.sys.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 铭鸽鸽
 * @create 2020/6/25 23:18
 */
public class TreeNodeBuilder {

    /**
     * 把简单的集合转成有层阶关系的集合
     * @param nodes
     * @param topPid
     * @return
     */
    public static List<TreeNode> builder(List<TreeNode> nodes,Integer topPid){
        List<TreeNode> treeNodes = new ArrayList<>();
        for (TreeNode n1 : nodes){
            if (n1.getPid().equals(topPid)){
                treeNodes.add(n1);
            }
            for (TreeNode n2 : nodes) {
                if (n2.getPid().equals(n1.getId())){
                    n1.getChildren().add(n2);
                }
            }
        }
        return treeNodes;
    }
}
