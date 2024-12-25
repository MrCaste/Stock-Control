package com.stockcontrol.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.stockcontrol.modelo.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class StockController implements Initializable {

    @FXML
    private TableColumn<Product, Integer> tablaIdentificador;

    @FXML
    private TableView<Product> tableView;

    @FXML
    private TableColumn<Product, String> tablaProducto;

    @FXML
    private TableColumn<Product, Integer> tablaStock;

    @FXML
    private TextField textFieldIdentificador;

    @FXML
    private TextField textFieldProducto;

    @FXML
    private TextField textFieldStock;

    @FXML
    void addStock(ActionEvent event) {
        String textIdentificator = textFieldIdentificador.getText();
        String textProduct = textFieldProducto.getText();
        String textStock = textFieldStock.getText();
        if(textStock.isEmpty() || textProduct.isEmpty() || textIdentificator.isEmpty()){
            ControllerTransitions.showErrorAlert("Error", "Todos los campos son requeridos");
        } else {
            Product product = new Product(Integer.parseInt(textFieldIdentificador.getText()), textFieldProducto.getText(), Integer.parseInt(textFieldStock.getText()));
            tableView.getItems().add(product);
        }
        textFieldIdentificador.clear();
        textFieldProducto.clear();
        textFieldStock.clear();
    }

    @FXML
    void copyOnFields(MouseEvent event) {
        Product product = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
        textFieldProducto.setText(product.getName());
        textFieldStock.setText(String.valueOf(product.getStock()));
    }

    @FXML
    void modifyStock(ActionEvent event) {
        Product product = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
        int index = tableView.getSelectionModel().getSelectedIndex();
        Product productModify = new Product(product.getIdentificator(), textFieldProducto.getText(), Integer.parseInt(textFieldStock.getText()));
        tableView.getItems().set(index, productModify);
        textFieldProducto.clear();
        textFieldStock.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /******Inicializamos la tabla******/
        tablaIdentificador.setCellValueFactory(new PropertyValueFactory<Product, Integer>("identificator"));
        tablaProducto.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        tablaStock.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
    }

}
