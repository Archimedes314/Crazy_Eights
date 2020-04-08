package Card_Games;

import java.util.Scanner;

public class Main {

/*Checks if someone has won (according to standard Crazy Eights rules*/
    public static boolean hasWinner(Hand[] h){
        int WinScore= 50*h.length;
        boolean winnerDetected=false;
        for(int i=0; i<h.length;i++){
            if(h[i].getPlayerScore()>=WinScore){
                winnerDetected=true;
            }

        }
        return winnerDetected;


    }
/*Returns player that has no cards left*/
    public static Hand getEmptyPlayer(Hand[] h){
        Hand temp=null;
        for(int i=0; i<h.length;i++){
            if(h[i].IsEmpty()){
                temp=h[i];
            }

        }
        return temp;




    }
/*Returns Player with highest score*/
    public static Hand MaxPlayerScore(Hand[] h){
        Hand highestScorer=h[0];
        int highestScore=h[0].getPlayerScore();
        for(int i=1;i<h.length;i++){
            if(h[i].getPlayerScore() > highestScore){
                highestScore=h[i].getPlayerScore();
                highestScorer=h[i];
            }


        }
        return highestScorer;


    }
    /*Checks if someone has no cards left*/
    public static boolean hasEmptyPlayer(Hand[] h){
        boolean emptyPlayerDetected=false;
        for(int i=0; i<h.length;i++){
            if(h[i].IsEmpty()){
                emptyPlayerDetected=true;
            }
        }
        return emptyPlayerDetected;



    }


/*Returns player with lowest score*/

    public static Hand PlayerMinCardScore(Hand[] h){
        Hand MinScorer=h[0];
        int MinScores=h[0].GetCardScore();
        for(int i=1;i<h.length;i++){
            if(h[i].GetCardScore()<MinScores){
                MinScores=h[i].GetCardScore();
                MinScorer=h[i];
            }
        }
        return MinScorer;


    }





    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.Shuffle();
        Scanner input = new Scanner(System.in);
        System.out.println("How many people are playing?(2-7)");
        int NumPlayers= input.nextInt();
        Hand[] Players= new Hand[NumPlayers];
        int NumStartCards;
        if(NumPlayers==2){
            NumStartCards=7;

        }
        else{
            NumStartCards=5;
        }


        for(int i=0;i<NumPlayers;i++){
            if(i==0){
                System.out.println("Enter name of dealer:");
            }
            else{
                System.out.println("Enter name of player "+i+":");
            }
            Players[i]= new Hand(input.next());
            deck.DealToHand(Players[i],NumStartCards);



        }
        TurnController controller=new TurnController(deck);
        Card TopJunkCard=deck.dealCard();
        deck.AddToJunk(TopJunkCard);
        int currentTurn=0;

        while(!hasWinner(Players)){

            if(currentTurn>NumPlayers-1){
                currentTurn=0;
            }



            if(deck.IsEmpty()){
                Hand MinScorePlayer= PlayerMinCardScore(Players);
                System.out.println("Deck is empty!!!, player with lowest card score is "+MinScorePlayer.getPlayerName()+" with card score of "+MinScorePlayer.GetCardScore());
                Card CurrentTopJunkCard=deck.ReturnTopJunkCard();

                for(int i =0;i<NumPlayers;i++){
                    int dif=Players[i].GetCardScore()-MinScorePlayer.GetCardScore();
                    MinScorePlayer.UpdateScore(dif);
                    System.out.println(MinScorePlayer.getPlayerName()+" has received "+dif+"pts from "+Players[i].getPlayerName());


                }



                deck.PartialResupplyDeck(Players,CurrentTopJunkCard);
            }

            else if(hasEmptyPlayer(Players)){
                Hand EmptyPlayer= getEmptyPlayer(Players);
                System.out.println(EmptyPlayer.getPlayerName()+" has emptied their hand!!!");
                Card currentTopCard=deck.ReturnTopJunkCard();

                for(int i =0; i<NumPlayers;i++){

                    if(Players[i]!=EmptyPlayer) {
                        EmptyPlayer.UpdateScore(Players[i].GetCardScore());
                        System.out.println(EmptyPlayer.getPlayerName()+" has received "+Players[i].GetCardScore()+"pts from "+Players[i].getPlayerName());
                    }

                    Players[i].EmptyAll(deck);

                }
                deck.EmptyDeckFailSafe(currentTopCard);

                for(int i=0;i<NumPlayers;i++){


                    deck.DealToHand(Players[i],NumStartCards);



                }



            }
            else{


                controller.initiateTurn(Players[currentTurn],deck.ReturnTopJunkCard());
                currentTurn++;




            }





        }
        if (hasWinner(Players)) {
            System.out.println(MaxPlayerScore(Players).getPlayerName()+" has won the game!!! (with a score of "+MaxPlayerScore(Players).getPlayerScore()+"pts)");



        }













	// write your code here
    }
}
