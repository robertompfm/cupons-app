package com.robertompfm.control;

import com.robertompfm.dao.CouponManager;
import com.robertompfm.model.Coupon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewCouponController implements Initializable {

    @FXML
    public TextField codeTextField;

    @FXML
    public TextField valueTextField;

    @FXML
    public DatePicker creationDate;

    @FXML
    public DatePicker expirationDate;

    @FXML
    public DatePicker utilizationDatePicker;

    @FXML
    public TextField statusTextField;

    @FXML
    public TextArea noteTextArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Coupon coupon = CouponManager.getInstance().getCurrentCoupon();
        codeTextField.setText(coupon.getCode());
        valueTextField.setText(Double.toString(coupon.getValue()));
        expirationDate.setValue(coupon.getExpirationDate());
        creationDate.setValue(coupon.getRegistrationDate());
        utilizationDatePicker.setValue(coupon.getUsageDate());

//        if (coupon.getUsageDate() != null) {
//            utilizationDatePicker.setValue(coupon.getUsageDate());
//        }
        noteTextArea.setText(coupon.getDescription());
    }
}
