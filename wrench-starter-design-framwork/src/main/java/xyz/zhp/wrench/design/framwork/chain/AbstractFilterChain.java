package xyz.zhp.wrench.design.framwork.chain;

/**
 * 责任链抽象类
 *
 * @author zhp
 * @since 2025-07-30 15:19
 */
public abstract class AbstractFilterChain<T> {


    /**
     * 业务链
     */
    protected AbstractFilterChain<T> next;

    /**
     * 处理逻辑
     *
     * @param t 上下文参数
     */
    protected abstract void handle(T t);

    /**
     * 是否中断后续链式逻辑
     *
     * @return y/n
     */
    protected boolean terminate(T t) {
        return false;
    }


    /**
     * 处理
     *
     * @param t 上下文参数
     */
    public final void doFilter(T t) {

        // 确保当前节点的handle被调用
        handle(t);

        if (terminate(t)) return;

        AbstractFilterChain<T> current = this.next;

        while (current != null) {

            // 处理
            current.handle(t);

            // 是否终止
            if (current.terminate(t)) break;

            // 下一个链
            current = current.next;
        }
    }


    public static class AbstractAbstractFilterChainBuilder<T> {
        private AbstractFilterChain<T> head;

        private AbstractFilterChain<T> tail;

        public AbstractAbstractFilterChainBuilder<T> add(AbstractFilterChain<T> AbstractFilterChain) {
            if (head == null) {
                head = tail = AbstractFilterChain;
            } else {
                tail.next = AbstractFilterChain;
                tail = AbstractFilterChain;
            }
            return this;
        }

        public AbstractFilterChain<T> build() {
            return head;
        }
    }

}
