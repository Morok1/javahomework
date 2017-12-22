

public interface ExecuteManager {
    Context execute(Runnable callback, Runnable... tasks);
}
