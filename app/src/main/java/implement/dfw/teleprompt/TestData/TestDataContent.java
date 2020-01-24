package implement.dfw.teleprompt.TestData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
//public class TestDataContent {                                // Original (system generated definition)
public class TestDataContent implements Serializable {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<TestDataItem> ITEMS = new ArrayList<TestDataItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, TestDataItem> ITEM_MAP = new HashMap<String, TestDataItem>();

    private static final int COUNT = 5;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createTestDataItem(i));
        }
    }

    private static void addItem(TestDataItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static TestDataItem createTestDataItem(int position) {
        return new TestDataItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

//    /**
//     * A dummy item representing a piece of content.       // Original Sample, that was replaced by .dummy.TestDataItem
//     */
//    public static class TestDataItem {
//        public final String id;
//        public final String content;
//        public final String details;
//
//        public TestDataItem(String id, String content, String details) {
//            this.id = id;
//            this.content = content;
//            this.details = details;
//        }

////        @Override
////        public String toString() {
////            return content;
////        }
//    }
}
