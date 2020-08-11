package com.robertompfm.control;

import com.robertompfm.Main;
import com.robertompfm.dao.CouponData;
import com.robertompfm.dao.CouponManager;
import com.robertompfm.model.Coupon;
import com.robertompfm.model.CouponStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private CouponManager couponManager;
    private static Stage newStage;

    public static Stage getNewStage() {
        return newStage;
    }

    @FXML
    TableView<Coupon> table;

    @FXML
    Button addCouponBtn;

    @FXML
    Button editCouponBtn;

    @FXML
    Button viewCouponBtn;

    @FXML
    Button delCouponBtn;

    @FXML
    Label feedbackLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        couponManager = CouponManager.getInstance();
        cellFactorySettings();
        feedbackLabel.setText("");


        loadTableView();
    }

    public void loadTableView() {
        couponManager.updateCoupons();
        table.setItems(couponManager.getCoupons());
//        feedbackLabel.setText("");
    }

    @FXML
    public void openAddCuponWindow() throws IOException {
        feedbackLabel.setText("");
        openNewWindow("/com/robertompfm/view/coupon_new.fxml", "Novo Cupom");
    }

    @FXML
    public void openEditCouponWindow() throws IOException {
        couponManager.setCurrentCoupon((Coupon) table.getSelectionModel().getSelectedItem());
        if (couponManager.getCurrentCoupon() == null) {
            feedbackLabel.setText("Você precisa selecionar um cupom");
        } else if (couponManager.getCurrentCoupon().getStatus() == CouponStatus.EXPIRED) {
            feedbackLabel.setText("Cupons expirados não podem ser editados");
        } else {
            feedbackLabel.setText("");
            openNewWindow("/com/robertompfm/view/coupon_edit.fxml", "Editar Cupom");
        }
    }

    @FXML
    public void openCouponInfoWindow() throws IOException {
        couponManager.setCurrentCoupon((Coupon) table.getSelectionModel().getSelectedItem());
        if (couponManager.getCurrentCoupon() != null) {
            feedbackLabel.setText("");
            openNewWindow("/com/robertompfm/view/coupon_info.fxml", "Detalhes do Cupom");
        } else {
            feedbackLabel.setText("Você precisa selecionar um cupom");
        }
    }

    @FXML
    public void deleteCoupon() {
        Coupon couponToDelete = ((Coupon) table.getSelectionModel().getSelectedItem());
        if (couponToDelete != null) {
            couponManager.deleteCoupon(couponToDelete);
        } else {
            feedbackLabel.setText("Você precisa selecionar um cupom");
        }
    }

    @FXML
    public void openFilterWindow() throws IOException {
        feedbackLabel.setText("");
        openNewWindow("/com/robertompfm/view/filter.fxml", "Filtrar Visualização");
    }

    private void openNewWindow(String path, String title) throws IOException {
        Stage primaryStage = Main.getStage();
        Parent root = FXMLLoader.load(getClass().getResource(path));
        newStage = new Stage();
        newStage.setTitle(title);
        newStage.setScene(new Scene(root));
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(primaryStage);
        newStage.show();
    }


    private void cellFactorySettings() {

        table.setRowFactory(row -> new TableRow<Coupon>() {
            @Override
            protected void updateItem(Coupon coupon, boolean b) {
                super.updateItem(coupon, b);

                if (coupon == null || b) {
                    setStyle("");
                } else {
                    if (coupon.getStatus() == CouponStatus.EXPIRED) {
                        setStyle("-fx-background-color: lightcoral;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });
    }

}
