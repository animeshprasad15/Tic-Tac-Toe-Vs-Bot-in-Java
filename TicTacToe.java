import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class TicTacToe extends JFrame implements ActionListener {

 private JButton [][]buttons = new JButton[3][3];
 private JButton playButton = new JButton("Play");
 private JLabel statusLabel = new JLabel("");
 private gameextend game = null;
 private int human = 0;
 private int computer = 0;
 private boolean isPlay = false;
 private String []chars=new String[]{"","X","O"};

 private void setStatus(String s) {
  statusLabel.setText(s);
 }
 public void setButtonsEnabled(boolean enabled) {
  for(int i=0;i<3;i++)
   for(int j=0;j<3;j++) {
    buttons[i][j].setEnabled(enabled);
    if(enabled) buttons[i][j].setIcon(null);
   }

 }

 public TicTacToe() {

  setTitle("Tic Tac Toe");
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setResizable(false);

  JPanel centerPanel = new JPanel(new GridLayout(3,3));
  Font font = new Font("Arial",Font.BOLD, 32);
  for(int i=0;i<3;i++)
   for(int j=0;j<3;j++) {
    buttons[i][j] = new JButton();
    buttons[i][j].setFont(font);
    buttons[i][j].addActionListener(this);
    buttons[i][j].setFocusable(false);
    centerPanel.add(buttons[i][j]);
   }

  playButton.addActionListener(this);

  JPanel northPanel = new JPanel();
  northPanel.add(statusLabel);

  JPanel southPanel = new JPanel();
  southPanel.add(playButton);

  setStatus("Click 'Play' To Start");
  setButtonsEnabled(false);
  

  add(northPanel,"North");
  add(centerPanel,"Center");
  add(southPanel,"South");

  setSize(500,500);

  
  setLocationRelativeTo(null);
 }

 
 private void computerTurn() {
  if(!isPlay) return;

  int []pos = game.nextMove(computer);
  if(pos!=null) {
   int i = pos[0];
   int j = pos[1];
   buttons[i][j].setIcon(new ImageIcon("X.png"));
   game.setBoardValue(i,j,computer);
  }

  checkState();
 }

 private void gameOver(String s) {
  setStatus(s);
  setButtonsEnabled(false);
  isPlay = false;
 }

 private void checkState() {
  if(game.isWin(human)) {
   gameOver("you won the game ");
  }
  if(game.isWin(computer)) {
   gameOver("Sorry!You lose the game!");
  }
  if(game.nextMove(human)==null && game.nextMove(computer)==null) {
   gameOver("Draw, Click 'Play' to reset!");
  }
 }

 private void click(int i,int j) {
  if(game.getBoardValue(i,j)==gameextend.EMPTY) {
   buttons[i][j].setIcon(new ImageIcon("O.png"));
   game.setBoardValue(i,j,human);
   checkState();
   computerTurn();
  }
 }

 public void actionPerformed(ActionEvent event) {
  if(event.getSource()==playButton) {
   play();
  }else {
   for(int i=0;i<3;i++)
    for(int j=0;j<3;j++)
     if(event.getSource()==buttons[i][j])
      click(i,j);
  }
 }

 public void play() {
  game = new gameextend();
  human = gameextend.ONE;
  computer = gameextend.TWO;
  setStatus("Your Turn");
  setButtonsEnabled(true);
  isPlay = true;
 }
}