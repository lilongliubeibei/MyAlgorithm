package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.class02et;

public class Problem04_CoverMax {

	public static class Rectangle {
		public int up;
		public int down;
		public int left;
		public int right;

		public Rectangle(int up, int down, int left, int right) {
			this.up = up;
			this.down = down;
			this.left = left;
			this.right = right;
		}

	}

	public static class DownComparator implements Comparator<Rectangle> {
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return o1.down - o2.down;
		}
	}

	public static class LeftComparator implements Comparator<Rectangle> {
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return o1.left - o2.left;
		}
	}

	public static class RightComparator implements Comparator<Rectangle> {
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return o1.right - o2.right;
		}
	}

	public static int maxCover(Rectangle[] recs) {
		if (recs == null || recs.length == 0) {
			return 0;
		}
		Arrays.sort(recs, new DownComparator());
		class02et<Rectangle> leftOrdered = new class02et<>(new LeftComparator());
		int ans = 0;
		for (int i = 0; i < recs.length; i++) {
			int curDown = recs[i].down;
			int index = i;
			while (recs[index].down == curDown) {
				leftOrdered.add(recs[index]);
				index++;
			}
			i = index;
			removeLowerOnCurDown(leftOrdered, curDown);
			class02et<Rectangle> rightOrdered = new class02et<>(new RightComparator());
			for (Rectangle rec : leftOrdered) {
				removeLeftOnCurLeft(rightOrdered, rec.left);
				rightOrdered.add(rec);
				ans = Math.max(ans, rightOrdered.size());
			}
		}
		return ans;
	}

	public static void removeLowerOnCurDown(class02et<Rectangle> set, int curDown) {
		List<Rectangle> removes = new ArrayList<>();
		for (Rectangle rec : set) {
			if (rec.up <= curDown) {
				removes.add(rec);
			}
		}
		for (Rectangle rec : removes) {
			set.remove(rec);
		}
	}

	public static void removeLeftOnCurLeft(class02et<Rectangle> rightOrdered, int curLeft) {
		List<Rectangle> removes = new ArrayList<>();
		for (Rectangle rec : rightOrdered) {
			if (rec.right > curLeft) {
				break;
			}
			removes.add(rec);
		}
		for (Rectangle rec : removes) {
			rightOrdered.remove(rec);
		}
	}

}
