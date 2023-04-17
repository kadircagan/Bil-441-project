import java.util.ArrayList;

public class KartOyunuYapayZeka {
    private int maxDerinlik;
    public ArrayList<String> cards;
    public ArrayList<String> takenDeck;
    public ArrayList<String> turnsCardComed;
    int awardValue =0;

        public KartOyunuYapayZeka(ArrayList<String> takenDeck,ArrayList<String> cards, int maxDerinlik,ArrayList<String> turnsCardComed) {
        this.takenDeck= (ArrayList<String>) takenDeck.clone();
        this.cards= (ArrayList<String>) cards.clone();
        this.turnsCardComed= (ArrayList<String>) turnsCardComed.clone();
        this.maxDerinlik = maxDerinlik;
        
    }
        public int go(){
            int end=-1;
            if(turnsCardComed.size()==0){
               end = minimaxFirstCard(0, new ArrayList<String>(), cards);
            }
            else if(turnsCardComed.size()==1){
                
                end = minimaxOtherCard(0, turnsCardComed, cards,3,turnsCardComed.get(0));
             }
            else if(turnsCardComed.size()==2){
                
                end = minimaxOtherCard(0, turnsCardComed, cards,2,turnsCardComed.get(0));
             }
             //System.out.println();

            return end;
        }


    // Minimax algoritmasi ile en iyi hamleyi seçer
  /*  private int[] minimax(KartOyunuDurumu durum, int derinlik, int alfa, int beta, boolean maksimizeEdiyor) {
        if (durum.bittiMi() || derinlik == 0) {
            return new int[]{durum.skor(), -1};
        }

        if (maksimizeEdiyor){
        int enIyiHamle = -1;
        int enIyiSkor = Integer.MIN_VALUE;
        ArrayList<Integer> olasiHamleler = durum.olasiHamleler();
        for (int i = 0; i < olasiHamleler.size(); i++) {
            int hamle = olasiHamleler.get(i);
            KartOyunuDurumu yeniDurum = durum.hamleYap(hamle);
            int[] sonuc = minimax(yeniDurum, derinlik - 1, alfa, beta, false);
            int skor = sonuc[0];
            if (skor > enIyiSkor) {
                enIyiSkor = skor;
                enIyiHamle = hamle;
            }
            alfa = Math.max(alfa, enIyiSkor);
            if (beta <= alfa) {
                break;
            }
        }
        return new int[]{enIyiSkor, enIyiHamle};
    }
    return new int[]{};
} */
    public int awardCalc(int awardValue,String card){
        String[] vals = "-2,-3,-4,-5,-6,-7,-8,-9,10,-J,-Q,-K,-A".split(",");

        for(int i=12;i>=0;i--){

                if(card.substring(card.length()-2).equals(vals[i])){
                    float x =(float)(0.5+i);
                    return (int)((x*1.25)*awardValue);
                
            }
        }
        return 0;
    }
    public int minimaxFirstCard(int nowPlaying, ArrayList<String> allCards, ArrayList<String> gameCards){
        awardValue++;
        ArrayList<String> allCardsCurrent = (ArrayList<String>) allCards.clone();//oyunda oynanan kartlar listesi
        ArrayList<String> gameCardsCurrent = (ArrayList<String>) gameCards.clone();//tüm kartlar listesi
        if(nowPlaying == 4){
            int whoWins= whoWins(allCards.get(0),allCards.get(0),allCards.get(1),allCards.get(2),allCards.get(3),0);
            if(whoWins == 0)return-50;
            else return 0;
        }
        if(nowPlaying ==0){//ilk kart atılıyor
            int maximumValue = Integer.MIN_VALUE;
            int maximumCard = -1; 
            for(int i=0;i<takenDeck.size();i++){
                allCardsCurrent.add(takenDeck.get(i));
                int index = gameCardsCurrent.indexOf(takenDeck.get(i));
                gameCardsCurrent.remove(index);
                int currentCardScore = minimaxFirstCard(nowPlaying+1, allCardsCurrent,gameCardsCurrent);
                int awardCalculated = awardCalc(awardValue,takenDeck.get(i));
                currentCardScore=currentCardScore+awardCalculated;
                awardValue=0;

              //  System.out.print(currentCardScore+" ");
                if(currentCardScore > maximumValue){
                    maximumValue =currentCardScore;
                    maximumCard = i;
                }
                gameCardsCurrent.add(index,takenDeck.get(i));
                allCardsCurrent.remove(0);
            }
            return maximumCard;
        }
        else{// diğer oyuncular atıyor
             
            int minimumValue = 0;
            
            for(int i=0; i<gameCardsCurrent.size();i++){
                allCardsCurrent.add(gameCardsCurrent.get(i));//kartı oynadı
                String playedCard = gameCardsCurrent.get(i);
                gameCardsCurrent.remove(i);
                minimumValue = minimumValue + minimaxFirstCard(nowPlaying+1, allCardsCurrent, gameCardsCurrent);
                gameCardsCurrent.add(i,playedCard);
                allCardsCurrent.remove(allCardsCurrent.size()-1);
            }
            return minimumValue;
        }

       
    }




