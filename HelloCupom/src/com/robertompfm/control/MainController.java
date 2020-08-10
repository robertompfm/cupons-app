package com.robertompfm.control;

import com.robertompfm.Main;
import com.robertompfm.dao.CouponData;
import com.robertompfm.dao.CouponManager;
import com.robertompfm.model.Coupon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        couponManager = CouponManager.getInstance();

        loadTableView();
    }

    public void loadTableView() {
        couponManager.updateCoupons();
        table.setItems(couponManager.getCoupons());
    }

    @FXML
    public void openAddCuponWindow() throws IOException {
        openNewWindow("/com/robertompfm/view/coupon_new.fxml");
    }

    @FXML
    public void openEditCouponWindow() throws IOException {
        couponManager.setCurrentCoupon((Coupon) table.getSelectionModel().getSelectedItem());
        if (couponManager.getCurrentCoupon() != null) {
            openNewWindow("/com/robertompfm/view/coupon_edit.fxml");
        }
    }

    @FXML
    public void openCouponInfoWindow() throws IOException {
        couponManager.setCurrentCoupon((Coupon) table.getSelectionModel().getSelectedItem());
        if (couponManager.getCurrentCoupon() != null) {
            openNewWindow("/com/robertompfm/view/coupon_info.fxml");
        }
    }

    @FXML
    public void openFilterWindow() throws IOException {
        openNewWindow("/com/robertompfm/view/filter.fxml");
    }

    public void openNewWindow(String path) throws IOException {
        Stage primaryStage = Main.getStage();
        Parent root = FXMLLoader.load(getClass().getResource(path));
        newStage = new Stage();
        newStage.setTitle("Novo cupom");
        newStage.setScene(new Scene(root));
        newStage.initModality(Modality.WINDOW_MODAL);
        newStage.initOwner(primaryStage);
        newStage.show();
    }





}
