package editor;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;

import java.util.LinkedList;   

public class EditorDraft3 extends Application {
    private Rectangle textBoundingBox;

    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;

    /** current position */
    double currentPosnX = 0;
    double currentPosnY = 0;

    double textHeight;
    double textWidth;
    double singleTextHeight;


    /** An EventHandler to handle keys that get pressed. */
    private class KeyEventHandler implements EventHandler<KeyEvent> {
        int textCenterX;
        int textCenterY;

        private static final int STARTING_FONT_SIZE = 10;
        private static final int STARTING_TEXT_POSITION_X = 250;
        private static final int STARTING_TEXT_POSITION_Y = 250;

        /** The Text to display on the screen. */
        private Text displayText = new Text(STARTING_TEXT_POSITION_X, STARTING_TEXT_POSITION_Y, "");
        private int fontSize = STARTING_FONT_SIZE;

        /** building a linked list to store the text*/
        private LinkedList<String> texts = new LinkedList<String>();

        private String fontName = "Verdana";

        KeyEventHandler(final Group root, int windowWidth, int windowHeight) {
            System.out.println("handler");
            textCenterX = windowWidth / 2;
            textCenterY = windowHeight / 2;

            // Initialize some empty text and add it to root so that it will be displayed.
            displayText = new Text(textCenterX, textCenterY, "");
            // Always set the text origin to be VPos.TOP! Setting the origin to be VPos.TOP means
            // that when the text is assigned a y-position, that position corresponds to the
            // highest position across all letters (for example, the top of a letter like "I", as
            // opposed to the top of a letter like "e"), which makes calculating positions much
            // simpler!
            displayText.setTextOrigin(VPos.TOP);
            displayText.setFont(Font.font(fontName, fontSize));

            // All new Nodes need to be added to the root in order to be displayed.
             root.getChildren().add(displayText);
        }

        public void setTextsLayOut(LinkedList<String> texts) {
            if(texts.size() != 0 && texts.size()%80 == 0) {
                texts.add("\n");
            }
            String result = String.join("", texts);
            displayText.setText(result);
        }

        @Override
        public void handle(KeyEvent keyEvent) {
            if (keyEvent.getEventType() == KeyEvent.KEY_TYPED) {
                // Use the KEY_TYPED event rather than KEY_PRESSED for letter keys, because with
                // the KEY_TYPED event, javafx handles the "Shift" key and associated
                // capitalization.
                String characterTyped = keyEvent.getCharacter();
                if (characterTyped.length() > 0) {
                    /** add character to the current position */
                    if(currentPosnX == 0) {
                        texts.add(characterTyped.toLowerCase());
                    } else {
                        texts.add(characterTyped.toLowerCase());
                    }
                    Text previous = new Text(currentPosnX, currentPosnY, characterTyped.toLowerCase());
                    singleTextHeight = previous.getLayoutBounds().getHeight();
                    currentPosnX += previous.getLayoutBounds().getWidth();
                    setTextsLayOut(texts);
                    keyEvent.consume();
                }
                centerText();
            } else if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
                // Arrow keys should be processed using the KEY_PRESSED event, because KEY_PRESSED
                // events have a code that we can check (KEY_TYPED events don't have an associated
                // KeyCode).
                KeyCode code = keyEvent.getCode();
                if (code == KeyCode.UP) {
                    fontSize += 5;
                    displayText.setFont(Font.font(fontName, fontSize));
                    centerText();
                } else if (code == KeyCode.DOWN) {
                    fontSize = Math.max(0, fontSize - 5);
                    displayText.setFont(Font.font(fontName, fontSize));
                    centerText();
                } else if(code == KeyCode.BACK_SPACE) {  
                    texts.removeLast();
                    setTextsLayOut(texts);
                    keyEvent.consume();
                }

            }
            textBoundingBox.setX(currentPosnX);
            textBoundingBox.setY(currentPosnY);
        }

