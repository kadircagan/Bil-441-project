import java.util.Random;
import java.util.ArrayList;

public class App {

    static String[] deck = new String[52];
    static playerObj mainPlayer;
    static playerObj player1;
    static playerObj player2;
    static playerObj player3;
    

    public static void main(String[] args) throws Exception {
        setGame();
        startGame();
    }

    public static void setGame(){
        createDeck();
        shuffleDeck();
        mainPlayer = new playerObj(deck);
        player1 = new playerObj(deck);
        player2 = new playerObj(deck);
        player3 = new playerObj(deck);
        dealCards();

    }
    public static void startGame(){
        gameLoop();
    }


    public static void gameLoop() {
        ArrayList<String> turnCards = new ArrayList<String>();
        int turn=0;
        for(int i=0;i<13;i++){
            //System.out.println(mainPlayer.getCards());  
            String mpMove= ""; 
            String player1Move = "";
            String player2Move = "";
            String player3Move = "";
            printAllDecks();
            String mainCard="";
            turnCards = new ArrayList<String>();

            if(turn==0){//önceki eli player0 kazandi
            mpMove = mainPlayer.play("",turnCards);
            mainCard=mpMove;
            player1Move = player1.play(turnCards);
            player2Move = player2.play(turnCards);
            player3Move = player3.play(turnCards);
            }else if(turn==1){//önceki eli player 1 kazandi
            player1Move = player1.play(turnCards);
            mainCard=player1Move;
            player2Move = player2.play(turnCards);
            player3Move = player3.play(turnCards);
            mpMove = mainPlayer.play("",turnCards);
            }else if(turn==2){//önceki eli player 2 kazandi
            player2Move = player2.play(turnCards);
            mainCard=player2Move;
            player3Move = player3.play(turnCards);
            mpMove = mainPlayer.play("",turnCards);
            player1Move = player1.play(turnCards);
            }else if(turn==3){//önceki eli player 3 kazandi
            player3Move = player3.play(turnCards);
            mainCard=player3Move;
            mpMove = mainPlayer.play("",turnCards);
            player1Move = player1.play(turnCards);
            player2Move = player2.play(turnCards);
            }
            System.out.println(mpMove + " "+ player1Move + " "+player2Move + " "+player3Move + " " );
            deleteFromAllMemory(mpMove,player1Move,player2Move,player3Move);
            turn = whoWins(mainCard,mpMove,player1Move,player2Move,player3Move,turn);
            System.out.println(turn);

        }
    }



    public static void createDeck() {
        String[] suit = "Kupa-,Sinek-,Maca-,Karo-".split(",");
        String[] vals = "2,3,4,5,6,7,8,9,10,J,Q,K,A".split(",");
      
        int deckTrace=0;
        for(int i=0;i<suit.length;i++)
           for(int y=0;y<vals.length;y++){
            deck[deckTrace]=(suit[i] + vals[y]);
            deckTrace++;  
           }
        printDeck(deck);
   
    }
    public static void shuffleDeck() {
        Random rand = new Random(); 
        int upperbound =52;
        int firstRandom = 0;
        int secondRandom = 0;

        for (int i =0; i<3000;i++){
        firstRandom = rand.nextInt(upperbound); 
        secondRandom = rand.nextInt(upperbound);
        String temp = deck[firstRandom];
        deck[firstRandom]=deck[secondRandom];
        deck[secondRandom] =temp;
        }
        printDeck(deck);

    }
    public static void printDeck(String[] printingDeck){
        for (String element : printingDeck)
        System.out.print(element + " ");
        System.out.println();
    }
    public static void dealCards(){
        for(int i=0; i<52;i=i+4){
            mainPlayer.addCardToDeck(deck[i]);
            player1.addCardToDeck(deck[i+1]);
            player2.addCardToDeck(deck[i+2]);
            player3.addCardToDeck(deck[i+3]);

        }
   

    }
    public static void printAllDecks() {
        System.out.println(mainPlayer.getCards());
        System.out.println(player1.getCards());
        System.out.println(player2.getCards());
        System.out.println(player3.getCards());
    }

    public static void deleteFromAllMemory(String mpMove, String player1Move, String player2Move, String player3Move) {
        for(int i=0; i<52;i++){
            if(deck[i]!=null && (deck[i].equals(mpMove)||deck[i].equals(player1Move)||deck[i].equals(player2Move)||deck[i].equals(player3Move) )){
                deck[i]=null;
            }
        }
    }
     
    private static int whoWins(String mainCard, String mpMove, String player1Move, String player2Move, String player3Move,int turn) {
        String[] vals = "-2,-3,-4,-5,-6,-7,-8,-9,10,-J,-Q,-K,-A".split(",");
        String[] array = new String[]{mpMove,player1Move,player2Move,player3Move};
        System.out.println(mainCard);
        for(int i=12;i>0;i--){
            
            for(int y=0;y<4;y++){
                if((array[y].indexOf(mainCard.substring(0,3))!=-1)&& array[y].substring(array[y].length()-2).equals(vals[i]) ){
                    return y;
                }
            }
        }
        System.out.println("code has beg");
        
        return turn;
    }


    
    
}
