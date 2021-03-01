package choices;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

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
    Button startButton;

    @FXML
    Button exitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleText.setText("Choices");
        authorText.setText("by NotyNote");
    }

    public void TitleMouseEnter() {
        pane.setStyle("-fx-background-color: red");
    }

    public void TitleMouseLeave() {
        pane.setStyle("-fx-background-color: white");
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
}
