package study.leetcode_advanced.TreeAndGraph;

import java.util.*;

/**
 * 课程表
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * <p>
 * 给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 * <p>
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
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
public class CanFinish {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] pathin = new int[numCourses];//每个数字的入度量

        Map<Integer, List<Integer>> map = new HashMap<>();

        //遍历prerequisites数组，得到每个数字的入度数，和每个数字的后继集合
        for (int i = 0; i < prerequisites.length; i++) {
            if (!map.containsKey(prerequisites[i][1])) {//当前数字还没有在map里存储
                //将当前数字和它的后继存入map中
                List<Integer> temp = new ArrayList();
                temp.add(prerequisites[i][0]);
                map.put(prerequisites[i][1], temp);
            } else {//已经有过当前数字
                //更新map里的后继
                List<Integer> temp = map.get(prerequisites[i][1]);
                temp.add(prerequisites[i][0]);
            }
            //记录后继数字的pathin
            pathin[prerequisites[i][0]]++;
        }

        List<Integer> result = new ArrayList<>();

        //找出入度数为0的数字，保存到集合中
        for (int i = 0; i < numCourses; i++) {
            for (int j = 0; j < numCourses; j++) {
                if (pathin[j] == 0) {//如果入度数为0 对应的map里的后继集合中的所有数字的pahtin--
                    result.add(j);//让入度为0的数字进入集合
                    pathin[j] = -1;//标记为已经查找过了
                    List<Integer> temp = map.get(j);
                    for (int k = 0; temp != null && k < temp.size(); k++) {
                        pathin[temp.get(k)]--;
                    }
                }
            }

        }

        return result.size() == numCourses;

    }

    /**
     * 时间最短
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        int[] visited = new int[numCourses]; // 0:未访问;1:已访问;-1:冲突
        // 初始化ArrayList数组
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        // 用邻接表形式构建有向图
        for (int[] pre : prerequisites) { // [0,1]表示1->0，1为前驱课程
            graph[pre[1]].add(pre[0]);
        }
        // DFS遍历有向图
        for (int i = 0; i < numCourses; i++) {
            if (!canFinishDFS(graph, visited, i)) { // 存在环
                return false;
            }
        }
        return true;
    }

    public boolean canFinishDFS(ArrayList<Integer>[] graph, int[] visited, int i) {
        if (visited[i] == -1) { // 有环
            return false;
        }
        if (visited[i] == 1) {
            return true;
        }
        visited[i] = -1;
        for (int index = 0; index < graph[i].size(); index++) {
            int adj = (int) graph[i].get(index); // 取每一组List中的各个值
            if (!canFinishDFS(graph, visited, adj)) {
                return false;
            }
        }
        visited[i] = 1;
        return true;
    }

    /**
     * 时间其次的另一种方法
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish3(int numCourses, int[][] prerequisites) {
        /**
         * 构建邻接表
         */
        EdgeNode[] edges = new EdgeNode[numCourses];
        Node temp = null;
        int topoSize = 0;
        for(int i=0;i<numCourses;i++){
            edges[i] = new EdgeNode();
            edges[i].in = 0;
            edges[i].val = i;
        }
        for(int i=0;i<prerequisites.length;i++){
            temp = edges[prerequisites[i][1]].next;
            Node newNode = new Node();
            newNode.val = prerequisites[i][0];
            edges[prerequisites[i][1]].next = newNode;
            newNode.next = temp;
            edges[prerequisites[i][0]].in ++;
        }
        /**
         * 进行拓扑排序
         */
        Stack<EdgeNode> stack = new Stack<EdgeNode>();//存储入度为0的结点
        for(int i=0;i<numCourses;i++){                //将入度为0的结点压入栈中
            if(edges[i].in==0){
                stack.push(edges[i]);
            }
        }
        EdgeNode deletedNode = null;
        while(!stack.isEmpty()){
            topoSize++;
            deletedNode = stack.pop();               //删除入读为0的结点
            temp = deletedNode.next;
            while(temp!=null){                       //更新其邻接点的入度
                if(edges[temp.val].in>0){
                    edges[temp.val].in --;
                    if(edges[temp.val].in == 0)      //如果更新后的邻接结点的入度为0，将其压入栈中
                        stack.push(edges[temp.val]);
                }
                temp = temp.next;
            }
        }
        return topoSize == numCourses;
    }

    class EdgeNode{
        int in;
        int val;
        Node next;
    }

    class Node{
        int val;
        Node next;
    }
}
