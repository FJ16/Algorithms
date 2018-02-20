import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortCharactersByFreq {

    class Entry {
        private char c;
        private int frequncy;
        public Entry(char c, int frequncy) {
            this.c = c;
            this.frequncy = frequncy;
        }
    }

    public String frequencySort(String s) {

        Entry[] freq = new Entry[256];

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (freq[cur] == null) {
                freq[cur] = new Entry(cur, 0);
            }
            freq[cur].frequncy++;
        }

        Arrays.sort(freq, new Comparator<Entry>() {
            @Override
            public int compare(Entry a, Entry b) {
                return b.frequncy - a.frequncy;
            }
        });

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < freq.length; i++) {
            for (int j = 0; j < freq[i].frequncy; j++) {
                res.append(freq[i].c);
            }
        }

        return res.toString();
    }
}
