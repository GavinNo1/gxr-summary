package alg;

import java.util.*;

/**
 * 621. 任务调度器
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 *
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 *
 * 你需要计算完成所有任务所需要的最短时间。
 *
 * 示例 1：
 *
 * 输入: tasks = ["A","A","A","B","B","B"], n = 2
 * 输出: 8
 * 执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 * 注：
 *
 * 任务的总个数为 [1, 10000]。
 * n 的取值范围为 [0, 100]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/task-scheduler
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TaskScheduler {
    /**
     * 排序
     * 由于相同的任务之间必须有 n 的冷却时间，所以我们可以想到按照任务的数量来安排它们，即一种任务的出现次数越多，我们就越早地安排。例如有 5 种任务 A, B, C, D, E，且它们分别有 6, 1, 1, 1, 1 个时，假设冷却时间 n = 2，那么我们首先安排任务 A，随后在 2 单位的冷却时间里，我们安排任务 B, C，随后继续安排任务 A，再安排任务 D, E，以此类推。
     * 因此我们得到了一种安排的方法：我们规定 n + 1 个任务为一轮，这样的好处是同一轮中一个任务最多只能被安排一次。在每一轮中，我们将当前的任务按照它们剩余的次数降序排序，并选择剩余次数最多的 n + 1 个任务依次执行。如果任务的种类 t 少于 n + 1 个，就只选择全部的 t 种任务，其余的时间空闲。这样做的正确性在于，由于冷却时间的存在，出现次数较多的那些任务如果不尽早安排，将会导致大量空闲时间的出现，因此贪心地将出现次数较多的任务安排在前面是合理的。同时我们可以保证，这一轮的第 k 个任务距离上一次执行至少有 n 个单位的冷却时间。我们可以使用逆向思维来证明：假设第 r 轮中某个任务在第 k 个执行，那么说明它在第 r 轮时为数量第 k 多的任务。在第 r 轮结束后，第 1 多到第 k 多的任务的数量都会减少 1，因此在第 r + 1 轮，这个任务最多也只能是数量第 k 多，因此它如果被执行，一定满足冷却时间的要求。
     * @param tasks
     * @param n
     * @return
     */
    public static int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int time = 0;
        while (map[25] > 0) {
            int i = 0;
            while (i <= n) {
                if (map[25] == 0){
                    break;
                }
                if (i < 26 && map[25 - i] > 0) {
                    map[25 - i]--;
                }
                time++;
                i++;
            }
            Arrays.sort(map);
        }
        return time;
    }

    /**
     * 优先队列
     * 在选择每一轮中的任务时，我们也可以用优先队列（堆）来代替排序。在一开始，我们把所有的任务加入到优先队列中。在每一轮，我们从优先队列中选择最多 n + 1 个任务，把它们的数量减去 1，再放回堆中（如果数量不为 0），直到堆为空。
     * @param tasks
     * @param n
     * @return
     */
    public static int leastInterval2(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c : tasks)
            map[c - 'A']++;
        PriorityQueue<Integer> queue = new PriorityQueue<>(26, Collections.reverseOrder());
        for (int f : map) {
            if (f > 0)
                queue.add(f);
        }
        int time = 0;
        while (!queue.isEmpty()) {
            int i = 0;
            List<Integer> temp = new ArrayList<>();
            while (i <= n) {
                if (!queue.isEmpty()) {
                    if (queue.peek() > 1)
                        temp.add(queue.poll() - 1);
                    else
                        queue.poll();
                }
                time++;
                if (queue.isEmpty() && temp.size() == 0)
                    break;
                i++;
            }
            for (int l : temp)
                queue.add(l);
        }
        return time;
    }



}
