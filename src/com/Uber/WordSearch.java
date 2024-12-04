class Solution {
    int[][] dirs = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};
    public boolean exist(char[][] board, String word) {
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(board[i][j] == word.charAt(0)){
                    if(solve(i,j,board, 0, word)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean solve(int row, int col, char[][] board, int len, String word){
        if(board[row][col] != word.charAt(len)){
            return false;
        }
        // if(board[row][col] == ch && len == word.length()){
        //     return true;
        // }
        if(len == word.length()-1){
            return true;
        }

        char c = board[row][col];
        board[row][col] = '#';
        for(int[] d: dirs){
            int nRow = row + d[0];
            int nCol = col + d[1];
            if(nRow < 0 || nRow >= board.length || nCol < 0 || nCol >= board[0].length){
                continue;
            }
            if(solve(nRow, nCol, board, len+1, word)){
                return true;
            }
        }
        board[row][col] = c;
        return false;
        /** OR another way w/o using directions array ->
          if (backtrack(board, word, visited, i + 1, j, index + 1) ||
            backtrack(board, word, visited, i - 1, j, index + 1) ||
            backtrack(board, word, visited, i, j + 1, index + 1) ||
            backtrack(board, word, visited, i, j - 1, index + 1)) {
            return true;
        }
         */
    }
}
//https://leetcode.com/problems/word-search/solutions/4965052/96-45-easy-solution-with-explanation/?envType=company&envId=amazon&favoriteSlug=amazon-three-months