        private void centerText() {
            // Figure out the size of the current text.
            // double textHeight = displayText.getLayoutBounds().getHeight();
            // double textWidth = displayText.getLayoutBounds().getWidth();

            // // Calculate the position so that the text will be centered on the screen.
            // double textTop = textCenterY - textHeight / 2;
            // double textLeft = textCenterX - textWidth / 2;
            textHeight = displayText.getLayoutBounds().getHeight();

            // Re-position the text.
            displayText.setX(5);
            displayText.setY(5);

            // Make sure the text appears in front of any other objects you might add.
            displayText.toFront();
        }
    }

    /** An event handler that displays the current position of the mouse whenever it is clicked. */
    private class MouseClickEventHandler implements EventHandler<MouseEvent> {
        /** A Text object that will be used to print the current mouse position. */
        // Text positionText;

        MouseClickEventHandler(Group root) {
            // For now, since there's no mouse position yet, just create an empty Text object.
            // // positionText = new Text("");
            textBoundingBox = new Rectangle(0, 0);
            // We want the text to show up immediately above the position, so set the origin to be
            // VPos.BOTTOM (so the x-position we assign will be the position of the bottom of the
            // text).
            // positionText.setTextOrigin(VPos.BOTTOM);

            // Add the positionText to root, so that it will be displayed on the screen.
            root.getChildren().add(textBoundingBox);
            makeRectangleColorChange();
        }


        @Override
        public void handle(MouseEvent mouseEvent) {
            // Because we registered this EventHandler using setOnMouseClicked, it will only called
            // with mouse events of type MouseEvent.MOUSE_CLICKED.  A mouse clicked event is
            // generated anytime the mouse is pressed and released on the same JavaFX node.
            double mousePressedX = mouseEvent.getX();
            double mousePressedY = mouseEvent.getY();

            double cursorTop = mousePressedX - textHeight / 2;
            if(mousePressedX <= textWidth || mousePressedY <= textHeight) {
                 currentPosnX = mousePressedX;
                currentPosnY = mousePressedY;
            }

            // Re-size and re-position the bounding box.
            textBoundingBox.setHeight(singleTextHeight);
            textBoundingBox.setWidth(1);

            // For rectangles, the position is the upper left hand corner.
            textBoundingBox.setX(currentPosnX);
            textBoundingBox.setY(currentPosnY);
        }
    }

    /** An EventHandler to handle changing the color of the rectangle. */
    private class RectangleBlinkEventHandler implements EventHandler<ActionEvent> {
        private int currentColorIndex = 0;
        private Color[] boxColors =
                {Color.BLACK, Color.WHITE};

        RectangleBlinkEventHandler() {
            // Set the color to be the first color in the list.
            changeColor();
        }

        private void changeColor() {
            textBoundingBox.setFill(boxColors[currentColorIndex]);
            currentColorIndex = (currentColorIndex + 1) % boxColors.length;
        }

        @Override
        public void handle(ActionEvent event) {
            changeColor();
        }
    }

    /** Makes the text bounding box change color periodically. */
    public void makeRectangleColorChange() {
        // Create a Timeline that will call the "handle" function of RectangleBlinkEventHandler
        // every 1 second.
        final Timeline timeline = new Timeline();
        // The rectangle should continue blinking forever.
        timeline.setCycleCount(Timeline.INDEFINITE);
        RectangleBlinkEventHandler cursorChange = new RectangleBlinkEventHandler();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), cursorChange);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a Node that will be the parent of all things displayed on the screen.
        Group root = new Group();
        // The Scene represents the window: its height and width will be the height and width
        // of the window displayed.
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);

        // To get information about what keys the user is pressing, create an EventHandler.
        // EventHandler subclasses must override the "handle" function, which will be called
        // by javafx.
        EventHandler<KeyEvent> keyEventHandler =
                new KeyEventHandler(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        // Register the event handler to be called for all KEY_PRESSED and KEY_TYPED events.
        scene.setOnKeyTyped(keyEventHandler);
        scene.setOnKeyPressed(keyEventHandler);

        EventHandler<MouseEvent> mouseEventHandler =
                new MouseClickEventHandler(root);
        scene.setOnMouseClicked(mouseEventHandler);

        primaryStage.setTitle("Editor");

        primaryStage.setScene(scene);

        // Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        // System.out.println(primaryScreenBounds.getWidth());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}