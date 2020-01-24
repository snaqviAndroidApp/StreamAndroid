package implement.dfw.teleprompt.TestData;

/**
 * A dummy item representing a piece of content.
 */

public class TestDataItem {
    public final String id;
    public final String content;
    public final String details;

    public TestDataItem(String id, String content, String details) {
        this.id = id;
        this.content = content;
        this.details = details;
    }

            @Override
        public String toString() {
            return content;
        }
}