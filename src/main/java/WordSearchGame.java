import java.util.ArrayList;
import java.util.List;

public class WordSearchGame {

/*    Given a 2D board and a list of words from the dictionary, find all words in the board.

    Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.


    For example, given words = ["oath","pea","eat","rain"] and board =

[
        ['o','a','a','n'],
        ['e','t','a','e'],
        ['i','h','k','r'],
        ['i','f','l','v']
        ]
    Return ["eat","oath"].*/
public List<String> findWords(char[][] board, String[] words) {
    ArrayList<String> result = new ArrayList<String>();

    int m = board.length;
    int n = board[0].length;

    for (String word : words) {
        boolean flag = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char[][] newBoard = new char[m][n];
                for (int x = 0; x < m; x++)
                    for (int y = 0; y < n; y++)
                        newBoard[x][y] = board[x][y];

                if (dfs(newBoard, word, i, j, 0)) {
                    flag = true;
                }
            }
        }
        if (flag) {
            result.add(word);
        }
    }

    return result;
}

    public boolean dfs(char[][] board, String word, int i, int j, int k) {
        int m = board.length;
        int n = board[0].length;

        if (i < 0 || j < 0 || i >= m || j >= n || k > word.length() - 1) {
            return false;
        }

        if (board[i][j] == word.charAt(k)) {
            char temp = board[i][j];
            board[i][j] = '#';

            if (k == word.length() - 1) {
                return true;
            } else if (dfs(board, word, i - 1, j, k + 1)
                    || dfs(board, word, i + 1, j, k + 1)
                    || dfs(board, word, i, j - 1, k + 1)
                    || dfs(board, word, i, j + 1, k + 1)) {
                board[i][j] = temp;
                return true;
            }

        } else {
            return false;
        }

        return false;
    }


    //Another implementation
    public boolean exist(char[][] board, String word) {
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(dfs1(board, word, i, j, 0)){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean dfs1(char[][] board, String word, int i, int j, int k){
        if(board[i][j]!=word.charAt(k)){
            return false;
        }

        if(k>=word.length()-1){
            return true;
        }

        int[] di={-1,0,1,0};
        int[] dj={0,1,0,-1};

        char t = board[i][j];
        board[i][j]='#';

        for(int m=0; m<4; m++){
            int pi=i+di[m];
            int pj=j+dj[m];
            if(pi>=0&&pi<board.length&&pj>=0&&pj<board[0].length&&board[pi][pj]==word.charAt(k+1)){
                if(dfs(board,word,pi,pj,k+1)){
                    return true;
                }
            }
        }

        board[i][j]=t;

        return false;
    }
}
