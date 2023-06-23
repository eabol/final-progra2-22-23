package entregas.joseSalceda.ahorcado.com.progra.hangman;

import entregas.joseSalceda.ahorcado.com.progra.hangman.base.Word;
import entregas.joseSalceda.ahorcado.com.progra.hangman.exceptions.InvalidWordException;
import entregas.joseSalceda.ahorcado.com.progra.hangman.parsers.WordParser;
import entregas.joseSalceda.ahorcado.com.progra.util.ReadFile;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserInterface {
    List<Word> words;
    int currentWordIndex;
    boolean gameOver;

    HangmanLogic logic;

    public UserInterface() {
        this.words = new ArrayList<>();
        this.currentWordIndex = 0;
    }

    private int randomIndex() {
        Random random = new Random();
        return random.nextInt(this.words.size());
    }

    public int sizeListWord() {
        return words.size();
    }

    private void loadData(String filename) throws FileNotFoundException, InvalidWordException {

        ReadFile fileReader = new ReadFile();
        List<String> csv = fileReader.loadSource(filename);
        WordParser parser = new WordParser();
        if(csv == null) throw new FileNotFoundException();
        if(csv.isEmpty()) throw new InvalidWordException("Word not valid, Valores de la palabra faltantes");

        this.words = csv.stream()
                .map(j -> {
                    try {
                        return parser.parse(j);
                    } catch (InvalidWordException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());

    }

    public void start(String filename) throws InvalidWordException, FileNotFoundException {
        loadData(filename);
        Word word = this.words.get( (int) (Math.random() * (words.size()-1)) );
        this.logic = new HangmanLogic(word);
    }

    public void play() {
        Scanner reader = new Scanner(System.in);


        System.out.println("==========================================================");
        System.out.println("=                      AHORCADO                          =");
        System.out.println("==========================================================");
        System.out.println("");
        printMenu();

        while (!this.gameOver && !this.logic.isWon()) {


            System.out.println("\nPalabra a adivinar con " + this.logic.hiddenWord().length() + " letras:");
            System.out.println( this.logic.hiddenWord());

            System.out.println("Ingrese una letra: ");
            String letter = reader.nextLine();

            if (letter.equals("salir")) {
                System.out.println("Gracias por jugar!");
                break;
            }else if (letter.length()==1) {
                this.logic.guessLetter(letter.toUpperCase());
            } else if (letter.isEmpty()) {
                printMenu();
            }

            System.out.println(this.logic.getHangman());
            this.gameOver = this.logic.isGameOver();

        }
        if (this.logic.isWon()) {
            System.out.println("Ganaste!");
        } else {
            System.out.println("Perdiste!");
        }

    }


    public void printMenu() {
        System.out.println("* Instrucciones *");
        System.out.println("salir   -  Salir del juego");
        System.out.println("Para jugar, ingrese una letra y presione enter. ");
    }

}