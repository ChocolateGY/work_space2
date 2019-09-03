package study.leetcode_advanced.TreeAndGraph;

import java.util.*;

/**
 * 课程表 II
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * <p>
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 * <p>
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2, [[1,0]]
 * 输出: [0,1]
 * 解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * 示例 2:
 * <p>
 * 输入: 4, [[1,0],[2,0],[3,1],[3,2]]
 * 输出: [0,1,2,3] or [0,2,1,3]
 * 解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 * 因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 * 说明:
 * <p>
 * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 提示:
 * <p>
 * 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
 * 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
 * 拓扑排序也可以通过 BFS 完成。
 */
public class FindOrder {

    /**
     * 分析
     * 解决这道题，我们首先要补充一下关于拓扑排序的知识。
     * <p>
     * 将有向图中的顶点以线性方式进行排序。即对于任何连接自顶点u到顶点v的有向边uv，在最后的排序结果中，顶点u总是在顶点v的前面。
     * <p>
     * 当有向图中存在环时，因为其中有互相依赖，导致无法决定谁前谁后，这时候这个有向图是无法被拓扑排序的。通过这种思想，我们只需要判断这个有向图是否可以被拓扑排序即可知道能否选取所有课程学习。
     * <p>
     * 那么接下来看看拓扑排序的经典算法：
     * <p>
     * 选择入度为0的节点输出
     * 从有向图中删除此节点以及出边
     * 循环上述过程，直到有向图中无节点或无入度为0的节点，循环结束
     * 若循环结束后，如果有向图中无节点，那么说明可以拓扑排序；如果有向图中仍存在节点，那么说明存在环，即不可被拓扑排序。
     * <p>
     * 这道题就采取上述思路，通过删除（同时记录）入度为0的节点，判断最后入度为0的节点的个数是否和总节点个数相同即可。
     * ---------------------
     * 作者：whd_Alive
     * 来源：CSDN
     * 原文：https://blog.csdn.net/whdAlive/article/details/80542676
     * 版权声明：本文为博主原创文章，转载请附上博文链接！
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    //拓扑排序
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //入度
        int[] degree = new int[numCourses];
        //节点，与其后继节点的集合
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        //建立邻接表
        for (int i = 0; i < prerequisites.length; i++) {
            if (!map.containsKey(prerequisites[i][1])) {
                List<Integer> next = new ArrayList<>();
                next.add(prerequisites[i][0]);
                map.put(prerequisites[i][1], next);
            } else {
                List<Integer> next = map.get(prerequisites[i][1]);
                next.add(prerequisites[i][0]);
            }
            degree[prerequisites[i][0]]++;
        }

        //记录入度为0的节点
        List<Integer> result = new ArrayList<>();

        //遍历，找入度为0的点，将其后继节点的入度全部减一
        for (int i = 0; i < numCourses; i++) {
            for (int j = 0; j < numCourses; j++) {
                if (degree[j] == 0) {
                    result.add(j);
                    degree[j] = -1;
                    List<Integer> next = map.get(j);
                    for (int k = 0; next != null && k < next.size(); k++) {
                        degree[next.get(k)]--;
                    }
                }
            }
        }
        return result.size() == numCourses;
    }

    /**
     * 分析
     * 只需要在 课程表 的前提下，把 result 列表输出出来即可。
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //入度
        int[] degree = new int[numCourses];
        //节点，与其后继节点的集合
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        //建立邻接表
        for (int i = 0; i < prerequisites.length; i++) {
            if (!map.containsKey(prerequisites[i][1])) {
                List<Integer> next = new ArrayList<>();
                next.add(prerequisites[i][0]);
                map.put(prerequisites[i][1], next);
            } else {
                List<Integer> next = map.get(prerequisites[i][1]);
                next.add(prerequisites[i][0]);
            }
            degree[prerequisites[i][0]]++;
        }

        //记录入度为0的节点
        List<Integer> result = new ArrayList<>();

        //遍历，找入度为0的点，将其后继节点的入度全部减一
        for (int i = 0; i < numCourses; i++) {
            for (int j = 0; j < numCourses; j++) {
                if (degree[j] == 0) {
                    result.add(j);
                    degree[j] = -1;
                    List<Integer> next = map.get(j);
                    for (int k = 0; next != null && k < next.size(); k++) {
                        degree[next.get(k)]--;
                    }
                }
            }
        }


        if (result.size() != numCourses) {
            return new int[0];
        } else {
            int[] res = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                res[i] = result.get(i);
            }
            return res;
        }
    }

    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        //采用bfs进行求解.类比之前的课程表,依然要先把有向图graph做出来,索引是先决课程1,列表中是受该先决课程影响的课程0.
        //bfs需要用一个数组limit表示每门课先决课程的数量,在bfs时,先决课程为0的,先要放在queue中,作为先访问的课程
        //先访问的课程如果是其他课程的先决,被访问后则在limit中应该在对应其他课程上减少先决的数量.当先决为0时,添加进offer.

        //先做好有向图graph.
        ArrayList[] graph = new ArrayList[numCourses];   //这里graph用ArrayList<<ArrayList<>>(),也可以.
        for(int i = 0; i < numCourses; i++){
            graph[i] = new ArrayList<Integer>();  //先把数组中填充空白列表
        }

        int[] limit = new int[numCourses];  //limit先决课程数量
        for(int[] pre : prerequisites){  //填充列表,并且把受控课程的先决数量标记清楚.
            graph[pre[1]].add(pre[0]);
            limit[pre[0]]++;
        }

        return bfs(graph, limit);
    }

    private int[] bfs(ArrayList[] graph, int[] limit){
        int[] order = new int[graph.length];  //要返回的排序数组
        Queue<Integer> queue = new ArrayDeque<>();
        //先把先决课程数量为0的填进queue中,作为层遍历的起始.
        for(int i = 0; i < limit.length; i++){
            if(limit[i] == 0){
                queue.offer(i);
            }
        }

        int index = 0;    //order数组的索引.
        while(!queue.isEmpty()){
            int head = queue.poll();  //开始访问
            order[index++] = head;    //放进数组中

            //更新先决课程数量,并把更新后为数量0的放入queue中.
            for(int i = 0; i < graph[head].size(); i++){   // graph[head]中是被head影响的课程temp;
                int temp = (int)graph[head].get(i); // ##注意要强转成int,并且ArrayList不能被强转,得get
                limit[temp]--;
                if(limit[temp] == 0){   //影响temp的先决课程为0个,则可以上temp课
                    queue.offer(temp);
                }
            }
        }

        return index == limit.length ? order : new int[]{};
    }



    public int[] findOrder3(int numCourses, int[][] prerequisites) {
        Graph  graph = new Graph(numCourses);
        int[] order = new int[numCourses];

        for(int i=0;i<prerequisites.length;i++){
            graph.addEdge(prerequisites[i][1],prerequisites[i][0]);
        }

        return kahn(graph.adj,order);
    }



    public int[] kahn(LinkedList<Integer> adj[],int[] order){
        int v = adj.length;
        //记录各顶点入度
        int[] inDegree = new int[v];
        for(int i = 0;i < v;i++){
            for(int j = 0;j < adj[i].size();j++){
                int w = adj[i].get(j);
                inDegree[w]++;
            }

        }
        //课程学习队列
        LinkedList<Integer> queue = new LinkedList<Integer>();
        int  courseStudyed = 0;
        for(int i = 0;i < v;i++){
            if(inDegree[i] == 0){
                //入度为0 无依赖 加入课程学习队列
                queue.add(i);
            }
        }

        int index = 0;
        while(!queue.isEmpty()){
            int i = queue.remove();
            courseStudyed++;
            order[index++] = i;
            for(int j = 0;j < adj[i].size();j++){
                //获取顶点adj[i]指向的节点k
                int k = adj[i].get(j);
                //节点k对应的入度-1(一个依赖被满足)
                inDegree[k]--;
                if(inDegree[k] == 0){
                    //节点k所有依赖被满足 加入课程学习队列
                    queue.add(k);
                }
            }
        }

        if(courseStudyed == v){
            return order;
        }else{
            return new  int[0];
        }

    }
    class Graph {
        // 顶点的个数
        private int v;
        // 邻接表
        private LinkedList<Integer> adj[];

        public Graph(int v) {
            this.v = v;
            adj = new LinkedList[v];
            for (int i=0; i<v; ++i) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int s, int t) {
            // s 先于 t，边 s->t
            adj[s].add(t);
        }
    }


    /**
     * 需要记录每个结点的入度，按照入度进行遍历
     * 用indegree数组记录所有结点的入度，初始化为0
     * 遍历prerequisites数组，如果
     * 用arrayList的数组记录每个结点的后继结点群
     * 使用dfs遍历所有结点，先找到所有入度为0的结点，加入ans list，压入栈中
     * 并将该结点所有后继结点入度全部减1
     * 如果减了后入度为0则加入stack继续搜寻。
     */
    private int[] indegree;
    private ArrayList<Integer>[] map;
    private int[] ans;
    public int[] findOrder4(int numCourses, int[][] prerequisites) {
        ans = new int[numCourses];
        map = new ArrayList[numCourses]; //used to
        indegree = new int[numCourses];
        for (int i = 0; i < map.length; i++) { //need memory alloc
            map[i] = new ArrayList<Integer>();
        }
        for (int[] pre: prerequisites) {
            indegree[pre[0]]++;
            if (!map[pre[1]].contains(pre[0]))
                map[pre[1]].add(pre[0]);
        }
        Stack<ArrayList<Integer>> stack = new Stack<>();
        int index = 0;
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                stack.push(map[i]);
                ans[index] = i;
                index++;
            }
        }

        while (!stack.empty()) {
            ArrayList<Integer> current = stack.peek();  //current node
            stack.pop();
            for (int i : current) {
                if (--indegree[i] == 0) {
                    stack.push(map[i]);
                    ans[index] = i;
                    index++;
                }
            }
        }
        if (index == numCourses)
            return ans;
        else
            return new int[0];
    }
}
