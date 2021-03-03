package choices;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    Pane pane;

    @FXML
    Text titleText;

    @FXML
    Text authorText;

    @FXML
    Text volumeText;

    @FXML
    Slider volumeSlider;

    @FXML
    Button startButton;

    @FXML
    Button exitButton;

    @FXML
    ImageView imageView;

    @FXML
    CheckBox muteCheck;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        titleText.setText("Choices");
        authorText.setText("by NotyNote");
        imageView.setImage(new Image("/resources/knight_idle.gif"));

        Main.songPlayer.stop();

        try {
            Main.mainSong = new Media(getClass().getResource("/resources/MainMenu.wav").toURI().toString());
            Main.songPlayer = new MediaPlayer(Main.mainSong);
            Main.songPlayer.setOnEndOfMedia(() -> Main.songPlayer.seek(Duration.ZERO));
            Main.songPlayer.setVolume(Main.volume);
            if (Main.isMusicPlaying) {
                Main.songPlayer.play();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        //volume slider
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

        muteCheck.setSelected(!Main.isMusicPlaying);

    }

    public void TitleMouseEnter() {
        pane.setStyle("-fx-background-color: red");
        Main.player.play();
    }

    public void TitleMouseLeave() {
        pane.setStyle("-fx-background-color: white");
        Main.player.pause();
    }

    public void RainbowDash() {
        String[] colors = {"green","blue","yellow","red","pink"};

        int rand = new Random().nextInt(colors.length);
        String color = colors[rand];

        pane.setStyle("-fx-background-color: " + color);

    }

    public void exitProgram() {
        System.exit(1);
    }

    @FXML
    private void StartGame() throws IOException {
        AnchorPane gamePane = FXMLLoader.load(getClass().getResource("GamePlay.fxml"));
        pane.getChildren().setAll(gamePane);
    }

    public void MusicToggle() {
        if (muteCheck.isSelected()) {
            Main.songPlayer.pause();
            Main.isMusicPlaying = false;
        } else {
            Main.songPlayer.play();
            Main.isMusicPlaying = true;
        }
    }
}
