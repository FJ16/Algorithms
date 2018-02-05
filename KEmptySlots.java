public class KEmptySlots {
    public int kempty(int[] flowers, int k) {
        // int[] flowers means: at i-th day, flowers[i]-th flower is blooming （P里，index是day的信息)
        // But here we want k empty between certain flower position, so how about we turn those info from flowers[] to
        // some other sequence that represents: at i-th position, the flower will be blooming at [i]-th day?
        // pos[]里，index 是 花位置的信息，这道题巧妙利用了 array index meaning 的转换

        int[] pos = new int[flowers.length];
        for (int i = 0; i < flowers.length; i++) {
            // 位置从1开始，存到数组里要 -1， 适应从零开始
            pos[flowers[i] - 1] = i + 1; // day + 1 以适应从1开始
        }

        int left = 0;
        int right = k + 1; // k + 1 --> k position in (left, k + 1)
        int ans = Integer.MAX_VALUE;
        // keep a tag here for continue operation
        search: while(right < pos.length) {
            for (int i = left + 1; i < right; i++) {
                if (pos[i] < pos[left] || pos[i] < pos[right]) {
                    left = i;
                    // since this i-th position flower has already infected the range from (left, right)
                    // then we need to slide the window over again from the i-th flower as the new edge left
                    right = i + k + 1;
                    continue search;
                }
            }
            ans = Math.min(ans, Math.max(pos[left], pos[right]));
            left = right;
            // 这里left edge可以直接移到right位置，因为目前 left 和 right 位置的花最早开，那么如果
            // 右移window，那么window中的 原来的right 一定比新 right早开，那么等新right开了，former right肯定也开了，
            // 所以不符合中间 empty的要求，可以直接移 window to the most right position as the new left edge doesn't include left
            right = left + k + 1;
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
