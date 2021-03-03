package choices;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
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

        Main.songPlayer.stop();
        try {
            Main.mainSong = new Media(getClass().getResource("/resources/GameScene.wav").toURI().toString());
            Main.songPlayer = new MediaPlayer(Main.mainSong);
            Main.songPlayer.setOnEndOfMedia(() -> Main.songPlayer.seek(Duration.ZERO));
            Main.songPlayer.setVolume(Main.volume);
            if (Main.isMusicPlaying) {
                System.out.println("here");
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
}
