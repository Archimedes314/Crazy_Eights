package Card_Games;

import java.util.Scanner;

public class TurnController {

    private Deck TargetDeck;

    private String RequestAction="What do you want to do?(A or B)\nA:Put down card\nB:Pick up card from deck";
    private String SpecialEightRequest="You have put down an eight!!!, which suit must the next player put down?(C,D,H or S)\nC:Clubs\tD:Diamonds\tH:Hearts\tS:Spades";


    public TurnController(Deck d) {
        TargetDeck=d;
    }




/*Initiates main user interaction involved with Crazy Eights, carries out a players' turn(using their hand as input), displays their cards, allows player to make choices regarding their cards and adds the card they put down to the junkPile*/
    public void initiateTurn(Hand h,Card TopCard){

        System.out.println("Current turn: "+h.getPlayerName());
        h.HandToString();
        System.out.println("Current top card:" +TopCard.CardToString());
        Scanner input = new Scanner(System.in);
        System.out.println(RequestAction);
        String choiceAction= input.next().toUpperCase();

        while((!choiceAction.equals("A"))&(!choiceAction.equals("B"))){
            System.out.println("Please choose between A and B only here");
            choiceAction=input.next().toUpperCase();
        }

        while (choiceAction.equals("B")){
            if(!TargetDeck.IsEmpty()){
                h.AddCard(TargetDeck.dealCard());
                h.HandToString();
                System.out.println("Current top card:" +TopCard.CardToString());
                System.out.println(RequestAction);
                choiceAction= input.next().toUpperCase();
            }
            else{
                break;
            }


        }

        if(choiceAction.equals("A")){
            System.out.println("Choose the number of the card you wish to put down");
            int cardIndex=input.nextInt();
            Card DroppedCard= h.getCard(cardIndex);

            while((!(DroppedCard.getValue()==8))&(!(DroppedCard.getValue()==TopCard.getValue()))&(!(DroppedCard.getSuit()==TopCard.getSuit()))){
                System.out.println("You cannot place that card, your card must match the top card in suit or value or be an eight");
                System.out.println("Choose the number of the card you wish to put down");
                cardIndex=input.nextInt();
                DroppedCard=h.getCard(cardIndex);

            }

            if(DroppedCard.getValue()==8){
                System.out.println(SpecialEightRequest);
                String suitResponse=input.next().toUpperCase();
                switch (suitResponse){
                    case "C":
                        TargetDeck.AddToJunk(new Card(0,8));
                        break;
                    case "D":
                        TargetDeck.AddToJunk(new Card(1,8));
                        break;
                    case "H":
                        TargetDeck.AddToJunk(new Card(2,8));
                        break;
                    case "S":
                        TargetDeck.AddToJunk(new Card(3,8));
                        break;
                }
                h.RemoveCard(cardIndex);

            }
            else if((DroppedCard.getSuit()==TopCard.getSuit())|| (DroppedCard.getValue()==TopCard.getValue())){
                TargetDeck.AddToJunk(DroppedCard);
                h.RemoveCard(cardIndex);




            }



        }








    }
}
