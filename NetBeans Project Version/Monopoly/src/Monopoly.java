/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nick
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Monopoly extends JPanel implements ActionListener, KeyListener{

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Monopoly t = new Monopoly();
        JFrame frame = new JFrame();
        frame.setTitle("Monopoly v1.2.3");
        frame.setSize(1600, 860);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(t);
        frame.validate();
        Thread thread;
        thread = new Thread();
        //thread.start();
        

    }
    int waitingTime = 1000;
    Timer tm = new Timer(5, this);
    int x = 1090, y = 740, velX = 0, velY = 0, count = 0, mx = 1160, my = 740, score = 0;
    int barLength = 100, bulletX = x, bulletY = y, barCount = 0;
    boolean shootBullet;
    boolean gameOver = false;
    boolean rageMode = false;
    //boolean invisible = false;
    boolean repaint = false;
    boolean rollDice = false;
    int diceimgtimer = 0;
    int randomroll = 0;
    int whoTurn = 0;
    int moveSpaces = 0;
    int space = 0;
    int a = 0;
    int b = 0;
    int shouldWeCountSpaces = 0;
    int totalMoves1 = 0;
    boolean start = true;
    int totalMoves2 = 0;
    int moveSpaces2 = 0;
    int monay1 = 1500;
    int monay2 = 1500;
    boolean buy = false;
    int whoWent = 0;
    /*add this*/
    JOptionPane endGame;
    int[] roadx = new int[40];
    int[] roady = new int[40];
    String[] places = new String[41];
    int[] placeCost = new int[40];
    Stack player2Things = new Stack();
    Stack player1Things = new Stack();
    int[] prices = new int[41]; //if a space isnt buyable, it is null
    int[] player1ThingsDispay = new int[15];
    int[] player2ThingsDispay = new int[15];
    BufferedImage imgy = null;
    BufferedImage img = null;
    BufferedImage imger = null;
    BufferedImage bullet = null;
    BufferedImage imgdice = null;
    Graphics g;
    

    public Monopoly() {
        
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        if (start) {
            System.out.println("buildBoard");
            for (int i = 1; i <= 39; i++) //calculate x values on board (ignore 0 value)
            {
                if (i <= 10) {
                    roadx[i] = 1090 - (i * 102); //assign x values down the bottom of board
                }
                if (i > 10 && i <= 20) {
                    roadx[i] = 70; //same x value at left column of board
                }
                if (i > 20 && i <= 30) {
                    roadx[i] = 70 + ((i - 20) * 102); //x values along top horizontal
                }
                if (i > 30) {
                    roadx[i] = 1090;
                }
                if (i == 40) {
                    break;
                }
            }
                
            
            
            for (int i = 1; i <= 39; i++) {
                if (i <= 10) {
                    roady[i] = 740;
                }
                if (i == 10) {
                    roady[i] = 715;
                }
                if (i > 10 && i <= 20) {
                    roady[i] = roady[i - 1] - 68;
                }
                if (i > 20 && i <= 30) {
                    roady[i] = 25;
                }
                if (i > 30) {
                    roady[i] = roady[i - 1] + 69;
                }
                if (i == 40) {
                    break;
                }
            }

            //make the places (strings)
            places[0] = "Go";
            places[1] = "Mediterranean Avenue";
            places[2] = "Community Chest";
            places[3] = "Baltic Avenue";
            places[4] = "Income Tax";
            places[5] = "Reading Railroad";
            places[6] = "Oriental Avenue";
            places[7] = "Chance";
            places[8] = "Vermont Avenue";
            places[9] = "Conneticut Avenue";
            places[10] = "Jail";
            places[11] = "St.Charles Place";
            places[12] = "Electric Company";
            places[13] = "States Avenue";
            places[14] = "Virginia Avenue";
            places[15] = "Pennsylvania Railroad";
            places[16] = "St.James Place";
            places[17] = "Community Chest";
            places[18] = "Tenessee Avenue";
            places[19] = "New York Avenue";
            places[20] = "Free Parking";
            places[21] = "Kentucky Avenue";
            places[22] = "Chance";
            places[23] = "Indiana Avenue";
            places[24] = "Illinois Avenue";
            places[25] = "B. & O. Railroad";
            places[26] = "Atlantic Avenue";
            places[27] = "Ventnor Avenue";
            places[28] = "Water Works";
            places[29] = "Marvin Gardens";
            places[30] = "Go To Jail!";
            places[31] = "Pacific Avenue";
            places[32] = "North Carolina Avenue";
            places[33] = "Community Chest";
            places[34] = "Pennsylvania Avenue";
            places[35] = "Short Line";
            places[36] = "Chance";
            places[37] = "Park Place";
            places[38] = "Luxury Tax!";
            places[39] = "Boardwalk";
            places[40] = "Go";

            prices[2] = 60;
            prices[4] = 60;
            prices[7] = 100;
            prices[9] = 100;
            prices[10] = 120;
            prices[12] = 140;
            prices[14] = 140;
            prices[15] = 160;
            prices[17] = 180;
            prices[19] = 180;
            prices[20] = 200;
            prices[22] = 220;
            prices[24] = 220;
            prices[25] = 240;
            prices[27] = 260;
            prices[28] = 260;
            prices[30] = 280;
            prices[32] = 300;
            prices[33] = 300;
            prices[35] = 320;
            prices[38] = 350;
            prices[40] = 400;
            prices[13] = 150;
            prices[29] = 150;
            prices[6] = 200;
            prices[16] = 200;
            prices[26] = 200;
            prices[36] = 200;

               if(rollDice)
                   super.paint(g);




        }
    }

    //public void playSound() /*add this*/
    //{
    //  try {
    //      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("neck_snap").getAbsoluteFile());
    //      Clip clip = AudioSystem.getClip();
    //      clip.open(audioInputStream);
    //     clip.start();
    //     } catch(Exception ex) {
    //         System.out.println("Error with playing sound.");
    //         ex.printStackTrace();
    ///    }
    //}
    //public String whatDoesPlayerOwn(int player)
    //{
    //	if(player == 1)
    //		return player1Things;
    //}
    
     
       
           
           
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        
          //still working on it
           System.out.println("in paintcomponent");
     
        try {
            imgy = ImageIO.read(new File("figure1.jpg"));
        } catch (IOException e) {
            System.out.println("figure2 not gettin");
        }



        try {
            img = ImageIO.read(new File("monopolyboard.jpg"));
        } catch (IOException e) {
            System.out.println("board not gettin");
        }



        try {
            imger = ImageIO.read(new File("figure2.jpg"));
        } catch (IOException e) {
            System.out.println("figure1 not gettin");
        }

        try {
            imgdice = ImageIO.read(new File("dice.jpg"));
        } catch (IOException e) {
            System.out.println("dice not gettin");
        }


        g.drawImage(img, 0, 0, 1250, 820, this); //board

        g.setColor(Color.BLUE);
        g.drawImage(imgy, x, y, 70, 70, this); //player 1
        g.drawRect(x, y, 70, 70);
        g.drawString("PLAYER 1 (Horse): ", 1265, 15);
        g.drawString("Current Location: " + places[(totalMoves1) % 40], 1265, 41);
        g.drawString("Money: $" + monay1, 1265, 54);

        g.setColor(Color.RED);
        g.drawImage(imger, mx, my, 70, 70, this); //player 2
        g.drawRect(mx, my, 70, 70);
        g.drawString("PLAYER 2 (Hat): ", 1265, 420);
        g.drawString("Current Location: " + places[(totalMoves2) % 40], 1265, 446);
        g.drawString("Money: $" + monay2, 1265, 459);
        g.setColor(Color.BLACK);
        g.fillRect(1250, 390, 360, 15);

        if (start) //build board at start
        {
            start = false;
        }
        g.setColor(Color.RED);




        /*  
         if(gameOver == true)
         {   
         if(score == 0)
         score = count;
         g.drawString("You Lost    Score: "+score, 400,400);
         }
         */
        if (space > 1) {
            space--;
            rollDice = true;
        }


        if (rollDice) {

            if (whoTurn == 1 || a == 1) //player 2 turn
            {

                if (diceimgtimer == 0) {
                    randomroll = ((int) (Math.random() * 10)) + 2;
                }
                moveSpaces2 = randomroll;
                diceimgtimer++;
                g.drawImage(imgdice, 400, 250, 500, 400, this);
                g.drawString("PLAYER 2 ROLLED: " + randomroll, 1265, 433);
                //System.out.println("diceimgtimer2: " + diceimgtimer);
                g.dispose();
                g.drawString("Current Location: " + places[(totalMoves2 + moveSpaces2) % 40], 1265, 446);    //display the place
                if (diceimgtimer > 6) {
                    g.dispose();

                    diceimgtimer = 0;
                    rollDice = false;
                    //add things that need to be drawn every time here
                    g.drawImage(img, 0, 0, 1250, 820, this); //board

                    g.setColor(Color.RED);

                    g.drawImage(imger, mx, my, 70, 70, this); //player 2
                    g.drawRect(mx, my, 70, 70);
                    g.drawString("PLAYER 2 (Hat): ", 1265, 420);
                    g.drawString("PLAYER 2 ROLLED: " + randomroll, 1265, 433);
                    g.drawString("Current Location: " + places[(totalMoves2 + moveSpaces2) % 40], 1265, 446);
                    g.drawString("Money: $" + monay2, 1265, 459);

                    g.setColor(Color.BLUE);
                    g.drawImage(imgy, x, y, 70, 70, this); //player 1
                    g.drawRect(x, y, 70, 70);
                    g.drawString("PLAYER 1 (Horse): ", 1265, 15);
                    g.drawString("Money: $" + monay1, 1265, 54);

                    whoTurn = 0;
                    g.setColor(Color.BLACK);
                    g.fillRect(1250, 390, 360, 15);
                    space = 0;
                    a = 0;
                    b = 1;
                    shouldWeCountSpaces = 2;
                    whoWent = 2;
                }
            }


            if (whoTurn == 0) //player 1 turn
            {
                if (diceimgtimer == 0) {
                    randomroll = ((int) (Math.random() * 10)) + 2;
                }
                moveSpaces = randomroll;
                diceimgtimer++;
                g.drawImage(imgdice, 400, 250, 500, 400, this);
                g.setColor(Color.BLUE);
                //if(diceimgtimer>2)
                g.drawString("PLAYER 1 ROLLED: " + randomroll, 1265, 28);

                //System.out.println("diceimgtimer: " + diceimgtimer);
                g.dispose();
                g.drawString("Current Location: " + places[(totalMoves1 + moveSpaces) % 40], 1265, 41);

                if (diceimgtimer > 6) {
                    g.dispose();

                    diceimgtimer = 0;
                    rollDice = false;
                    //add things that need to be drawn every time here
                    g.drawImage(img, 0, 0, 1250, 820, this); //board

                    g.setColor(Color.RED);
                    g.drawImage(imger, mx, my, 70, 70, this);//player 2
                    g.drawRect(mx, my, 70, 70);
                    g.drawString("PLAYER 2 (Hat): ", 1265, 420);
                    g.drawString("Money: $" + monay2, 1265, 459);

                    g.setColor(Color.BLUE);
                    g.drawImage(imgy, x, y, 70, 70, this);  //player 1
                    g.drawRect(x, y, 70, 70);
                    g.drawString("PLAYER 1 (Horse): ", 1265, 15);
                    g.drawString("PLAYER 1 ROLLED: " + randomroll, 1265, 28);
                    g.drawString("Current Location: " + places[(totalMoves1 + moveSpaces) % 40], 1265, 41);
                    g.drawString("Money: $" + monay1, 1265, 54);
                    whoTurn = 1;
                    g.setColor(Color.BLACK);
                    g.fillRect(1250, 390, 360, 15);
                    space = 0;
                    a = 1;
                    shouldWeCountSpaces = 1;
                    whoWent = 1;
                }


                if (whoTurn != 0) {
                    whoTurn = 1;
                }





            }


        }



        //make mve v2
        if (shouldWeCountSpaces == 1) //after rollDice (1 = player 1, 0 = not to at all, 2 = player 2)
        {
            totalMoves1 += moveSpaces;
            //totalMoves2 += moveSpaces2;
            moveSpaces = 0;
            moveSpaces2 = 0;
            x = roadx[totalMoves1 % 40];
            y = roady[totalMoves1 % 40];
            shouldWeCountSpaces = 0;
        }
        if (shouldWeCountSpaces == 2) //after rollDice (1 = player 1, 0 = not to at all, 2 = player 2)
        {
            totalMoves2 += moveSpaces2;
            //totalMoves1 += moveSpaces;
            moveSpaces2 = 0;
            moveSpaces = 0;
            mx = roadx[totalMoves2 % 40] - 2;
            my = roady[totalMoves2 % 40] - 2;
            shouldWeCountSpaces = 0;
        }
        

        //if()

        System.out.println("-----whoWent : " + whoWent);
        System.out.println("(totalMoves2+moveSpaces2)%40 : " + (totalMoves2 + moveSpaces2) % 40);
        System.out.println("(totalMoves1+moveSpaces)%40 : " + (totalMoves1 + moveSpaces) % 40);
        System.out.println("buy : " + buy);
        System.out.println("price of p1 square : " + prices[(totalMoves1 + moveSpaces) % 40 + 1]);
        System.out.println("price of p2 square : " + prices[(totalMoves2 + moveSpaces2) % 40 + 1]);
        System.out.println();
        if (prices[(totalMoves1 + moveSpaces) % 40 + 1] > 0) {
            System.out.println("place valid1");
        }
        if (whoWent == 2 && buy && prices[(totalMoves2 + moveSpaces2) % 40 + 1] <= monay2 && prices[(totalMoves2 + moveSpaces2) % 40 + 1] > 0 && (totalMoves2 + moveSpaces2) % 40 > 1
                && !player1Things.contains(places[(totalMoves2 + moveSpaces2) % 40]) && !player2Things.contains(places[(totalMoves2 + moveSpaces2) % 40]))//player 2 buy (if has enough money)
        {
            System.out.println("player 2 buy");
            monay2 -= prices[(totalMoves2 + moveSpaces2) % 40 + 1];
            player2Things.add(places[(totalMoves2 + moveSpaces2) % 40]);
            buy = false;
        }

        if (whoWent == 1 && buy && prices[(totalMoves1 + moveSpaces) % 40 + 1] <= monay1 && prices[(totalMoves1 + moveSpaces) % 40 + 1] > 0 && (totalMoves1 + moveSpaces) % 40 > 1
                && !player2Things.contains(places[(totalMoves1 + moveSpaces) % 40]) && !player1Things.contains(places[(totalMoves1 + moveSpaces) % 40]))//player 1 buy (if has enough money)
        {
            System.out.println("player 1 buy");
            monay1 -= prices[(totalMoves1 + moveSpaces) % 40 + 1];
            player1Things.add(places[(totalMoves1 + moveSpaces) % 40]);
            buy = false;

        }

        g.setColor(Color.BLUE);
        if (!player1Things.isEmpty()) //display player 1 things
        {
            g.drawString("Player 1 Ownes: ", 1265, 67);
            for (int i = 0; i < player1Things.size(); i++) {
                g.drawString(player1Things.get(i).toString(), 1265, 67 + (i + 1) * 13);
            }
        }

        g.setColor(Color.RED);
        if (!player2Things.isEmpty()) //display player 1 things
        {
            g.drawString("Player 2 Ownes: ", 1265, 472);
            for (int k = 0; k < player2Things.size(); k++) {
                g.drawString(player2Things.get(k).toString(), 1265, 472 + (k + 1) * 13);
            }
        }
        buy = false;

        //here we make the moves
         /*
         if(shouldWeCountSpaces == 1) //after rollDice (1 = player 1, 0 = not to at all, 2 = player 2)
         {
         System.out.println("\n in spaces move");
         	
         if(totalMoves1%40<10)
         {
         		
         //if(moveSpaces>
         if(moveSpaces+totalMoves1%40>10 ) //see if player needs to move up
         {
         int ups;
         y = y-93;
         if(totalMoves1%40==10)														//CHANGE TO X>EUE/....
         ups = 1; //see how many to move up
         else
         ups = (moveSpaces - ((int)(x/102)))-1;
         x = x- ((moveSpaces-(ups))*102); //see how many to move left
         y = y- ((ups)*68);//move up
         System.out.println("detected ups, ups = " + ups);
         }
         	
         totalMoves1 += moveSpaces;
         System.out.println("totalMoves1: " + totalMoves1);
         		
         if(x<200)   //dont move horizonal if it is in left column
         moveSpaces = 0;
         x = x- (moveSpaces*102);//move left
         }
         	
         else if(totalMoves1%40 >=10  && totalMoves1%40<21) //see if player needs up/ right 
         {
         int ups=0;
         y = y-93;
         //if(totalMoves1%40==10)														//CHANGE TO X>EUE/....
         //ups = 1; //move up
         			
         if(totalMoves1%40==20 || totalMoves1%40+moveSpaces>20) //WHAT IF ROLL TWICE THIS CHANGEYYY------------
         x+=30;
         		
         int rights = 0;
         if(totalMoves1%40+moveSpaces>20)
         {
         rights = moveSpaces - ups;
         x = x + (rights*102);
         }
         		
         if(totalMoves1%40+moveSpaces>20)	
         ups = moveSpaces-rights;
         else
         ups = moveSpaces;
         y = y- ((ups)*68);
         		
         System.out.println("rights = " + rights);
         System.out.println("detected ups, ups = " + ups);
         totalMoves1 += moveSpaces;
         System.out.println("totalMoves1: " + totalMoves1);
         }
         	
         else if(totalMoves1%40>19 && totalMoves1%40<30)
         {
         		
         }
         		
         		
         shouldWeCountSpaces = 0;
         	
         } */
 

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //invisible = false;

        if (x < 0) //These are for the boundaries//
        {
            velX = 0;
            x = 0;
        }

        if (x > 1550) {
            velX = 0;
            x = 1550;
        }

        if (y < 0) {
            velY = 0;
            y = 0;
        }

        if (y > 800)//make sure left and right walls are > instead of <//
        {
            velY = 0;
            y = 800;
        }

        //x = x + 2*velX;
        //y = y + 2*velY;
        repaint();
    }

    @Override
     public void keyPressed(KeyEvent e) 
    {
        int c = e.getKeyCode();

        if (c == KeyEvent.VK_SPACE) {
            rollDice = true;
            space = 25;
            System.out.println("space bar pressed");
            
        }
        if (c == KeyEvent.VK_SHIFT) {
            if (diceimgtimer == 0 || diceimgtimer == 1) {
                buy = true;
            }
            System.out.println("shift pressed-------------------------------------{{{{{");
        }

    }

    public void keyTyped(KeyEvent e){
    }

    @Override
    public void keyReleased(KeyEvent e) {
        velX = 0;
        velY = 0;


    }
}