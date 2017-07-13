/**
 * Add you netid here..
 * @author kenneth2
 *
 */
public class PlayListUtil {

	/**
	 * Debug ME! Use the unit tests to reverse engineer how this method should work.
	 * Hint: Fix the formatting (shift-cmd-F) to help debug the following code
	 * @param list
	 * @param maximum
	 */
	public static void list(String[] list, int maximum) {
		if (maximum > 0) {
			for (int i = 0; i < maximum; i++)
			{
				System.out.println((i + 1) + ". " + list[i]);
			}
		} else if (maximum == -1) {
			list(list, list.length);
		}
	}
	/**
	 * Appends or prepends the title
	 * @param list
	 * @param title
	 * @param prepend if true, otherwise append the title
	 * @return a new list with the title prepended or appended to the original list
	 */
	public static String[] add (String[] list, String title, boolean prepend) {
		String[] array = new String[list.length + 1];
		if (prepend) {
			array[0] = title;
			for (int i = 1; i <= list.length; i++) {
				array[i] = list[i - 1];
			}
		} else {
			for (int i = 0; i < list.length; i++) {
				array[i] = list[i];
			}
			array[array.length - 1] = title;
		}
		return array;
	}
	/**
	 * Returns a new list with the element at index removed.
	 * @param list the original list
	 * @param index the array index to remove.
	 * @return a new list with the String at position 'index', absent.
	 */
	public static String[] discard(String[] list, int index) {
		String[] array = new String[list.length - 1];
		int j = 0;

		for (int i = 0; i <= array.length; i++) {
			if (i != index) {
				array[j] = list[i];
			} else {
				j--;
			}
			j++;
		}
		return array;
	}
}
