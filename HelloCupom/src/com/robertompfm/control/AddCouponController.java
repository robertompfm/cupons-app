package com.robertompfm.control;

import com.robertompfm.dao.CouponManager;
import com.robertompfm.model.Coupon;
import com.robertompfm.model.CouponStatus;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddCouponController implements Initializable {

    private CouponManager couponManager;

    @FXML
    private TextField codeTextField;

    @FXML
    private TextField valueTextField;

    @FXML
    public DatePicker expirationDatePicker;

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
        couponManager = CouponManager.getInstance();
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
        // getting values from fields
        String code = codeTextField.getText();
        String valueStr = valueTextField.getText();
        LocalDate expirationDate = expirationDatePicker.getValue();
        boolean wasUsed = utilizationCheckBox.isSelected();
        LocalDate utilizationDate = utilizationDatePicker.getValue();
        String description = noteTextArea.getText();

        // validate input
        if (!validateCode(code)) return;
        if (!validateValue(valueStr)) return;
        if (!validateExpirationDate(expirationDate)) return;
        if (wasUsed && !validateUtilizationDate(utilizationDate, expirationDate)) return;
        if (!validateDescription(description)) return;

        // new coupon
        double value = Double.parseDouble(valueStr);
        LocalDate creationDate = LocalDate.now();
        CouponStatus status;
        if (wasUsed) {
            status = CouponStatus.USED;
        } else if (expirationDate.compareTo(LocalDate.now()) < 0) {
            status = CouponStatus.EXPIRED;
        } else {
            status = CouponStatus.ACTIVE;
        }
        Coupon newCoupon = new Coupon(code, value, creationDate, expirationDate, utilizationDate, status, description);

        // add new coupon
        couponManager.addCoupon(newCoupon);
        feedbackLabel.setText("");
        MainController.getNewStage().hide();
    }

    @FXML
    public void cancel() {

        MainController.getNewStage().hide();
    }

    // INPUT VALIDATION METHODS
    private boolean validateCode(String code) {
        if (code.equals("")) {
            feedbackLabel.setText("O campo código precisa ser preenchido");
            return false;
        }
        if (!code.matches("[a-zA-Z]{3}[0-9]{4}")) {
            feedbackLabel.setText("O código não está no formato correto");
            return false;
        }
        if (!checkIfCodeIsAvailable(code)) {
            feedbackLabel.setText("Já existe cupom cadastrado com esse código");
            return false;
        }
        return true;
    }

    private boolean checkIfCodeIsAvailable(String code) {
        return couponManager.searchCoupon(code) == null;
    }

    private boolean validateValue(String valueStr) {
        if (valueStr.equals("")) {
            feedbackLabel.setText("O campo valor precisa ser preenchido");
            return false;
        }
        double value = -1;
        try {
            value = Double.parseDouble(valueStr);
        } catch (Exception e) {
            feedbackLabel.setText("O campo valor não foi preenchido corretamente");
            return false;
        }
        if (value <= 0) {
            feedbackLabel.setText("O valor do cupom deve ser maior que zero");
            return false;
        }
        return true;
    }

    private boolean validateExpirationDate(LocalDate expirationDate) {
        if (expirationDate == null) {
            feedbackLabel.setText("Você precisa selecionar uma data de expiração");
            return false;
        }
        return true;
    }

    private boolean validateUtilizationDate(LocalDate utilizationDate, LocalDate expirationDate) {
        if (utilizationDate == null) {
            feedbackLabel.setText("Você nao preencheu a data de utilização");
            return false;
        }
        if (utilizationDate.compareTo(expirationDate) > 0) {
            feedbackLabel.setText("Data de utilização não pode ser posterior a validade");
            return false;
        }
        return true;
    }

    private boolean validateDescription(String description) {
        if (description.equals("")) {
            feedbackLabel.setText("O campo descrição precisa ser preenchido");
            return false;
        }
        return true;
    }
}
