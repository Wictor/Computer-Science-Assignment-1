import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class uppgift1 {
    private static String[] stringArray = new String[1];
    private static boolean letComputerLoop = false;
    private static Scanner in = new Scanner(System.in);

    //Låt användaren välja om programmet ska köras manuellt eller datorn ska köra programmet valt antal gånger.
    public static void main(String[] args) {
        int choice;
        boolean acceptedInput = false;

        while(!acceptedInput) {
            System.out.println("(1). Kör programmet manuellt.\n(2). Välja hur många inputs datorn ska göra.");
            System.out.print("Ditt val: ");
            try{
                if ((choice = in.nextInt()) == 1 || choice == 2) {
                    if(choice == 1){
                        manualDrive();
                    }else {
                        System.out.print("Hur många inputs vill du att datorn ska göra: ");
                        int amountOfInputs = in.nextInt();
                        letComputerLoop = true;
                        createCharArray(amountOfInputs);
                    }
                    acceptedInput = true;
                }
                else {
                    System.out.println("Du kan enbart skriva in 1 eller 2");
                }
            }catch (InputMismatchException notANumber) {
                System.out.println("Du kan enbart skriva in 1 eller 2");
                in.next();
            }
        }
    }

    //Kallar på metoden detectTInArray med charArrayen som argument.
    //Kallar på compareArrays med båda kontrollerade charArrayerna som argument.
    //Kallar på metoden createCharArray med "0" som argument då vi nu kommer köra denna metod manuellt.
    private static void manualDrive(){
        char[] charArray1 = {'s', 't', 'r', 'i', 'n', 'g'};
        char[] charArray2 = {'s', 'i', 'n', 'g'};

        System.out.println("Datalogi Uppgift 1:");
        System.out.print("charArray1: ");
        charArray1 = detectTInArray(charArray1);
        System.out.print("charArray2: ");
        charArray2 = detectTInArray(charArray2);
        System.out.print("\nd) ");
        compareArrays(charArray1, charArray2);
        createCharArray(0);
    }
    //Kontrollerar först om deverse array innehåller ett 't', retunerar i så fall true annars false.
    //Därefter byter vi ut alla 'r' i deverse charArray till 't'.
    //Kallar på metoden toUpperCase med argumentet charArray, som retunerar charArrayen med alla 't' som versaler.
    //Kallar på metoden removeDuplicates med argumentet charArray, som retunerar charArrayen utan dubbletter.
    //Retunerar den nya arrayen.
    private static char[] detectTInArray(char[] charArray) {
        Boolean flag = false;
        for (char character : charArray) {
            if (character == 't') {
                flag = true;
            }
        }
        if(flag){
            System.out.println("true");
        }else{
            System.out.println("false");
        }
        for (int i = 0; i < charArray.length; i++) {
            if(charArray[i] == 'r'){
                charArray[i] = 't';
            }
        }
        System.out.print("a) ");
        System.out.println(charArray);
        toUpperCase(charArray);
        System.out.print("b) ");
        System.out.println(charArray);
        System.out.print("c) ");
        charArray = removeDuplicates(charArray);
        System.out.println(charArray);
        return charArray;
    }
    //Tar emot en charArray & retunerar tillbaka den med alla 't' i versaler.
    private static void toUpperCase(char[] charArray){
        for(int i = 0; i < charArray.length; i++){
            if(charArray[i] == 't'){
                charArray[i] = Character.toUpperCase(charArray[i]);
            }
        }
    }
    //Kontrollerar om det finns fler än en av samma char i arrayen & tar i så fall bort alla utom den första. Retunerar arrayen utan dubbletter.
    private static char[] removeDuplicates(char[] charArray){
        String newString = "";
        char[] newCharArray;

        for(int i = 0; i < charArray.length; i++){
            char tempChar = charArray[i];
            for(int x = 0; x < charArray.length; x++){
                if(tempChar == charArray[x] && x != i){
                    charArray[x] = ',';
                }
            }
            if(charArray[i] != ','){
                newString += charArray[i];
            }
        }
        newCharArray = newString.toCharArray();
        return newCharArray;
    }
    //Kontrollerar om de båda metoderna ser likadana ut, skriver ut ett svar i konsolen beroende på.
    private static void compareArrays(char[] charArray1, char[] charArray2){
        if(Arrays.equals(charArray1, charArray2)){
            System.out.println("Array 1 and Array 2 are identical.");
        }else{
            System.out.println("Array 1 and Array 2 are NOT identical.");
        }
    }
    //Om användaren valt att låta datorn göra jobbet körs denna metod med parametern som användaren valt antal gånger den vill byta ut värden i arrayen.
    //Om användaren valt att låta datorn göra jobbet är letComputerLoop true och denna if sats körs.
    //Om användaren valt att köra programmet manuellt kommer programmet skippa denna if sats & gå in i else, in i while loopen & låta anvädaren mata in de tre första värderna.
    //Därefter har användaren möjlighet att byta ut värdena ett värde i taget, med ett nytt värde på den plats man vill 1[0], 2[1] eller 3[2].
    //Metoden kommer oavsätt om datorn eller anvädaren jobbar kalla på increaseStringArray med parametern den senaste charen man satt in i arrayen i slutet på varje loop.
    //Skickar användaren in ett tomt värde avslutas loopen.
    private static void createCharArray(int amountOfInputs){
        String checkInputIfEmpty;
        int indexToReplace = 1;
        char[] inputCharArray = new char[3];

        if(letComputerLoop){
            String chars = "abcdefghijklmnopqrstuvxyzåäö1234567890";
            Random rand = new Random();

            for (int i = 0; i < amountOfInputs; i++){
                char randomChar = chars.charAt(rand.nextInt(chars.length()));
                int index = rand.nextInt(3);

                inputCharArray[index] = randomChar;

                System.out.print("e) ");
                System.out.println(inputCharArray);
                increaseStringArray(inputCharArray);
            }
        }
        else {
            while (true) {
                boolean inputAccepted = false;
                try {
                    System.out.print("Ett nytt värde: ");
                    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                    if (((checkInputIfEmpty = input.readLine()) != null && checkInputIfEmpty.length() != 0) && checkInputIfEmpty.length() == 1 && !checkInputIfEmpty.equals(" ")) {

                        if (inputCharArray[2] != '\u0000') {
                            while (!inputAccepted) {
                                System.out.println("Välj vilken indexplats 1-3 du vill byta ut: ");
                                try {
                                    if ((indexToReplace = in.nextInt()) > 0 && indexToReplace <= 3) {
                                        inputAccepted = true;
                                    } else {
                                        System.out.println("Bara indexplats 1-3 går att byta ut.");
                                    }
                                } catch (InputMismatchException notANumber) {
                                    System.out.println("Bara indexplats 1-3 går att byta ut.");
                                    in.next();
                                }
                            }
                        }
                        inputCharArray[indexToReplace - 1] = checkInputIfEmpty.charAt(0);
                        indexToReplace = indexToReplace + 1;
                    } else {
                        break;
                    }
                } catch (IOException e) {
                    break;
                }
                System.out.print("e) ");
                System.out.println(inputCharArray);
                increaseStringArray(inputCharArray);
            }
        }
    }
    //Här lägger vi till varje ny charArray som skapas i en stringArray för att kunna printa ut varje kombination utav de olika charArrayerna som skapas när användaren eller datorn byter värden i arrayen.
    //Därefter kallar vi på recursionOutput med argumenten stringArray som vi skapar i denna metoden & längden på arrayen -1.
    private static void increaseStringArray(char[] inputCharArray) {
        String charTextString = "";
        int indexToPlaceStr = stringArray.length;

        for (int i = 0; i < inputCharArray.length; i++){
            charTextString += inputCharArray[i];
        }
        stringArray[indexToPlaceStr-1] = charTextString;

        System.out.print("f) ");
        System.out.println(Arrays.toString(stringArray));

        System.out.print("g): ");
        recursionOutput(stringArray, stringArray.length -1);
        System.out.print("\n");

        stringArray = Arrays.copyOf(stringArray, stringArray.length + 1);
    }
    //Här printar vi ut vår nya stringArray med hjälp av rekursion.
    private static void recursionOutput(String[] stringArray, int index){
        if(index != -1) {
            recursionOutput(stringArray, index-1);
            System.out.print(stringArray[index] + " ");
        }
    }
}
