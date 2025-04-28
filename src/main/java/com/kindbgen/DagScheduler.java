package com.kindbgen;

import java.util.*;

/**
 * @author <a href="mailto:kindbgen@gmail.com">Kindbgen<a/>
 * @description DAG调度器实现（Kahn算法拓扑排序）
 * @date 2025/4/24
 * @since 1.0.0
 */
public class DagScheduler {
    public static List<String> schedule(Map<String, DataLoader<?>> loaders) {
        // 构建入度表
        Map<String, Integer> inDegree = new HashMap<>();
        // 构建邻接表
        Map<String, List<String>> adjacency = new HashMap<>();

        // 初始化数据结构
        loaders.keySet().forEach(name -> {
            inDegree.put(name, 0);
            adjacency.put(name, new ArrayList<>());
        });

        // 填充依赖关系
        loaders.values().forEach(loader -> {
            for (String dep : loader.getDependencies()) {
                adjacency.get(dep).add(loader.getName());
                inDegree.put(loader.getName(), inDegree.get(loader.getName()) + 1);
            }
        });

        // Kahn算法核心逻辑
        Queue<String> queue = new LinkedList<>();
        inDegree.forEach((k, v) -> {
            if (v == 0) queue.add(k);
        });

        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            String current = queue.poll();
            result.add(current);

            for (String neighbor : adjacency.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (result.size() != loaders.size()) {
            throw new RuntimeException("存在循环依赖！");
        }

        return result;
    }
}