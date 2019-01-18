package com.graduation.service.algorithm;

import com.graduation.domain.vo.CategoryVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption
 * @Author fuxiaoxiang2
 * @Create 2019/1/13 17:14
 */
public class RankAlgorithm<T> {
    private List<T> result = new ArrayList<>();

    public void rank(List<T> targetList, Class clazz) {
        String className = clazz.getSimpleName();
        if ("CategoryVO".equals(className)) {
            //存储对象与分值映射关系
            Map<T, Double> maps = new HashMap<>();
            for (T t : targetList) {
                CategoryVO vo = (CategoryVO) t;
                maps.put(t, getScore(vo.getViewNum(), vo.getLikeNum(), vo.getHateNum()));
            }
            realRank(maps, 0);
        }
    }

    /**
     * 排序核心算法
     *
     * @param maps 分值与对象映射关系
     * @param flag 0类目排序
     */
    private void realRank(Map<T, Double> maps, int flag) {
        if (flag == 0) {
        }
    }

    private void sort(Map<T, Double> maps) {
        int len = maps.size();
        int index = (len - 1) >> 1;
        for (int i = index; i >= 0; i--) {

        }
    }
    private void bulidHeap(Map<T, Double> maps, int len, int root) {

    }
    public void swap(Map<T, Double> maps,int a,int b){

    }

    public List<T> pick() {
        return result;
    }

    /**
     * 类目热度分值计算公式
     *
     * @param viewNum 3/10
     * @param likeNum 8/10
     * @param hateNum -1/10
     * @return
     */
    private Double getScore(int viewNum, int likeNum, int hateNum) {
        return (double) (viewNum * 3 + likeNum * 8 + hateNum * (-1));
    }
}
