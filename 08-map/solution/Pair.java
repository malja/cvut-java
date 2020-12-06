package solution;

/**
 * Storage for key -> value pair.
 */
public class Pair<L, R> {
    private final L left;
    private final R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }

        Pair other = (Pair)o;
        return  this.left.equals(other.getLeft()) &&
                this.right.equals(other.getRight());
    }
}
