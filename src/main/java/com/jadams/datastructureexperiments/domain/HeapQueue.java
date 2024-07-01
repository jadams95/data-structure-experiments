package com.jadams.datastructureexperiments.domain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class HeapQueue {

    private static class WebElement {
        char[] content;
        boolean isBody;
        boolean hasDivInSemanticTag;

        WebElement(char[] content, boolean isBody, boolean hasDivInSemanticTag) {
            this.content = content;
            this.isBody = isBody;
            this.hasDivInSemanticTag = hasDivInSemanticTag;
        }
    }

    private WebElement[][] webElements;
    private int maxSize;
    private int heapSize;

    public HeapQueue(String webPage) {
        List<WebElement> elements = new ArrayList<>();
        boolean isInBody = false;

        // Regular expressions to identify semantic tags
        String[] semanticTags = {"article", "section", "nav", "aside", "header", "footer", "div"};

        int i = 0;
        while (i < webPage.length()) {
            if (webPage.charAt(i) == '<') {
                int tagEnd = webPage.indexOf('>', i);
                if (tagEnd != -1) {
                    String tag = webPage.substring(i, tagEnd + 1);
                    boolean hasDivInSemanticTag = false;

                    if (tag.equalsIgnoreCase("<body>")) {
                        isInBody = true;
                    } else if (tag.equalsIgnoreCase("</body>")) {
                        isInBody = false;
                    }

                    // Check if the tag is a semantic tag and contains a div
                    for (String semanticTag : semanticTags) {
                        if (tag.contains("<" + semanticTag) && webPage.substring(tagEnd + 1).contains("<div>")) {
                            hasDivInSemanticTag = true;
                            break;
                        }
                    }

                    if (tag.equalsIgnoreCase("<br>") || tag.equalsIgnoreCase("<br/>") || tag.equalsIgnoreCase("<br />")) {
                        i = tagEnd + 1; // Skip standalone <br> tags
                    } else {
                        elements.add(new WebElement(tag.toCharArray(), isInBody, hasDivInSemanticTag));
                        i = tagEnd + 1;
                    }
                } else {
                    break;
                }
            } else {
                int textEnd = webPage.indexOf('<', i);
                if (textEnd == -1) {
                    textEnd = webPage.length();
                }
                String text = webPage.substring(i, textEnd).trim();
                if (!text.isEmpty()) {
                    elements.add(new WebElement(text.toCharArray(), isInBody, false));
                }
                i = textEnd;
            }
        }

        this.maxSize = elements.size();
        this.webElements = new WebElement[maxSize][];
        for (int j = 0; j < maxSize; j++) {
            this.webElements[j] = new WebElement[]{elements.get(j)};
        }
        this.heapSize = 0;

        // Insert all elements into the heap
        for (int j = 0; j < maxSize; j++) {
            insert(this.webElements[j][0]);
        }

        // Apply higherPriority method
        higherPriority(0);
    }

    private int parent(int pos) {
        return (pos - 1) / 2;
    }

    private int leftChild(int pos) {
        return 2 * pos + 1;
    }

    private int rightChild(int pos) {
        return 2 * pos + 2;
    }

    private boolean isLeaf(int pos) {
        return pos >= (heapSize / 2) && pos <= heapSize;
    }

    private void swap(int fpos, int spos) {
        WebElement[] tmp;
        tmp = webElements[fpos];
        webElements[fpos] = webElements[spos];
        webElements[spos] = tmp;
    }

    private void heapifyDown(int pos) {
        if (!isLeaf(pos)) {
            int left = leftChild(pos);
            int right = rightChild(pos);
            int smallest = pos;

            if (left < heapSize && compare(webElements[left][0], webElements[smallest][0]) < 0) {
                smallest = left;
            }
            if (right < heapSize && compare(webElements[right][0], webElements[smallest][0]) < 0) {
                smallest = right;
            }

            if (smallest != pos) {
                swap(pos, smallest);
                heapifyDown(smallest);
            }
        }
    }

    private void heapifyUp(int pos) {
        int current = pos;

        while (current > 0 && compare(webElements[current][0], webElements[parent(current)][0]) < 0) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public void insert(WebElement element) {
        if (heapSize >= maxSize) {
            throw new RuntimeException("Heap is full");
        }
        webElements[heapSize] = new WebElement[]{element};
        int current = heapSize;
        heapSize++;
        heapifyUp(current);
    }

    public WebElement removeMin() {
        if (heapSize == 0) {
            throw new RuntimeException("Heap is empty");
        }
        WebElement popped = webElements[0][0];
        webElements[0] = webElements[--heapSize];
        heapifyDown(0);
        return popped;
    }

    private int compare(WebElement a, WebElement b) {
        // Prioritize body elements over head elements
        if (a.isBody && !b.isBody) {
            return -1;
        } else if (!a.isBody && b.isBody) {
            return 1;
        }

        // Prioritize elements with semantic tags containing div over others
        if (a.hasDivInSemanticTag && !b.hasDivInSemanticTag) {
            return -1;
        } else if (!a.hasDivInSemanticTag && b.hasDivInSemanticTag) {
            return 1;
        }

        // If both are from the same section, compare based on the length of the string bytes
        return Integer.compare(a.content.length, b.content.length);
    }

    private void higherPriority(int pos) {
        if (pos < heapSize) {
            int middleIndex = heapSize / 2;
            if (pos == 0 && heapSize > 0) {
                swap(0, middleIndex);
                heapifyDown(0);
            }

            higherPriority(leftChild(pos));
            higherPriority(rightChild(pos));

            // Determine lengths of header and footer
            int headerLength = 0;
            int footerLength = 0;
            for (int i = 0; i < middleIndex; i++) {
                headerLength += webElements[i][0].content.length;
            }
            for (int i = middleIndex + 1; i < heapSize; i++) {
                footerLength += webElements[i][0].content.length;
            }

            // Flip the tree if the footer is longer than the header
            if (footerLength > headerLength) {
                flipTree();
            }
        }
    }

    public void flipTree() {
        for (int i = 0; i < heapSize / 2; i++) {
            swap(i, heapSize - 1 - i);
        }
        // Rebuild the heap to maintain the heap property
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    public void printHeap() {
        for (int i = 0; i < heapSize / 2; i++) {
            System.out.print("PARENT: " + new String(webElements[i][0].content) + " LEFT CHILD: " + new String(webElements[2 * i + 1][0].content) + " RIGHT CHILD: " + new String(webElements[2 * i + 2][0].content));
            System.out.println();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < heapSize; i++) {
            sb.append("[");
            sb.append(webElements[i][0].content);
            sb.append("]");
        }
        return sb.toString();
    }
}