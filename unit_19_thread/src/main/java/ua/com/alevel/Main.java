package ua.com.alevel;

public class Main {

    public static void main(String[] args) {
        // Get current size of heap in bytes
        long heapSize = Runtime.getRuntime().totalMemory();
        System.out.println("heapSize = " + heapSize);

// Get maximum size of heap in bytes. The heap cannot grow beyond this size.// Any attempt will result in an OutOfMemoryException.
        long heapMaxSize = Runtime.getRuntime().maxMemory();
        System.out.println("heapMaxSize = " + heapMaxSize);

        // Get amount of free memory within the heap in bytes. This size will increase // after garbage collection and decrease as new objects are created.
        long heapFreeSize = Runtime.getRuntime().freeMemory();
        System.out.println("heapFreeSize = " + heapFreeSize / 1024 );

        ProgramRun.run();
    }
}
