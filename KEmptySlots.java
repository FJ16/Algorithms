public class KEmptySlots {
    public int kempty(int[] P, int K) {
        int[] days =  new int[P.length];
        for(int i=0; i<P.length; i++) days[P[i] - 1] = i + 1;
        int left = 0, right = K + 1, res = Integer.MIN_VALUE;

        for(int i = 0; right < days.length; i++){
            if(days[i] > days[left] || days[i] >= days[right]){
                if (i == right) res = Math.max(res, Math.max(days[left], days[right]));
                left = i;
                right = K + 1 + i;
            }
        }
        return (res == Integer.MIN_VALUE) ? -1 : res;
    }
}
