package com.robertompfm.control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCouponController implements Initializable {

    @FXML
    private TextField codeTextField;

    @FXML
    private TextField valueTextField;

    @FXML
    public DatePicker expirationDate;

    @FXML
    private DatePicker utilizationDatePicker;

    @FXML
    private CheckBox utilizationCheckBox;

    @FXML
    private TextArea noteTextArea;

    @FXML
    private Label feedbackLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        utilizationDatePicker.setDisable(true);
        feedbackLabel.setText("");
    }

    @FXML
    public void toggleUtilizationDatePicker() {
        if (utilizationCheckBox.isSelected()) {
            utilizationDatePicker.setDisable(false);
            utilizationDatePicker.requestFocus();
        } else {
            utilizationDatePicker.setValue(null);
            utilizationDatePicker.setDisable(true);
        }
    }

    @FXML
    public void save() {

        MainController.getNewStage().hide();
    }

    @FXML
    public void cancel() {

        MainController.getNewStage().hide();
    }
}
