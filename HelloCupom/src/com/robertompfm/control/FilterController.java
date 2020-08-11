package com.robertompfm.control;

import com.robertompfm.dao.CouponManager;
import com.robertompfm.model.CouponStatus;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FilterController implements Initializable {

    private CouponManager couponManager;

    @FXML
    public CheckBox expirationDateCheckBox;

    @FXML
    public DatePicker initialDatePicker;

    @FXML
    public DatePicker finalDatePicker;

    @FXML
    public CheckBox statusCheckBox;

    @FXML
    public ComboBox<CouponStatus> statusComboBox;

    @FXML
    public Label feedbackLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        couponManager = CouponManager.getInstance();

        statusComboBox.getItems().addAll(
                CouponStatus.ACTIVE,
                CouponStatus.USED,
                CouponStatus.EXPIRED
        );

        toggleExpirationDateCheckBox();
        toggleStatusCheckBox();
        feedbackLabel.setText("");
    }

    @FXML
    public void toggleExpirationDateCheckBox() {
        initialDatePicker.setDisable(!expirationDateCheckBox.isSelected());
        finalDatePicker.setDisable(!expirationDateCheckBox.isSelected());
        initialDatePicker.setValue(null);
        finalDatePicker.setValue(null);
    }

    @FXML
    public void toggleStatusCheckBox() {
        statusComboBox.setDisable(!statusCheckBox.isSelected());
        statusComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    public void save() {
        // GET VALUES FROM FIELDS
        boolean isExpirationDateFilterSelected = expirationDateCheckBox.isSelected();
        boolean isStatusFilterSelected = statusCheckBox.isSelected();
        LocalDate initialDate = initialDatePicker.getValue();
        LocalDate finalDate = finalDatePicker.getValue();
        CouponStatus status = statusComboBox.getValue();

        // VALIDATE VALUES
        if (isExpirationDateFilterSelected && !validatePeriod(initialDate, finalDate)) return;
        if (isStatusFilterSelected && !validateStatus(status)) return;

        // SET FILTERS
        couponManager.setExpirationDateFilter(isExpirationDateFilterSelected, initialDate, finalDate);
        couponManager.setStatusFilter(isStatusFilterSelected, status);

        // UPDATE COUPONS
        couponManager.updateCoupons();

        MainController.getNewStage().hide();
    }

    @FXML
    public void cancel() {

        MainController.getNewStage().hide();
    }

    private boolean validatePeriod(LocalDate initialDate, LocalDate finalDate) {
        if (initialDate == null) {
            feedbackLabel.setText("Nenhuma data foi selecionada no campo data inicial");
            return false;
        }
        if (finalDate == null) {
            feedbackLabel.setText("Nenhuma data foi selecionada no campo data final");
            return false;
        }
        if (initialDate.compareTo(finalDate) > 0) {
            feedbackLabel.setText("A data inicial não pode ser posterior a data final");
            return false;
        }
        feedbackLabel.setText("");
        return true;
    }

    private boolean validateStatus(CouponStatus status) {
        if (status == null) {
            feedbackLabel.setText("Nenhuma opção foi selecionada no campo situação");
            return false;
        }
        feedbackLabel.setText("");
        return true;
    }
}
