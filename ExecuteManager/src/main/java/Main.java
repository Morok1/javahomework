public class Main {
    public static void main(String[] args) {
        Runnable[] tasks = new Runnable[]{
                new Task(0),
                new Task(1),
                new Task(2),
                new Task(3),
                new Task(4),
                new Task(5),
                new Task(6),
        };
        Runnable callback = () -> System.err.println("\n Callback run now \n");
        ExecutionManagerImpl executionManager = new ExecutionManagerImpl();
        Context context = executionManager.execute(callback, tasks);

        System.err.println("Finished!" + context.isFinished());
        System.err.println("Finished!" + context.getCompletedTaskCount());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.err.println("Finished!" + context.isFinished());
        System.err.println("Finished!" + context.getCompletedTaskCount());

    }
}
