package com.example.labgui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    AnchorPane mainPane;
    @FXML
    TextField nameField;
    @FXML
    Pane recPane;
    java.util.LinkedList<Label> rectsList;
    java.util.LinkedList<Line> linesList;
    int x = 10;
    int y = 100;
    boolean tikla;

    public Circle CreatRect() {
        Circle circle = new Circle(25);
        circle.setFill(Color.rgb(219, 75, 67));
        return circle;
    }

    public void tiklanma() {
        tikla = false;
    }

    public Label CreatText(String value) {
        tikla = true;
        Circle circle = CreatRect();
        Label rect;
        rect = new Label(String.valueOf(value), circle);
        rect.setTextFill(Color.BLACK);
        rect.setFont(Font.font("DejaVu Sans Mono", FontWeight.BOLD, 14));
        rect.setLayoutX(x);
        rect.setLayoutY(y);
        rect.setContentDisplay(ContentDisplay.CENTER);
        recPane.getChildren().add(rect);
        return rect;
    }

    public Line CreatLine(Label txt) {
        Line line = new Line(txt.getLayoutX() + 52, (txt.getLayoutY() + 25), txt.getLayoutX() + 100, txt.getLayoutY() + 25);
        line.setStroke(Color.rgb(123,118,122));
        line.setStrokeWidth(3);
        recPane.getChildren().add(line);
        return line;
    }

    public void ekle() {
        String value = nameField.getText();
        if (x + 90 <= recPane.getWidth()) {
            Label rec = CreatText(value);
            Line line = CreatLine(rec);
            linesList.add(line);
            rectsList.add(rec);
            x += 100;
        } else if (y + 100 <= recPane.getHeight()) {
            y += 80;
            x = 10;
        }
    }

    public void sil() {
        if (!rectsList.isEmpty()) {
            x-=100;
            recPane.getChildren().remove(rectsList.removeLast());
            recPane.getChildren().remove(linesList.removeLast());
        }
    }

    public void printList1() {
        System.out.println(rectsList.stream().toList());
    }
    public void bastanSil(){
        if (!rectsList.isEmpty()) {
            recPane.getChildren().remove(rectsList.removeFirst());
            recPane.getChildren().remove(linesList.removeFirst());
            for (int i = 0; i < rectsList.size(); i++) {
                rectsList.get(i).setLayoutX(rectsList.get(i).getLayoutX()-100);
                linesList.get(i).setLayoutX(linesList.get(i).getLayoutX()-100);
            }
        }
    }
    public void ortadanSil(){
        if (!rectsList.isEmpty()) {
            Iterator<Label> rectsIterator = rectsList.iterator();
            Iterator<Line> linesIterator = linesList.iterator();
            boolean found = false;
            Label label;
            Line line;
            while (rectsIterator.hasNext() && linesIterator.hasNext()) {
                 label = rectsIterator.next();
                 line = linesIterator.next();

                if (nameField.getText().equals(label.getText())) {
                    rectsIterator.remove();
                    linesIterator.remove();
                    recPane.getChildren().remove(label);
                    recPane.getChildren().remove(line);
                    found = true;
                    x-=100;
                }
                if (found) {
                    label.setLayoutX(label.getLayoutX() - 100);
                    line.setLayoutX(line.getLayoutX() - 100);
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rectsList = new<Label> LinkedList();
        linesList = new<Line> LinkedList();
    }
}
