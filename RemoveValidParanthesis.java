//Time Complexity: O(2 power n)
class Solution {
    List<String> res;
    HashSet<String> set;

    public List<String> removeInvalidParentheses(String s) {
        this.res = new ArrayList<>();
        this.set = new HashSet<>();

        Queue<String> q = new LinkedList<>();
        q.add(s);
        set.add(s);

        boolean found = false;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String curr = q.poll();

                if (isValid(curr)) {
                    res.add(curr);
                    found = true;
                }

                // Once a valid string is found at this level, don't generate deeper levels
                if (found)
                    continue;

                for (int k = 0; k < curr.length(); k++) {
                    char c = curr.charAt(k);
                    if (c != '(' && c != ')')
                        continue;

                    String baby = curr.substring(0, k) + curr.substring(k + 1);
                    if (!set.contains(baby)) {
                        q.add(baby);
                        set.add(baby);
                    }
                }
            }
            // Finished the whole level; if anything valid was found, stop
            if (found)
                break;
        }
        return res;
    }

    private boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0)
                    return false; // too many ')' so far
            }
        }
        return count == 0;
    }
}