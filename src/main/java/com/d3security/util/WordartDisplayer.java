package com.d3security.util;

/**
 * Art word display
 *
 * @author Lucy Du
 * @version 1.0.0
 * @date 2022/10/19 13:46
 */
public class WordartDisplayer {
    /**
     * wordart
     */
    private static final String wordart =
            "**       **       **  ******  *****   **     **  ***" + "\n" +
            "  *     *  *     *    *       *   *    *     *    *" + "\n" +
            "   *   *    *   *     *****   ******   *     *    *" + "\n" +
            "    * *      * *      *       *    *   *     *    *" + "\n" +
            "     *        *       ******  ******   *******   ***" + "\n" +
            "\n" +
            "  *******  ******  ******  *******        **     ***" + "\n" +
            "     *     *       *          *         *  *      *" + "\n" +
            "     *     *****   ******     *       ********    *" + "\n" +
            "     *     *            *     *            *      *" + "\n" +
            "     *     ******  ******     *            *    ***";

    /**
     * Outer constructs are not allowed
     */
    private WordartDisplayer() {}

    /**
     * display
     */
    public static void display() {
        System.out.println(wordart);
    }
}
