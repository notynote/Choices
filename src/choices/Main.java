package choices;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public static MediaPlayer player, songPlayer;
    public static boolean isMusicPlaying;
    public static Media mainSong;
    public static double volume;
    public static String playingSong;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Media nyancat = new Media(getClass().getResource("/resources/NyanCat.mp3").toURI().toString());
        player = new MediaPlayer(nyancat);
        player.setOnEndOfMedia(() -> player.seek(Duration.ZERO));

        mainSong = new Media(getClass().getResource("/resources/MainMenu.wav").toURI().toString());
        playingSong = "/resources/MainMenu.wav";
        songPlayer = new MediaPlayer(mainSong);
        songPlayer.setOnEndOfMedia(() -> songPlayer.seek(Duration.ZERO));

        //Start playing bg music
        volume = 0.5;
        Main.songPlayer.setVolume(volume);
        Main.songPlayer.play();
        isMusicPlaying = true;

        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        primaryStage.setTitle("Choices");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
