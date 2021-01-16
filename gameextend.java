public class gameextend {


 private int board[][];
 public static final int EMPTY = 0;

 public static final int ONE = 1;
 public static final int TWO = 2;

 public gameextend() {
  board = new int[3][3];
 }

 public boolean isWin(int token) {
  final int DI[]={-1,0,1,1};
  // direction vectors for row
  final int DJ[]={1,1,1,0};
  //direction vectors for column
  for(int i=0;i<3;i++)
   for(int j=0;j<3;j++) {

    if(getBoardValue(i, j)!=token) continue;

    for(int k=0;k<4;k++) {
     int ctr = 0;
                 while(getBoardValue(i+DI[k]*ctr, j+DJ[k]*ctr)==token) ctr++;

     if(ctr==3) return true;
    }
  }
  return false;
    }
 public int getBoardValue(int i,int j) {
  if(i < 0 || i >= 3) return EMPTY;
  if(j < 0 || j >= 3) return EMPTY;
  return board[i][j];
    }


 public void setBoardValue(int i,int j,int token) {
  if(i < 0 || i >= 3) return;
  if(j < 0 || j >= 3) return;
  board[i][j] = token;
    }

 
 public int []nextWinningMove(int token) {

  for(int i=0;i<3;i++)
   for(int j=0;j<3;j++)
    if(getBoardValue(i, j)==EMPTY) {
     board[i][j] = token;
     boolean win = isWin(token);
     board[i][j] = EMPTY;
     if(win) return new int[]{i,j};
    }

  return null;
    }

    public int inverse(int token) {
  return token==ONE ? TWO : ONE;
 }

   
    public int []nextMove(int token) {

        
        if(getBoardValue(1, 1)==EMPTY) return new int[]{1,1};

        
        int winMove[] = nextWinningMove(token);
        if(winMove!=null) return winMove;

        
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(getBoardValue(i, j)==EMPTY)
                {
                    board[i][j] = token;
              boolean ok = nextWinningMove(inverse(token)) == null;
                    board[i][j] = EMPTY;
                    if(ok) return new int[]{i,j};
                }

        
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(getBoardValue(i, j)==EMPTY)
                    return new int[]{i,j};

       
        return null;
    }

 
 
} 
