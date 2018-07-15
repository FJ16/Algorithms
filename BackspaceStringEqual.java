public class BackspaceStringEqual {
    public boolean backspaceCompare(String S, String T) {
        int ls = S.length() - 1;
        int lt = T.length() - 1;

        int cns = 0;
        int cnt = 0;
        while (ls >= 0 && lt >= 0) {
            if (S.charAt(ls) == '#') {
                cns++;
                ls--;
            }
            else if (T.charAt(lt) == '#') {
                cnt++;
                lt--;
            }
            else {
                if (cns > 0) {
                    ls--;
                    cns--;
                }
                else if (cnt > 0) {
                    lt--;
                    cnt--;
                } else {
                    if (S.charAt(ls--) != T.charAt(lt--)) {
                        return false;
                    }
                }
            }
        }

        if (ls < 0 && lt < 0) {
            return true;
        }

        return ls >= 0 ? isEmpty(ls, S, cns) : isEmpty(lt, T, cnt);
    }

    private boolean isEmpty(int r, String str, int ct) {
        int cnt = ct;
        while (r >= 0) {
            if (str.charAt(r--) == '#') {
                cnt++;
            }
            else {
                if (cnt > 0) {
                    r--;
                } else return false;
            }
        }
        return true;
    }
}
