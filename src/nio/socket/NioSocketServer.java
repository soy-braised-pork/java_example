package nio.socket;

/**
 * 局限性
 * 从始至终只使用了一个线程，多线程可发挥多核CPU优势
 * 代码复杂度高
 *    优化：selectionKey可再绑定attach----回调
 */

public class NioSocketServer {
    private static final int BUF_SIZE=1024;
}
