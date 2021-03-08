package choices;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class GamePlayController implements Initializable {

    @FXML
    Pane pane;

    @FXML
    Text volumeText;

    @FXML
    Slider volumeSlider;

    @FXML
    CheckBox muteCheck;

    @FXML
    Button choice1;

    @FXML
    Button choice2;

    @FXML
    Button choice3;

    @FXML
    ImageView image;

    @FXML
    Text questionTitle;

    @FXML
    TextArea questionDesc;

    Question[] questions = {
            new Question("Welcome to the Game", 0, "Welcome you guys to the game bla bla bla bla", "/resources/480x270.png",
                    "First Question", 1, "Second Question", 2, "Third Question", 3,
                    "/resources/GameScene.wav"),

            new Question("First Question", 1, "First Question", "/resources/480x270.png",
                    "Second Question", 2, "Third Question", 3, "Four Question", 4,
                    "/resources/GameScene.wav"),

            new Question("Second Question", 2, "Second Question", "/resources/480x270.png",
                    "Third Question", 3, "Four Question", 4, "Final Question", 5,
                    "/resources/GameScene.wav"),

            new Question("Third Question", 3, "Third Question", "/resources/480x270.png",
                    "Four Question", 4, "Final Question", 5, "Final Question", 5,
                    "/resources/GameScene.wav"),

            new Question("Four Question", 4, "Four Question", "/resources/480x270.png",
                    "Final Question", 5, "Final Question", 5, "Final Question", 5,
                    "/resources/FourthQuestion.wav"),

            new Question("Final Question", 5, "Final Question", "/resources/480x270.png",
                    "The End", 0, "The End", 0, "The End", 0,
                    "/resources/GameScene.wav")
    };

    Question currentQuestion;

    public void MusicToggle() {
        if (muteCheck.isSelected()) {
            Main.songPlayer.pause();
            Main.isMusicPlaying = false;
        } else {
            Main.songPlayer.play();
            Main.isMusicPlaying = true;
        }
    }

    @FXML
    private void ReturnToMenu() throws IOException {
        AnchorPane menuPane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        pane.getChildren().setAll(menuPane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        currentQuestion = questions[0];

        try {
            SetDisplayQuestion(currentQuestion);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        Main.songPlayer.stop();
        try {
            Main.mainSong = new Media(getClass().getResource("/resources/GameScene.wav").toURI().toString());
            Main.playingSong = "/resources/GameScene.wav";
            Main.songPlayer = new MediaPlayer(Main.mainSong);
            Main.songPlayer.setOnEndOfMedia(() -> Main.songPlayer.seek(Duration.ZERO));
            Main.songPlayer.setVolume(Main.volume);
            if (Main.isMusicPlaying) {
                Main.songPlayer.play();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        muteCheck.setSelected(!Main.isMusicPlaying);

        volumeSlider.setValue(Main.songPlayer.getVolume());
        volumeText.setText(String.format("%.1f", volumeSlider.getValue()));
        volumeSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                volumeText.setText(String.format("%.1f", volumeSlider.getValue()));
                Main.volume = volumeSlider.getValue();
                Main.songPlayer.setVolume(volumeSlider.getValue());
            }
        });
    }

    public void aboutPage() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Pane about = loader.load(getClass().getResource("About.fxml").openStream());

        Scene scene = new Scene(about);
        Stage nStage = new Stage();
        nStage.setTitle("About Choices");
        nStage.setScene(scene);
        nStage.setAlwaysOnTop(true);

        pane.setDisable(true);

        nStage.showAndWait();

        pane.setDisable(false);

    }

    public void ChangeQuestion(Button button) {
        int nextQuestionId = Integer.parseInt(button.getId());
        currentQuestion = questions[nextQuestionId];

        try {
            SetDisplayQuestion(currentQuestion);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    public void SetDisplayQuestion(Question question) throws URISyntaxException {

        if(!question.music.equalsIgnoreCase(Main.playingSong)) {
            Main.songPlayer.stop();
            Main.mainSong = new Media(getClass().getResource(question.music).toURI().toString());
            Main.playingSong = question.music;
            Main.songPlayer = new MediaPlayer(Main.mainSong);
            Main.songPlayer.setOnEndOfMedia(() -> Main.songPlayer.seek(Duration.ZERO));
            Main.songPlayer.setVolume(Main.volume);
            if (Main.isMusicPlaying) {
                Main.songPlayer.play();
            }
        }

        questionTitle.setText(question.eventTitle);
        questionDesc.setText(question.eventDesc);
        image.setImage(new Image(question.imagePath));

        choice1.setText(question.answerOneText);
        choice1.setId(String.valueOf(question.answerOneDest));
        choice1.setOnAction(actionEvent -> ChangeQuestion(choice1));

        choice2.setText(question.answerTwoText);
        choice2.setId(String.valueOf(question.answerTwoDest));
        choice2.setOnAction(actionEvent -> ChangeQuestion(choice2));

        choice3.setText(question.answerThreeText);
        choice3.setId(String.valueOf(question.answerThreeDest));
        choice3.setOnAction(actionEvent -> ChangeQuestion(choice3));
    }
}
