import java.util.*;

public class MediaPlayerEngine {

    public static class Song {
        private final String id;
        private final String title;
        private final String artist;
        private final int durationSeconds;

        public Song(String id, String title, String artist, int durationSeconds) {
            this.id = id;
            this.title = title;
            this.artist = artist;
            this.durationSeconds = durationSeconds;
        }

        public String getId() { return id; }
        public String getTitle() { return title; }
        public String getArtist() { return artist; }
        public int getDurationSeconds() { return durationSeconds; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Song song = (Song) o;
            return Objects.equals(id, song.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return String.format("\"%s\" by %s [%s]", title, artist, id);
        }
    }

    private final LinkedList<Song> queue;
    private final Deque<Song> historyStack;
    private final int maxHistorySize;
    
    private ListIterator<Song> cursor;
    private Song currentSong;

    public MediaPlayerEngine(int maxHistorySize) {
        this.queue = new LinkedList<>();
        this.historyStack = new ArrayDeque<>();
        this.maxHistorySize = maxHistorySize;
        this.cursor = queue.listIterator();
        this.currentSong = null;
    }

   
    public void addToEnd(Song song) {
        queue.addLast(song);
        resetCursorToCurrent();
    }

    public void addPriority(Song song) {
        queue.addFirst(song);
        resetCursorToCurrent();
    }

    public void insertAfterCurrent(Song song) {
        if (currentSong == null && queue.isEmpty()) {
            queue.add(song);
        } else {
            cursor.add(song); 
        }
        resetCursorToCurrent();
    }

    public Song playNext() {
        if (!cursor.hasNext()) {
            System.out.println("End of queue reached.");
            return null;
        }

        if (currentSong != null) {
            pushToHistory(currentSong);
        }

        currentSong = cursor.next();
        return currentSong;
    }

 
    public Song playPrevious() {
        if (!cursor.hasPrevious()) {
            System.out.println("Start of queue reached.");
            return null;
        }

        cursor.previous(); 
        
        if (!cursor.hasPrevious()) {
            System.out.println("Already at the first song.");
            cursor.next(); 
            return currentSong;
        }

        currentSong = cursor.previous();
        cursor.next(); 
        return currentSong;
    }


    public void removeDuplicates() {
        Set<String> seenIds = new HashSet<>();
        ListIterator<Song> dedupeIterator = queue.listIterator();

        while (dedupeIterator.hasNext()) {
            Song song = dedupeIterator.next();
            if (seenIds.contains(song.getId())) {
                dedupeIterator.remove(); // O(1) removal on LinkedList node
            } else {
                seenIds.add(song.getId());
            }
        }
        resetCursorToCurrent();
    }



    private void pushToHistory(Song song) {
        if (historyStack.size() >= maxHistorySize) {
            historyStack.removeLast(); // Evict oldest
        }
        historyStack.push(song);
    }

    public List<Song> getRecentHistory() {
        return new ArrayList<>(historyStack);
    }

    private void resetCursorToCurrent() {
        this.cursor = queue.listIterator();
        if (currentSong != null) {
            while (cursor.hasNext()) {
                if (cursor.next().equals(currentSong)) {
                    break;
                }
            }
        }
    }

    public void printQueueState() {
        System.out.println("\n--- Current Queue State ---");
        System.out.println("Now Playing: " + (currentSong != null ? currentSong : "None"));
        int index = 0;
        for (Song s : queue) {
            String marker = s.equals(currentSong) ? " -> [PLAYING]" : "";
            System.out.println(index++ + ". " + s + marker);
        }

    }

    public static void main(String[] args) {
        MediaPlayerEngine player = new MediaPlayerEngine(5);

        Song s1 = new Song("S1", "Bohemian Rhapsody", "Queen", 354);
        Song s2 = new Song("S2", "Hotel California", "Eagles", 390);
        Song s3 = new Song("S3", "Stairway to Heaven", "Led Zeppelin", 482);
        Song s4 = new Song("S4", "Hotel California", "Eagles", 390); // Duplicate entry

        player.addToEnd(s1);
        player.addToEnd(s2);
        player.addToEnd(s3);
        player.addToEnd(s4);

        Song p1 = new Song("S0", "Intro Track", "Unknown", 120);
        player.addPriority(p1);

        player.printQueueState();

        System.out.println("--> Removing duplicates...");
        player.removeDuplicates();
        player.printQueueState();

        System.out.println("--> Play Next: " + player.playNext());
        System.out.println("--> Play Next: " + player.playNext());
        System.out.println("--> Play Next: " + player.playNext());

        Song queuedNext = new Song("S99", "Urgent Play", "Various", 200);
        System.out.println("--> Inserting 'Urgent Play' right after current track...");
        player.insertAfterCurrent(queuedNext);

        player.printQueueState();

        System.out.println("--> Play Previous: " + player.playPrevious());
      
        System.out.println("\nRecent History Stack: " + player.getRecentHistory());
    }
}