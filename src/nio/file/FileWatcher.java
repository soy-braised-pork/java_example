package nio.file;

import java.io.IOException;
import java.nio.file.*;

public class FileWatcher {
    public static void main(String[] args) throws IOException, InterruptedException {
        try (WatchService watcher = FileSystems.getDefault().newWatchService()) {
            Path dir = Paths.get("/home/zhulixin/IdeaProjects/java/lib/");
            WatchKey key = dir.register(watcher, StandardWatchEventKinds.ENTRY_DELETE);
            for (; ; ) {
                key = watcher.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    System.out.println(event.kind());
                }
                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
        }
    }
}
