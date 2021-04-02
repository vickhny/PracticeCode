/*Approach : Let the coordinates of four corners be A(x1, y1), B(x2, y2), C(x3, y3) and D(x4, y4). And coordinates of the given point P be (x, y)

        1) Calculate area of the given rectangle, i.e., area of the rectangle ABCD as area of triangle ABC + area of triangle ACD.
        Area A = [ x1(y2 – y3) + x2(y3 – y1) + x3(y1-y2)]/2 + [ x1(y4 – y3) + x4(y3 – y1) + x3(y1-y4)]/2
        2) Calculate area of the triangle PAB as A1.
        3) Calculate area of the triangle PBC as A2.
        4) Calculate area of the triangle PCD as A3.
        5) Calculate area of the triangle PAD as A4.
        6) If P lies inside the triangle, then A1 + A2 + A3 + A4 must be equal to A.*/
public class PointLiesInRectangle {

    static float area(int x1, int y1, int x2,
                      int y2, int x3, int y3)
    {
        return (float)Math.abs((x1 * (y2 - y3) +
                x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
    }

    /* A function to check whether point P(x, y)
    lies inside the rectangle formed by A(x1, y1),
    B(x2, y2), C(x3, y3) and D(x4, y4) */
    static boolean check(int x1, int y1, int x2, int y2,
                         int x3, int y3, int x4, int y4, int x, int y)
    {

        /* Calculate area of rectangle ABCD */
        float A = area(x1, y1, x2, y2, x3, y3)+
                area(x1, y1, x4, y4, x3, y3);

        /* Calculate area of triangle PAB */
        float A1 = area(x, y, x1, y1, x2, y2);

        /* Calculate area of triangle PBC */
        float A2 = area(x, y, x2, y2, x3, y3);

        /* Calculate area of triangle PCD */
        float A3 = area(x, y, x3, y3, x4, y4);

        /* Calculate area of triangle PAD */
        float A4 = area(x, y, x1, y1, x4, y4);

        /* Check if sum of A1, A2, A3 and A4
        is same as A */
        return (A == A1 + A2 + A3 + A4);
    }

    // Driver code
    public static void main (String[] args)
    {

        /* Let us check whether the point P(10, 15)
        lies inside the rectangle formed by A(0, 10),
        B(10, 0) C(0, -10) D(-10, 0) */
        if (check(0, 10, 10, 0, 0, -10, -10, 0, 10, 15))
            System.out.print("yes");
        else
            System.out.print("no");
    }
}
