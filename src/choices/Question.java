package choices;

public class Question {

    String eventTitle;
    int eventID;
    String eventDesc;

    String imagePath;

    String answerOneText;
    int answerOneDest;

    String answerTwoText;
    int answerTwoDest;

    String answerThreeText;
    int answerThreeDest;

    String music;

    public Question(String eventTitle, int eventID, String eventDesc, String imagePath, String answerOneText, int answerOneDest, String answerTwoText, int answerTwoDest, String answerThreeText, int answerThreeDest, String music) {
        this.eventTitle = eventTitle;
        this.eventID = eventID;
        this.eventDesc = eventDesc;
        this.imagePath = imagePath;
        this.answerOneText = answerOneText;
        this.answerOneDest = answerOneDest;
        this.answerTwoText = answerTwoText;
        this.answerTwoDest = answerTwoDest;
        this.answerThreeText = answerThreeText;
        this.answerThreeDest = answerThreeDest;
        this.music = music;
    }


}
