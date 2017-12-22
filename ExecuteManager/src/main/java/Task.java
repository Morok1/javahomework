public class Task implements Runnable {
    private final int id;

    public Task(int id) {
        this.id = id;
    }
    @Override
    public void run(){
        System.err.println(id + "in thread" + Thread.currentThread().getName());
        double a = 0;
        for (int i = 0; i < 10000; i++) {
            a += Math.tan(a) + Math.sin(a) + Math.cos(a);
        }
        System.err.println("Work ID" + id + " in thread " + Thread.currentThread().getName());
    }
}
