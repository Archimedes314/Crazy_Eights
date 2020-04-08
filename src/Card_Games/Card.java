package Card_Games;

public class Card {
    /*Shortcuts for referencing card suits and special values*/
    public static final int Ace=1;
    public static final int Jack=11;
    public static final int Queen=12;
    public static final int King=13;
    public static final int Clubs= 0;
    public static final int Diamonds=1;
    public static final int Hearts =2;
    public static final int Spades=3;
    private int suit;
    private int value;

    public Card(int suit, int value) {
        this.suit=suit;
        this.value=value;
    }

    public int getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }
    /*Converts suit to string*/
    private String convertSuit(){
        switch(suit){
            case Clubs:
                return "Clubs";

            case Diamonds:
                return "Diamonds";
            case Hearts:
                return "Hearts";
            case Spades:
                return "Spades";
            default:
                return "No suit specified";

        }


    }

    /*Converts value to string*/
    private String convertValues(){
        switch(value){
            case Ace:
                return "Ace";
            case Jack:
                return "Jack";
            case Queen:
                return "Queen";
            case King:
                return "King";
            default:
                return Integer.toString(value);
        }
    }
    /*Returns suit and value of card in format (value) of (suit)*/
    public String CardToString(){
        return convertValues()+" of "+convertSuit();
    }

}
