package class01;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;


//并查集结构的详解和实现
//一个快速进行进行集合查询和合并的算法  并查集
public class Code04_UnionFind {

    public static class Element<V> {
        public V value;

        public Element(V value) {
            this.value = value;
        }

    }

    public static class UnionFindSet<V> {
        public HashMap<V, Element<V>> elementMap;
        public HashMap<Element<V>, Element<V>> fatherMap;
        public HashMap<Element<V>, Integer> sizeMap;

        
        public UnionFindSet(List<V> list) {
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value : list) {
                Element<V> element = new Element<V>(value);
                elementMap.put(value, element);
                fatherMap.put(element, element);
                sizeMap.put(element, 1);
            }
        }


        //寻找顶节点
        private Element<V> findHead(Element<V> element) {
            Stack<Element<V>> path = new Stack<>();
            while (element != fatherMap.get(element)) {
                path.push(element);
                element = fatherMap.get(element);
            }
            //将每个路径上的节点的父亲指向顶节点  顶节点父亲指向自己
            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), element);
            }
            return element;
        }

        //判断两点是不是在一个集合中
        public boolean isSameSet(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }
            return false;
        }
     

        //集合合并
        public void union(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                Element<V> aF = findHead(elementMap.get(a));
                Element<V> bF = findHead(elementMap.get(b));
                //如果父节点不相同
                if (aF != bF) {
                	//大集合  谁的结点多
                    Element<V> big = sizeMap.get(aF) >= sizeMap.get(bF) ? aF : bF;
                    //小集合
                    Element<V> small = big == aF ? bF : aF;
                    //小集合的顶点执行大集合顶点
                    fatherMap.put(small, big);
                    sizeMap.put(big, sizeMap.get(aF) + sizeMap.get(bF));
                    sizeMap.remove(small);
                }
            }
        }
    }
}
