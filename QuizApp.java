import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp {
    static int currentQuestionIndex = 0;
    static int userScore = 0;
    static Question[] questions;
    static Timer timer;

    public static void main(String[] args) {
        initializeQuestions();
        startQuiz();
    }

    static void initializeQuestions() {
        // Initialize your array of questions here
        // Each question should have the question text, options, and correct answer
        questions = new Question[3];
        questions[0] = new Question("What is the capital of France?", new String[]{"London", "Berlin", "Paris", "Madrid"}, 2);
        questions[1] = new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Venus"}, 1);
        questions[2] = new Question("What is the largest mammal?", new String[]{"Blue Whale", "Elephant", "Giraffe", "Hippopotamus"}, 0);
    }

    static void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Quiz!");
        System.out.println("--------------------");

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! Moving to the next question.");
                displayNextQuestion();
            }
        }, 20000); // Timer set for 20 seconds per question

        displayNextQuestion();
    }

    static void displayNextQuestion() {
        if (currentQuestionIndex < questions.length) {
            Question currentQuestion = questions[currentQuestionIndex];
            System.out.println("\n" + currentQuestion.getQuestionText());
            
            for (int i = 0; i < currentQuestion.getOptions().length; i++) {
                System.out.println((i + 1) + ". " + currentQuestion.getOptions()[i]);
            }
            
            Scanner scanner = new Scanner(System.in);
            System.out.print("Select your answer (1-" + currentQuestion.getOptions().length + "): ");
            int userAnswer = scanner.nextInt() - 1;
            
            if (userAnswer == currentQuestion.getCorrectAnswer()) {
                userScore++;
            }
            
            currentQuestionIndex++;
            timer.cancel();
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up! Moving to the next question.");
                    displayNextQuestion();
                }
            }, 20000);
            
            displayNextQuestion();
        } else {
            endQuiz();
        }
    }

    static void endQuiz() {
        System.out.println("\nQuiz Finished!");
        System.out.println("Your score: " + userScore + "/" + questions.length);
        System.out.println("Thank you for playing!");
    }
}

class Question {
    private String questionText;
    private String[] options;
    private int correctAnswer;

    public Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