    public int minimaxOtherCard(int nowPlaying, ArrayList<String> allCards, ArrayList<String> gameCards,int nowPlayingBool, String mainCard){//3 ise 1 tane atıldı /////  2 ise 2 tane atıldı
        awardValue++;
        ArrayList<String> allCardsCurrent = (ArrayList<String>) allCards.clone();//oyunda oynanan kartlar listesi
        ArrayList<String> gameCardsCurrent = (ArrayList<String>) gameCards.clone();//tüm kartlar listesi
        if(nowPlaying == nowPlayingBool){
            int whoWins= whoWins(mainCard,allCards.get(0),allCards.get(1),allCards.get(2),allCards.get(3),0);
            if(whoWins == 0)return-50;
            else return 0;
        }
        if(nowPlaying ==0){//ilk kart atılıyor
            int maximumValue = Integer.MIN_VALUE;
            int maximumCard = -1; 
            for(int i=0;i<takenDeck.size();i++){
                
                allCardsCurrent.add(0,takenDeck.get(i));
                int index = gameCardsCurrent.indexOf(takenDeck.get(i));
                gameCardsCurrent.remove(index);
                int currentCardScore = minimaxOtherCard(nowPlaying+1, allCardsCurrent,gameCardsCurrent,nowPlayingBool,mainCard);
                int awardCalculated = awardCalc(awardValue,takenDeck.get(i));
                currentCardScore=currentCardScore+awardCalculated;
                awardValue=0;
                awardValue=0;
              //  System.out.print(currentCardScore+" ");
                if(currentCardScore > maximumValue){
                    maximumValue =currentCardScore;
                    maximumCard = i;
                }
                gameCardsCurrent.add(index,takenDeck.get(i));
                allCardsCurrent.remove(0);
            }
            return maximumCard;
        }
        else{// diğer oyuncular atıyor
             
            int minimumValue = 0;
            
            for(int i=0; i<gameCardsCurrent.size();i++){
                allCardsCurrent.add(gameCardsCurrent.get(i));//kartı oynadı
                String playedCard = gameCardsCurrent.get(i);
                gameCardsCurrent.remove(i);
                minimumValue = minimumValue + minimaxOtherCard(nowPlaying+1, allCardsCurrent, gameCardsCurrent,nowPlayingBool,mainCard);
                gameCardsCurrent.add(i,playedCard);
                allCardsCurrent.remove(allCardsCurrent.size()-1);
            }
            return minimumValue;
        }

       
    }



    private static int whoWins(String mainCard, String mpMove, String player1Move, String player2Move, String player3Move,int turn) {
        String[] vals = "-2,-3,-4,-5,-6,-7,-8,-9,10,-J,-Q,-K,-A".split(",");
        String[] array = new String[]{mpMove,player1Move,player2Move,player3Move};
        for(int i=12;i>0;i--){
            
            for(int y=0;y<4;y++){
                if((array[y].indexOf(mainCard.substring(0,3))!=-1)&& array[y].substring(array[y].length()-2).equals(vals[i]) ){
                    return y;
                }
            }
        }
        
        return turn;
    }
}
