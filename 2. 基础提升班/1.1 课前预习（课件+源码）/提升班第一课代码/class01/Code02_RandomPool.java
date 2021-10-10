package class01;

import java.util.HashMap;


//设计RandomPool结构
//		【题目】
//		设计一种结构，在该结构中有如下三个功能:
//		insert(key):将某个key加入到该结构，做到不重复加入
//		delete(key):将原本在结构中的某个key移除
//		getRandom(): 等概率随机返回结构中的任何一个key。
//		【要求】
//		Insert、delete和getRandom方法的时间复杂度都是O(1)
public class Code02_RandomPool {

	public static class Pool<K> {
		private HashMap<K, Integer> keyIndexMap;
		private HashMap<Integer, K> indexKeyMap;
		private int size;

		public Pool() {
			this.keyIndexMap = new HashMap<K, Integer>();
			this.indexKeyMap = new HashMap<Integer, K>();
			this.size = 0;
		}

		public void insert(K key) {
			if (!this.keyIndexMap.containsKey(key)) {
				//index就是当前的size
				this.keyIndexMap.put(key, this.size);
				this.indexKeyMap.put(this.size++, key);
			}
		}

		public void delete(K key) {
			if (this.keyIndexMap.containsKey(key)) {
				//key对应的index
				int deleteIndex = this.keyIndexMap.get(key);
				//最后一个index
				int lastIndex = --this.size;
				//最后一个key
				K lastKey = this.indexKeyMap.get(lastIndex);
				//将最后一个key存在在deleteIndex
				this.keyIndexMap.put(lastKey, deleteIndex);
				//将最后一个key存在在deleteIndex
				this.indexKeyMap.put(deleteIndex, lastKey);
				this.keyIndexMap.remove(key);
				this.indexKeyMap.remove(lastIndex);
			}
		}

		public K getRandom() {
			if (this.size == 0) {
				return null;
			}
			int randomIndex = (int) (Math.random() * this.size); // 0 ~ size -1
			return this.indexKeyMap.get(randomIndex);
		}

	}

	public static void main(String[] args) {
		Pool<String> pool = new Pool<String>();
		pool.insert("zuo");
		pool.insert("cheng");
		pool.insert("yun");
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());

	}

}
