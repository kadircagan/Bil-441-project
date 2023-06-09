import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class playerObj {
    private ArrayList<String> cards = new ArrayList<String>();
    String[] deck ;
    String nameofPLayer= "";
    public  playerObj(String[] deckNew, String name){
        deck =deckNew;
        nameofPLayer = name;
    }

    void addCardToDeck(String card){
        cards.add(card);
        Collections.sort(cards);
    }

    public ArrayList<String> getCards() {
        return cards;
    }
    public static void deleteFromMem(String Move) {
        for(int i=0; i<52;i++){
            if(App.deck[i]!=null && App.deck[i].equals(Move)){
                App.deck[i]=null;
            }
        }
        App.deckList = App.arraytoList();
    }

    public String play(String cardtoPlay,ArrayList<String> turnCards){
        Scanner sc= new Scanner(System.in);
        boolean isValid =false;
        String returnStr="";
        int i = 0;
        while(true){
            System.out.print("Oynamak istediginiz karti yaziniz: ");  
            String str= sc.nextLine();
            for (i = 0; i < cards.size(); i++) {
                if(cards.get(i).equals(str)){
                    isValid = true;
                    returnStr=str;
                    break;
                }
        }
            if(isValid){
                cards.remove(i);
                Collections.sort(cards);
                break;

            }else{
                System.out.println("Hatali oynadiniz lutfen tekrar oynayin");
                continue;
            }
        }
    deleteFromMem(returnStr);
    turnCards.add(returnStr);
    return returnStr;
    }

    
    public String play(ArrayList<String> turnCards){
        if(cards.size()==1)
            return cards.get(0);
        int playingCard = 0;
        String stringNameOfCardPlayed;
        ArrayList<String> newdeck = new ArrayList<String>();
        if(turnCards.size()==0){
            stringNameOfCardPlayed=thinktoPlay(cards,false,turnCards);
        }else{
            String mainCard = turnCards.get(0);
            mainCard = mainCard.substring(0,4);
            for (String card : cards) {
                if(card.indexOf(mainCard)!= -1){
                    newdeck.add(card);
                }
                
            }
            //System.out.println(newdeck);
            stringNameOfCardPlayed = thinktoPlay(newdeck,true,turnCards);
        }
        int i =0;
        for (String card : cards) {
            if(card.equals(stringNameOfCardPlayed)){
                playingCard = i;
            }
            i=i+1;
        }
        
        String choosenCard = cards.get(playingCard);
        turnCards.add(choosenCard);
        deleteFromMem(choosenCard);
        System.out.println( nameofPLayer+ " || " + choosenCard + " || attı.");
        cards.remove(playingCard);
        Collections.sort(cards);

        return choosenCard;
    }
    String thinktoPlay(ArrayList<String> deck,boolean isNewDeck,ArrayList<String> turnCardsComed){//ai code will be implemented
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {}// TODO Auto-generated catch blocke.printStackTrace(); }
        if(deck.size()==1)
            return deck.get(0);
        String playedOne="kadiriiriririirir";
        int cardPlaceThatPlayed;
        ArrayList<String> turnCards =(ArrayList<String>) turnCardsComed.clone();
        String[] vals = "-2,-3,-4,-5,-6,-7,-8,-9,10,-J,-Q,-K,-A".split(",");
        if(!isNewDeck){//yeni deck yapmadıysak ve ilk atan degilsek  elimizdeki en büyük kartı elimizden çıkarmak isteriz
            if(turnCardsComed.size()!=0)
            for (int i=vals.length-1;i>0;i--) {
                for (String card : cards) {
                    if(card.indexOf(vals[i])!=-1){
                        return card;
                    }
                }
                
            }
        }else{//isnewdeck 
          int highestCard =-1;
          //TODO
          //buraya girdim ama atacak kartım yok o zaman elimde atabilecegim en yüksek kartı atmalıyım  fakat esitlerde hangisini atmalıyım ai için tanıtılabilir
          if(deck.size()==0)
          for (int i=vals.length-1;i>0;i--) {
              for (String card : cards) {
                  if(card.indexOf(vals[i])!=-1){
                      return card;
                  }
              }
            }
          //


            if(turnCards.size()!=0){

                for (int a=0;a<turnCards.size(); a++) {
                    if(!(turnCards.get(a).substring(0,3).equals(turnCards.get(0).substring(0,3)))){
                        turnCards.remove(a);
                        a--; 
                    }
                    a++;  
                }

                boolean breakeFromSecondfor =false;
                for (int i=vals.length-1;i>0;i--) {
                    for (String card : turnCards) {
                        if(card.indexOf(vals[i])!=-1){
                            highestCard = i;
                            breakeFromSecondfor = true;
                        }
                    }    
                    if(breakeFromSecondfor)
                    break;
                }

                for (int i=highestCard;i>=0;i--) {
                    for (String card : deck) {
                        if(card.indexOf(vals[i])!=-1){
                            return card;
                        }
                    }  
                }

                if(turnCardsComed.size()==3)
                for (int i=vals.length-1;i>=0;i--) {
                    for (String card : deck) {
                        if(card.indexOf(vals[i])!=-1){
                            return card;
                        }
                    }  
                }
                

            }
        }                                                      //eğerki isnewdeck ise ai oynamalı değil ise elindeki en yüksek kartı atmalsın
                                                                     //aynı zamanda son oyuncuysan ve yeni deck ise atılandan küçük olan en büyük kartı 
                                                                     //atmalısın ve eğerki son oyuncuysan ve 

        if(!isNewDeck){//eger ilk atıyosam 
          //  System.out.println("R1");
            cardPlaceThatPlayed = thinktoPlayAi(cards,0,turnCardsComed);
            playedOne = cards.get(cardPlaceThatPlayed);
            return playedOne;
        }
        //benden öncekilerden düşük atamadıysam  kupa10 kupa 2 varken r2ye girmesinin sebebi hangisini atıcagından emin deil
           // System.out.println("R2");
            cardPlaceThatPlayed = thinktoPlayAi(deck,1,turnCardsComed);
            playedOne = deck.get(cardPlaceThatPlayed);
        
        return playedOne;
    }

    int thinktoPlayAi(ArrayList<String> takenDeck, int state,ArrayList<String> turnCardsComed){
        if(state ==0){
        KartOyunuYapayZeka decicionObject = new KartOyunuYapayZeka(takenDeck,App.deckList ,4,turnCardsComed);
        int pref = decicionObject.go();
        return pref;
    }if(state==1){
        KartOyunuYapayZeka decicionObject = new KartOyunuYapayZeka(takenDeck,App.deckList ,4,turnCardsComed);
        int pref = decicionObject.go();
        return pref;

     //   Random rand = new Random(); 
     //   int firstRandom = rand.nextInt(takenDeck.size()); 
     //   return firstRandom;
    }
        return -1;
    }



    
}
