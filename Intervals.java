import java.util.*;

public class Intervals {
    public List<Interval> insertInterval(List<Interval> intervals, Interval newInterval) {
        int ns = newInterval.start;
        int ne = newInterval.end;
        List<Interval> res = new ArrayList<>();

        // corner case
        if (intervals == null || intervals.size() == 0) {
            res.add(newInterval);
            return res;
        }

        boolean flag = false;
        for (Interval cur : intervals) {
            if (flag) { // if we have already insert new interval then we just add current one
                res.add(cur);
                continue;
            }
            if (ne < cur.start) { // if we find the current mergeing END value is smaller than cur.start, then we have 2 cases:
                // 1. if we haven't added one, then add
                res.add(new Interval(ns, ne));
                flag = true;
                // 2. the current interval has no intersection with mergeing range [ns, ne], then we always need add current one
                res.add(cur);
            }
            else if (ns <= cur.end) { // if we find [ns] < cur.end, then it is for sure we have overlap
                if (ns >= cur.start) // check if we need to update lower value for ns
                    ns = cur.start;
                if (ne <= cur.end) { // check if we need to update upper value for ne
                    ne = cur.end;
                }
            } else { // otherwise, we always add cur one when we don't have any overlap and we haven't added a new Interval
                res.add(cur);
            }
        }
        // post-check for the final corner case when we has the ne > max cur.end
        if (!flag) res.add(new Interval(ns, ne));

        return res;
    }

    public List<Interval> mergeIntervals(List<Interval> intervals) {

        List<Interval>  re = new ArrayList<>();

        // !!!corner case
        if (intervals == null || intervals.size() == 0) return re;

        // using Collections.sort and customize Comparator class to sort List interface
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                if (a.start == b.start) {
                    return 0;
                }
                return a.start < b.start ? -1 : 1;
                //返回值为-1的(负数)表达式会被优先处理，所以这里是先处理最小值
                // 对应由小到大排序
            }
        });
        //这里拍左边界是因为：
        // interval肯定是左小右大，那么对start排好ascending的顺序之后，第一个区间的start一定是<左区间值里的最小>
        // <最小的min已经取出，所以在遍历的时候，每一步才能正确处理区间，不miss正确的最小值>
        ListIterator<Interval> iter = intervals.listIterator();

        // 这里需要记录<当前>(需要被传递的)最大最小值就可以正确合并区间

        // ！物理意义：
        //       当前 原interval 之前的最大值，最小值 -> 合并 or 输出？
        int min = intervals.get(0).start;
        int max = intervals.get(0).end;

        while (iter.hasNext()) {
            Interval t = iter.next();
            if (t.start > max) {
                re.add(new Interval(min, max));
                //区间断开，我们对更新前的max和min划定interval
                //划好不再处理，更新min,max
                min = t.start;
                max = t.end;
            }
            // otherwise 我们的 max > cur.start, 就有交集，还不能add new interval, （t.start < max <? t.end）
            // 也要查看有没有必要更新max, 需要查看有交集的intervals的查看更新右边界, 暂时存下来，不确定该interval就是结果之一
            if (t.end > max) max = t.end;
        }
        // post processing: 最后一个interval没在loop中被处理
        // 用传递出来的 min , max 生成 interval
        re.add(new Interval(min, max));
        return  re;

    }
}

class Interval {
    int start;
    int end;
    Interval() {start = 0; end = 0;};
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
