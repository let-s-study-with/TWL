/*
https://leetcode.com/problems/determine-the-winner-of-a-bowling-game/
n == player1.length == player2.length
1 <= n <= 1000
0 <= player1[i], player2[i] <= 10

아이디어
기능구현

자료구조
배열

O(N)
 */

public class Solution {
    public int isWinner(int[] player1, int[] player2) {
        // 종합 점수
        int player1Score = 0;
        int player2Score = 0;
        // 스트라이크 여부
        int player1All = -1;
        int player2All = -1;

        for (int i = 0; i < player1.length; i++) {
            // p1 계산
            if (player1All >= 0 && player1All < 2) {
                player1Score += player1[i] * 2;
                player1All++;
            } else {
                player1Score += player1[i];
            }

            if (player1[i] == 10) player1All = 0;

            // p2 계산
            if (player2All >= 0 && player2All < 2) {
                player2Score += player2[i] * 2;
                player2All++;
            } else {
                player2Score += player2[i];
            }

            if (player2[i] == 10) player2All = 0;
        }

        return player1Score > player2Score ? 1 : player1Score < player2Score ? 2 : 0;
    }
}
