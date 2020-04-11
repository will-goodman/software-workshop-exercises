import java.util.regex.Pattern;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

/**
 * Takes two lists of numbers as input and merges them into a single, ascending order list.
 *
 * @author Will Goodman
 */
public class ListMerger {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Arguments must consist of two lists of numbers, separated by spaces.");
        } else {
            if (Pattern.matches("^(-?\\d+(.\\d+)?( |$))*", args[0]) && Pattern.matches("^(-?\\d+(.\\d+)?( |$))*", args[1])) {
                String[] firstListString = args[0].split(" ");
                String[] secondListString = args[1].split(" ");

                ArrayList<Float> conjoinedList = new ArrayList<>();

                for (String string : firstListString) {
                    conjoinedList.add(Float.parseFloat(string));
                }

                for (String string : secondListString) {
                    conjoinedList.add(Float.parseFloat(string));
                }

                List<Float> sorted = mergeSort(conjoinedList);

                System.out.println(sorted);

            } else {
                System.out.println("Arguments must consist of two lists of numbers, separated by spaces.");
            }
        }
    }

    /**
     * Sorts a list into ascending order using Merge Sort
     *
     * @param list List<T> List of a Comparable type to be sorted
     * @return The sorted list
     */
    private static <T extends Comparable<T>> List<T> mergeSort(List<T> list) {
        if (list.size() <= 1) {
            return list;
        }

        List<T> firstHalf = list.subList(0, list.size() / 2);
        List<T> secondHalf = list.subList(list.size() / 2, list.size());

        List<T> sorted = merge(mergeSort(firstHalf), mergeSort(secondHalf));

        return sorted;

    }

    /**
     * Merges two sorted lists into a single sorted list.
     *
     * @param firstList  List<T> The first list to merge
     * @param secondList List<T> The second list to merge
     * @return The merged list
     */
    private static <T extends Comparable<T>> List<T> merge(List<T> firstList, List<T> secondList) {

        LinkedList<T> firstLinkedList = new LinkedList(firstList);
        LinkedList<T> secondLinkedList = new LinkedList(secondList);
        ArrayList<T> merged = new ArrayList<>();

        while (firstLinkedList.size() > 0 && secondLinkedList.size() > 0) {
            if (firstLinkedList.peek().compareTo(secondLinkedList.peek()) < 1) {
                merged.add(firstLinkedList.pop());
            } else {
                merged.add(secondLinkedList.pop());
            }
        }

        // one list may be shorter than the other, so any remaining values must be added
        merged.addAll(firstLinkedList);
        merged.addAll(secondLinkedList);

        return merged;
    }
}
